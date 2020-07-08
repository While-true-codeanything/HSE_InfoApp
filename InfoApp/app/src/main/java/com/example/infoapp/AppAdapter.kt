package com.example.infoapp

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kotlinx.android.synthetic.main.header_item.view.*
import kotlinx.android.synthetic.main.info_item.view.*
import kotlinx.android.synthetic.main.skill_item.view.*
import kotlinx.android.synthetic.main.skill_item.view.nameField

class AppAdapter(Languages: ArrayList<Language>, m: MainActivity) :
    RecyclerView.Adapter<ViewHolder>() {
    var position2 = -1
    var mainrecol = m // Может показаться слегка неблагоразумно, но на самом деле нет
    var Languages: ArrayList<Language> = Languages
        set(value) {
            field = value
        }

    fun getNumberOfFiltredItems(): Int {
        var am = 0
        for (lang in Languages) {
            for (filter in MainActivity.getfilters()) {
                if (filter.isEn) {
                    if ((filter.value == 6) || (filter.value == 7 && lang.time > 5) || (filter.value == lang.time)) {
                        am++
                        break
                    }
                }
            }
        }
        return am
    }

    fun getiltredItems(): ArrayList<Language> {
        var am = ArrayList<Language>()
        for (lang in Languages) {
            for (filter in MainActivity.getfilters()) {
                if (filter.isEn) {
                    if ((filter.value == 6) || (filter.value == 7 && lang.time > 5) || (filter.value == lang.time)) {
                        am.add(lang)
                        break
                    }
                }
            }
        }
        return am
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        position2++
        return when (position2) {
            0 -> {
                ViewHolderFirstInfo(
                    LayoutInflater.from(parent.context).inflate(R.layout.info_item, parent, false)
                )
            }
            1 -> {
                ViewHolderLanguage(
                    LayoutInflater.from(parent.context).inflate(R.layout.text_item, parent, false)
                )
            }
            2 -> {
                ViewHolderHeader(
                    LayoutInflater.from(parent.context).inflate(R.layout.header_item, parent, false)
                )
            }
            else -> {
                ViewHolderLanguage(
                    LayoutInflater.from(parent.context).inflate(R.layout.skill_item, parent, false)
                )
            }
        }
    }

    override fun getItemCount(): Int =
        getNumberOfFiltredItems() + 3

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position == 0) {
            val holderthis = holder as ViewHolderFirstInfo
            holderthis.gitB.setOnClickListener {
                mainrecol.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://github.com/While-true-codeanything")
                    )
                )
            }
        }
        if (position > 2) {
            val holderthis = holder as ViewHolderLanguage
            holderthis.onBind(
                getiltredItems().get(position - 3).name,
                getiltredItems().get(position - 3).TimetoText()
            )
        }
        if (position == 2) {
            val holderthis = holder as ViewHolderHeader
            if (MainActivity.getfilters().get(0).isEn) holderthis.img.setImageResource(R.drawable.j)
            else holderthis.img.setImageResource(R.drawable.j2)
            holderthis.img.setOnClickListener {
                mainrecol.startActivityForResult(Intent(mainrecol, FilterActivity::class.java), 111)
            }
        }
    }

    inner class ViewHolderLanguage(root: View) :
        ViewHolder(root) {  // Можно было бы делать для каждого view отедельный ViewHolder, это было бы наверное даже более красивее, но так как данные в них статичны, я пошел по этому более эффективному пути
        private val name = root.nameField
        private val time = root.timeField

        fun onBind(namet: String, timet: String) {
            name.text = namet
            time.text = timet
        }
    }

    inner class ViewHolderHeader(root: View) :
        ViewHolder(root) {
        val img = root.flt
    }

    inner class ViewHolderFirstInfo(root: View) :
        ViewHolder(root) {
        val gitB = root.git
    }
}
