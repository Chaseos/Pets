package com.chaseolson.pets.oldstuff

import com.tickaroo.tikxml.annotation.Attribute
import com.tickaroo.tikxml.annotation.TextContent
import com.tickaroo.tikxml.annotation.Xml

@Xml
data class OldPhoto(
        @Attribute
        val size: String = "",
        @TextContent
        val photo: String = ""
)
