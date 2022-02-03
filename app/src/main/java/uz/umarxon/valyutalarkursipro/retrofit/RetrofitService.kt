package uz.umarxon.retrofit.retrofit

import retrofit2.Call
import retrofit2.http.GET
import uz.umarxon.valyutalarkursipro.models.CurrencyModel

interface RetrofitService {

    @GET("json")
    fun getUsers():Call<List<CurrencyModel>>


}