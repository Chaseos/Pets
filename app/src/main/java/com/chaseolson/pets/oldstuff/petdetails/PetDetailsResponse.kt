package com.chaseolson.pets.oldstuff.petdetails

import com.chaseolson.pets.oldstuff.Photo
import com.chaseolson.pets.oldstuff.PetFinderResponse
import com.tickaroo.tikxml.annotation.*

@Xml(name = "petfinder")
data class PetDetailsResponse(
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
        }
}