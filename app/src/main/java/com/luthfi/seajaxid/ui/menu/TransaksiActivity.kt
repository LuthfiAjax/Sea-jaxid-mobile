package com.luthfi.seajaxid.ui.menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.luthfi.seajaxid.R
import com.luthfi.seajaxid.data.local.SharedPreference
import com.luthfi.seajaxid.data.networking.Response
import com.luthfi.seajaxid.models.Pesanan
import com.luthfi.seajaxid.ui.MainActivity
import kotlinx.android.synthetic.main.activity_transaksi.*

class TransaksiActivity : AppCompatActivity() {

    private lateinit var rvProduk: RecyclerView
    private lateinit var pBar: ProgressBar
    private var list: ArrayList<Pesanan> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaksi)

        val sharedPreference = SharedPreference(this)
        val idCust = sharedPreference.getPreferenceString("ID")

        pBar = menu_load

        rvProduk = rvProdukMain
        rvProduk.setHasFixedSize(true)

        rvProduk.layoutManager = LinearLayoutManager(this)
        Response().getHistory(list, rvProduk, idCust!!, pBar)

        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}