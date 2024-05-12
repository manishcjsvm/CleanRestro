package com.assignment.presentation.features.homescreen

import androidx.lifecycle.viewModelScope
import com.assignment.domain.APIResult
import com.assignment.domain.usecases.GetDisneyCharactersListUseCase
import com.assignment.presentation.base.BaseViewModel
import com.assignment.presentation.di.IODispatcher
import com.assignment.presentation.mappers.CharacterListMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * View model for characters list screen.
 *
 * @param getDisneyCharactersListUseCase use case to get the characters list from the server.
 * @param mapper to map the characters list entity to characters list model.
 * @param ioDispatcher io dispatcher on which co-routine will run.
 *
 */
@HiltViewModel
class CharactersListViewModel @Inject constructor(
    private val getDisneyCharactersListUseCase: GetDisneyCharactersListUseCase,
    private val mapper: CharacterListMapper,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) : BaseViewModel<CharacterListViewState, CharacterListViewIntent, NavigateToCharacterDetailsSideEffect>() {

    init {
        sendIntent(CharacterListViewIntent.LoadData)
    }

    /**
     * To fetch characters list from the server.
     */
    private fun getCharactersList() {

        viewModelScope.launch(ioDispatcher) {
            when (val apiResult = getDisneyCharactersListUseCase()) {
                is APIResult.Success -> {
                    mapper.map(apiResult.data)
                        .also {characterList->

                            state.update {
                                CharacterListViewState.Success(characterList)
                            }
                        }
                }

                is APIResult.Error -> {
                    state.update {
                        CharacterListViewState.Error(apiResult.errorCode,apiResult.errorMessage)
                    }
                }
            }
        }
    }

    override fun initialState(): CharacterListViewState {
        return CharacterListViewState.Loading
    }

    override fun sendIntent(viewIntent: CharacterListViewIntent) {
        when (viewIntent) {
            is CharacterListViewIntent.LoadData -> {
                getCharactersList()
            }

            is CharacterListViewIntent.OnItemClicked -> {
                viewModelScope.launch {
                    sideEffect.emit(NavigateToCharacterDetailsSideEffect(viewIntent.id))
                }
            }
        }
    }
}