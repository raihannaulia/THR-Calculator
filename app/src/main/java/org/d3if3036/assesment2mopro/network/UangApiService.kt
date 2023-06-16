package org.d3if3036.assesment2mopro.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if3036.assesment2mopro.ui.daftarUang.Uang
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/raihannaulia/json-THR-calculator/main/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface UangApiService {
    @GET("uang.json")
    suspend fun getUang():List<Uang>
}

object UangApi {
    val service:UangApiService by lazy {
        retrofit.create(UangApiService::class.java)
    }

    fun getUangUrl(gambar: String): String {
        return "$BASE_URL$gambar.jpg"
    }
}

enum class ApiStatus { LOADING, SUCCESS, FAILED}