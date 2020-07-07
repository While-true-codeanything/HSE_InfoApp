package com.example.infoapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {  // На данный момент проблем со сменой ориентации экрана нет, следовательно не надо делать ничего специального
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val l = arrayListOf<Language>()
        l.add(Language("Java", 3))
        l.add(Language("SQL", 1))
        l.add(Language("C#", 1))
        l.add(Language("Python", 0))
        l.add(Language("С++", 1))
        l.add(Language("Kotlin", 0))
        Content.adapter = AppAdapter(l, this)
    }
}
