package ru.lyrian.kotlinmultiplatformsandbox.android.feature.spaceXLaunches.presentation.viewmodel

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
import ru.lyrian.kotlinmultiplatformsandbox.android.feature.spaceXLaunches.presentation.model.MainActivityViewState
import ru.lyrian.kotlinmultiplatformsandbox.android.feature.spaceXLaunches.presentation.ui.MainActivityEvent
import ru.lyrian.kotlinmultiplatformsandbox.core.AppConstants.APP_LOG_TAG
import ru.lyrian.kotlinmultiplatformsandbox.core.AppConstants.EXCEPTION_PREFIX
import ru.lyrian.kotlinmultiplatformsandbox.feature.spaceXLaunches.domain.SpaceXSDK

class MainActivityViewModel constructor(private val spaceXSdk: SpaceXSDK): ViewModel() {
    private val _viewState = MutableStateFlow(MainActivityViewState())
    val viewState = _viewState.asStateFlow()

    private val _event = MutableSharedFlow<MainActivityEvent>() // private mutable shared flow
    val event = _event.asSharedFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        viewModelScope.launch {
            Log.e(APP_LOG_TAG, EXCEPTION_PREFIX ,throwable)
            _event.emit(MainActivityEvent.ShowToast("Update failed" + throwable.localizedMessage?.let { ": $it" }))

            _viewState.update {
                it.copy(
                    isLoading = false,
                )
            }
        }
    }

    fun refresh(forceReload: Boolean = false) {
        viewModelScope.launch(coroutineExceptionHandler) {
            _viewState.update {
                it.copy(
                    isLoading = true
                )
            }

            val launches = spaceXSdk.getLaunches(forceReload)

            _viewState.update {
                it.copy(
                    isError = false,
                    isLoading = false,
                    launches = launches
                )
            }
        }
    }
}
