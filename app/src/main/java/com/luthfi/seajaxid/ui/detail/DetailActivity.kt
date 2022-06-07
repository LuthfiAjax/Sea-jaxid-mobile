package com.luthfi.seajaxid.ui.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.luthfi.seajaxid.R
import com.luthfi.seajaxid.data.local.SharedPreference
import com.luthfi.seajaxid.data.networking.Response
import com.luthfi.seajaxid.models.Produk
import com.luthfi.seajaxid.ui.MainActivity
import com.midtrans.sdk.corekit.callback.TransactionFinishedCallback
import com.midtrans.sdk.corekit.core.MidtransSDK
import com.midtrans.sdk.corekit.core.SdkCoreFlowBuilder
import com.midtrans.sdk.corekit.core.TransactionRequest
import com.midtrans.sdk.corekit.core.themes.CustomColorTheme
import com.midtrans.sdk.corekit.models.BillingAddress
import com.midtrans.sdk.corekit.models.CustomerDetails
import com.midtrans.sdk.corekit.models.ShippingAddress
import com.midtrans.sdk.uikit.SdkUIFlowBuilder
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val DATA_PRODUK = "data_produk"
    }
    
    val temp_id: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val sharedPreference = SharedPreference(this)

        val idCust = sharedPreference.getPreferenceString("ID")
        val alamat = sharedPreference.getPreferenceString("alamat")

        var order_id = ""

        val produk = intent.getParcelableExtra<Produk>(DATA_PRODUK)

        val id_produk = produk!!.id_produk
        val url_gambar = produk.url_gambar
        val nama_produk = produk.nama_produk
        val detail_produk = produk.detail_produk
        val satuan = produk.satuan
        val berat = produk.berat
        val harga = produk.harga

        Glide.with(this)
            .load(url_gambar)
            .apply(RequestOptions())
            .into(ivProduct)
        tvTittle.text = nama_produk
        tvDescription.text = detail_produk
        tvBerat.text = berat
        tvSatuan.text = satuan
        ivPrice.text = harga

        SdkUIFlowBuilder.init()
            .setClientKey("Mid-client-U1SmJinbmpFjebme") // client_key is mandatory
            .setContext(applicationContext) // context is mandatory
            .setTransactionFinishedCallback(TransactionFinishedCallback {
                    result ->
                // this is logic
                Response().postPesanan(
                    idProd = id_produk, idCust = idCust!!, tujuan = alamat!!, id_order = order_id!!, ctx = this
                )
                
                Log.d("Order ID", temp_id)

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            })
            .setMerchantBaseUrl("https://jaxid.site/TA/API/midtrans.php/") //set merchant url (required)
            .enableLog(true) // enable sdk log (optional)
            .setColorTheme(CustomColorTheme("#FFE51255", "#B61548", "#FFE51255"))
            .setLanguage("id")
            .buildSDK()


        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnBuy.setOnClickListener {
            order_id = idCust+ "-" + System.currentTimeMillis().toString()
            
            temp_id = order_id
            val transactionRequest = TransactionRequest(order_id, harga.toDouble())
            val detail = com.midtrans.sdk.corekit.models.ItemDetails(nama_produk, harga.toDouble(), 1, nama_produk)
            val itemDetails = ArrayList<com.midtrans.sdk.corekit.models.ItemDetails>()
            itemDetails.add(detail)
            uiKitDetails(transactionRequest)
            transactionRequest.itemDetails = itemDetails
            MidtransSDK.getInstance().transactionRequest = transactionRequest
            MidtransSDK.getInstance().startPaymentUiFlow(this)
        }
    }

    private fun uiKitDetails(transactionRequest: TransactionRequest) {

        val sharedPreference = SharedPreference(this)

        val id = sharedPreference.getPreferenceString("ID")
        val name = sharedPreference.getPreferenceString("nama")
        val email = sharedPreference.getPreferenceString("email")
        val username = sharedPreference.getPreferenceString("username")
        val hp = sharedPreference.getPreferenceString("No HP")
        val alamat = sharedPreference.getPreferenceString("alamat")


        val customerDetails = CustomerDetails()
        customerDetails.customerIdentifier = id
        customerDetails.phone = hp
//        customerDetails.firstName = "Supri"
//        customerDetails.lastName = "Yanto"
        customerDetails.email = email
        val shippingAddress = ShippingAddress()
//        shippingAddress.address = "Baturan, Gantiwarno"
//        shippingAddress.city = "Klaten"
//        shippingAddress.postalCode = "51193"
        customerDetails.shippingAddress = shippingAddress
        val billingAddress = BillingAddress()
        billingAddress.address  = alamat
//        billingAddress.city = "Klaten"
//        billingAddress.postalCode = "51193"
        customerDetails.billingAddress = billingAddress

        transactionRequest.customerDetails = customerDetails
    }
}
