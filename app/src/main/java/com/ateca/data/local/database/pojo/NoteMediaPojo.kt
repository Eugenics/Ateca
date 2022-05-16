package com.ateca.data.local.database.pojo

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.ateca.domain.core.IEntityModel
import com.ateca.domain.core.IModel

@Entity(tableName = "notes_media", primaryKeys = ["noteMediaUID"])
data class NoteMediaPojo(
    @ColumnInfo(name = "noteMediaUID")
    val noteMediaUID: String,
    @ColumnInfo(name = "noteUID")
    val noteUID: String,
    @ColumnInfo(name = "noteMediaPath")
    val noteMediaPath: String
) : IEntityModel {
    override fun convertToModel(): IModel {
        TODO("Not yet implemented")
    }
}
