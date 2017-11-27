package dmitriy.deomin.dokires.pot


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import dmitriy.deomin.dokires.Main

import dmitriy.deomin.dokires.R
import org.jetbrains.anko.backgroundColor

//тут будут сокращения и таблицы


class PageKontent : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(R.layout.kontent, null)

        //установим цвет как фона как везде
        v.findViewById<LinearLayout>(R.id.fon_kontent).backgroundColor = Main.COLOR_FON

        return v
    }


}
