package com.luthfi.seajaxid.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.luthfi.seajaxid.R
import com.luthfi.seajaxid.data.networking.Response
import com.luthfi.seajaxid.models.Produk
import com.luthfi.seajaxid.ui.menu.ProfilActivity
import com.luthfi.seajaxid.ui.menu.TransaksiActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var rvProduk: RecyclerView
    private lateinit var pBar: ProgressBar
    private var list: ArrayList<Produk> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pBar = menu_load

        AndroidNetworking.initialize(this)

        rvProduk = rvProdukMain
        rvProduk.setHasFixedSize(true)

        rvProduk.layoutManager = LinearLayoutManager(this)
        Response().getProduk(list, rvProduk, pBar)

        btnProfile.setOnClickListener {
            val intent = Intent(this, ProfilActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnHistory.setOnClickListener {
            val intent = Intent(this, TransaksiActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}