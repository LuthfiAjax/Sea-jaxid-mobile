package com.luthfi.seajaxid.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.luthfi.seajaxid.R
import com.luthfi.seajaxid.models.Produk
import com.luthfi.seajaxid.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.item_produk.view.*

class MainAdapter(private val listProduk: ArrayList<Produk>) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.MainViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_produk, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainAdapter.MainViewHolder, position: Int) {
        val produk = listProduk[position]

        Glide.with(holder.itemView.context)
            .load(produk.url_gambar)
            .apply(RequestOptions())
            .into(holder.imgProduk)

        holder.tvNamaProduk.text = produk.nama_produk
        holder.tvHarga.text = produk.harga
        holder.tvBerat.text = produk.berat
        holder.tvSatuan.text = produk.satuan
        holder.tvOngkir.text = produk.ongkir

        holder.itemView.setOnClickListener {
            val moveWithObjectIntent = Intent(holder.mContext, DetailActivity::class.java)

            moveWithObjectIntent.putExtra(DetailActivity.DATA_PRODUK, produk)
            holder.mContext.startActivity(moveWithObjectIntent)
        }

    }

    override fun getItemCount(): Int {
        return listProduk.size
    }

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mContext: Context = itemView.context
        var imgProduk: ImageView = itemView.imgProduk
        var tvNamaProduk: TextView = itemView.tvNamaProduk
        var tvHarga: TextView = itemView.tvHarga
        var tvBerat: TextView = itemView.tvBerat
        var tvSatuan: TextView = itemView.tvSatuan
        var tvOngkir: TextView = itemView.tvOngkir
    }
}