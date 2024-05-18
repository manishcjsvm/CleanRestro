package com.assignment.presentation.features.homescreen

import androidx.lifecycle.viewModelScope
import com.assignment.domain.common.APIResult
import com.assignment.domain.usecases.GetDisneyCharactersListUseCase
import com.assignment.presentation.base.BaseViewModel
import com.assignment.presentation.di.IODispatcher
import com.assignment.presentation.toCharacterList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
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
                    apiResult.data.toCharacterList()
                        .also { characterList ->
                            state.update {
                                CharacterListViewState.Success(characterList)
                            }
                        }
                }

                is APIResult.Error -> {
                    state.update {
                        CharacterListViewState.Error(apiResult.errorCode, apiResult.errorMessage)
                    }
                }
            }
        }
    }

    override fun initialState() = CharacterListViewState.Loading

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