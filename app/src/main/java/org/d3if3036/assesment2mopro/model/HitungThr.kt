package org.d3if3036.assesment2mopro.model

import org.d3if3036.assesment2mopro.db.ThrEntity

fun ThrEntity.hitungThr(): HasilThr {
    val thr = if (lamaKerja >= 12) 1 * (gaji + tunjangan) else (lamaKerja / 12) * (gaji + tunjangan)
    val status = if (lamaKerja >= 12) KategoriThr.SENIOR else KategoriThr.JUNIOR

    return HasilThr(thr, status)
}