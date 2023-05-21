package org.d3if3036.assesment2mopro.ui.histori

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.*
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.d3if3036.assesment2mopro.R
import org.d3if3036.assesment2mopro.databinding.ItemHistoriBinding
import org.d3if3036.assesment2mopro.db.ThrEntity
import org.d3if3036.assesment2mopro.model.KategoriThr
import org.d3if3036.assesment2mopro.model.hitungThr
import java.text.SimpleDateFormat
import java.util.*

class HistoriAdapter : ListAdapter<ThrEntity, HistoriAdapter.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<ThrEntity>() {
                override fun areItemsTheSame(
                    oldData: ThrEntity, newData: ThrEntity
                ): Boolean {
                    return oldData.id == newData.id
                }
                override fun areContentsTheSame(
                    oldData: ThrEntity, newData: ThrEntity
                ): Boolean {
                    return oldData == newData
                }
            }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoriBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemHistoriBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val dateFormatter = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))

        fun bind(item: ThrEntity) = with(binding) {
            val hasilThr = item.hitungThr()
            kategoriTextView.text = hasilThr.kategori.toString().substring(0, 1)
            val colorRes = when(hasilThr.kategori) {
                KategoriThr.SENIOR -> R.color.senior
                KategoriThr.JUNIOR -> R.color.junior
            }

            val circleBg = kategoriTextView.background as GradientDrawable
            circleBg.setColor(getColor(root.context, colorRes))
            tanggalTextView.text = dateFormatter.format(Date(item.tanggal))
            bmiTextView.text = root.context.getString(R.string.hasil_x,
                hasilThr.thr, hasilThr.kategori.toString())
            val gender = root.context.getString(
                if (item.isMale) R.string.pria else R.string.wanita)
            dataTextView.text = root.context.getString(R.string.data_x,
                item.berat, item.tinggi, gender)
        }
    }
}