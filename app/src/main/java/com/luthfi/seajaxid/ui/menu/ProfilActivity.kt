package com.luthfi.seajaxid.ui.menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.luthfi.seajaxid.R
import com.luthfi.seajaxid.data.local.SharedPreference
import kotlinx.android.synthetic.main.activity_profil.*

class ProfilActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)

        val sharedPreference = SharedPreference(this)


        val name = sharedPreference.getPreferenceString("nama")
        val email = sharedPreference.getPreferenceString("email")
        val username = sharedPreference.getPreferenceString("username")
        val hp = sharedPreference.getPreferenceString("No HP")
        val alamat = sharedPreference.getPreferenceString("alamat")

        etFullName.setText(name)
        etEmail.setText(email)
        etUsernameR.setText(username)
        etNoHP.setText(hp)
        etAlamat.setText(alamat)

        btnKeluar.setOnClickListener {
            sharedPreference.clearSharedPreference()
            Toast.makeText(this,"User LogOut Successfully.",Toast.LENGTH_SHORT).show()

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }


    }
}