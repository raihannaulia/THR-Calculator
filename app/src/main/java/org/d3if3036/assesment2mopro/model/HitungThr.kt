package org.d3if3036.assesment2mopro.model

import org.d3if3036.assesment2mopro.db.ThrEntity

fun ThrEntity.hitungThr(): HasilThr {
    val status = Int
    val thr = status / (gaji + tunjangan)
    val kategori = if (isSenior) {
        when {
            status = senior ->KategoriThr.SENIOR
        }
    }


    return HasilThr(thr)
}