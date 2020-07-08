package com.example.infoapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        private var filters: ArrayList<FilterParam> = ArrayList()
        fun getfilters(): ArrayList<FilterParam> = filters
        const val KEY = "KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (filters.isNullOrEmpty()) filters.add(FilterParam("Все", true, 6))
        setContentView(R.layout.activity_main)
        val l = arrayListOf<Language>()
        l.add(Language("Java", 3))
        l.add(Language("SQL", 1))
        l.add(Language("C#", 1))
        l.add(Language("Python", 0))
        l.add(Language("С++", 1))
        l.add(Language("Kotlin", 0))
        l.add(Language("ExampleSkill", 7))
        Content.adapter = AppAdapter(l, this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            filters = data.getParcelableArrayListExtra(KEY)
            recreate()
        }
    }
}
