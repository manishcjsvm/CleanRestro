package com.assignment.presentation.features.detailsscreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.domain.common.APIResult
import com.assignment.domain.usecases.GetDisneyCharacterDetailsUseCase
import com.assignment.presentation.base.ViewModelDelegate
import com.assignment.presentation.di.IODispatcher
import com.assignment.presentation.navigation.NavRoutes
import com.assignment.presentation.toCharacter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * View model for character details screen.
 *
 * @param savedStateHandle to get the arguments supplied.
 * @param getDisneyCharacterDetailsUseCase use case to get the character details from the server.
 * @param ioDispatcher io dispatcher on which co-routine will run.
 *
 */
@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getDisneyCharacterDetailsUseCase: GetDisneyCharacterDetailsUseCase,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {


    private val characterDetailsViewModelDelegate =
        ViewModelDelegate<CharacterDetailsViewState, CharacterDetailsLoadDataViewIntent, CharactersDetailsSideEffect>(
            initialState = CharacterDetailsViewState.Loading
        )
        { viewIntent ->
            getCharacterDetails(viewIntent.id)
        }

    init {
        val id =
            savedStateHandle.get<Int>(NavRoutes.characterId) // will work because of navBackStackEntry which implements ViewModelStoreOwner and holds the arguments.
        sendIntent(CharacterDetailsLoadDataViewIntent(id ?: 0))
    }

    val stateFlow: StateFlow<CharacterDetailsViewState>
        get() = characterDetailsViewModelDelegate.stateFlow


    /**
     * To fetch character details from the server.
     *
     * @param id id of the selected character.
     */
    private fun getCharacterDetails(id: Int) {

        viewModelScope.launch(ioDispatcher) {
            when (val apiResult = getDisneyCharacterDetailsUseCase(id)) {
                is APIResult.Success -> {
                    apiResult.data.toCharacter()
                        .also { character ->
                            characterDetailsViewModelDelegate.stateFlow.update {
                                CharacterDetailsViewState.Success(character)
                            }
                        }
                }

                is APIResult.Error -> {
                    characterDetailsViewModelDelegate.stateFlow.update {
                        CharacterDetailsViewState.Error(apiResult.errorCode, apiResult.errorMessage)
                    }
                }
            }
        }
    }

    /**
     * To send the view intent to the delegate.
     *
     * @param viewIntent [CharacterDetailsLoadDataViewIntent]
     */
    fun sendIntent(viewIntent: CharacterDetailsLoadDataViewIntent) {
        characterDetailsViewModelDelegate.sendIntent(viewIntent)
    }
}