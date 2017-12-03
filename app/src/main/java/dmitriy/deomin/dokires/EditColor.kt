package dmitriy.deomin.dokires

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.edit_color.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import com.github.danielnilsson9.colorpickerview.dialog.ColorPickerDialogFragment
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.browse
import org.jetbrains.anko.textColor

class EditColor : Activity(),ColorPickerDialogFragment.ColorPickerDialogListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_color)

        //устанавливаем цвета
        fon_edit_color.backgroundColor = Main.COLOR_FON

        //меняем у виджетов
        name_i_version.textColor = Main.COLOR_TEXT
        edit_color_text.textColor = Main.COLOR_TEXT
        edit_color_fon.textColor = Main.COLOR_TEXT
        edit_color_fon_dialog.textColor = Main.COLOR_TEXT



        name_i_version.text = getString(R.string.app_name) + " ver:" + getString(R.string.versionName)

        name_i_version.onClick {
            //играем анимацию
            val anim = AnimationUtils.loadAnimation(Main.con, R.anim.myalpha)
            name_i_version.startAnimation(anim)

            //закрываем окошко
            finish()

            //открываем ссылку на группу в вконтакте
            browse("https://vk.com/dokires")

        }


        edit_color_fon.onClick {
            //играем анимацию
            val anim = AnimationUtils.loadAnimation(Main.con, R.anim.myalpha)
            edit_color_fon.startAnimation(anim)


            val f = ColorPickerDialogFragment
                    .newInstance(0, null, null, resources.getColor(R.color.colorFon), true)

            f.setStyle(DialogFragment.STYLE_NORMAL, R.style.myTheme)
            f.show(fragmentManager, "d")
        }

        edit_color_fon_dialog.onClick {
            //играем анимацию
            val anim = AnimationUtils.loadAnimation(Main.con, R.anim.myalpha)
            edit_color_fon_dialog.startAnimation(anim)


            val f = ColorPickerDialogFragment
                    .newInstance(1, null, null, resources.getColor(R.color.colorFon_dialog), true)

            f.setStyle(DialogFragment.STYLE_NORMAL, R.style.myTheme)
            f.show(fragmentManager, "d")
        }

        edit_color_text.onClick {
            //играем анимацию
            val anim = AnimationUtils.loadAnimation(Main.con, R.anim.myalpha)
            edit_color_text.startAnimation(anim)


            val f = ColorPickerDialogFragment
                    .newInstance(2, null, null, resources.getColor(R.color.colorText), true)

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
                Main.save_str("color_fon_dialog",color.toString())
                Main.COLOR_FON_DIALOG = color
            }
            2 -> {
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