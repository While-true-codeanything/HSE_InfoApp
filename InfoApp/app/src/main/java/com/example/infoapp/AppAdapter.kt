package com.example.infoapp

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.info_item.*
import kotlinx.android.synthetic.main.skill_item.view.*

class AppAdapter(Languages: ArrayList<Language>, m: MainActivity) :
    RecyclerView.Adapter<AppAdapter.ViewHolder>() {
    var position2 = -1
    var mainrecol = m // Может показаться слегка неблагоразумно, но на самом деле нет
    var Languages: ArrayList<Language> = Languages
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        position2++
        return when (position2) {
            0 -> {
                ViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.info_item, parent, false)
                )
            }
            1 -> {
                ViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.text_item, parent, false)
                )
            }
            2 -> {
                ViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.header_item, parent, false)
                )
            }
            else -> {
                ViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.skill_item, parent, false)
                )
            }
        }
    }

    override fun getItemCount(): Int =
        Languages.size + 3

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position > 2) holder.onBind(
            Languages.get(position - 3).name,
            Languages.get(position - 3).TimetoText()
        )
        if (position == 3) {  // Этот пункт точно будет
            mainrecol.git.setOnClickListener {
                mainrecol.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://github.com/While-true-codeanything")
                    )
                )
            }
        }
    }

    inner class ViewHolder(root: View) :
        RecyclerView.ViewHolder(root) {  // Можно было бы делать для каждого view отедельный ViewHolder, это было бы наверное даже более красивее, но так как данные в них статичны, я пошел по этому более эффективному пути
        private val name = root.nameField
        private val time = root.timeField

        fun onBind(namet: String, timet: String) {
            name.text = namet
            time.text = timet
        }
    }
}
