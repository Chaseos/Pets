package com.chaseolson.pets.oldstuff

data class SearchModel(
        val animal: String? = null, // all lowercase ("dog")
        val breed: String? = null, // Title Case ("Golden Retriever")
        val size: String? = null, // "One Capital Letter ("L")
        val sex: String? = null, // One Capital Letter ("F")
        val age: String? = null, // Capitalization ("Young")
        val location: String? = null) // 5 number zip ("75001") or location ("Addison, TX")

