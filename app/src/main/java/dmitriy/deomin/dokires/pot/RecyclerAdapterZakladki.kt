package dmitriy.deomin.dokires.pot
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

class RecyclerAdapterZakladki(private var items: ArrayList<Map<String, String>>): RecyclerView.Adapter<RecyclerAdapterZakladki.ViewHolder>() {


    class ViewHolder(row: View) : RecyclerView.ViewHolder(row) {
        lateinit var txtName: Button
        init {
            this.txtName = row.findViewById<Button>(R.id.item_zakladki)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.recycler_item_zakladki, parent, false)
        return ViewHolder(itemView)
    }


    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //ставим название закладки на кнопку
        holder.txtName.text = items[position]["name"]

        //при клике будем загружать текст в книгу
        holder.txtName.onClick {
          //играем анимацию
          val anim = AnimationUtils.loadAnimation(Main.con_v_palto, R.anim.myalpha)
          holder.txtName.startAnimation(anim)

            //заполняем чушью
            PageBook.add_tetx(items[position]["name"].toString())

        }

        //при долгом клике будем предлогать удалить закладку
        holder.txtName.onLongClick(returnValue = true){




        }
    }
}