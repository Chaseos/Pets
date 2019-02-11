package com.chaseolson.pets

import com.chaseolson.pets.home.HomeScreenLogic
import com.chaseolson.pets.home.PetFeed
import com.chaseolson.pets.home.model.PetFinderResponse
import com.chaseolson.pets.home.model.PetFinderResponse.Pet
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
            sex = "M",
            photos = listOf(
                    Pet.Photo("pn", "http://www.photoL.com/"),
                    Pet.Photo("pnt", "http://www.photoNotL.com/")),
            contact = Pet.Contact("Addison")
    )
    private val petResponseTwo = Pet(
            name = "Scooby-Doo",
            animal = "Dog",
            sex = "M",
            contact = Pet.Contact("Dallas")
    )

    private val petExpected = PetListItemViewModel.Pet(
            name = "Snowball",
            city = "He's in Addison!",
            images = listOf("http://www.photoL.com/"),
            backupImage = R.drawable.dog_silhouette,
            offset = 20
    )
    private val petExpected2 = PetListItemViewModel.Pet(
            name = "Scooby-Doo",
            city = "He's in Dallas!",
            images = emptyList(),
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
