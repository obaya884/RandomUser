package io.github.obaya884.randomuser

import androidx.lifecycle.MutableLiveData
import io.github.obaya884.randomuser.presentation.LoadState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

fun <T> Flow<T>.setLoadState(
    loadState: MutableLiveData<LoadState>,
    shouldShowProgress: Boolean = true
) =
    onStart { loadState.postValue(LoadState.Loading(shouldShowProgress)) }
        .catch { cause: Throwable -> loadState.postValue(LoadState.Error(cause)) }
        .onEach { loadState.postValue((LoadState.Succeeded)) }