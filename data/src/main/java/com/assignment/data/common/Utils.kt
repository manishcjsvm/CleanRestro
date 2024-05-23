package com.assignment.data.common

import com.assignment.common.logger.Logger
import com.assignment.domain.common.APIResult
import retrofit2.Response
import javax.inject.Inject

private const val TAG = "Utils==>"

class Utils @Inject constructor(private val logger: Logger) {

    /**
     * To place api calls safely.
     *
     * @param call suspend function to execute.
     * @param transform to call extension function on [T], could be used to map object from data to domain model.
     *
     * @return [APIResult] which is the transformed result from T->R.
     */
    suspend fun <T, R> callSafely(
        call: suspend () -> Response<T>,
        transform: T.() -> R
    ): APIResult<R> =
        try {
            val response = call.invoke()
            with(response)
            {
                if (isSuccessful) {
                    body()?.let {
                        APIResult.Success(it.transform())
                    } ?: run {
                        logger.error("$TAG ${ErrorConstants.API_ERROR} ${toLogError()}")
                        toAPIResult()
                    }
                } else {
                    logger.error("$TAG ${ErrorConstants.API_ERROR} ${toLogError()}")
                    toAPIResult()
                }
            }
        } catch (exp: Exception) {
            logger.error("$TAG ${ErrorConstants.API_ERROR} ${exp.message}")
            exp.toAPIResult()
        }
}