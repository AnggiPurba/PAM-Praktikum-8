package com.ifs21012.pampraktikum8

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Dinosss(

    var name: String,
    var icon: Int,
    var description: String,
    var Karakteristik: String,
    var Kelompok: String,
    var Habitat: String,
    var PanjangTinggiBobot: String,
    var Kelemahan: String,
    var Makanan: String,

    ) : Parcelable