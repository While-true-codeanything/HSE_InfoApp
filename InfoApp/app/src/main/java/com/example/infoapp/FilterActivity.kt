package com.example.infoapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.infoapp.MainActivity.Companion.KEY
import kotlinx.android.synthetic.main.activity_filter.*

class FilterActivity : AppCompatActivity() {
    companion object {
        const val KEY_SAVEDATA = "data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)
        setTitle("Фильтр")
        val l = arrayListOf<FilterParam>()
        l.add(FilterParam("Все", true, 6))
        l.add(FilterParam("Менее года", true, 0))
        l.add(FilterParam("1 год", true, 1))
        l.add(FilterParam("2 года", true, 2))
        l.add(FilterParam("3 года", true, 3))
        l.add(FilterParam("4 года", true, 4))
        l.add(FilterParam("5 лет", true, 5))
        l.add(FilterParam("более 5 лет", true, 7))
        if (FilterAdapter.Parameters.isNullOrEmpty()) FilterAdapter.Parameters = l
        filters.adapter = FilterAdapter()
        Apply.setOnClickListener {
            val data = Intent()
            data.putParcelableArrayListExtra(KEY, FilterAdapter.Parameters)
            setResult(Activity.RESULT_OK, data)
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.barmenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId() == R.id.action_close) {
            finish()
        }
        return true
    }
}
