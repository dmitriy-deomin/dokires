package dmitriy.deomin.dokires.pot
import android.content.DialogInterface
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.LinearLayout
import dmitriy.deomin.dokires.Main
import dmitriy.deomin.dokires.R
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.sdk25.coroutines.onLongClick
import android.content.Intent
import org.jetbrains.anko.*


class RecyclerAdapterZakladki(private var items: ArrayList<Map<String, String>>): RecyclerView.Adapter<RecyclerAdapterZakladki.ViewHolder>() {


    class ViewHolder(row: View) : RecyclerView.ViewHolder(row) {
        lateinit var txtName: Button
        lateinit var fon:LinearLayout
        lateinit var menu:DialogInterface
        lateinit var menu_rename:DialogInterface
        init {
            this.txtName = row.findViewById<Button>(R.id.item_zakladki)
            this.fon = row.findViewById<LinearLayout>(R.id.fon_item_zakladki)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.recycler_item_zakladki, parent, false)
        return ViewHolder(itemView)
    }


    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        //цвет фона
        holder.fon.backgroundColor = Main.COLOR_FON_DIALOG

        //ставим название закладки на кнопку
        //если есть псевдоним поставим его
        if(Main.read_str(items[position]["name"]+"position:"+items[position]["position"]).length>0){
            holder.txtName.text =Main.read_str(items[position]["name"]+"position:"+items[position]["position"])
        }else{
            //иначе что передали
            holder.txtName.text = items[position]["name"]!!.replace(".html","")
        }


        //при клике будем загружать текст в книгу
        holder.txtName.onClick {
          //играем анимацию
          val anim = AnimationUtils.loadAnimation(Main.con, R.anim.myalpha)
          holder.txtName.startAnimation(anim)

            //устанавливаем прокрутку
            Main.save_str("old_skrol_book_pot", items[position]["position"].toString())

            //заполняем чушью
            PageBook.add_tetx(items[position]["name"].toString())
        }

        //при долгом клике будем предлогать удалить или переименовать закладку
        holder.txtName.onLongClick(returnValue = true){
            //диалоговое окно
            holder.menu =  PageBook.con.alert{
                customView {

                    linearLayout {
                        orientation=LinearLayout.VERTICAL
                        backgroundColor = Main.COLOR_FON_DIALOG

                        button("Переименовать").onClick {
                            //закрываем диалоговое окно
                            holder.menu.cancel()
                            //создаём псевдоним в новом окне
                            holder.menu_rename  = PageBook.con.alert {
                                customView {
                                    linearLayout {
                                        orientation = LinearLayout.VERTICAL
                                        backgroundColor = Main.COLOR_FON_DIALOG
                                        //пояснялка
                                        textView("Введите новое название")
                                        //напишем в поле ввода текущий псевдоним
                                       var new_name =  editText(holder.txtName.text)
                                        //кнопка сохранить
                                        button("Сохранить").onClick {
                                            //создаём псевдоним в памяти для этой закладки
                                            Main.save_str(items[position]["name"]+"position:"+items[position]["position"],new_name.text.toString())
                                            //переименовываем сразу эту кнопку в псевдоним
                                            holder.txtName.text =Main.read_str(items[position]["name"]+"position:"+items[position]["position"])
                                            //закрываем диалог
                                            holder.menu_rename.cancel()
                                        }
                                    }
                                }
                            }.show()
                        }
                        button("Удалить").onClick {
                            //получаем все закладки из памяти
                            var vse_zakladki =  Main.read_arraylist("zakladki")
                            //удаляем нужную
                            vse_zakladki.removeAt(position)
                            //сохраняем в память
                            Main.save_arraylist("zakladki",vse_zakladki)
                            //пошлём сигнал чтобы список обновил закладки
                            val i = Intent("signal_update_list_zakladok")
                            Main.con.sendBroadcast(i)
                            //закрываем диалоговое окно
                            holder.menu.cancel()
                        }
                    }
                }
            }.show()
        }
    }
}