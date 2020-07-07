package com.example.infoapp

class Language(var name: String, var time: Int) {
    fun TimetoText(): String {
        if (time < 1) return "<1 года"
        if ((1 < time % 10) && (time % 10 < 5)) return "$time года"
        if (((time % 10 == 1) && (time != 11)) || (time == 1)) return "$time год"
        return "$time лет"
    }
}
