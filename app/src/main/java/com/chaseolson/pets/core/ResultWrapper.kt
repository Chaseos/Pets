package com.chaseolson.pets.core

import com.chaseolson.pets.core.Status.*
import retrofit2.Response

/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * TODO I don't know if I want to use this or not... Requires extra layer of abstraction from API calls
 * A generic class that holds a value with its loading status.
 * @param <T>
</T> */
data class ResultWrapper<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): ResultWrapper<T> = ResultWrapper(SUCCESS, data, null)
        fun <T> error(msg: String, data: T?): ResultWrapper<T> = ResultWrapper(ERROR, data, msg)
        fun <T> loading(data: T?): ResultWrapper<T> = ResultWrapper(LOADING, data, null)
    }
}

/**
 * Status of a resource that is provided to the UI.
 *
 *
 * These are usually created by the Repository classes where they return
 * `LiveData<Result<T>>` to pass back the latest data to the UI with its fetch status.
 */
enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}

//fun <T> Response<T>.toResult(): ResultWrapper<T> {
//    return try {
//        if (this.isSuccessful) ResultWrapper.success(this.body())
//        else ResultWrapper.error(this.message(), this.code(), null)
//    } catch (e: Exception) {
//        ResultWrapper.error(e.localizedMessage, null, null)
//    }
//}