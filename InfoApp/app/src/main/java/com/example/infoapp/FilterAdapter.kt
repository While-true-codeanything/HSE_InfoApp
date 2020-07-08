package com.example.infoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.filter_item.view.*

class FilterAdapter() :
    RecyclerView.Adapter<FilterAdapter.ViewHolder>() {
    companion object {
        var Parameters: ArrayList<FilterParam> = ArrayList()
            set(value) {
                field = value
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.filter_item, parent, false))

    override fun getItemCount(): Int =
        Parameters.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var forCheck = true
        holder.onBind(
            Parameters.get(position).text,
            Parameters.get(position).isEn
        )
        if (position != 0) {
            holder.field.setOnClickListener {
                if (Parameters.get(position).isEn) {
                    Parameters.get(0).isEn = false
                    Parameters.get(position).isEn = false
                } else {
                    for (i in 1..Parameters.size - 1) if (!Parameters.get(i).isEn && position != i) {
                        forCheck = false
                    }
                    if (forCheck) Parameters.get(0).isEn = true
                    Parameters.get(position).isEn = true
                }
                notifyDataSetChanged()
            }
        } else {
            holder.field.setOnClickListener {
                Parameters.get(position).isEn = !Parameters.get(position).isEn
                for (i in Parameters) i.isEn = Parameters.get(position).isEn

                notifyDataSetChanged()
            }
        }
    }

    inner class ViewHolder(root: View) :
        RecyclerView.ViewHolder(root) {
        val field = root.par

        fun onBind(txt: String, isEn: Boolean) {
            field.isChecked = isEn
            field.text = txt
        }
    }
}
