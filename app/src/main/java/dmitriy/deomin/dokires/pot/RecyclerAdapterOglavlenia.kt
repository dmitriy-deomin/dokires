package dmitriy.deomin.dokires.pot

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
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


        //посиотрим если есть отмеченые кнопки окрасим из
        if(Main.read_str(items[position]["glava"].toString())==items[position]["glava"].toString()){
            holder?.txtName?.textColor =Color.RED
        }else{
            holder?.txtName?.textColor =items[position]["color_text"]!!.toInt()
        }

        holder?.txtName?.text = items[position]["glava"]!!.replace(".html","")

        holder?.txtName?.onClick {
            //играем анимаию
            val anim = AnimationUtils.loadAnimation(Main.con_v_palto, R.anim.myalpha)
            holder?.txtName?.startAnimation(anim)
            //заполняем чушью
            PageBook.add_tetx(items[position]["text_glavi"].toString())
            //перематываем к началу
            PageBook.skroll_book!!.scrollTo(0,0)
            //переходим на страницу
            Pot.viewpager?.currentItem = 1
        }

        //при долгом клике будем рисовать текст кнопки ярким

        holder?.txtName?.onLongClick{
            //посиотрим если она отмечена сбросим
            if (Main.read_str(items[position]["glava"].toString()) == items[position]["glava"].toString()) {
                holder?.txtName?.textColor = items[position]["color_text"]!!.toInt()
                Main.save_str(items[position]["glava"].toString(), "")
            } else {
                //иначече окрасим
                holder?.txtName?.textColor = Color.RED
                Main.save_str(items[position]["glava"].toString(), items[position]["glava"].toString())
            }
        }
    }
}