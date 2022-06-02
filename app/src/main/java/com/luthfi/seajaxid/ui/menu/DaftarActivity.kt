package com.luthfi.seajaxid.ui.menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.luthfi.seajaxid.R
import com.luthfi.seajaxid.data.local.SharedPreference
import com.luthfi.seajaxid.data.networking.Response
import com.luthfi.seajaxid.models.Produk
import com.luthfi.seajaxid.models.User
import com.luthfi.seajaxid.ui.MainActivity
import kotlinx.android.synthetic.main.activity_daftar.*

class DaftarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar)

        val sharedPreference = SharedPreference(this)

        btnDaftar.setOnClickListener {
            val user = User()
            user.nama_cust = etFullName.text.toString()
            user.email_cust = etEmail.text.toString()
            user.username = etUsernameR.text.toString()
            user.password = etPasswordR.text.toString()
            user.no_cust = etNoHP.text.toString()
            user.alamat_cust = etAlamat.text.toString()

            if(user.nama_cust == "" || user.email_cust == "" || user.username == "" || user.password == "" || user.no_cust == "" || user.alamat_cust == ""){
                Toast.makeText(this,"Please Enter Details.",Toast.LENGTH_SHORT).show()
            }else {

                Response().postRegister(user, this)
            }
        }
    }
}