package com.luthfi.seajaxid.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Produk (
    var id_produk: String = "",
    var url_gambar: String = "",
    var nama_produk: String = "",
    var detail_produk: String = "",
    var ongkir: String = "",
    var satuan: String = "",
    var berat: String = "",
    var harga: String = ""
) : Parcelable


