package uz.umarxon.valyutalarkursipro

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import com.eftimoff.viewpagertransformers.AccordionTransformer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.umarxon.retrofit.retrofit.Common
import uz.umarxon.retrofit.retrofit.RetrofitService
import uz.umarxon.valyutalarkursipro.Utils.MyData
import uz.umarxon.valyutalarkursipro.adapter.ViewPagerAdapter
import uz.umarxon.valyutalarkursipro.databinding.ActivityMainBinding
import uz.umarxon.valyutalarkursipro.fragments.CurrencyListFragment
import uz.umarxon.valyutalarkursipro.models.CurrencyModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var list: ArrayList<CurrencyModel>
    lateinit var retrofitService: RetrofitService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.searchBadge.setOnClickListener {
            binding.titleTv12.visibility = View.GONE
            binding.openMenu.visibility = View.GONE
            binding.searchEdt1.visibility = View.VISIBLE
            binding.close.visibility = View.VISIBLE

        }

        binding.close.setOnClickListener {
            binding.titleTv12.visibility = View.VISIBLE
            binding.openMenu.visibility = View.VISIBLE
            binding.searchEdt1.visibility = View.GONE
            binding.close.visibility = View.GONE
        }

        binding.searchEdt1.addTextChangedListener {
            MyData.myLiveData.set1(binding.searchEdt1.text.toString())
        }

        retrofitService = Common.retrofitService(binding.root.context)

        downloadList()

        binding.viewPager.adapter = ViewPagerAdapter(supportFragmentManager)

        binding.viewPager.setPageTransformer(false, AccordionTransformer())

        binding.openMenu.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }

        binding.searchBadge.visibility = View.GONE

        binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                changePos(position)
                when (position) {
                    0 -> {
                        binding.searchBadge.visibility = View.GONE
                        binding.searchEdt1.visibility = View.GONE
                        binding.close.visibility = View.GONE
                        binding.titleTv12.visibility = View.VISIBLE
                        binding.openMenu.visibility = View.VISIBLE
                    }
                    1 -> {
                        binding.searchBadge.visibility = View.VISIBLE
                    }
                    2 -> {
                        binding.searchBadge.visibility = View.GONE
                        binding.searchEdt1.visibility = View.GONE
                        binding.close.visibility = View.GONE
                        binding.titleTv12.visibility = View.VISIBLE
                        binding.openMenu.visibility = View.VISIBLE
                    }
                }
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

        binding.bottomMenu.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    binding.viewPager.currentItem = 0
                }
                R.id.all -> {
                    binding.viewPager.currentItem = 1
                }
                R.id.calc -> {
                    binding.viewPager.currentItem = 2
                }
            }
            true
        }

    }

    fun changePos(pos: Int) {
        when (pos) {
            0 -> {
                binding.bottomMenu.selectedItemId = R.id.home
            }
            1 -> {
                binding.bottomMenu.selectedItemId = R.id.all
            }
            2 -> {
                binding.bottomMenu.selectedItemId = R.id.calc
            }
        }
    }

    private fun downloadList() {

        retrofitService.getUsers().enqueue(object : Callback<List<CurrencyModel>> {
            override fun onResponse(
                call: Call<List<CurrencyModel>>,
                response: Response<List<CurrencyModel>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    list = ArrayList()
                    list.addAll(response.body()!!)
                    MyData.myLiveData.set(list)
                    Log.d("Murodhonov", "$list")
                }
            }

            override fun onFailure(call: Call<List<CurrencyModel>>, t: Throwable) {
                Log.d("Murodhonov", "Error ${t.message}")
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}