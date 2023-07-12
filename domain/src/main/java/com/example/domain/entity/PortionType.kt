package com.example.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PortionType(
    val id : Long,
    val name : String,//gramm/millilitres
    val shortName : String//g/ml
) : Parcelable