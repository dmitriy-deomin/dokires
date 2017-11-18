package dmitriy.deomin.dokires

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import dmitriy.deomin.dokires.pot.Pot
import kotlinx.android.synthetic.main.main.*
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import android.view.animation.AnimationUtils

class Main : Activity() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        var con_v_palto: Context? = null

        //сохранялка
        //----------------------------------------------------------------
        var settings: SharedPreferences? = null // сохранялка

        //чтение настроек
        fun read_str(key: String): String {
            if (ne_pusto(key)) {
                return settings!!.getString(key, "")
            } else {
                return ""
            }
        }

        fun ne_pusto(key: String): Boolean {
            return settings!!.contains(key)
        }

        //запись настроек
        fun save_str(key: String, value: String) {
            settings!!.edit().putString(key, value).apply()
        }
        //----------------------------------------------------------------
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        con_v_palto = applicationContext

        settings = getSharedPreferences("mysettings", FragmentActivity.MODE_PRIVATE)

        //устанавливаем цвета
        if (read_str("color_fon").length > 1) {
            fon.backgroundColor = read_str("color_fon").toInt()
        }
        if (read_str("color_text").length > 1) {
            val text_color = read_str("color_text").toInt()
            nameapp.textColor = text_color
            rasshifrovka.textColor = text_color
            but_pot.textColor = text_color
            button2.textColor = text_color
        }


        //титл, при клике будем открывать меню настройки цвета фона и текста
        nameapp.onClick { val anim = AnimationUtils.loadAnimation(this@Main, R.anim.myalpha)
            nameapp.startAnimation(anim); startActivity<EditColor>() }

        //кнопки
        but_pot.onClick { val anim = AnimationUtils.loadAnimation(this@Main, R.anim.myalpha)
            but_pot.startAnimation(anim); startActivity<Pot>() }
    }
}

