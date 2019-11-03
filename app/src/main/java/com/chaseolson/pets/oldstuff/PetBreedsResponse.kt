package com.chaseolson.pets.oldstuff

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Path
import com.tickaroo.tikxml.annotation.TextContent
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "petfinder")
data class PetBreedsResponse(
        @Path("breeds")
        @Element
        val breeds: List<Breed>? = emptyList()
) {

    @Xml
    data class Breed(
            @TextContent
            val breed: String = ""
    )
}