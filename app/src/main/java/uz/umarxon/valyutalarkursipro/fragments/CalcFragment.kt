package uz.umarxon.valyutalarkursipro.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.umarxon.retrofit.retrofit.RetrofitService
import uz.umarxon.valyutalarkursipro.Data.MyLiveData
import uz.umarxon.valyutalarkursipro.R
import uz.umarxon.valyutalarkursipro.Utils.MyData
import uz.umarxon.valyutalarkursipro.Utils.MyData.myLiveData
import uz.umarxon.valyutalarkursipro.adapter.SpinnerAdapter
import uz.umarxon.valyutalarkursipro.databinding.FragmentCalcBinding
import uz.umarxon.valyutalarkursipro.models.CurrencyModel

class CalcFragment : Fragment() {

    lateinit var binding: FragmentCalcBinding
    lateinit var list: ArrayList<CurrencyModel>
    private val TAG = "CalcFragment"

    override fun onResume() {
        super.onResume()
        binding.spinner1.setSelection(MyData.pos)
        binding.edt1.setText("")
        binding.buyTxt.text = ""
        binding.sellTxt.text = ""
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_calc, container, false)

        myLiveData.get().observe(viewLifecycleOwner, Observer {it as ArrayList<CurrencyModel>
            binding.spinner1.adapter = SpinnerAdapter(it)
            binding.spinner2.adapter = SpinnerAdapter(it)
            list = it
        })

        binding.changeImage.setOnClickListener {
            val pos1 = binding.spinner1.selectedItemPosition
            val pos2 = binding.spinner2.selectedItemPosition

            binding.spinner1.setSelection(pos2)
            binding.spinner2.setSelection(pos1)
            calc()
        }

        binding.spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                calc()
            }

            override fun onNothingSelected(parent: AdapterView<out Adapter>?) {

            }
        }

        binding.spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                calc()
            }

            override fun onNothingSelected(parent: AdapterView<out Adapter>?) {

            }
        }

        binding.edt1.addTextChangedListener {
            calc()
        }

        return binding.root
    }


    fun calc(){
        if (binding.edt1.text.isNotEmpty()) {

            val somOlish = list[binding.spinner1.selectedItemPosition].nbu_buy_price.toDouble() * binding.edt1.text.toString().toDouble()
            val somSotish = list[binding.spinner1.selectedItemPosition].nbu_cell_price.toDouble() * binding.edt1.text.toString().toDouble()

            val sTv = (somSotish / list[binding.spinner2.selectedItemPosition].nbu_cell_price.toDouble())
            val oTv = (somOlish / list[binding.spinner2.selectedItemPosition].nbu_buy_price.toDouble())

            if (sTv.toString().contains('E') || oTv.toString().contains('E')) {
                binding.buyTxt.text = oTv.toInt().toString()
                binding.sellTxt.text = sTv.toInt().toString()
            } else {
                try {
                    binding.sellTxt.text = sTv.toString().subSequence(0, sTv.toString().indexOf(".") + 3).toString()
                    binding.buyTxt.text = oTv.toString().subSequence(0, oTv.toString().indexOf(".") + 3).toString()
                } catch (e: Exception) {
                    binding.sellTxt.text = sTv.toString()
                    binding.buyTxt.text = oTv.toString()
                }
            }

        }
    }


}