package dmitriy.deomin.dokires.pot

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import dmitriy.deomin.dokires.R
import org.jetbrains.anko.sdk25.coroutines.onClick


class RecyclerAdapter(private var items: ArrayList<Map<String, String>>): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {


    class ViewHolder(row: View) : RecyclerView.ViewHolder(row) {
        var txtName: Button? = null

        init {
            this.txtName = row?.findViewById<Button>(R.id.item_glava)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.recycler_item, parent, false)
        return ViewHolder(itemView)
    }


    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.txtName?.text = items[position]["glava"]!!.replace(".txt","")
        holder?.txtName?.onClick {

            //заполняем чушью
            //обрежем заголовок если длинный
            var t = items[position]["glava"]!!.replace(".txt","")
            if(t.length>36){
                t= t.substring(0,34)+"..."
            }
            Book_pot.title!!.text = t

            Book_pot.book!!.text = items[position]["text_glavi"]

            //перематываем к началу
            Book_pot.skroll_book!!.scrollTo(0,0)


            //переходим на страницу
            Pot.viewpager?.setCurrentItem(1)
        }
    }
}