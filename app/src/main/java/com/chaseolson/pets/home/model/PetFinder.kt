package com.chaseolson.pets.home.model

import com.tickaroo.tikxml.annotation.*


@Xml(name = "petfinder")
data class PetFinder(
    @Path("pets")
    @Element
    val pet: List<Pet>? = null
) {

    @Xml
    data class Pet(
        @PropertyElement
        val name: String? = null,
        @PropertyElement
        val age: String? = null,
        @PropertyElement
        val sex: String? = null,
        @Path("breeds")
        @PropertyElement
        val breed: String? = null,
        @Path("media/photos")
        @Element
        val photos: List<Photo>? = null
    ) {

        @Xml
        data class Photo(
            @Attribute
            val id: String? = null,
            @Attribute
            val size: String? = null,
            @TextContent
            val photo: String? = null
        )
    }
}