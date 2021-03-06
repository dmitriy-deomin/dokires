package dmitriy.deomin.dokires.pot

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dmitriy.deomin.dokires.R
import android.support.v7.widget.RecyclerView
import dmitriy.deomin.dokires.Main
import java.io.File
import java.util.*

class PageOglavlenie : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v: View = inflater.inflate(R.layout.oglavlenie_pot, null)

        val recyclerView: RecyclerView= v.findViewById(R.id.my_recycler_view)

        val adapter = RecyclerAdapterOglavlenia(generateData(v.context.assets.list("pot_book")))
        val layoutManager = LinearLayoutManager(v.context)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()

        recyclerView.adapter = adapter

        return v
    }

   private fun generateData(files: Array<String>): ArrayList<Map<String,String>> {

        val result = ArrayList<Map<String,String>>()
        var stroka:Map<String,String>

        for (i in 0 until files.size) {
            stroka = HashMap<String,String>(files.size)
            stroka.put("color_text",Main.COLOR_TEXT.toString() )
            stroka.put("glava", File(files.get(i)).name)
            stroka.put("text_glavi", files[i])
            result.add(stroka)
        }

        return result
    }

}
