package uz.umarxon.valyutalarkursipro.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_item.view.*
import kotlinx.android.synthetic.main.spinner_item.view.*
import uz.umarxon.valyutalarkursipro.R
import uz.umarxon.valyutalarkursipro.models.CurrencyModel
import java.util.*
import kotlin.collections.ArrayList

class SpinnerAdapter(private val list: ArrayList<CurrencyModel>) : BaseAdapter() {
    override fun getCount(): Int = list.size

    override fun getItem(p0: Int): Any = list[p0]

    override fun getItemId(p0: Int): Long = p0.toLong()

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        val itemView: View = p1 ?: LayoutInflater.from(p2?.context).inflate(R.layout.spinner_item, p2, false)

        itemView.text_valuta.text = list[p0].code

        Picasso.get().load("https://raw.githubusercontent.com/hampusborgos/country-flags/main/png1000px/${list[p0].code.subSequence(0,2).toString().lowercase(
            Locale.getDefault())}.png").into(itemView.image_valuta1)

        return itemView
    }

}