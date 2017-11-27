package dmitriy.deomin.dokires

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.DialogFragment
import kotlinx.android.synthetic.main.edit_color.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import com.github.danielnilsson9.colorpickerview.dialog.ColorPickerDialogFragment
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.textColor

class EditColor : Activity(),ColorPickerDialogFragment.ColorPickerDialogListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_color)

        //устанавливаем цвета если есть в сохранялках
        fon_edit_color.backgroundColor = Main.COLOR_FON

        //меняем у виджетов
        name_i_version.textColor = Main.COLOR_TEXT
        edit_color_text.textColor = Main.COLOR_TEXT
        edit_color_fon.textColor = Main.COLOR_TEXT



        name_i_version.text = getString(R.string.app_name) + " ver:" + getString(R.string.versionName)

        edit_color_fon.onClick {

            val f = ColorPickerDialogFragment
                    .newInstance(0, null, null, resources.getColor(R.color.colorFon), true)

            f.setStyle(DialogFragment.STYLE_NORMAL, R.style.myTheme)
            f.show(fragmentManager, "d")
        }

        edit_color_text.onClick {

            val f = ColorPickerDialogFragment
                    .newInstance(1, null, null, resources.getColor(R.color.colorText), true)

            f.setStyle(DialogFragment.STYLE_NORMAL, R.style.myTheme)
            f.show(fragmentManager, "d")
        }

    }

    override fun onColorSelected(dialogId: Int, color: Int) {
        when (dialogId) {
            0 -> {
                //сохраняем цвет
                Main.save_str("color_fon",color.toString())
                Main.COLOR_FON = color
                //меняем у виджетов
                fon_edit_color.backgroundColor = Main.COLOR_FON

            }
            1 -> {
                //сохраняем цвет
                Main.save_str("color_text",color.toString())
                Main.COLOR_TEXT = color
                //меняем у виджетов
                name_i_version.textColor = Main.COLOR_TEXT
                edit_color_text.textColor =Main.COLOR_TEXT
                edit_color_fon.textColor = Main.COLOR_TEXT
            }
        }
    }

    override fun onDialogDismissed(dialogId: Int) {

    }
}