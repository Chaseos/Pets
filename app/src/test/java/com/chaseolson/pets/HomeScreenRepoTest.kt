package com.chaseolson.pets

import com.chaseolson.pets.oldstuff.OldHomeScreenRepo
import com.chaseolson.pets.oldstuff.PetFeed
import com.chaseolson.pets.oldstuff.OldPetFinderResponse
import com.chaseolson.pets.oldstuff.OldPetFinderResponse.Pet
import com.chaseolson.pets.oldstuff.OldPetListItemViewState
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class HomeScreenRepoTest {

    private var listener: PetFeed.Listener? = null

    var presentHit = false
    var presentViewModelList: OldPetListItemViewState? = null
    var presentErrorHit = false
    var presentErrorString: String? = null

    private val petResponse = Pet(
            name = "Snowball",
            animal = "Dog",
            sex = "M",
            oldPhotos = listOf(
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

    private val petExpected = OldPetListItemViewState.Pet(
            name = "Snowball",
            city = "He's in Addison!",
            images = listOf("http://www.photoL.com/"),
            backupImage = R.drawable.dog_silhouette,
            offset = 20
    )
    private val petExpected2 = OldPetListItemViewState.Pet(
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
        val vm = OldHomeScreenRepo.oldResponseToViewModel(null)
        assertEquals(OldPetListItemViewState(emptyList()), vm)
    }

    @Test
    fun `responseToViewModel Default Response`() {
        val vm = OldHomeScreenRepo.oldResponseToViewModel(OldPetFinderResponse())
        assertEquals(OldPetListItemViewState(emptyList()), vm)
    }

    @Test
    fun `responseToViewModel Real One Pet Two Images`() {
        val vm = OldHomeScreenRepo.oldResponseToViewModel(
            OldPetFinderResponse(
                pet = listOf(petResponse),
                lastOffset = 20
            )
        )
        assertEquals(OldPetListItemViewState(listOf(petExpected)), vm)
    }

    @Test
    fun `responseToViewModel Real Two Pet Two Images`() {
        val vm = OldHomeScreenRepo.oldResponseToViewModel(
            OldPetFinderResponse(
                pet = listOf(petResponse, petResponseTwo),
                lastOffset = 20
            )
        )
        assertEquals(
            OldPetListItemViewState(
                listOf(
                    petExpected,
                    petExpected2
                )
            ), vm)
    }
}
