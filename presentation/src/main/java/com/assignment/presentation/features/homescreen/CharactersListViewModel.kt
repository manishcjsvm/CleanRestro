package com.assignment.presentation.features.homescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.domain.common.APIResult
import com.assignment.domain.usecases.GetDisneyCharactersListUseCase
import com.assignment.presentation.base.ViewModelDelegate
import com.assignment.presentation.di.IODispatcher
import com.assignment.presentation.toCharacterList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * View model for characters list screen.
 *
 * @param getDisneyCharactersListUseCase use case to get the characters list from the server.
 * @param ioDispatcher io dispatcher on which co-routine will run.
 *
 */
@HiltViewModel
class CharactersListViewModel @Inject constructor(
    private val getDisneyCharactersListUseCase: GetDisneyCharactersListUseCase,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {


    private val charactersListViewModelDelegate =
        ViewModelDelegate<CharacterListViewState, CharacterListViewIntent, NavigateToCharacterDetailsSideEffect>(
            CharacterListViewState.Loading
        ) { viewIntent ->
            when (viewIntent) {
                is CharacterListViewIntent.LoadData -> {
                    getCharactersList()
                }

                is CharacterListViewIntent.OnItemClicked -> {
                    viewModelScope.launch {
                        sideEffectFlow.emit(NavigateToCharacterDetailsSideEffect(viewIntent.id))
                    }
                }
            }
        }

    init {
        sendIntent(CharacterListViewIntent.LoadData)
    }

    val stateFlow: MutableStateFlow<CharacterListViewState>
        get() = charactersListViewModelDelegate.stateFlow

    val sideEffectFlow: MutableSharedFlow<NavigateToCharacterDetailsSideEffect>
        get() = charactersListViewModelDelegate.sideEffectFlow


    /**
     * To fetch characters list from the server.
     */
    private fun getCharactersList() {


        viewModelScope.launch(ioDispatcher) {

            when (val apiResult = getDisneyCharactersListUseCase()) {
                is APIResult.Success -> {
                    apiResult.data.toCharacterList().also { characterList ->
                        stateFlow.update {
                            CharacterListViewState.Success(characterList)
                        }
                    }
                }

                is APIResult.Error -> {
                    stateFlow.update {
                        CharacterListViewState.Error(apiResult.errorCode, apiResult.errorMessage)
                    }
                }
            }
        }
    }


    /**
     * To send the view intent to the delegate.
     *
     * @param viewIntent [CharacterListViewIntent]
     */
    fun sendIntent(viewIntent: CharacterListViewIntent) {
        charactersListViewModelDelegate.sendIntent(viewIntent)
    }
}