package org.d3if3036.assesment2mopro.ui.hitung

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if3036.assesment2mopro.db.ThrDao
import org.d3if3036.assesment2mopro.db.ThrEntity
import org.d3if3036.assesment2mopro.model.HasilThr
import org.d3if3036.assesment2mopro.model.KategoriThr
import org.d3if3036.assesment2mopro.model.hitungThr

class HitungViewModel(private val db: ThrDao) : ViewModel() {

    private val hasilThr = MutableLiveData<HasilThr?>()
    private val navigasi = MutableLiveData<KategoriThr?>()

    fun hitungThr(gaji: Float, tunjangan: Float,lamaKerja: Float, pangkat: Boolean ) {
        val dataThr = ThrEntity(
            gaji = gaji,
            tunjangan = tunjangan,
            lamaKerja = lamaKerja,
            pangkat = pangkat

        )
        hasilThr.value = dataThr.hitungThr()

        viewModelScope.launch {
            withContext(Dispatchers.IO) {

                db.insert(dataThr)
            }
        }
    }

    fun getHasilThr(): LiveData<HasilThr?> = hasilThr

    fun mulaiNavigasi() {
        navigasi.value = hasilThr.value?.status
    }

    fun selesaiNavigasi() {
        navigasi.value = null
    }

    fun getNavigasi() : LiveData<KategoriThr?> = navigasi
}