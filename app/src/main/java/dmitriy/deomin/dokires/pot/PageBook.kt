package dmitriy.deomin.dokires.pot
import android.annotation.SuppressLint
import android.graphics.Color
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
import org.jetbrains.anko.sdk25.coroutines.onLongClick
import uk.co.deanwild.flowtextview.FlowTextView

class PageBook :Fragment(){

    companion object {
        var book:FlowTextView? = null
        @SuppressLint("StaticFieldLeak")
        var skroll_book:ScrollView? = null
        var text_book:String =""

        fun add_tetx(t:String){
         text_book= t   //название файла с текстом

            if (Build.VERSION.SDK_INT >= 24) {
                book!!.text = Html.fromHtml(Main.con_v_palto!!.assets.open("pot_book/"+t).reader().readText(), 0) // for 24 api and more
            } else {
                book!!.text = Html.fromHtml(Main.con_v_palto!!.assets.open("pot_book/"+t).reader().readText()) // or for older api
            }
        }

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(R.layout.book_pot, null)

        skroll_book = v.findViewById(R.id.skroll_book_pot)
        book  = v.findViewById(R.id.book_telo)


        //Выключим меню
        v.book_menu.visibility = View.GONE

        //установим размер шрифта из памяти
        val save_size = Main.read_str("book_font_size")
        if(save_size.length>1){
            book!!.setTextSize(save_size.toFloat())
        }


        //установим цвета
        //установим цвет фона
        if (Main.read_str("color_fon").length > 1) {
           v.fon_book_pot.setBackgroundColor(Main.read_str("color_fon").toInt())
        }

        if(Main.read_str("color_text").length>1){
           book!!.setTextColor(Main.read_str("color_text").toInt())
        }else{
            book!!.setTextColor(Color.BLACK)
        }

        //установим текст  из памяти
        if(Main.read_str("old_text_book_pot")!=""){
            if (Build.VERSION.SDK_INT >= 24) {
                book!!.text = Html.fromHtml(
                        Main.con_v_palto!!.assets.open(
                                "pot_book/"+Main.read_str("old_text_book_pot")).reader().readText(), 0) // for 24 api and more
            } else {
                book!!.text = Html.fromHtml( Main.con_v_palto!!.assets.open(
                        "pot_book/"+Main.read_str("old_text_book_pot")).reader().readText()) // or for older api
            }
        }

        book!!.onLongClick {
            if(v.book_menu.visibility==View.GONE){
            v.book_menu.visibility = View.VISIBLE
        }else{v.book_menu.visibility = View.GONE}
        }

        v.big_text.onClick {
            val size = v.book_telo.textsize
            book!!.setTextSize(size+1)
            Main.save_str("book_font_size",v.book_telo.textsize.toString())
        }

        v.smoll_text.onClick {
            val size = v.book_telo.textsize
            book!!.setTextSize(size-1)
            Main.save_str("book_font_size",v.book_telo.textsize.toString())
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
            PageBook.skroll_book!!.smoothScrollTo(0,s.toInt())
        }
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

