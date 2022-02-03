package uz.umarxon.valyutalarkursipro.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import uz.umarxon.valyutalarkursipro.databinding.ItemRv2Binding
import uz.umarxon.valyutalarkursipro.databinding.ItemRvBinding
import uz.umarxon.valyutalarkursipro.models.CurrencyModel

class RvAdapter2(private val list: List<CurrencyModel>) :
    RecyclerView.Adapter<RvAdapter2.Vh>() {
    inner class Vh(var itemRv: ItemRv2Binding) : RecyclerView.ViewHolder(itemRv.root) {
        fun onBind(user: CurrencyModel, position: Int) {
            itemRv.buyTxt.text = user.nbu_buy_price
            itemRv.sellTxt.text = user.nbu_cell_price
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRv2Binding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size
}