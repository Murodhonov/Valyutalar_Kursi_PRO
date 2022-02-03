package uz.umarxon.retrofit.retrofit

import android.content.Context
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    fun getRetrofit(baseUrl:String,context: Context): Retrofit {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()

        return retrofit
    }
}