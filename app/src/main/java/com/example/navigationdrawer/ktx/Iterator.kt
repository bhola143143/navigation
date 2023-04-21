package com.example.navigationdrawer.ktx

internal fun <T> Iterator<T>.nextOrNull(): T? = if (hasNext()) next() else null
