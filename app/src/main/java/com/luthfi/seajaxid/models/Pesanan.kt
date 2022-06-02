package com.luthfi.seajaxid.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pesanan (
    var id_pesanan: String = "",
    var id_cust: String = "",
    var url_gambar: String = "",
    var nama_produk: String = "",
    var nama_cust: String = "",
    var status_bayar: String = "",
    var status_proses: String = "",
    var satuan: String = "",
    var berat: String = "",
    var id_order: String = "",
    var harga: String = ""
) : Parcelable