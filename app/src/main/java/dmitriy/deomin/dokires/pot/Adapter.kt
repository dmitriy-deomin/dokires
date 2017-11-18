package dmitriy.deomin.dokires.pot

/**
 * Created by dimon on 06.11.2017.
 */
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter


class Adapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return PageOglavlenie()
            1 -> return PageBook()
            2 -> return PageKontent()
        }
        return null
    }

    override fun getCount(): Int {
        return 3
    }
}

