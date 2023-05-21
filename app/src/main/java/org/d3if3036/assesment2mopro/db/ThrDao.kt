package org.d3if3036.assesment2mopro.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ThrDao {

    @Insert
    fun insert(thr: ThrEntity)

    @Query("SELECT * FROM thr ORDER BY id DESC")
    fun getLastThr(): LiveData<List<ThrEntity?>>

    @Query("DELETE FROM thr")
    fun clearData()


}