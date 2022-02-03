package uz.umarxon.valyutalarkursipro

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_item.view.*
import uz.umarxon.valyutalarkursipro.models.CurrencyModel
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


private const val ARG_PARAM1 = "param1"

class ItemFragment : Fragment() {

    private lateinit var currencyModel: CurrencyModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            currencyModel = it.getSerializable(ARG_PARAM1) as CurrencyModel
        }
    }

    lateinit var root:View
    @SuppressLint("SimpleDateFormat")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        root = inflater.inflate(R.layout.fragment_item, container, false)

        Picasso.get().load("https://raw.githubusercontent.com/hampusborgos/country-flags/main/png1000px/${currencyModel.code.subSequence(0,2).toString().lowercase(Locale.getDefault())}.png").into(root.flag)

        root.date.text = setDateParsing(currencyModel.date)
        root.buy_txt.text = if (currencyModel.nbu_buy_price==""){
            "Mavjud emas"
        }else{
            currencyModel.nbu_buy_price+" UZS"
        }
        root.sell_txt.text = if (currencyModel.nbu_buy_price==""){
            "Mavjud emas"
        }else{
            currencyModel.nbu_cell_price+" UZS"
        }
        return root
    }

    @Throws(ParseException::class)
    fun setDateParsing(date: String?): String? {

        // This is the format date we want
        val mSDF: DateFormat = SimpleDateFormat("dd.MM.yyyy")

        // This format date is actually present
        val formatter = SimpleDateFormat("dd.MM.yyyy hh:mm:ss")
        return mSDF.format(formatter.parse(date))
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: CurrencyModel) =
            ItemFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                }
            }
    }
}