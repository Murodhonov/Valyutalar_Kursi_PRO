package uz.umarxon.valyutalarkursipro.models

import java.io.Serializable

data class CurrencyModel(
    val cb_price: String,
    val code: String,
    val date: String,
    var nbu_buy_price: String,
    var nbu_cell_price: String,
    val title: String
):Serializable