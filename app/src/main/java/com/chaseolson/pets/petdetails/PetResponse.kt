package com.chaseolson.pets.petdetails

import com.chaseolson.pets.home.model.PetFinderResponse
import com.tickaroo.tikxml.annotation.*

@Xml(name = "petfinder")
data class PetResponse(
        @Element
        val pet: Pet
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
                @PropertyElement
                val age: String = "N/A",
                @Path("breeds")
                @Element
                val breeds: List<Breed>? = emptyList(),
                @Path("media/photos")
                @Element
                val photos: List<Photo>? = emptyList(),
                @Element
                val contact: PetFinderResponse.Pet.Contact
        ) {

                @Xml
                data class Breed(
                        @TextContent
                        val breed: String = ""
                )

                @Xml
                data class Photo(
                        @Attribute
                        val size: String = "",
                        @TextContent
                        val photo: String = ""
                )
        }
}