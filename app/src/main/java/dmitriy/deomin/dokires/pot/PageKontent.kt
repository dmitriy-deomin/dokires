package dmitriy.deomin.dokires.pot


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import dmitriy.deomin.dokires.R

//тут будут сокращения и таблицы


class PageKontent : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(R.layout.kontent, null)

        return v
    }


}
