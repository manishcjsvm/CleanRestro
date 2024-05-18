package com.assignment.domain.common

/**
 * To be used as a result of api request.
 */
sealed class APIResult<out T> {

    /**
     * To be used when there will be a success response.
     *
     * @param data data to be passed.
     *
     */
    data class Success<T>(val data: T) : APIResult<T>()

    /**
     * To be used where there will be error in response.
     *
     * @param errorCode error code to be passed.
     * @param errorMessage error message to be passed.
     */
    data class Error(val errorCode: Int, val errorMessage: String) : APIResult<Nothing>()

}