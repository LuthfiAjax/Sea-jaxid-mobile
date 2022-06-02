package com.luthfi.seajaxid.ui.menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.luthfi.seajaxid.R
import com.luthfi.seajaxid.data.local.SharedPreference
import com.luthfi.seajaxid.data.networking.Response
import com.luthfi.seajaxid.ui.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val sharedPreference = SharedPreference(this)

        val str_login_status = sharedPreference.getPreferenceString("ID")

        if (str_login_status!=null){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnLogin.setOnClickListener {
            val edt_username = etUsername.text.toString()
            val edt_password = etPassword.text.toString()

            if(edt_username == "" || edt_password == ""){
                Toast.makeText(this,"Please Enter Details.",Toast.LENGTH_SHORT).show()
            }else {
                Response().postLogin(username = edt_username, password = edt_password, ctx = this)
            }
        }

        tvDaftar.setOnClickListener {
            val intent = Intent(this, DaftarActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}