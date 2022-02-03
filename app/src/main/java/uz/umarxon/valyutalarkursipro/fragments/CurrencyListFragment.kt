package uz.umarxon.valyutalarkursipro.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import uz.umarxon.retrofit.retrofit.Common
import uz.umarxon.retrofit.retrofit.RetrofitService
import uz.umarxon.valyutalarkursipro.MainActivity
import uz.umarxon.valyutalarkursipro.R
import uz.umarxon.valyutalarkursipro.Utils.MyData
import uz.umarxon.valyutalarkursipro.Utils.MyData.myLiveData
import uz.umarxon.valyutalarkursipro.adapter.RvAdapter
import uz.umarxon.valyutalarkursipro.adapter.SpinnerAdapter
import uz.umarxon.valyutalarkursipro.databinding.FragmentCalcBinding
import uz.umarxon.valyutalarkursipro.databinding.FragmentCurrencyListBinding
import uz.umarxon.valyutalarkursipro.models.CurrencyModel
import java.util.*
import kotlin.collections.ArrayList


class CurrencyListFragment : Fragment() {

    lateinit var binding: FragmentCurrencyListBinding
    lateinit var retrofitService: RetrofitService
    lateinit var list: ArrayList<CurrencyModel>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCurrencyListBinding.inflate(layoutInflater)

        binding.loadingView.start()
        retrofitService = Common.retrofitService(binding.root.context)
        binding.noWifi.visibility = View.GONE

        myLiveData.get().observe(viewLifecycleOwner, Observer {
            list = it as ArrayList<CurrencyModel>
            binding.rv.adapter = RvAdapter(it, object : RvAdapter.Click {
                override fun clicking(user: CurrencyModel, position: Int) {
                    MyData.pos = position
                    (activity as MainActivity?)?.changePos(2)

                }
            })
            binding.loadingView.stop()
            binding.loadingView.visibility = View.GONE
        })

        search()

        return binding.root
    }

    fun calculationF(fragmentCCalculatorBinding:FragmentCalcBinding, list2:ArrayList<CurrencyModel>){
        if (fragmentCCalculatorBinding.edt1.text.isNotEmpty()) {
            val somOlish = list2[fragmentCCalculatorBinding.spinner1.selectedItemPosition].nbu_buy_price.toDouble() * fragmentCCalculatorBinding.edt1.text.toString().toDouble()
            val somSotish = list2[fragmentCCalculatorBinding.spinner1.selectedItemPosition].nbu_cell_price.toDouble() * fragmentCCalculatorBinding.edt1.text.toString().toDouble()

            val sTv = (somSotish / list2[fragmentCCalculatorBinding.spinner2.selectedItemPosition].nbu_cell_price.toDouble())
            val oTv = (somOlish / list2[fragmentCCalculatorBinding.spinner2.selectedItemPosition].nbu_buy_price.toDouble())

            if (sTv.toString().contains('E') || oTv.toString().contains('E')) {
                fragmentCCalculatorBinding.buyTxt.text = oTv.toInt().toString()
                fragmentCCalculatorBinding.sellTxt.text = sTv.toInt().toString()
            } else {
                try {
                    fragmentCCalculatorBinding.sellTxt.text = sTv.toString().subSequence(0, sTv.toString().indexOf(".") + 3).toString()
                    fragmentCCalculatorBinding.buyTxt.text = oTv.toString().subSequence(0, oTv.toString().indexOf(".") + 3).toString()
                } catch (e: Exception) {
                    fragmentCCalculatorBinding.sellTxt.text = sTv.toString()
                    fragmentCCalculatorBinding.buyTxt.text = oTv.toString()
                }
            }
        }
    }

    fun search(){
        myLiveData.get1().observe(viewLifecycleOwner, Observer {
            val sortList = ArrayList<CurrencyModel>()

            for (i in list){
                if (i.code.toString().lowercase(Locale.getDefault()).startsWith(it.lowercase(Locale.getDefault()))){
                    sortList.add(i)
                }
            }

            binding.rv.adapter = RvAdapter(sortList,object :RvAdapter.Click{
                override fun clicking(user: CurrencyModel, position: Int) {
                    MyData.pos = position
                    (activity as MainActivity?)?.changePos(2)
                }
            })
        })
    }
}