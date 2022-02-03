package uz.umarxon.valyutalarkursipro.fragments

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.eftimoff.viewpagertransformers.DrawFromBackTransformer
import uz.umarxon.valyutalarkursipro.R
import uz.umarxon.valyutalarkursipro.adapter.ViewPagerAdapter
import uz.umarxon.valyutalarkursipro.databinding.FragmentHomeBinding
import android.view.MotionEvent

import android.view.View.OnTouchListener




class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        binding.viewPager.adapter = ViewPagerAdapter(parentFragmentManager)

        binding.viewPager.setPageTransformer(true, DrawFromBackTransformer())

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
                    }
                    1 -> {
                        binding.searchBadge.visibility = View.VISIBLE
                    }
                    2 -> {
                        binding.searchBadge.visibility = View.GONE
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



        return binding.root
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
}
