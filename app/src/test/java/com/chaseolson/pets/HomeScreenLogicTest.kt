package com.chaseolson.pets

import com.chaseolson.pets.home.HomeScreenLogic
import com.chaseolson.pets.home.model.PetFinderResponse
import com.chaseolson.pets.home.model.PetListItemViewModel
import com.chaseolson.pets.home.retrofit.PetListingApi
import okhttp3.Request
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeScreenLogicTest {

    var homeScreenLogic: HomeScreenLogic? = null
    private var listener: HomeScreenLogic.Listener? = null

    var presentHit = false
    var presentViewModelList: PetListItemViewModel? = null
    var presentErrorHit = false
    var presentErrorString: String? = null

    private val petResponse = PetFinderResponse.Pet(
        "Snowball", "Adult", "M", "Maltese",
        listOf(PetFinderResponse.Pet.Photo("x", "http://www.photoL.com/"),
            PetFinderResponse.Pet.Photo("pnt", "http://www.photoNotL.com/")))
    private val petResponseTwo = PetFinderResponse.Pet(
        "Scooby-Doo", "Adult", "M", "Great Dane",
        listOf(PetFinderResponse.Pet.Photo("x", "http://www.photoL2.com/"),
            PetFinderResponse.Pet.Photo("pnt", "http://www.photoNotL2.com/")))

    private val petExpected = PetListItemViewModel.Pet("Snowball", "Adult",
        "M", "Maltese", null, null, listOf("http://www.photoL.com/"))
    private val petExpected2 = PetListItemViewModel.Pet("Scooby-Doo", "Adult",
        "M", "Great Dane", null, null, listOf("http://www.photoL2.com/"))

    @Before
    fun `Test Setup`() {
        presentHit = false
        presentViewModelList = null
        presentErrorHit = false
        presentErrorString = null

        listener = object : HomeScreenLogic.Listener {
            override fun present(vm: PetListItemViewModel?) {
                presentHit = true
                presentViewModelList = vm
            }

            override fun presentError(error: String) {
                presentErrorHit = true
                presentErrorString = error
            }
        }
    }

    @Test
    fun `setup Successful Response`() {
        val petFinderResponse = PetFinderResponse(listOf(petResponse, petResponseTwo))

        val api = object: PetListingApi {
            override fun getPetsList(
                animal: String?, breed: String?, size: String?, sex: Char?, location: String,
                age: String?, offset: String?, count: Int?, output: String?, format: String?
            ): Call<PetFinderResponse> {
                return object: Call<PetFinderResponse> {
                    override fun enqueue(callback: Callback<PetFinderResponse>) {
                        return callback.onResponse(this, Response.success(petFinderResponse))
                    }

                    override fun isExecuted(): Boolean { TODO("Not Used") }
                    override fun clone(): Call<PetFinderResponse> { TODO("Not Used") }
                    override fun isCanceled(): Boolean { TODO("Not Used") }
                    override fun cancel() { TODO("Not Used") }
                    override fun execute(): Response<PetFinderResponse> { TODO("Not Used") }
                    override fun request(): Request { TODO("Not Used") }
                }
            }
        }

        homeScreenLogic = HomeScreenLogic(listener!!, api)

        val petExpectedVM = PetListItemViewModel(listOf(petExpected, petExpected2))

        homeScreenLogic?.setup()
        assertTrue(presentHit)
        assertEquals(petExpectedVM, presentViewModelList)
    }

    @Test
    fun `setup Failure Response`() {
        val api = object: PetListingApi {
            override fun getPetsList(
                animal: String?, breed: String?, size: String?, sex: Char?, location: String,
                age: String?, offset: String?, count: Int?, output: String?, format: String?
            ): Call<PetFinderResponse> {
                return object: Call<PetFinderResponse> {
                    override fun enqueue(callback: Callback<PetFinderResponse>) {
                        return callback.onFailure(this, Throwable("Test Error"))
                    }

                    override fun isExecuted(): Boolean { TODO("Not Used") }
                    override fun clone(): Call<PetFinderResponse> { TODO("Not Used") }
                    override fun isCanceled(): Boolean { TODO("Not Used") }
                    override fun cancel() { TODO("Not Used") }
                    override fun execute(): Response<PetFinderResponse> { TODO("Not Used") }
                    override fun request(): Request { TODO("Not Used") }
                }
            }
        }

        homeScreenLogic = HomeScreenLogic(listener!!, api)

        homeScreenLogic?.setup()
        assertTrue(presentErrorHit)
        assertEquals(presentErrorString, "Test Error")
    }

    @Test
    fun `responseToViewModel null`() {
        val vm = HomeScreenLogic.responseToViewModel(null)
        assertNull(vm)
    }

    @Test
    fun `responseToViewModel Empty List`() {
        val vm = HomeScreenLogic.responseToViewModel(emptyList())
        assertEquals(PetListItemViewModel(emptyList()), vm)
    }

    @Test
    fun `responseToViewModel Empty Pet`() {
        val vm = HomeScreenLogic.responseToViewModel(listOf(PetFinderResponse.Pet()))

        assertEquals(PetListItemViewModel(listOf(PetListItemViewModel.Pet())), vm)
    }

    @Test
    fun `responseToViewModel Real One Pet Two Images`() {
        val vm = HomeScreenLogic.responseToViewModel(listOf(petResponse))
        assertEquals(PetListItemViewModel(listOf(petExpected)), vm)
    }

    @Test
    fun `responseToViewModel Real Two Pet Two Images`() {
        val vm = HomeScreenLogic.responseToViewModel(listOf(petResponse, petResponseTwo))
        assertEquals(PetListItemViewModel(listOf(petExpected, petExpected2)), vm)
    }
}
































