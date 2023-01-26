package ru.lyrian.kotlinmultiplatformsandbox.android.feature.launchDetails.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.lyrian.kotlinmultiplatformsandbox.android.feature.launchDetails.presentation.model.LaunchDetailsArgs
import ru.lyrian.kotlinmultiplatformsandbox.android.feature.launchDetails.presentation.model.LaunchDetailsEvent
import ru.lyrian.kotlinmultiplatformsandbox.android.feature.launchDetails.presentation.model.LaunchDetailsState
import ru.lyrian.kotlinmultiplatformsandbox.feature.launches.domain.LaunchesInteractor
import ru.lyrian.kotlinmultiplatformsandbox.feature.launches.domain.RocketLaunch

class LaunchDetailsViewModel(
    private val launchesInteractor: LaunchesInteractor,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val launchDetailsArgs = LaunchDetailsArgs(savedStateHandle)
    private var launch: RocketLaunch? = null

    private val _state = MutableStateFlow(LaunchDetailsState(launchDetailsArgs.launchTitle))
    val state: StateFlow<LaunchDetailsState> = _state.asStateFlow()

    private val _eventChannel: Channel<LaunchDetailsEvent> = Channel()
    val eventChannel = _eventChannel.receiveAsFlow()

    init {
        loadLaunch(launchDetailsArgs.launchId)
    }

    fun refreshLaunchDetails() = loadLaunch(launchDetailsArgs.launchId)

    private fun loadLaunch(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                _state.update {
                    it.copy(
                        isLoading = true,
                        isError = false
                    )
                }
                launchesInteractor.getLaunchById(id)
            }.onSuccess { rocketLaunch ->
                launch = rocketLaunch
                _state.update {
                    it.copy(
                        launch = launch,
                        isLoading = false
                    )
                }
            }.onFailure { throwable ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        isError = true
                    )
                }
                _eventChannel.send(LaunchDetailsEvent.ShowErrorMessage("Error: ${throwable.localizedMessage}"))
            }
        }
    }
}
