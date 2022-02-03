package uz.umarxon.valyutalarkursipro.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.eftimoff.viewpagertransformers.BackgroundToForegroundTransformer
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.umarxon.retrofit.retrofit.Common
import uz.umarxon.retrofit.retrofit.RetrofitService
import uz.umarxon.valyutalarkursipro.Data.MyLiveData
import uz.umarxon.valyutalarkursipro.Utils.MyData.myLiveData
import uz.umarxon.valyutalarkursipro.adapter.RvAdapter2
import uz.umarxon.valyutalarkursipro.adapter.ViewPagerAdapter2
import uz.umarxon.valyutalarkursipro.databinding.FragmentMainBinding
import uz.umarxon.valyutalarkursipro.models.CurrencyModel

class MainFragment : Fragment() {

    lateinit var binding: FragmentMainBinding
    lateinit var retrofitService: RetrofitService
    lateinit var list: ArrayList<CurrencyModel>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater)

        retrofitService = Common.retrofitService(binding.root.context)

        binding.loadingView.start()
        binding.noWifi.visibility = View.GONE
        binding.titleTv.visibility = View.GONE
        binding.container.visibility = View.GONE

        myLiveData.get().observe(viewLifecycleOwner, Observer { it ->
            binding.rv.adapter = RvAdapter2(it)

            list = it as ArrayList<CurrencyModel>
            list.forEach {
                if (it.nbu_cell_price == "")
                    it.nbu_cell_price = it.cb_price
                if (it.nbu_buy_price == "")
                    it.nbu_buy_price = it.cb_price
            }

            binding.loadingView.stop()
            binding.loadingView.visibility = View.GONE
            binding.titleTv.visibility = View.VISIBLE
            binding.container.visibility = View.VISIBLE
            binding.viewPager.adapter = ViewPagerAdapter2(it, childFragmentManager)
            binding.viewPager.setPageTransformer(true, BackgroundToForegroundTransformer())
            binding.tabLayout.setupWithViewPager(binding.viewPager)
            binding.indicator.attachToPager(binding.viewPager)

            val tabLayout = binding.tabLayout

            setTabs(it)

            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    binding.viewPager.currentItem = tab.position
                }

                override fun onTabUnselected(tab: TabLayout.Tab) {

                }

                override fun onTabReselected(tab: TabLayout.Tab) {

                }
            })
        })

        return binding.root
    }

    private fun setTabs(list: List<CurrencyModel>) {

        for (i in list.indices) {
            val tab = binding.tabLayout.getTabAt(i)
            tab?.text = list[i].code
        }
    }

    fun hideLoader(){
        binding.loadingView.stop()
        binding.loadingView.visibility = View.GONE
    }

}