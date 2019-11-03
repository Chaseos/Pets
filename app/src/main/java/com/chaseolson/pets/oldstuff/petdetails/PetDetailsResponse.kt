package com.chaseolson.pets.oldstuff.petdetails

import com.chaseolson.pets.oldstuff.OldPhoto
import com.chaseolson.pets.oldstuff.OldPetFinderResponse
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
                @Path("media/oldPhotos")
                @Element
                val oldPhotos: List<OldPhoto>? = emptyList(),
                @Element
                val contact: OldPetFinderResponse.Pet.Contact
        ) {

                @Xml
                data class Breed(
                        @TextContent
                        val breed: String = ""
                )
        }
}