package com.assignment.common

/**
 * To be implemented by mapper for presentation and data layers.
 */
interface Mapper<F, T> {
    fun map(from: F): T
}