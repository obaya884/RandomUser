package io.github.obaya884.randomuser.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.obaya884.randomuser.combine
import io.github.obaya884.randomuser.domain.RandomUser
import io.github.obaya884.randomuser.domain.usecase.RandomUserUseCase
import io.github.obaya884.randomuser.setLoadState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * - エラー時の表示（リロード表示）
 * - Flowに置き換え
 * - フィルター
 * - 詳細画面
 * - 状態保存（savedInstance, DataStore）
 */

@HiltViewModel
class RandomUserListViewModel @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val userUseCase: RandomUserUseCase
) : ViewModel() {

    private val _users = MutableLiveData<List<RandomUser>>()
    private val _loadState = MutableLiveData<LoadState>(LoadState.Initialized)
    private val _pagination =
        MutableLiveData<Pagination>(Pagination.createInitialPagination(pageSize = PAGE_SIZE))

    val uiModel: LiveData<RandomUserListUiModel> by lazy {
        combine(
            RandomUserListUiModel(),
            _users,
            _loadState,
            _pagination
        ) { _, users, loadState, pagination ->
            RandomUserListUiModel(users, loadState, pagination)
        }
    }

    fun getUsersFirstPage() {
        _pagination.postValue(Pagination.createInitialPagination(pageSize = PAGE_SIZE))
        getUsers(page = _pagination.value!!.page)
    }

    fun getUsersNextPage() {
        getUsers(_pagination.value!!.nextPage)
    }

    private fun getUsers(page: Int) {
        viewModelScope.launch(context = dispatcher, start = CoroutineStart.DEFAULT) {
            userUseCase.getRandomUsers(page, _pagination.value!!.pageSize)
                .setLoadState(_loadState)
                .collect {
                    val currentUsers = _users.value?.toMutableList() ?: mutableListOf()
                    _users.postValue(currentUsers + it)
                }
        }
    }

    companion object {
        private const val PAGE_SIZE = 25
    }
}

data class RandomUserListUiModel(
    val users: List<RandomUser>? = null,
    val loadState: LoadState = LoadState.Initialized,
    val pagination: Pagination = Pagination.createInitialPagination(pageSize = 25)
) {
    val showReloadButton = users.isNullOrEmpty() && loadState is LoadState.Error
}
