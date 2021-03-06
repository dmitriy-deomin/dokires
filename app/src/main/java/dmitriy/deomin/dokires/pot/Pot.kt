package dmitriy.deomin.dokires.pot
import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v4.view.ViewPager
import android.view.View
import dmitriy.deomin.dokires.Main
import dmitriy.deomin.dokires.R
import kotlinx.android.synthetic.main.pot.*
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.sdk25.coroutines.onLongClick
import org.jetbrains.anko.support.v4.onPageChangeListener

class Pot : FragmentActivity(){

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit  var viewpager: ViewPager
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pot)


        fon_book_font_size.visibility = View.GONE
        fon_book_font_size.backgroundColor = Main.COLOR_FON_DIALOG

        //красим фон кнопок переключения вкладок
        btn_oglavlenie.backgroundColor = Main.COLOR_FON
        btn_book_text.backgroundColor = Main.COLOR_FON
        btn_tablica.backgroundColor = Main.COLOR_FON

        val adapter = Adapter(supportFragmentManager)
        viewpager = findViewById<View>(R.id.pager) as ViewPager

        viewpager.adapter = adapter
        viewpager.offscreenPageLimit = 3


        viewpager.onPageChangeListener {
            onPageSelected {
               fon_butn(viewpager.currentItem)
            }
        }


        //если первый раз открывает то покажем оглавление
        if(Main.read_str("old_text_book_pot").length<2) {
            viewpager.currentItem = 0
        }else{
            //иначе продолжим чтение
            viewpager.currentItem = 1
        }


        btn_oglavlenie.onClick { viewpager.currentItem=0 }
        btn_book_text.onClick { viewpager.currentItem=1 }
        btn_tablica.onClick { viewpager.currentItem=2 }



        btn_book_text.onLongClick {

            //если открыта книга
            if(viewpager.currentItem==1){
                if( fon_book_font_size.visibility == View.GONE){
                    fon_book_font_size.visibility = View.VISIBLE
                }else{
                    fon_book_font_size.visibility = View.GONE
                }
            }



        }



        big_text.onClick {
            val size = PageBook.book.textsize
            PageBook.book.setTextSize(size+1)
            Main.save_str("book_font_size",PageBook.book.textsize.toString())
        }

        smoll_text.onClick {
            val size = PageBook.book.textsize
            PageBook.book.setTextSize(size-1)
            Main.save_str("book_font_size",PageBook.book.textsize.toString())

        }
    }


    private fun fon_butn(number:Int){
        when(number){
            0->{
                btn_oglavlenie.typeface = Typeface.DEFAULT_BOLD
                btn_book_text.typeface = Typeface.DEFAULT
                btn_tablica.typeface = Typeface.DEFAULT
                //уберем кнопки размера текста
                if(fon_book_font_size.visibility == View.VISIBLE)fon_book_font_size.visibility =View.GONE
            }
            1->{
                btn_book_text.typeface = Typeface.DEFAULT_BOLD
                btn_oglavlenie.typeface = Typeface.DEFAULT
                btn_tablica.typeface = Typeface.DEFAULT
            }
            2->{
                btn_tablica.typeface = Typeface.DEFAULT_BOLD
                btn_book_text.typeface = Typeface.DEFAULT
                btn_oglavlenie.typeface = Typeface.DEFAULT
                //уберем кнопки размера текста
                if(fon_book_font_size.visibility == View.VISIBLE)fon_book_font_size.visibility =View.GONE
            }
        }

    }

    override fun onBackPressed() {

        if(viewpager.currentItem==0){
            this.finish()
        }else{
            viewpager.currentItem=0
            return
        }

        super.onBackPressed()
    }
}
