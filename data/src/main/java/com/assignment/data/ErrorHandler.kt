package com.assignment.data

import com.assignment.domain.APIResult
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun Exception.toAPIResult(): APIResult.Error {

    return when (this) {

        is SocketTimeoutException -> {
            APIResult.Error(ErrorUtils.SOCKET_TIMEOUT_CODE, ErrorUtils.SOCKET_TIMEOUT_MESSAGE)
        }

        is HttpException -> {
            APIResult.Error(ErrorUtils.HTTP_EXCEPTION_CODE, ErrorUtils.HTTP_EXCEPTION_MESSAGE)
        }

        is UnknownHostException -> {
            APIResult.Error(
                ErrorUtils.UNKNOWN_HOST_CODE,
                ErrorUtils.UNKNOWN_HOST_EXCEPTION_MESSAGE
            )
        }

        is IOException -> {
            APIResult.Error(ErrorUtils.IO_EXCEPTION_CODE, ErrorUtils.IO_EXCEPTION_MESSAGE)
        }


        else -> {
            APIResult.Error(
                ErrorUtils.GENERIC_EXCEPTION_CODE,
                ErrorUtils.GENERIC_EXCEPTION_MESSAGE
            )
        }
    }
}

class ErrorUtils {
    companion object {
        const val SOCKET_TIMEOUT_CODE = 1
        const val IO_EXCEPTION_CODE = 2
        const val HTTP_EXCEPTION_CODE = 3
        const val GENERIC_EXCEPTION_CODE = 1
        const val UNKNOWN_HOST_CODE = -1

        const val SOCKET_TIMEOUT_MESSAGE = "Request Timed Out, Please try later"
        const val IO_EXCEPTION_MESSAGE = "Error while parsing the request."
        const val HTTP_EXCEPTION_MESSAGE = "HTTP Exception"
        const val GENERIC_EXCEPTION_MESSAGE = "Something went wrong! Please try again!"
        const val UNKNOWN_HOST_EXCEPTION_MESSAGE =
            "Unknown host!"
    }
}
