package com.assignment.common.logger

/**
 * To provide the logging feature.
 */
interface Logger {

    /**
     * To log the debug message.
     *
     * @param message message to log.
     */
    fun debug(message: String)

    /**
     * To log the error message.
     *
     * @param message message to log.
     */
    fun error(message: String)

}