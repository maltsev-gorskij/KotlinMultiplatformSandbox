package ru.lyrian.kotlinmultiplatformsandbox.android.feature.launchesList.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.lyrian.kotlinmultiplatformsandbox.android.feature.launchesList.presentation.model.LaunchesListState
import ru.lyrian.kotlinmultiplatformsandbox.android.feature.launchesList.presentation.ui.LaunchesListEvent
import ru.lyrian.kotlinmultiplatformsandbox.core.constants.LoggingConstants.APP_LOG_TAG
import ru.lyrian.kotlinmultiplatformsandbox.core.constants.LoggingConstants.EXCEPTION_PREFIX
import ru.lyrian.kotlinmultiplatformsandbox.feature.launches.domain.LaunchesInteractor

class LaunchesListViewModel constructor(
    private val launchesInteractor: LaunchesInteractor
) : ViewModel() {
    private val _viewState = MutableStateFlow(LaunchesListState())
    val viewState = _viewState.asStateFlow()

    private val _event = MutableSharedFlow<LaunchesListEvent>()
    val event = _event.asSharedFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        viewModelScope.launch {
            Log.e(APP_LOG_TAG, EXCEPTION_PREFIX, throwable)
            _event.emit(LaunchesListEvent.ShowToast("Update failed" + throwable.localizedMessage?.let { ": $it" }))

            _viewState.update {
                it.copy(
                    isLoading = false,
                )
            }
        }
    }

    init {
        refresh()
    }

    fun refresh(forceReload: Boolean = false) {
        viewModelScope.launch(coroutineExceptionHandler) {
            _viewState.update {
                it.copy(
                    isLoading = true
                )
            }

            val launches = launchesInteractor.getAllLaunches(forceReload)

            _viewState.update {
                it.copy(
                    isError = false,
                    isLoading = false,
                    launches = launches
                )
            }

            if (forceReload) _event.emit(LaunchesListEvent.ShowToast("Reload completed"))
        }
    }
}
