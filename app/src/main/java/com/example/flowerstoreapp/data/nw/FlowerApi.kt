package com.example.flowerstoreapp.data.nw

import com.example.flowerstoreapp.data.models.Message
import com.example.flowerstoreapp.domain.models.Bouquets
import com.example.flowerstoreapp.domain.models.CreateOrder
import com.example.flowerstoreapp.domain.models.Flower
import com.example.flowerstoreapp.domain.models.Orders
import com.example.flowerstoreapp.domain.models.SingleBouquet
import com.example.flowerstoreapp.domain.models.UserInfo
import com.example.flowerstoreapp.domain.models.UserRegistration
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "http://192.168.1.184:5211/"

interface FlowerApi {
    companion object {
        private val logger: HttpLoggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        private val okHttp =
            OkHttpClient.Builder()
                .connectTimeout(6, TimeUnit.SECONDS)
                .readTimeout(6, TimeUnit.SECONDS)
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

    @GET("api/Users/{userId}/Orders")
    suspend fun getUserOrders(@Path("userId") userId: Int): Response<List<Orders>>

    @POST("api/users/login?")
    suspend fun login(
        @Query("username") username: String,
        @Query("password") password: String
    ): Response<UserInfo>

    @POST("api/users/register")
    suspend fun register(
        @Body userData: UserRegistration
    ): Response<Message>

    @POST("api/Orders")
    suspend fun createOrder(@Body createOrder: CreateOrder): Response<Message>


}
