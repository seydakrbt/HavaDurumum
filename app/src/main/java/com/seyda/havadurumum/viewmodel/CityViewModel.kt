
package com.seyda.havadurumum.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seyda.havadurumum.model.City
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import java.util.Locale

class CityViewModel : ViewModel() {

    private val allCities = listOf(
        City("Adana", "01", 37.0000, 35.3213),
        City("Adıyaman", "02", 37.7648, 38.2786),
        City("Afyonkarahisar", "03", 38.7507, 30.5567),
        City("Ağrı", "04", 39.7191, 43.0503),
        City("Amasya", "05", 40.6499, 35.8353),
        City("Ankara", "06", 39.9334, 32.8597),
        City("Antalya", "07", 36.8969, 30.7133),
        City("Artvin", "08", 41.1828, 41.8183),
        City("Aydın", "09", 37.8560, 27.8416),
        City("Balıkesir", "10", 39.6484, 27.8826),
        City("Bilecik", "11", 40.1506, 29.9793),
        City("Bingöl", "12", 38.8854, 40.4966),
        City("Bitlis", "13", 38.4006, 42.1095),
        City("Bolu", "14", 40.7395, 31.6116),
        City("Burdur", "15", 37.7203, 30.2908),
        City("Bursa", "16", 40.1885, 29.0610),
        City("Çanakkale", "17", 40.1553, 26.4142),
        City("Çankırı", "18", 40.6013, 33.6134),
        City("Çorum", "19", 40.5506, 34.9556),
        City("Denizli", "20", 37.7765, 29.0864),
        City("Diyarbakır", "21", 37.9144, 40.2306),
        City("Edirne", "22", 41.6771, 26.5557),
        City("Elazığ", "23", 38.6810, 39.2264),
        City("Erzincan", "24", 39.7500, 39.5000),
        City("Erzurum", "25", 39.9000, 41.2700),
        City("Eskişehir", "26", 39.7767, 30.5206),
        City("Gaziantep", "27", 37.0662, 37.3833),
        City("Giresun", "28", 40.9128, 38.3895),
        City("Gümüşhane", "29", 40.4603, 39.4814),
        City("Hakkâri", "30", 37.5744, 43.7408),
        City("Hatay", "31", 36.2021, 36.1606),
        City("Isparta", "32", 37.7648, 30.5566),
        City("Mersin", "33", 36.8000, 34.6333),
        City("İstanbul", "34", 41.0082, 28.9784),
        City("İzmir", "35", 38.4237, 27.1428),
        City("Kars", "36", 40.6013, 43.0975),
        City("Kastamonu", "37", 41.3887, 33.7827),
        City("Kayseri", "38", 38.7312, 35.4787),
        City("Kırklareli", "39", 41.7351, 27.2252),
        City("Kırşehir", "40", 39.1458, 34.1639),
        City("Kocaeli", "41", 40.7654, 29.9408),
        City("Konya", "42", 37.8746, 32.4932),
        City("Kütahya", "43", 39.4192, 29.9857),
        City("Malatya", "44", 38.3552, 38.3095),
        City("Manisa", "45", 38.6191, 27.4289),
        City("Kahramanmaraş", "46", 37.5753, 36.9228),
        City("Mardin", "47", 37.3212, 40.7245),
        City("Muğla", "48", 37.2153, 28.3636),
        City("Muş", "49", 38.9462, 41.7539),
        City("Nevşehir", "50", 38.6244, 34.7239),
        City("Niğde", "51", 37.9667, 34.6833),
        City("Ordu", "52", 40.9839, 37.8764),
        City("Rize", "53", 41.0201, 40.5234),
        City("Sakarya", "54", 40.7569, 30.3781),
        City("Samsun", "55", 41.2867, 36.3300),
        City("Siirt", "56", 37.9333, 41.9500),
        City("Sinop", "57", 42.0268, 35.1625),
        City("Sivas", "58", 39.7477, 37.0179),
        City("Tekirdağ", "59", 40.9781, 27.5117),
        City("Tokat", "60", 40.3167, 36.5500),
        City("Trabzon", "61", 41.0027, 39.7168),
        City("Tunceli", "62", 39.1079, 39.5401),
        City("Şanlıurfa", "63", 37.1674, 38.7955),
        City("Uşak", "64", 38.6823, 29.4082),
        City("Van", "65", 38.4891, 43.4089),
        City("Yozgat", "66", 39.8200, 34.8044),
        City("Zonguldak", "67", 41.4564, 31.7987),
        City("Aksaray", "68", 38.3687, 34.0370),
        City("Bayburt", "69", 40.2552, 40.2249),
        City("Karaman", "70", 37.1811, 33.2150),
        City("Kırıkkale", "71", 39.8468, 33.5153),
        City("Batman", "72", 37.8812, 41.1351),
        City("Şırnak", "73", 37.4187, 42.4918),
        City("Bartın", "74", 41.5811, 32.4610),
        City("Ardahan", "75", 41.1105, 42.7022),
        City("Iğdır", "76", 39.9237, 44.0450),
        City("Yalova", "77", 40.6550, 29.2769),
        City("Karabük", "78", 41.2061, 32.6204),
        City("Kilis", "79", 36.7184, 37.1212),
        City("Osmaniye", "80", 37.0742, 36.2478),
        City("Düzce", "81", 40.8438, 31.1565)
    )

    // Türkçe karakterleri normalleştir
    private fun normalizeText(text: String): String {
        return text
            .lowercase(Locale.forLanguageTag("tr-TR"))
            .replace("ı", "i")
            .replace("ğ", "g")
            .replace("ü", "u")
            .replace("ş", "s")
            .replace("ö", "o")
            .replace("ç", "c")
            .trim()
    }

    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText.asStateFlow()

    private val _selectedCity = MutableStateFlow<City?>(null)
    val selectedCity: StateFlow<City?> = _selectedCity.asStateFlow()

    // Filtrelenmiş şehir listesi
    val filteredCities: StateFlow<List<City>> = combine(
        _searchText,
        _selectedCity
    ) { search, _ ->

        val normalizedSearch = normalizeText(search)

        if (normalizedSearch.isBlank()) {
            allCities
        } else {
            allCities.filter { city ->
                normalizeText(city.name).contains(normalizedSearch) ||
                        city.plateCode.contains(normalizedSearch)
            }
        }

    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = allCities
    )

    fun updateSearchText(text: String) {
        _searchText.value = text
    }

    fun selectCity(city: City) {
        _selectedCity.value = city
    }
}
