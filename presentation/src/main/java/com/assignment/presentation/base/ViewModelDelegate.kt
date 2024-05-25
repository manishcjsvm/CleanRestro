package com.assignment.presentation.base

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * ViewModel delegate to provide the generic functionality.
 */
class ViewModelDelegate<VS : ViewState, VI : ViewIntent, SE : SideEffect>(
    initialState: VS,
    private val sendIntentFunction: (VI) -> Unit
) {

    // Stateflow which could be observed for the future view states
    val stateFlow: MutableStateFlow<VS> = MutableStateFlow(initialState)


    // SharedFlow which could be observed for one time events
    val sideEffectFlow: MutableSharedFlow<SE> = MutableSharedFlow()

    /**
     * To send the view intent to the delegate.
     *
     * @param viewIntent [VI]
     */
    fun sendIntent(viewIntent: VI) {
        sendIntentFunction(viewIntent)
    }

}