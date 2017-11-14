package dmitriy.deomin.dokires

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.DialogFragment
import kotlinx.android.synthetic.main.edit_color.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import com.github.danielnilsson9.colorpickerview.dialog.ColorPickerDialogFragment
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.textColor

class Edit_color : Activity(),ColorPickerDialogFragment.ColorPickerDialogListener{

    private val DIALOG_fon: Int = 0
    private val DIALOG_text: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_color)

        //устанавливаем цвета если есть в сохранялках
        if(Main.read_str("color_fon").length>1){
            fon_edit_color.backgroundColor = Main.read_str("color_fon").toInt()
        }
        if(Main.read_str("color_text").length>1){
            val color = Main.read_str("color_text").toInt()
            //меняем у виджетов
            name_i_version.textColor = color
            edit_color_text.textColor = color
            edit_color_fon.textColor = color
        }



        name_i_version.text = getString(R.string.app_name) + " ver:" + getString(R.string.versionName)

        edit_color_fon.onClick {

            val f = ColorPickerDialogFragment
                    .newInstance(DIALOG_fon, null, null, resources.getColor(R.color.colorFon), true)

            f.setStyle(DialogFragment.STYLE_NORMAL, R.style.myTheme)
            f.show(fragmentManager, "d")
        }

        edit_color_text.onClick {

            val f = ColorPickerDialogFragment
                    .newInstance(DIALOG_text, null, null, resources.getColor(R.color.colorText), true)

            f.setStyle(DialogFragment.STYLE_NORMAL, R.style.myTheme)
            f.show(fragmentManager, "d")
        }

    }

    override fun onColorSelected(dialogId: Int, color: Int) {
        when (dialogId) {
            0 -> {
                //сохраняем цвет
                Main.save_str("color_fon",color.toString())
                //меняем у виджетов
                fon_edit_color.backgroundColor = color

            }
            1 -> {
                //сохраняем цвет
                Main.save_str("color_text",color.toString())
                //меняем у виджетов
                name_i_version.textColor = color
                edit_color_text.textColor = color
                edit_color_fon.textColor = color
            }
        }
    }

    override fun onDialogDismissed(dialogId: Int) {

    }
}