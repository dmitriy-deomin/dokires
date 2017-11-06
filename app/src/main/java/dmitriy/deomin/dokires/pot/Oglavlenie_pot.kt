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
import java.io.File

/**
 * Created by dimon on 06.11.2017.
 */

class Oglavlenie_pot : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v: View = inflater.inflate(R.layout.oglavlenie_pot, null)

        val recyclerView: RecyclerView= v.findViewById(R.id.my_recycler_view)

        val adapter = RecyclerAdapter(generateData(v.context.assets.list("pot_book")))
        val layoutManager = LinearLayoutManager(v.context)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()

        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()

        return v
    }

   private fun generateData(files: Array<String>): ArrayList<Map<String,String>> {

        val result = ArrayList<Map<String,String>>()
        var stroka:Map<String,String>

        for (i in 0..files.size-1) {
            stroka = HashMap<String,String>(files.size)
            stroka.put("glava", File(files.get(i)).name)
            stroka.put("text_glavi", context!!.assets.open("pot_book/"+files.get(i)).reader().readText())
            result.add(stroka)
        }

        return result
    }

}
