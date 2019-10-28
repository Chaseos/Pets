package com.chaseolson.pets.repo

import com.chaseolson.pets.BuildConfig
import com.chaseolson.pets.core.TokenResponseDto
import com.chaseolson.pets.home.model.NewPetFinderResponse
import com.chaseolson.pets.home.model.PetFinderResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MobileEndpointsNew {
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

    @GET("oauth2/token")
    suspend fun getToken(
        @Query("client_id") key: String = BuildConfig.petFinderKey2,
        @Query("client_secret") secret: String = BuildConfig.petFinderSecret2
    ): Response<TokenResponseDto>

    @GET("animals")
    suspend fun getPetListingNew(
        @Header("Authorization")
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
    ): Response<NewPetFinderResponse>
}

fun <T> Response<T>.toResult(): ApiResult<T>
{
    return try {
        if (this.isSuccessful) ApiResult.success(this.body())
        else ApiResult.error(this.message(), this.code(), null)
    } catch (e: Exception) {
        ApiResult.error(e.localizedMessage,null,null)
    }
}

enum class ResultStatus {
    SUCCESS,
    ERROR
}

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
</T> */
data class ApiResult<out T>(val status: ResultStatus, val data: T?, val message: String?, val errorCode:Int?) {
    companion object {
        fun <T> success(data: T?): ApiResult<T> {
            return ApiResult(ResultStatus.SUCCESS, data, null, null)
        }

        fun <T> error(msg: String, errorCode: Int?,  data: T?): ApiResult<T> {
            return ApiResult(ResultStatus.ERROR, data, msg, errorCode)
        }
    }
}