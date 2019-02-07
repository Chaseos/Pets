package com.chaseolson.pets

import com.chaseolson.pets.core.*
import com.chaseolson.pets.home.model.PetFinderResponse
import org.junit.Assert.assertEquals
import org.junit.Test

class PetExtensionsTest {

    @Test
    fun `String filterName with numbers`() {
        val numName = "3Max3"
        assertEquals("Max", numName.filterName())
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

    @Test
    fun `String charSizeToStringSize Small`() {
        val petSize = "S"
        assertEquals("Small", petSize.charSizeToStringSize())
    }

    @Test
    fun `String charSizeToStringSize Medium`() {
        val petSize = "M"
        assertEquals("Medium", petSize.charSizeToStringSize())
    }

    @Test
    fun `String charSizeToStringSize Large`() {
        val petSize = "L"
        assertEquals("Large", petSize.charSizeToStringSize())
    }

    @Test
    fun `String charSizeToStringSize Other`() {
        val petSize = "O"
        assertEquals("Size N/A", petSize.charSizeToStringSize())
    }

    @Test
    fun `String charGenderToStrinGender Female`() {
        val petGender = "F"
        assertEquals("Female", petGender.charGenderToStringGender())
    }

    @Test
    fun `String charGenderToStrinGender Male`() {
        val petGender = "M"
        assertEquals("Male", petGender.charGenderToStringGender())
    }

    @Test
    fun `String charGenderToStrinGender Other`() {
        val petGender = "O"
        assertEquals("Gender N/A", petGender.charGenderToStringGender())
    }

    @Test
    fun `List PetFinderResponsePetBreed mapBreedsToList empty`() {
        val emptyList = emptyList<PetFinderResponse.Pet.Breed>()
        assertEquals(emptyList<String>(), emptyList.mapBreedsToList())
    }

    @Test
    fun `List PetFinderResponsePetBreed mapBreedsToList 2 breeds`() {
        val breedList = listOf(
                PetFinderResponse.Pet.Breed("Chihuahua"),
                PetFinderResponse.Pet.Breed("Terrier"))
        assertEquals(listOf("Chihuahua", "Terrier"), breedList.mapBreedsToList())
    }

    @Test
    fun `List PetFinderResponsePetPhoto filterImagesList empty`() {
        val emptyList = emptyList<PetFinderResponse.Pet.Photo>()
        assertEquals(emptyList<PetFinderResponse.Pet.Photo>(), emptyList.filterImagesList())
    }

    @Test
    fun `List PetFinderResponsePetPhoto filterImagesList 2 images`() {
        val photos = listOf(
                PetFinderResponse.Pet.Photo("pn", "http://www.photoL.com/"),
                PetFinderResponse.Pet.Photo("pnt", "http://www.photoNotL.com/"))
        assertEquals(listOf("http://www.photoL.com/"), photos.filterImagesList())
    }
}