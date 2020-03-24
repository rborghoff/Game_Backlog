package com.example.gamebacklog.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "backlogTable")
data class Backlog (

    @ColumnInfo(name = "title")
            var title: String,

    @ColumnInfo(name = "platform")
    var platform : String,

    @ColumnInfo(name = "dag")
    var dag: Int,

    @ColumnInfo( name = "maand")
    var maand: String,

    @ColumnInfo(name = "jaar")
    var jaar: Int,

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Long? = null


):Parcelable