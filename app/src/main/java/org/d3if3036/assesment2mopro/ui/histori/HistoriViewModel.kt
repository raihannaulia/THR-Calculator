package org.d3if3036.assesment2mopro.ui.histori

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if3036.assesment2mopro.db.ThrDao


class HistoriViewModel(private val db: ThrDao) : ViewModel() {
    val data = db.getLastThr()

    fun hapusData() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            db.clearData()
        }
    }
}