package com.assignment.data.common


object ErrorConstants {

    const val SOCKET_TIMEOUT_CODE = 1
    const val IO_EXCEPTION_CODE = 2
    const val HTTP_EXCEPTION_CODE = 3
    const val GENERIC_EXCEPTION_CODE = 1
    const val UNKNOWN_HOST_CODE = -1

    const val STATUS_400 = 400
    const val STATUS_401 = 401
    const val STATUS_404 = 404
    const val STATUS_500 = 500
    const val STATUS_503 = 503

    const val SOCKET_TIMEOUT_MESSAGE = "Request Timed Out, Please try later"
    const val IO_EXCEPTION_MESSAGE = "Error while parsing the request."
    const val HTTP_EXCEPTION_MESSAGE = "HTTP Exception"
    const val GENERIC_EXCEPTION_MESSAGE = "Something went wrong! Please try again!"
    const val UNKNOWN_HOST_EXCEPTION_MESSAGE =
        "Unknown host error! Please check your internet connection."

    const val API_ERROR = "Error occurred while executing API :"

    const val BAD_REQUEST_MESSAGE = "Error while processing the request. Please try again later."
    const val UNAUTHORIZED_MESSAGE = "Something went wrong!"
    const val RESOURCE_NOT_FOUND_MESSAGE = "File or resource not found!"
    const val INTERNAL_SERVER_ERROR_MESSAGE = "Internal server error occurred!"
    const val SERVICE_UNAVAILABLE_ERROR_MESSAGE =
        "Internal server error!"

    const val RESPONSE_CODE = "Response Code :"
    const val BODY = "Body :"
    const val ERROR_BODY = "Error Body :"


}

