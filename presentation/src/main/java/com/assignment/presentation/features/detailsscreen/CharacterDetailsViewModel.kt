package com.assignment.presentation.features.detailsscreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.assignment.common.APIResult
import com.assignment.domain.usecases.GetDisneyCharacterDetailsUseCase
import com.assignment.presentation.base.BaseViewModel
import com.assignment.presentation.constants.Constants
import com.assignment.presentation.di.IODispatcher
import com.assignment.presentation.mappers.CharacterMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * View model for character details screen.
 *
 * @param savedStateHandle to get the arguments supplied.
 * @param getDisneyCharacterDetailsUseCase use case to get the character details from the server.
 * @param mapper to map the character entity to character model.
 * @param ioDispatcher io dispatcher on which co-routine will run.
 *
 */
@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getDisneyCharacterDetailsUseCase: GetDisneyCharacterDetailsUseCase,
    private val mapper: CharacterMapper,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) : BaseViewModel<CharacterDetailsViewState, CharacterDetailsViewIntent, CharactersDetailsSideEffect>() {

    init {
        val id =
            savedStateHandle.get<Int>(Constants.characterId) // will work because of navBackStackEntry which implements ViewModelStoreOwner and holds the arguments.
        sendIntent(CharacterDetailsViewIntent.LoadData(id ?: 0))
    }

    /**
     * To fetch character details from the server.
     *
     * @param id id of the selected character.
     */
    private fun getCharacterDetails(id: Int) {

        viewModelScope.launch(ioDispatcher) {
            when (val apiResult = getDisneyCharacterDetailsUseCase(id)) {
                is APIResult.Success -> {
                    mapper.mapToCharacter(apiResult.data)
                        .also {
                            state.emit(CharacterDetailsViewState.Success(it))
                        }
                }

                is APIResult.Error -> {
                    state.emit(CharacterDetailsViewState.Error(apiResult.exception))
                }
            }
        }
    }

    override fun initialState(): CharacterDetailsViewState {
        return CharacterDetailsViewState.Loading
    }

    override fun sendIntent(viewIntent: CharacterDetailsViewIntent) {
        when (viewIntent) {
            is CharacterDetailsViewIntent.LoadData -> {
                getCharacterDetails(viewIntent.id)
            }
        }
    }
}