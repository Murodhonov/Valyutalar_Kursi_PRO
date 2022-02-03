package uz.umarxon.valyutalarkursipro.Data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.umarxon.valyutalarkursipro.models.CurrencyModel

class MyLiveData {
    private var liveDouble = MutableLiveData<List<CurrencyModel>>()

    private var searchText = MutableLiveData<String>()

    fun set1(value:String){
        searchText.value = value
    }

    fun get1():LiveData<String>{
        return searchText
    }

    fun set(value:List<CurrencyModel>){
        liveDouble.value = value
    }

    fun get():LiveData<List<CurrencyModel>>{
        return liveDouble
    }
}