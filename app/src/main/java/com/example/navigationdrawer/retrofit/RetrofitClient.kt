package com.example.navigationdrawer.retrofit





import com.example.navigationdrawer.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    const val base_url = "https://midasfurniture.com/rest/V1/"
    


    val retrofitClient: Retrofit.Builder by lazy {

        val levelType: Level
        if (BuildConfig.BUILD_TYPE.contentEquals("debug"))
            levelType = Level.BODY else levelType = Level.NONE

        val logging = HttpLoggingInterceptor()
        logging.setLevel(levelType)

        val okhttpClient = OkHttpClient.Builder()
        okhttpClient.addInterceptor(logging)

        okhttpClient.addInterceptor { chain ->
            val request: Request =
                chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer x7ezt0c88acxp6330sh220z3tuo2o5dk").build()
            chain.proceed(request)
        }



        Retrofit.Builder()
            .baseUrl(base_url)
            .client(okhttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
    }

    val apiInterface: ApiInterface by lazy {
        retrofitClient
            .build()
            .create(ApiInterface::class.java)
    }
}
