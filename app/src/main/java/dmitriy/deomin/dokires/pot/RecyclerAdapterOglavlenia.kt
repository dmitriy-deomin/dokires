package dmitriy.deomin.dokires.pot

import android.graphics.Color
import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.LinearLayout
import dmitriy.deomin.dokires.Main
import dmitriy.deomin.dokires.R
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.sdk25.coroutines.onLongClick
import org.jetbrains.anko.textColor


class RecyclerAdapterOglavlenia(private var items: ArrayList<Map<String, String>>): RecyclerView.Adapter<RecyclerAdapterOglavlenia.ViewHolder>() {


    class ViewHolder(row: View) : RecyclerView.ViewHolder(row) {
        lateinit var txtName: Button
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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        //установим фон как везде
        holder.txtName.backgroundColor = Main.COLOR_FON

        //посиотрим если есть отмеченые кнопки окрасим из
        if(Main.read_str(items[position]["glava"].toString())==items[position]["glava"].toString()){
            holder.txtName.textColor =Color.RED
        }else{
            holder.txtName.textColor =items[position]["color_text"]!!.toInt()
        }

        //посмотрим если эта книга открыта текст кнопки сделаем жирным
        if(Main.read_str("old_text_book_pot")==items[position]["glava"].toString()){
            holder.txtName.typeface = Typeface.DEFAULT_BOLD
        }else{
            //иначе сбросим
            holder.txtName.typeface = Typeface.DEFAULT
        }

        //ставим название главы на кнопку
        holder.txtName.text = items[position]["glava"]!!.replace(".html","")

        //при клике будем загружать текст и перехадить на др вклвдку
        holder.txtName.onClick {

                //играем анимацию
                val anim = AnimationUtils.loadAnimation(Main.con, R.anim.myalpha)
                holder.txtName.startAnimation(anim)

                //делаем текст кнопки жирным
                holder.txtName.typeface = Typeface.DEFAULT_BOLD


                //проверим если эта книга уже открыта то просто крутнём вьюпейджер
                if(Main.read_str("old_text_book_pot")==items[position]["text_glavi"].toString()){
                    //переходим на страницу
                    Pot.viewpager.currentItem = 1
                }else{
                //сбрасываем сохраненую позицию
                 Main.save_str("old_skrol_book_pot", "")

                //заполняем чушью
                PageBook.add_tetx(items[position]["text_glavi"].toString())

                //сохраняем текст , при следующем открытии книги будет открыатся сразу
                Main.save_str("old_text_book_pot", items[position]["text_glavi"].toString())

                //переходим на страницу
                Pot.viewpager.currentItem = 1
                }
        }

        //при долгом клике будем рисовать текст кнопки ярким
        holder.txtName.onLongClick(returnValue = true){
            //посмотрим если она отмечена, сбросим
            if (Main.read_str(items[position]["glava"].toString()) == items[position]["glava"].toString()) {
                holder.txtName.textColor = items[position]["color_text"]!!.toInt()
                //сохраним
                Main.save_str(items[position]["glava"].toString(), "")
            } else {
                //иначече окрасим
                holder.txtName.textColor = Color.RED
                //сохраним
                Main.save_str(items[position]["glava"].toString(), items[position]["glava"].toString())
            }
        }
    }
}