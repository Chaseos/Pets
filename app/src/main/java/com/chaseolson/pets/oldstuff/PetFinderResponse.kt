package com.chaseolson.pets.oldstuff

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
            val id: Int = 0,
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
        data class Contact(
                @PropertyElement
                val city: String = "an area near you"
        )
    }
}