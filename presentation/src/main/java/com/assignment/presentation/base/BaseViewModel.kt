package com.assignment.presentation.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * A base abstract class with common functionality that deals with MVI architecture.
 *
 * [VS] is the ViewState,
 * [VI] is the ViewIntent,
 * [SE] is the SideEffect
 *
 */
abstract class BaseViewModel<VS : ViewState, VI : ViewIntent, SE : SideEffect> : ViewModel() {

    // Initial view state
    private val initialState: VS by lazy {
        initialState()
    }

    // Stateflow which could be observed for the future view states
    protected val state: MutableStateFlow<VS> = MutableStateFlow(initialState)

    val stateFlow: MutableStateFlow<VS>
        get() = state

    // SharedFlow which could be observed for one time events
    protected val sideEffect: MutableSharedFlow<SE> = MutableSharedFlow()

    val sideEffectFlow: MutableSharedFlow<SE>
        get() = sideEffect

    /**
     * Provides the initial view state.
     *
     * @return [VS]
     */
    abstract fun initialState(): VS

    /**
     * To be used as single source of truth to send the events to the view model.
     * On every intent there could be view state or side effect returned from the view mdoel.
     *
     * @return [VI]
     */
    abstract fun sendIntent(viewIntent: VI)
}