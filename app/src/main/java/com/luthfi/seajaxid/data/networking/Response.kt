package com.luthfi.seajaxid.data.networking

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.luthfi.seajaxid.adapter.HistoryAdapter
import com.luthfi.seajaxid.adapter.MainAdapter
import com.luthfi.seajaxid.data.local.SharedPreference
import com.luthfi.seajaxid.models.Pesanan
import com.luthfi.seajaxid.models.Produk
import com.luthfi.seajaxid.models.User
import com.luthfi.seajaxid.ui.MainActivity
import com.luthfi.seajaxid.ui.menu.LoginActivity
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class Response {

    fun getProduk(
        list: ArrayList<Produk> = arrayListOf(),
        rvProduk: RecyclerView,
        pBar: ProgressBar
    ){
        val listProduk = ArrayList<Produk>()
        pBar.visibility = View.VISIBLE
        AndroidNetworking.get("https://jaxid.site/TA/API/produk.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONArray(object : JSONArrayRequestListener {
                override fun onResponse(response: JSONArray) {
                    // do anything with response
                    try {
                        Log.d("Responses : ", response.toString())
                        for (i in 0 until response.length()) {
                            val produk = Produk()
                            produk.id_produk = response.getJSONObject(i).getString("id_produk")
                            produk.url_gambar = response.getJSONObject(i).getString("url_gambar")
                            produk.nama_produk = response.getJSONObject(i).getString("nama_produk")
                            produk.detail_produk = response.getJSONObject(i).getString("detail_produk")
                            produk.ongkir = response.getJSONObject(i).getString("ongkir")
                            produk.satuan = response.getJSONObject(i).getString("satuan")
                            produk.berat = response.getJSONObject(i).getString("berat")
                            produk.harga = response.getJSONObject(i).getString("harga")

                            listProduk.add(produk)
                        }
                        list.addAll(listProduk)
                        rvProduk.adapter = MainAdapter(list)
                        pBar.visibility = View.GONE
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }

                override fun onError(error: ANError) {
                    // handle error
                }
            })
    }

    fun getHistory(
        list: ArrayList<Pesanan> = arrayListOf(),
        rvProduk: RecyclerView,
        key: String,
        pBar: ProgressBar
    ){
        val listPesanan = ArrayList<Pesanan>()
        pBar.visibility = View.VISIBLE
        AndroidNetworking.post("https://jaxid.site/TA/API/pesanan.php?")
            .addBodyParameter("id_cust", key)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONArray(object : JSONArrayRequestListener {
                override fun onResponse(response: JSONArray) {
                    // do anything with response
                    try {
                        Log.d("Responses : ", response.toString())
                        for (i in 0 until response.length()) {
                            val pesanan = Pesanan()
                            pesanan.id_pesanan = response.getJSONObject(i).getString("id_pesanan")
                            pesanan.id_cust = response.getJSONObject(i).getString("id_cust")
                            pesanan.url_gambar = response.getJSONObject(i).getString("url_gambar")
                            pesanan.nama_produk = response.getJSONObject(i).getString("nama_produk")
                            pesanan.nama_cust = response.getJSONObject(i).getString("nama_cust")
                            pesanan.status_bayar = response.getJSONObject(i).getString("status_bayar")
                            pesanan.status_proses = response.getJSONObject(i).getString("status_proses")
                            pesanan.satuan = response.getJSONObject(i).getString("satuan")
                            pesanan.berat = response.getJSONObject(i).getString("berat")
                            pesanan.harga = response.getJSONObject(i).getString("harga")
                            pesanan.id_order = response.getJSONObject(i).getString("id_order")

                            listPesanan.add(pesanan)
                        }
                        list.addAll(listPesanan)
                        rvProduk.adapter = HistoryAdapter(list)
                        pBar.visibility = View.GONE
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }

                override fun onError(error: ANError) {
                    // handle error
                }
            })
    }

    fun postRegister(user: User, ctx: Context){
        AndroidNetworking.post("https://jaxid.site/TA/API/register.php")
            .addBodyParameter(user)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    // do anything with response
                    val status = response.getString("status")

                    if(status == "true"){
                        val intent = Intent(ctx, LoginActivity::class.java)
                        startActivity(ctx,intent, Bundle.EMPTY)
                        Toast.makeText(ctx,"Data Berhasil Disimpan.",Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(ctx,"Akun Sudah di Daftarkan.",Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onError(error: ANError) {
                    // handle error
                    Toast.makeText(ctx,error.toString(),Toast.LENGTH_SHORT).show()
                }
            })
    }


    fun postLogin(username: String, password: String, ctx: Context){
        AndroidNetworking.post("https://jaxid.site/TA/API/login.php")
            .addBodyParameter("post_username", username)
            .addBodyParameter("post_password", password)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    // do anything with response
                    val sharedPreference = SharedPreference(ctx)

                    val status = response.getString("respone")

                    if(status == "true"){
                        val payload = response.getJSONObject("payload")

                        val id = payload.getString("ID")
                        val user = payload.getString("username")
                        val nama = payload.getString("nama")
                        val email = payload.getString("email")
                        val hp = payload.getString("No HP")
                        val alamat = payload.getString("Alamat")

                        sharedPreference.save_String("ID", id)
                        sharedPreference.save_String("username", user)
                        sharedPreference.save_String("nama",  nama)
                        sharedPreference.save_String("email", email)
                        sharedPreference.save_String("No HP", hp)
                        sharedPreference.save_String("alamat", alamat)

                        val intent = Intent(ctx, MainActivity::class.java)
                        startActivity(ctx, intent, Bundle.EMPTY)
                    }else{
                        Toast.makeText(ctx,"Username Atau Password Anda Salah.",Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onError(error: ANError) {
                    // handle error
                    Toast.makeText(ctx,error.toString(),Toast.LENGTH_SHORT).show()
                }
            })
    }

    fun postPesanan(id_order:String, idProd: String, idCust: String, tujuan: String, ctx: Context){
        AndroidNetworking.post("https://jaxid.site/TA/API/beli.php")
            .addBodyParameter("id_produk", idProd)
            .addBodyParameter("id_cust", idCust)
            .addBodyParameter("tujuan", tujuan)
            .addBodyParameter("status_bayar", "Pending")
            .addBodyParameter("status_proses", "Menunggu Konfrimasi")
            .addBodyParameter("id_order", id_order)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONArray(object : JSONArrayRequestListener {
                override fun onResponse(response: JSONArray) {
                    // do anything with response
                    Toast.makeText(ctx,"Pemesanan Berhasil",Toast.LENGTH_SHORT).show()
                }

                override fun onError(error: ANError) {
                    // handle error
                }
            })
    }


}