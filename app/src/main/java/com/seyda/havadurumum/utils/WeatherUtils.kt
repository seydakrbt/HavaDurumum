
package com.seyda.havadurumum.utils

fun getWeatherDescription(code: Int): String {
    return when (code) {
        0 -> "Açık"
        1 -> "Çoğunlukla açık"
        2 -> "Parçalı bulutlu"
        3 -> "Kapalı"
        45, 48 -> "Sisli"
        51, 53, 55 -> "Çisenti"
        56, 57 -> "Dondurucu çisenti"
        61, 63, 65 -> "Yağmurlu"
        66, 67 -> "Dondurucu yağmur"
        71, 73, 75 -> "Karlı"
        77 -> "Kar taneleri"
        80, 81, 82 -> "Sağanak yağış"
        85, 86 -> "Kar sağanağı"
        95 -> "Gök gürültülü fırtına"
        96, 99 -> "Dolu ihtimalli fırtına"
        else -> "Bilinmeyen hava durumu"
    }
}

fun getWeatherIcon(code: Int): String {
    return when (code) {
        0, 1 -> "☀️"
        2 -> "🌤️"
        3 -> "☁️"
        45, 48 -> "🌫️"
        51, 53, 55, 56, 57 -> "🌦️"
        61, 63, 65, 66, 67, 80, 81, 82 -> "🌧️"
        71, 73, 75, 77, 85, 86 -> "❄️"
        95, 96, 99 -> "⛈️"
        else -> "🌡️"
    }
}
