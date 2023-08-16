package com.ghn.iris.core.util

interface Paginator<T> {

    suspend fun loadNextItems()
}