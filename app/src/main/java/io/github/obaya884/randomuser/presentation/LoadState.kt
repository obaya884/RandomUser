package io.github.obaya884.randomuser.presentation


interface IsLoading {
    val isLoading: Boolean
}

interface HasThrowable {
    val throwable: Throwable
}

sealed class LoadState {
    object Initialized : LoadState()
    data class Loading(override val isLoading: Boolean) : LoadState(), IsLoading
    object Succeeded : LoadState()
    data class Error(override val throwable: Throwable) : LoadState(), HasThrowable
}
