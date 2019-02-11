package com.chaseolson.pets.home.model

import com.tickaroo.tikxml.annotation.*


@Xml(name = "petfinder")
data class PetFinderResponse(
        @PropertyElement
        val lastOffset: Int? = 40,
        @Path("pets")
        @Element
        val pet: List<Pet>? = emptyList()
) {

    @Xml
    data class Pet(
            @PropertyElement
            val name: String = "N/A",
            @PropertyElement
            val animal: String = "N/A",
            @PropertyElement
            val sex: String = "N/A",
            @Path("media/photos")
            @Element
            val photos: List<Photo>? = emptyList(),
            @Element
            val contact: Contact
    ) {

        @Xml
        data class Photo(
                @Attribute
                val size: String = "",
                @TextContent
                val photo: String = ""
        )

        @Xml
        data class Contact(
                @PropertyElement
                val city: String = "an area near you"
        )
    }
}