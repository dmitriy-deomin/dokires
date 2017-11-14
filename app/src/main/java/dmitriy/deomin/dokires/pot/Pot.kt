package dmitriy.deomin.dokires.pot
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v4.view.ViewPager
import android.view.View
import dmitriy.deomin.dokires.Main
import dmitriy.deomin.dokires.R

class Pot : FragmentActivity(){

    companion object {
        var viewpager: ViewPager? = null
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pot)

        val adapter = Adapter(supportFragmentManager)
        viewpager = findViewById<View>(R.id.pager) as ViewPager

        viewpager?.adapter = adapter


        //если первый раз открывает то покажем оглавление
        if(Main.read_str("old_text_book_pot").length<2) {
            viewpager?.currentItem = 0
        }else{
            //иначе продолжим чтение
            viewpager?.currentItem = 1
        }

    }

    override fun onBackPressed() {

        if(viewpager!!.currentItem==0){
            this.finish()
        }else{
            viewpager!!.setCurrentItem(0)
            return
        }

        super.onBackPressed()
    }
}
