package com.example.navigationdrawer.ktx

internal fun <R> runCatchingAsNull(block: () -> R): R? {
    return try {
        block()
    } catch (e: Throwable) {
        null
    }
}
