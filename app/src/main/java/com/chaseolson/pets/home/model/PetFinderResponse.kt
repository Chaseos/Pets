package com.chaseolson.pets.home.model

import com.tickaroo.tikxml.annotation.*


@Xml(name = "petfinder")
data class PetFinderResponse(
    @Path("pets")
    @Element
    val pet: List<Pet>? = null
) {

    @Xml
    data class Pet(
        @PropertyElement
        val name: String = "Unknown Name",
        @PropertyElement
        val animal: String = "Unknown Animal Type",
        @PropertyElement
        val age: String = "Unknown Age",
        @PropertyElement
        val sex: String = "Unknown Gender",
        @Path("breeds")
        @PropertyElement
        val breed: String = "Unknown Breed",
        @Path("media/photos")
        @Element
        val photos: List<Photo> = emptyList()
    ) {

        @Xml
        data class Photo(
            @Attribute
            val size: String = "",
            @TextContent
            val photo: String = ""
        )
    }
}