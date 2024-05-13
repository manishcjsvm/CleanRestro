package com.assignment.data


object ErrorConstants {
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
        "Unknown host error! Please check your internet connection."

    const val FETCH_CHARACTERS_LIST_ERROR = "Error while fetching characters list :"
    const val FETCH_CHARACTER_DETAILS_ERROR = "Error while fetching character details :"
}

