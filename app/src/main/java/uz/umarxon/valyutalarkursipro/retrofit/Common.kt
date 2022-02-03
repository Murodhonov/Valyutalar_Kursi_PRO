package uz.umarxon.retrofit.retrofit

import android.content.Context

object Common {
    val BASE_URL = "https://nbu.uz/uz/exchange-rates/"

    fun retrofitService(context: Context):RetrofitService{
        return RetrofitClient.getRetrofit(BASE_URL,context).create(RetrofitService::class.java)
    }
}