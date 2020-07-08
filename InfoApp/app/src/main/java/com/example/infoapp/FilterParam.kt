package com.example.infoapp

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable

class FilterParam(var text: String, var isEn: Boolean, var value: Int) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readByte() != 0.toByte(),
        parcel.readInt()!!
    )

    @SuppressLint("NewApi")
    override fun writeToParcel(p0: Parcel, p1: Int) {
        p0.writeString(text)
        p0.writeBoolean(isEn) // Эта функция работает только на новых apiб. Можно было бы вообще делать все через статику, но я решил попробовать так
        p0.writeInt(value)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<FilterParam> {
        override fun createFromParcel(parcel: Parcel): FilterParam =
            FilterParam(parcel)

        override fun newArray(size: Int): Array<FilterParam?> =
            arrayOfNulls(size)
    }
}
