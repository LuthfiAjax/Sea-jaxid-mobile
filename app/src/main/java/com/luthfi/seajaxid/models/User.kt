package com.luthfi.seajaxid.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User (
    var ID: String = "",
    var username: String = "",
    var password: String = "",
    var nama_cust: String = "",
    var email_cust: String = "",
    var no_cust: String = "",
    var alamat_cust: String = ""
) : Parcelable