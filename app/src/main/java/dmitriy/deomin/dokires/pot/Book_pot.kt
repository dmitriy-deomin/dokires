package dmitriy.deomin.dokires.pot
import android.support.v4.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.ScrollView
import android.widget.TextView
import dmitriy.deomin.dokires.Main
import dmitriy.deomin.dokires.R
import kotlinx.android.synthetic.main.book_pot.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import uk.co.deanwild.flowtextview.FlowTextView

class Book_pot:Fragment(){

    companion object {
        var skroll_book:ScrollView? =null
        var book:FlowTextView? = null
        var title: TextView? = null

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v: View = inflater!!.inflate(R.layout.book_pot, null)

        skroll_book = v.findViewById(R.id.skroll_book_pot)
        title = v.findViewById(R.id.title_book)
        book = v.findViewById(R.id.book)





        //Выключим меню
        v.book_menu.visibility = View.GONE

        //установим размер шрифта из памяти
        val save_size = Main.read_str("book_font_size")
        if(save_size.length>1){
            v.book.setTextSize(save_size.toFloat())
        }

        //установим текст и прокрутку из памяти
        book?.text = Main.read_str("old_text_book_pot")
        title?.text = Main.read_str("old_title_book_pot")


        //проматаем
        val s = Main.read_str("old_skrol_book_pot")
        if(s != ""){
           // skroll_book!!.scrollTo(0,s.toInt())
        }


        v.menu_on_of.onClick {
            if(v.book_menu.visibility==View.GONE){
                v.book_menu.visibility = View.VISIBLE
            }else{v.book_menu.visibility = View.GONE}
        }

        v.big_text.onClick {
            val size = v.book.textsize
            v.book.setTextSize(size+1)
            Main.save_str("book_font_size",v.book.textsize.toString())
        }

        v.smoll_text.onClick {
            val size = v.book.textsize
            v.book.setTextSize(size-1)
            Main.save_str("book_font_size",v.book.textsize.toString())
        }
        return v
    }


    override fun onDestroyView() {
        //сохраняем текст
        Main.save_str("old_text_book_pot", book!!.text as String)
        Main.save_str("old_title_book_pot", title!!.text as String)
        //сохраняем прокрутку
        Main.save_str("old_skrol_book_pot", skroll_book!!.scrollY.toString())

        Log.d("ttt",skroll_book!!.scrollY.toString())

        super.onDestroyView()
    }
}

