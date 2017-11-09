package dmitriy.deomin.dokires.pot
import android.annotation.SuppressLint
import android.os.Build
import android.support.v4.app.Fragment
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.ScrollView
import dmitriy.deomin.dokires.Main
import dmitriy.deomin.dokires.R
import kotlinx.android.synthetic.main.book_pot.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import uk.co.deanwild.flowtextview.FlowTextView

class Book_pot:Fragment(){

    companion object {
        var book:FlowTextView? = null
        @SuppressLint("StaticFieldLeak")
        var skroll_book:ScrollView? =null
        var text_book:String =""

        fun add_tetx(t:String){
         text_book=t

            if (Build.VERSION.SDK_INT >= 24) {
                book!!.text = Html.fromHtml(t, 63) // for 24 api and more
            } else {
                book!!.text = Html.fromHtml(t) // or for older api
            }
        }

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(R.layout.book_pot, null)

        skroll_book = v.findViewById(R.id.skroll_book_pot)
        book = v.findViewById(R.id.book)


        //Выключим меню
        v.book_menu.visibility = View.GONE

        //установим размер шрифта из памяти
        val save_size = Main.read_str("book_font_size")
        if(save_size.length>1){
            v.book.setTextSize(save_size.toFloat())
        }

        //установим текст  из памяти
        if(Main.read_str("old_text_book_pot")!=""){
            if (Build.VERSION.SDK_INT >= 24) {
                book!!.text = Html.fromHtml(Main.read_str("old_text_book_pot"), 63) // for 24 api and more
            } else {
                book!!.text = Html.fromHtml(Main.read_str("old_text_book_pot")) // or for older api
            }
        }else{
            book?.text ="Выберите главу\n(клик по заголовку откроем меню настройки шрифта)"

        }

        Log.d("ttt","oncreate")
        v.title_book.onClick {
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

//        //при скролинге текста будем скрывать заголовок
//        v.skroll_book_pot.onScrollChange { v, scrollX, scrollY, oldScrollX, oldScrollY ->
//            if(scrollX==100){
//                v!!.title_book.visibility = View.GONE
//            }
//            if(scrollX==0){
//                v!!.title_book.visibility = View.VISIBLE
//            }
//        }




        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //проматаем
        val s = Main.read_str("old_skrol_book_pot")
        if(s != ""){
             skroll_book!!.scrollTo(0,s.toInt())
        }
        Log.d("ttt","oncreated")
    }



    override fun onDestroyView() {
        //сохраняем текст
        Main.save_str("old_text_book_pot", text_book)
        //сохраняем прокрутку
        Main.save_str("old_skrol_book_pot", skroll_book!!.scrollY.toString())

        Log.d("ttt",skroll_book!!.scrollY.toString())

        super.onDestroyView()
    }
}

