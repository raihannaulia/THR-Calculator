package org.d3if3036.assesment2mopro.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "thr")
data class ThrEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var tanggal: Long = System.currentTimeMillis(),
    var gaji: Float,
    var tunjangan: Float,

)