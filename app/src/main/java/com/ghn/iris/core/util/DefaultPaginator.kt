package com.ghn.iris.core.util

class DefaultPaginator<T>(
    private val onLoadUpdated: (Boolean) -> Unit,
    private val onRequest: suspend (nextPage: Int) -> Resource<List<T>>,
    private val onError: suspend (UiText) -> Unit,
    private val onSuccess: (items: List<T>, fisrtPage: Boolean) -> Unit
) : Paginator<T> {

    private var page = 1

    override suspend fun loadNextItems() {
        onLoadUpdated(true)
        when (val result = onRequest(page)) {
            is Resource.Success -> {
                val items = result.data ?: emptyList()
                page++
                onSuccess(items, false)
                onLoadUpdated(false)
            }

            is Resource.Error -> {
                onError(result.uiText ?: UiText.unknownError())
                onLoadUpdated(false)
            }
        }
    }

    override suspend fun loadFirstItems() {
        onLoadUpdated(true)
        when (val result = onRequest(0)) {
            is Resource.Success -> {
                val items = result.data ?: emptyList()
                page = 1
                onSuccess(items, true)
                onLoadUpdated(false)
            }

            is Resource.Error -> {
                onError(result.uiText ?: UiText.unknownError())
                onLoadUpdated(false)
            }
        }
    }
}