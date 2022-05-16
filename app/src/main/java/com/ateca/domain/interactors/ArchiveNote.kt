package com.ateca.domain.interactors

import com.ateca.domain.core.DataState
import com.ateca.domain.core.ProgressBarState
import com.ateca.domain.core.UIComponent
import com.ateca.domain.datasource.INoteDataSource
import com.ateca.domain.models.NoteId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by dronpascal on 16.05.2022.
 */
class ArchiveNote(
    private val noteSource: INoteDataSource,
) {
    fun execute(id: NoteId): Flow<DataState<Nothing>> = flow {
        try {
            emit(DataState.Loading(progressBarState = ProgressBarState.Loading))
            noteSource.setArchived(id, true)
        } catch (e: Exception) {
            e.printStackTrace()
            emit(
                @Suppress("RemoveExplicitTypeArguments")  // Error without Response type
                DataState.Response<Nothing>(
                    uiComponent = UIComponent.Dialog(
                        title = "Error",
                        description = e.message ?: "Failed to archive note"
                    )
                )
            )
        } finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }
    }
}