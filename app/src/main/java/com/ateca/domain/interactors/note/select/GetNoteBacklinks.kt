package com.ateca.domain.interactors.note.select

import com.ateca.R
import com.ateca.domain.core.AppDispatchers
import com.ateca.domain.core.DataState
import com.ateca.domain.core.ProgressBarState
import com.ateca.domain.datasource.ILinkDataSource
import com.ateca.domain.interactors.IGetNoteBacklinks
import com.ateca.domain.interactors.util.debugBehavior
import com.ateca.domain.interactors.util.genericDialogResponse
import com.ateca.domain.models.Link
import com.ateca.domain.models.NoteId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * Created by dronpascal on 18.05.2022.
 */
class GetNoteBacklinks(
    private val linkSource: ILinkDataSource,
    private val dispatchers: AppDispatchers,
) : IGetNoteBacklinks {

    override fun execute(param: NoteId): Flow<DataState<List<Link>>> = flow {
        @Suppress("UnnecessaryVariable")
        val id = param

        try {
            debugBehavior()
            emit(DataState.Loading(progressBarState = ProgressBarState.Loading))
            val links = linkSource.getBacklinksById(id)
            emit(DataState.Data(links))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(genericDialogResponse(e, R.string.error, R.string.get_note_backlinks_error_msg))
        } finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }
    }.flowOn(dispatchers.io)
}