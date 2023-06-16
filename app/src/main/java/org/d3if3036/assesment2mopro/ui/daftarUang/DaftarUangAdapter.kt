package org.d3if3036.assesment2mopro.ui.daftarUang

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.d3if3036.assesment2mopro.R
import org.d3if3036.assesment2mopro.databinding.ListItemBinding
import org.d3if3036.assesment2mopro.network.UangApi

class DaftarUangAdapter : RecyclerView.Adapter<DaftarUangAdapter.ViewHolder>() {

    private val data = mutableListOf<Uang>()

    fun updateData(newData: List<Uang>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: ListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(uang: Uang) = with(binding) {
            namaTextView.text = uang.nama
            Glide.with(imageView.context)
                .load(UangApi.getUangUrl(uang.gambar))
                .error(R.drawable.ic_baseline_broken_image_24)
                .into(imageView)


            root.setOnClickListener {
                val message = root.context.getString(R.string.message, uang.nama)
                Toast.makeText(root.context, message, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }
}