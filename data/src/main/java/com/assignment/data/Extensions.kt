package com.assignment.data

import com.assignment.data.dto.CharacterDTO
import com.assignment.data.dto.CharactersListDTO
import com.assignment.domain.APIResult
import com.assignment.domain.entities.CharacterEntity
import com.assignment.domain.entities.CharacterListEntity
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Maps the exception to [APIResult].
 *
 * @return [APIResult.Error]
 */

fun Exception.toAPIResult(): APIResult.Error = when (this) {
    is SocketTimeoutException -> {
        APIResult.Error(
            ErrorConstants.SOCKET_TIMEOUT_CODE, ErrorConstants.SOCKET_TIMEOUT_MESSAGE
        )
    }

    is HttpException -> {
        APIResult.Error(
            ErrorConstants.HTTP_EXCEPTION_CODE, ErrorConstants.HTTP_EXCEPTION_MESSAGE
        )
    }

    is UnknownHostException -> {
        APIResult.Error(
            ErrorConstants.UNKNOWN_HOST_CODE, ErrorConstants.UNKNOWN_HOST_EXCEPTION_MESSAGE
        )
    }

    is IOException -> {
        APIResult.Error(ErrorConstants.IO_EXCEPTION_CODE, ErrorConstants.IO_EXCEPTION_MESSAGE)
    }

    else -> {
        APIResult.Error(
            ErrorConstants.GENERIC_EXCEPTION_CODE, ErrorConstants.GENERIC_EXCEPTION_MESSAGE
        )
    }
}


/**
 * Maps the error response to [APIResult].
 *
 * @return [APIResult.Error]
 */
fun <T> Response<T>.toAPIResult(): APIResult.Error = when (val code = this.code()) {
    ErrorConstants.STATUS_400 -> APIResult.Error(code, ErrorConstants.BAD_REQUEST_MESSAGE)
    ErrorConstants.STATUS_401 -> APIResult.Error(code, ErrorConstants.UNAUTHORIZED_MESSAGE)
    ErrorConstants.STATUS_404 -> APIResult.Error(
        code, ErrorConstants.RESOURCE_NOT_FOUND_MESSAGE
    )

    ErrorConstants.STATUS_500 -> APIResult.Error(
        code, ErrorConstants.INTERNAL_SERVER_ERROR_MESSAGE
    )

    ErrorConstants.STATUS_503 -> APIResult.Error(
        code, ErrorConstants.SERVICE_UNAVAILABLE_ERROR_MESSAGE
    )

    else -> APIResult.Error(code, ErrorConstants.GENERIC_EXCEPTION_MESSAGE)
}


/**
 * Format the response to log.
 *
 * @return [String]
 */
fun <T> Response<T>.toLogError(): String =
    "${ErrorConstants.RESPONSE_CODE} ${code()}, ${ErrorConstants.BODY} ${body()}, ${ErrorConstants.ERROR_BODY} ${errorBody()}"


/**
 *  Mapper which maps dto to domain model.
 *
 *  @return [CharacterListEntity]
 */
fun CharactersListDTO.toCharactersListEntity() = CharacterListEntity(data.map {
    it.toCharacterEntity()
})

/**
 *  Mapper which maps dto to domain model.
 *
 *  @return [CharacterEntity]
 */
fun CharacterDTO.toCharacterEntity() = CharacterEntity(
    id = id,
    name = name,
    imageUrl = imageUrl ?: "",
    films = films,
    tvShows = tvShows,
    createdAt = createdAt,
    updatedAt = updatedAt
)

