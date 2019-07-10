package com.chaseolson.pets.core

import com.tickaroo.tikxml.annotation.Attribute
import com.tickaroo.tikxml.annotation.TextContent
import com.tickaroo.tikxml.annotation.Xml

@Xml
data class Photo(
        @Attribute
        val size: String = "",
        @TextContent
        val photo: String = ""
)
