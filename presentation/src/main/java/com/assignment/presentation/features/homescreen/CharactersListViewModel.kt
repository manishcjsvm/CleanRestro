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
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
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
            processIntent(viewIntent)
        }

    init {
        sendIntent(CharacterListViewIntent.LoadData)
    }

    val stateFlow: StateFlow<CharacterListViewState>
        get() = charactersListViewModelDelegate.stateFlow

    val sideEffectFlow: SharedFlow<NavigateToCharacterDetailsSideEffect>
        get() = charactersListViewModelDelegate.sideEffectFlow


    /**
     * To fetch characters list from the server.
     */
    private fun getCharactersList() {


        viewModelScope.launch(ioDispatcher) {

            when (val apiResult = getDisneyCharactersListUseCase()) {
                is APIResult.Success -> {
                    apiResult.data.toCharacterList().also { characterList ->
                        charactersListViewModelDelegate.stateFlow.update {
                            CharacterListViewState.Success(characterList)
                        }
                    }
                }

                is APIResult.Error -> {
                    charactersListViewModelDelegate.stateFlow.update {
                        CharacterListViewState.Error(apiResult.errorCode, apiResult.errorMessage)
                    }
                }
            }
        }
    }


    /**
     * To process the intent received from the delegate.
     *
     * @param viewIntent [CharacterListViewIntent]
     */
    private fun processIntent(viewIntent: CharacterListViewIntent) {
        viewModelScope.launch {
            when (viewIntent) {
                is CharacterListViewIntent.LoadData -> {
                    getCharactersList()
                }

                is CharacterListViewIntent.OnItemClicked -> {
                    viewModelScope.launch {

                        charactersListViewModelDelegate.sideEffectFlow.emit(
                            NavigateToCharacterDetailsSideEffect(
                                viewIntent.id
                            )
                        )
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