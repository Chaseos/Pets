package com.chaseolson.pets

import com.chaseolson.pets.utils.animalToBackupImage
import com.chaseolson.pets.utils.filterName
import org.junit.Assert.assertEquals
import org.junit.Test

class PetExtensionsTest {

    @Test
    fun `String filterName with numbers`() {
        val numName = "3Max3"
        assertEquals("Max", numName.filterName())
    }

    fun Array<Int>.returnFilteredNums(other: Array<Int>) = filterNot { other.contains(it) }.sorted().filteredListString()
    fun Array<Int>.returnMappedNums(other: Array<Int>) = mapNotNull { if (!other.contains(it)) it else null }.sorted().filteredListString()
    fun Array<Int>.returnLongWayNums(other: Array<Int>): String {
        val listOfANotB = mutableListOf<Int>()
        forEach { if (!other.contains(it)) listOfANotB.add(it) }
        return listOfANotB.sorted().filteredListString()
    }
    fun List<Int>.filteredListString() = toString().replace("[]\\[,]".toRegex(), "")

    fun reconcileHelper(a : Array<Int>, b: Array<Int>) : String {
        val aNotInB = "Numbers in array 1 that aren\'t in array 2: ${a.returnLongWayNums(b)}"
        val bNotInA = "Numbers in array 2 that aren\'t in array 1: ${b.returnLongWayNums(a)}"
        return "$aNotInB\n$bNotInA"
    }

    @Test
    fun `returnFilteredNums should return sorted items in first list but not second`() {
        val array1 = arrayOf(5, 3, 4, 6, 2)
        val array2 = arrayOf(1, 5, 4, 9, 11)

        assertEquals(array1.returnFilteredNums(array2), "2 3 6")
        assertEquals(array2.returnFilteredNums(array1), "1 9 11")
    }

    @Test
    fun `returnMappedNums should return sorted items in first list but not second`() {
        val array1 = arrayOf(5, 3, 4, 6, 2)
        val array2 = arrayOf(1, 5, 4, 9, 11)

        assertEquals(array1.returnMappedNums(array2), "2 3 6")
        assertEquals(array2.returnMappedNums(array1), "1 9 11")
    }

    @Test
    fun `returnLongWayNums should return sorted items in first list but not second`() {
        val array1 = arrayOf(5, 3, 4, 6, 2)
        val array2 = arrayOf(1, 5, 4, 9, 11)

        assertEquals(array1.returnLongWayNums(array2), "2 3 6")
        assertEquals(array2.returnLongWayNums(array1), "1 9 11")
    }

    @Test
    fun `filteredListString should remove open bracket, close bracket and commas from string`() {
        assertEquals(listOf(1, 2, 3).filteredListString(), "1 2 3")
    }

    @Test
    fun `String filterName with $ sign`() {
        val dollName = "Max $$$$"
        assertEquals("Max", dollName.filterName())
    }

    @Test
    fun `String filterName with spaces`() {
        val spaceName = "   Max   "
        assertEquals("Max", spaceName.filterName())
    }

    @Test
    fun `String filterName with other weird chars`() {
        val weirdName = "^_#+=':Max;/{}][<>,.!!!"
        assertEquals("Max", weirdName.filterName())
    }

    @Test
    fun `String filterName with allowed chars`() {
        val goodName = "MaX & (LoLa) * FrAnK - BoB"
        assertEquals("MaX & (LoLa) * FrAnK - BoB", goodName.filterName())
    }

    @Test
    fun `String filterName with ampersand`() {
        val goodName = "Max &amp Lola"
        assertEquals("Max & Lola", goodName.filterName())
    }

    @Test
    fun `String animalToBackupImage Cat`() {
        val petType = "Cat"
        assertEquals(R.drawable.cat_silhouette, petType.animalToBackupImage())
    }

    @Test
    fun `String animalToBackupImage Dog`() {
        val petType = "Dog"
        assertEquals(R.drawable.dog_silhouette, petType.animalToBackupImage())
    }

    @Test
    fun `String animalToBackupImage Other`() {
        val petType = "Other"
        assertEquals(R.drawable.dog_silhouette, petType.animalToBackupImage())
    }

//    @Test
//    fun `String charSizeToStringSize Small`() {
//        val petSize = "S"
//        assertEquals("Small", petSize.charSizeToStringSize())
//    }
//
//    @Test
//    fun `String charSizeToStringSize Medium`() {
//        val petSize = "M"
//        assertEquals("Medium", petSize.charSizeToStringSize())
//    }
//
//    @Test
//    fun `String charSizeToStringSize Large`() {
//        val petSize = "L"
//        assertEquals("Large", petSize.charSizeToStringSize())
//    }
//
//
//    @Test
//    fun `String charSizeToStringSize Extra Large`() {
//        val petSize = "XL"
//        assertEquals("Extra Large", petSize.charSizeToStringSize())
//    }
//
//    @Test
//    fun `String charSizeToStringSize Other`() {
//        val petSize = "O"
//        assertEquals("Size N/A", petSize.charSizeToStringSize())
//    }
//
//    @Test
//    fun `String charGenderToStringGender Female`() {
//        val petGender = "F"
//        assertEquals("Female", petGender.charGenderToStringGender())
//    }

//    @Test
//    fun `String charGenderToStringGender Male`() {
//        val petGender = "M"
//        assertEquals("Male", petGender.charGenderToStringGender())
//    }
//
//    @Test
//    fun `String charGenderToStringGender Other`() {
//        val petGender = "O"
//        assertEquals("Gender N/A", petGender.charGenderToStringGender())
//    }

//    @Test
//    fun `List PetFinderResponsePetBreed mapBreedsToList empty`() {
//        val emptyList = emptyList<OldPetFinderResponse.Pet.Breed>()
//        assertEquals(emptyList<String>(), emptyList.mapBreedsToList())
//    }
//
//    @Test
//    fun `List PetFinderResponsePetBreed mapBreedsToList 2 breeds`() {
//        val breedList = listOf(
//                OldPetFinderResponse.Pet.Breed("Chihuahua"),
//                OldPetFinderResponse.Pet.Breed("Terrier"))
//        assertEquals(listOf("Chihuahua", "Terrier"), breedList.mapBreedsToList())
//    }
//
//    @Test
//    fun `List PetFinderResponsePetPhoto filterImagesList empty`() {
//        val emptyList = emptyList<OldPhoto>()
//        assertEquals(emptyList<OldPhoto>(), emptyList.filterImagesList())
//    }
//
//    @Test
//    fun `List PetFinderResponsePetPhoto filterImagesList 2 images`() {
//        val photos = listOf(
//            OldPhoto("pn", "http://www.photoL.com/"),
//            OldPhoto("pnt", "http://www.photoNotL.com/")
//        )
//        assertEquals(listOf("http://www.photoL.com/"), photos.filterImagesList())
//    }
}