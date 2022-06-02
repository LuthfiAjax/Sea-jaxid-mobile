package com.luthfi.seajaxid.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.luthfi.seajaxid.R
import com.luthfi.seajaxid.models.Pesanan
import kotlinx.android.synthetic.main.transaksi_item.view.*

class HistoryAdapter(private val listPesanan: ArrayList<Pesanan>) :
    RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HistoryAdapter.HistoryViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.transaksi_item, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryAdapter.HistoryViewHolder, position: Int) {
        val pesanan = listPesanan[position]

        Glide.with(holder.itemView.context)
            .load(pesanan.url_gambar)
            .apply(RequestOptions())
            .into(holder.imgProduk)

        holder.tvNamaProduk.text = pesanan.nama_produk
        holder.tvHarga.text = pesanan.harga
        holder.tvBerat.text = pesanan.berat
        holder.tvSatuan.text = pesanan.satuan
        holder.tvOngkir.text = pesanan.status_proses
        holder.tvStatusOrder.text = pesanan.id_order
        holder.tvStatusBayar.text = pesanan.status_bayar
    }

    override fun getItemCount(): Int {
        return listPesanan.size
    }

    inner class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mContext: Context = itemView.context
        var imgProduk: ImageView = itemView.imgProduk
        var tvNamaProduk: TextView = itemView.tvNamaProduk
        var tvHarga: TextView = itemView.tvHarga
        var tvBerat: TextView = itemView.tvBerat
        var tvSatuan: TextView = itemView.tvSatuan
        var tvOngkir: TextView = itemView.tvStatus
        var tvStatusOrder: TextView = itemView.tvStatusOrder
        var tvStatusBayar: TextView = itemView.tvStatusBayar
    }
}