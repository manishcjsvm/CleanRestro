package com.assignment.common

/**
 * To be used as a result of api request.
 */
sealed interface APIResult<out T> {

    /**
     * To be used when there will be a success response.
     *
     * @param data data to be passed.
     *
     */
    data class Success<T>(val data: T) : APIResult<T>

    /**
     * To be used where there will be error in response.
     *
     * @param exception error to be passed.
     */
    data class Error(val exception: Exception) : APIResult<Nothing>
}