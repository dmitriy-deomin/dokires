package dmitriy.deomin.dokires.pot
import android.graphics.Color
import android.os.Build
import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import dmitriy.deomin.dokires.Main
import dmitriy.deomin.dokires.R
import kotlinx.android.synthetic.main.book_pot.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.sdk25.coroutines.onLongClick
import uk.co.deanwild.flowtextview.FlowTextView
import org.jetbrains.anko.sdk25.coroutines.onScrollChange
import org.jetbrains.anko.support.v4.toast
import java.io.File
import java.util.HashMap
import kotlin.collections.ArrayList


class PageBook :Fragment(){

    var igrik:Int = 0

    companion object {
        var book:FlowTextView? = null

        fun add_tetx(t:String){
            if (Build.VERSION.SDK_INT >= 24) {
                book!!.text = Html.fromHtml(Main.con_v_palto!!.assets.open("pot_book/"+t).reader().readText(), 0) // for 24 api and more
            } else {
                book!!.text = Html.fromHtml(Main.con_v_palto!!.assets.open("pot_book/"+t).reader().readText()) // or for older api
            }
        }

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(R.layout.book_pot, null)

        book  = v.findViewById(R.id.book_telo)

        //Выключим меню настройки размера текста и списка закладок
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
           book!!.textColor=(Main.read_str("color_text").toInt())
        }else{
            book!!.textColor = Color.BLACK
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


        //при долгом нажатии будет открыватся еще список закладок
        //заполняем его
        val recyclerView: RecyclerView = v.findViewById(R.id.list_zakladki)

        val adapter = RecyclerAdapterZakladki(generateData(Main.read_arraylist("zakladki")))
        val layoutManager = LinearLayoutManager(v.context)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()

        recyclerView.adapter = adapter





        book!!.onLongClick {
            //сохраняем прокрутку
            Main.save_str("old_skrol_book_pot", igrik.toString())

            if(v.book_menu.visibility==View.GONE){
            v.book_menu.visibility = View.VISIBLE
        }else {
                v.book_menu.visibility = View.GONE}
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


        v.add_zakladka.onClick {

            //получаем все закладки из памяти
            var vse_zakladki =  Main.read_arraylist("zakladki")
            //добавляем в список текущею главу и позицию прокрутки
            if(Main.read_str("old_text_book_pot")!=""){
                vse_zakladki.add(Main.read_str("old_text_book_pot")+"position:"+igrik.toString())
            }else{
                toast("Произошла ошибка")
            }

            //сохраняем в память
            Main.save_arraylist("zakladki",vse_zakladki)
            //обновляем список закладок
            recyclerView.adapter.notifyDataSetChanged()


        }


        v.skroll_book_pot!!.viewTreeObserver.addOnGlobalLayoutListener {
            //проматаем на сохранёную позицию
            if(Main.read_str("old_skrol_book_pot").length>1) {
                v.skroll_book_pot!!.scrollY = Main.read_str("old_skrol_book_pot").toInt()
            }else{
                //или на начало
                v.skroll_book_pot!!.scrollY=0
            }
        }
        v.skroll_book_pot.onScrollChange { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            igrik = oldScrollY
        }

        return v
    }


    private fun generateData(zakladki_list: ArrayList<String>): ArrayList<Map<String, String>> {

        val result = ArrayList<Map<String,String>>()
        var stroka:Map<String,String>

        for (i in 0 until zakladki_list.size) {
            stroka = HashMap<String,String>(zakladki_list.size)
            stroka.put("name", zakladki_list[i].split("position:")[0])
            stroka.put("position", zakladki_list[i].split("position:")[1])
            result.add(stroka)
        }

        return result
    }

    override fun onPause() {
        //сохраняем прокрутку
        Main.save_str("old_skrol_book_pot", igrik.toString())
        super.onPause()
    }

}

