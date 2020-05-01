package com.chaseolson.pets.repo

import com.chaseolson.pets.home.models.PetFinderResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PetFinderEndpoints {
    /**
    https://www.petfinder.com/developers/v2/docs/

    General Errors
    ERR-401
    Access was denied due to invalid credentials. This could be an invalid API key/secret combination, missing access token, or expired access token.

    ERR-403
    Access denied due to insufficient access.

    ERR-404
    The requested resource was not found.

    ERR-500
    The request ran into an unexpected error. If the problem persists, please contact support.

    ERR-00001
    The request has missing parameters.

    ERR-00002
    Your request contains invalid parameters.
    This response will also include a "invalid-params" array that includes all invalid parameters allowing you to use this information to fix the parameters and try again.
    Each invalid parameter includes the location of the parameter using the "in" key (e.g. "query" for a query parameter), the parameter name ("path"), and a message on why it's invalid.
     **/

    @GET("animals")
    suspend fun getPetListingNew(
        @Query("type") type: String? = null, // Grabbed from getAnimalTypes call
        @Query("breed") breed: String? = null, // Grabbed from getAnimalBreeds call
        @Query("size") size: String? = null, // small, medium, large, xlarge (accepts multiple values)
        @Query("gender") gender: String? = null, // male, female, unknown (accepts multiple values)
        @Query("age") age: String? = null, // baby, young, adult, senior (accepts multiple values)
        @Query("color") color: String? = null, // Grabbed from getAnimalTypes call
        @Query("coat") coat: String? = null, // short, medium, long, wire, hairless, curly (accepts multiple values)
        @Query("status") status: String? = null, // adoptable, adopted, found
        @Query("name") name: String? = null,
        @Query("organization") organization: String? = null, // [ID1] (accepts multiple values)
        @Query("good_with_children") goodWithChildren: Boolean? = null,
        @Query("good_with_dogs") goodWithDogs: Boolean? = null,
        @Query("good_with_cats") goodWithCats: Boolean? = null,
        @Query("location") location: String? = null, // (city, state), (latitude, longitude), or postal code
        @Query("distance") distance: Int? = null, // requires location to be set (default 100, max 500)
        @Query("sort") sort: String? = null, // recent, -recent, distance, -distance, random
        @Query("page") page: Int? = null,
        @Query("limit") limit: Int? = null // default 20 max 100
    ): Response<PetFinderResponse>
}