package uz.umarxon.valyutalarkursipro.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import uz.umarxon.valyutalarkursipro.ItemFragment
import uz.umarxon.valyutalarkursipro.models.CurrencyModel

class ViewPagerAdapter2(var list:List<CurrencyModel>,fragmentManager: FragmentManager) : FragmentPagerAdapter(
    fragmentManager,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
    override fun getCount(): Int = list.size

    override fun getItem(position: Int): Fragment {
        return ItemFragment.newInstance(list[position])
    }
}