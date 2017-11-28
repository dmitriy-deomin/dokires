package dmitriy.deomin.dokires

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.annotation.IntegerRes
import android.support.v4.app.FragmentActivity
import dmitriy.deomin.dokires.pot.Pot
import kotlinx.android.synthetic.main.main.*
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import android.view.animation.AnimationUtils
import org.json.JSONException
import org.json.JSONArray



class Main : Activity() {

    companion object {
        var COLOR_FON = 0
        var COLOR_TEXT = 0

        @SuppressLint("StaticFieldLeak")
        lateinit var con_v_palto: Context

        //сохранялка
        //----------------------------------------------------------------
        lateinit var settings: SharedPreferences// сохранялка

        //чтение настроек
        fun read_str(key: String): String {
            if (ne_pusto(key)) {
                return settings.getString(key, "")
            } else {
                return ""
            }
        }

        fun ne_pusto(key: String): Boolean {
            return settings.contains(key)
        }

        //запись настроек
        fun save_str(key: String, value: String) {
            settings.edit().putString(key, value).apply()
        }


        @SuppressLint("ApplySharedPref")
//*************************************************************************************************
        //ArrayList
        fun save_arraylist(key: String, values: ArrayList<String>) {
            val editor = settings.edit()
            val a = JSONArray()
            for (i in 0 until values.size) {
                a.put(values[i])
            }
            if (!values.isEmpty()) {
                editor.putString(key, a.toString())
            } else {
                editor.putString(key, null)
            }
            editor.commit()
        }
        fun read_arraylist(key: String): ArrayList<String> {
            val json = settings.getString(key, null)
            val urls = ArrayList<String>()
            if (json != null) {
                try {
                    val a = JSONArray(json)
                    for (i in 0 until a.length()) {
                        val url = a.optString(i)
                        urls.add(url)
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }

            }
            return urls
        }
        //*************************************************************************************************

        //----------------------------------------------------------------
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        con_v_palto = applicationContext

        settings = getSharedPreferences("mysettings", FragmentActivity.MODE_PRIVATE)

        //устанавливаем цвета
        if (read_str("color_fon").length > 1) {
            COLOR_FON=read_str("color_fon").toInt()
        }else{
            COLOR_FON = resources.getColor(R.color.colorFon)
        }
        if (read_str("color_text").length > 1) {
            COLOR_TEXT = read_str("color_text").toInt()
        }else{
            COLOR_TEXT = resources.getColor(R.color.colorText)
        }

        fon.backgroundColor = COLOR_FON
        nameapp.textColor = COLOR_TEXT
        rasshifrovka.textColor = COLOR_TEXT
        but_pot.textColor = COLOR_TEXT
        button2.textColor = COLOR_TEXT



        //титл, при клике будем открывать меню настройки цвета фона и текста
        nameapp.onClick { val anim = AnimationUtils.loadAnimation(this@Main, R.anim.myalpha)
            nameapp.startAnimation(anim); startActivity<EditColor>() }

        //кнопки
        but_pot.onClick { val anim = AnimationUtils.loadAnimation(this@Main, R.anim.myalpha)
            but_pot.startAnimation(anim); startActivity<Pot>() }
    }
}

