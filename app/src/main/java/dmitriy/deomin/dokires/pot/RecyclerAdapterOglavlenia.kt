package dmitriy.deomin.dokires.pot

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.content.res.ResourcesCompat.getColor
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import dmitriy.deomin.dokires.Main
import dmitriy.deomin.dokires.R
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.sdk25.coroutines.onLongClick
import org.jetbrains.anko.textColor


class RecyclerAdapterOglavlenia(private var items: ArrayList<Map<String, String>>): RecyclerView.Adapter<RecyclerAdapterOglavlenia.ViewHolder>() {


    class ViewHolder(row: View) : RecyclerView.ViewHolder(row) {
        var txtName: Button? = null
        init {
            this.txtName = row.findViewById<Button>(R.id.item_glava)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.recycler_item_oglavlenia, parent, false)
        return ViewHolder(itemView)
    }


    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.txtName?.textColor = items[position]["color_text"]!!.toInt()
        holder?.txtName?.text = items[position]["glava"]!!.replace(".html","")

        holder?.txtName?.onClick {

            //заполняем чушью
            PageBook.add_tetx(items[position]["text_glavi"].toString())

            //перематываем к началу
            PageBook.skroll_book!!.scrollTo(0,0)

            //переходим на страницу
            Pot.viewpager?.currentItem = 1
        }
    }
}