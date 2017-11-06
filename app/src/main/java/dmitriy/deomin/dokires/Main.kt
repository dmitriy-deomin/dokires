package dmitriy.deomin.dokires

import android.app.Activity
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import dmitriy.deomin.dokires.pot.Pot
import kotlinx.android.synthetic.main.main.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity


class Main : Activity() {

    companion object {
        //сохранялка
        //----------------------------------------------------------------
        var settings: SharedPreferences? = null // сохранялка
        //чтение настроек
        fun read_str(key:String):String{ if(ne_pusto(key)){return settings!!.getString(key,"")}else{return ""} }
        fun ne_pusto(key:String):Boolean{return settings!!.contains(key)}
        //запись настроек
        fun save_str(key:String,value:String){ settings!!.edit().putString(key,value).apply() }
        //----------------------------------------------------------------

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        settings = getSharedPreferences("mysettings", FragmentActivity.MODE_PRIVATE)

        version.text = getString(R.string.versionName)
        but_pot.onClick { startActivity<Pot>() }
    }
}
