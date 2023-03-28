package com.ghn.iris.core.presentation.util

interface Paginator<T> {

    suspend fun loadNextItems()
}