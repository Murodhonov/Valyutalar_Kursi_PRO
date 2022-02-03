package uz.umarxon.valyutalarkursipro.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_item.view.*
import uz.umarxon.valyutalarkursipro.databinding.ItemRvBinding
import uz.umarxon.valyutalarkursipro.models.CurrencyModel
import java.util.*

class RvAdapter(private val list: List<CurrencyModel>,var click:Click) :
    RecyclerView.Adapter<RvAdapter.Vh>() {
    inner class Vh(var itemRv: ItemRvBinding) : RecyclerView.ViewHolder(itemRv.root) {
        fun onBind(user: CurrencyModel, position: Int) {
            itemRv.btnCalc.setOnClickListener {
                click.clicking(user,position)
            }
            Picasso.get().load("https://raw.githubusercontent.com/hampusborgos/country-flags/main/png1000px/${user.code.subSequence(0,2).toString().lowercase(Locale.getDefault())}.png").into(itemRv.flag)
            itemRv.code.text = user.code
            itemRv.buyTxt.text = if (user.nbu_buy_price==""){
                "Mavjud emas"
            }else{
                user.nbu_buy_price+" UZS"
            }
            itemRv.sellTxt.text = if (user.nbu_buy_price==""){
                "Mavjud emas"
            }else{
                user.nbu_cell_price+" UZS"
            }
        }
    }

    interface Click{
        fun clicking(user: CurrencyModel,position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size
}