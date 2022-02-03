package uz.umarxon.valyutalarkursipro.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import uz.umarxon.valyutalarkursipro.fragments.CalcFragment
import uz.umarxon.valyutalarkursipro.fragments.CurrencyListFragment
import uz.umarxon.valyutalarkursipro.fragments.MainFragment

class ViewPagerAdapter(fragmentManager: FragmentManager): FragmentStatePagerAdapter(fragmentManager,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getCount(): Int = 3

    override fun getItem(position: Int): Fragment {
        return when(position){
            0->{
                MainFragment()
            }
            1->{
                CurrencyListFragment()
            }
            else->{
                CalcFragment()
            }
        }
    }
}