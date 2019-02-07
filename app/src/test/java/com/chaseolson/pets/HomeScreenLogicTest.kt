package com.chaseolson.pets

import com.chaseolson.pets.home.HomeScreenLogic
import com.chaseolson.pets.home.PetFeed
import com.chaseolson.pets.home.model.PetFinderResponse
import com.chaseolson.pets.home.model.PetFinderResponse.Pet
import com.chaseolson.pets.home.model.PetFinderResponse.Pet.Breed
import com.chaseolson.pets.home.model.PetListItemViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class HomeScreenLogicTest {

    private var listener: PetFeed.Listener? = null

    var presentHit = false
    var presentViewModelList: PetListItemViewModel? = null
    var presentErrorHit = false
    var presentErrorString: String? = null

    private val petResponse = Pet(
            name = "Snowball",
            animal = "Dog",
            age = "Adult",
            sex = "M",
            breeds = listOf(Breed("Maltese"), Breed("Hound")),
            photos = listOf(
                    Pet.Photo("pn", "http://www.photoL.com/"),
                    Pet.Photo("pnt", "http://www.photoNotL.com/")),
            contact = Pet.Contact("Addison"),
            size = "S"
    )
    private val petResponseTwo = Pet(
            name = "Scooby-Doo",
            animal = "Dog",
            age = "Adult",
            sex = "M",
            breeds = listOf(Breed("Great Dane")),
            contact = Pet.Contact("Dallas"),
            size = "L"
    )

    private val petExpected = PetListItemViewModel.Pet(
            name = "Snowball",
            age = "Adult",
            gender = "Male",
            size = "Small",
            breed = listOf("Maltese", "Hound"),
            images = listOf("http://www.photoL.com/"),
            city = "Addison",
            backupImage = R.drawable.dog_silhouette,
            offset = 20
    )
    private val petExpected2 = PetListItemViewModel.Pet(
            name = "Scooby-Doo",
            age = "Adult",
            gender = "Male",
            size = "Large",
            breed = listOf("Great Dane"),
            images = emptyList(),
            city = "Dallas",
            backupImage = R.drawable.dog_silhouette,
            offset = 20
    )

    @Before
    fun `Test Setup`() {
        presentHit = false
        presentViewModelList = null
        presentErrorHit = false
        presentErrorString = null

        listener = object : PetFeed.Listener {
            override fun presentError(error: String) {
                presentErrorHit = true
                presentErrorString = error
            }
        }
    }

    @Test
    fun `responseToViewModel null`() {
        val vm = HomeScreenLogic.responseToViewModel(null)
        assertEquals(PetListItemViewModel(emptyList()), vm)
    }

    @Test
    fun `responseToViewModel Default Response`() {
        val vm = HomeScreenLogic.responseToViewModel(PetFinderResponse())
        assertEquals(PetListItemViewModel(emptyList()), vm)
    }

    @Test
    fun `responseToViewModel Real One Pet Two Images`() {
        val vm = HomeScreenLogic.responseToViewModel(PetFinderResponse(pet = listOf(petResponse), lastOffset = 20))
        assertEquals(PetListItemViewModel(listOf(petExpected)), vm)
    }

    @Test
    fun `responseToViewModel Real Two Pet Two Images`() {
        val vm = HomeScreenLogic.responseToViewModel(
                PetFinderResponse(
                        pet = listOf(petResponse, petResponseTwo),
                        lastOffset = 20
                )
        )
        assertEquals(PetListItemViewModel(listOf(petExpected, petExpected2)), vm)
    }
}
