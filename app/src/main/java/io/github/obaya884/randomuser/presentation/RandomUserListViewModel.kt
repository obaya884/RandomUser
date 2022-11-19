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


data class RandomUserListUiState(
    val users: List<RandomUser>? = null,
    val loadState: LoadState = LoadState.Initialized
)

@HiltViewModel
class RandomUserListViewModel @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val userUseCase: RandomUserUseCase
) : ViewModel() {
//    private val _users: MutableStateFlow<RandomUserListUiState> =
//        MutableStateFlow(RandomUserListUiState())
//    val uiState: StateFlow<RandomUserListUiState> = _uiState.asStateFlow()

    private val _users = MutableLiveData<List<RandomUser>>()
    private val _loadState = MutableLiveData<LoadState>(LoadState.Initialized)
    val uiState: LiveData<RandomUserListUiState> by lazy {
        combine(
            RandomUserListUiState(),
            _users,
            _loadState
        ) { _, users, loadState ->
            RandomUserListUiState(users, loadState)
        }
    }

    fun getUsers() {
        viewModelScope.launch(context = dispatcher, start = CoroutineStart.DEFAULT) {
            userUseCase.getRandomUsers(1, 25)
                .setLoadState(_loadState)
                .collect {
                    _users.postValue(it)
                }
        }
    }
}