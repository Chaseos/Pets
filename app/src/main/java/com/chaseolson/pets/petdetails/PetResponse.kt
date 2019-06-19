package com.chaseolson.pets.petdetails

import com.tickaroo.tikxml.annotation.Path
import com.tickaroo.tikxml.annotation.Xml

//@Xml(name = "petfinder")
data class PetResponse(
//        @Path("pet")
        val pet: String
)