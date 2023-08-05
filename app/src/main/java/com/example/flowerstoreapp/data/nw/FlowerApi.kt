package com.example.flowerstoreapp.data.nw

import com.example.flowerstoreapp.domain.models.Bouquets
import com.example.flowerstoreapp.domain.models.Flower
import com.example.flowerstoreapp.domain.models.SingleBouquet
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "http://192.168.1.184:5211/"

interface FlowerApi {
    companion object {
        private val logger: HttpLoggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        private val okHttp =
            OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(logger)
                .build()

        fun createApiService(): FlowerApi {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttp)
                .build()
            return retrofit.create(FlowerApi::class.java)
        }
    }

    @GET("api/Flowers")// http://192.168.1.184:5211/api/Flowers
    suspend fun getFlowers(): Response<List<Flower>>

    @GET("api/Bouquets")
    suspend fun getBouquets(): Response<List<Bouquets>>

    @GET("api/Bouquets/GetByFlower/{flowerId}")
    suspend fun getBouquetByFlower(@Path("flowerId") flowerId: Int): Response<List<Bouquets>>

    @GET("api/Bouquets/{bouquetId}")
    suspend fun getBouquet(@Path("bouquetId") bouquetId: Int): Response<SingleBouquet>
}
