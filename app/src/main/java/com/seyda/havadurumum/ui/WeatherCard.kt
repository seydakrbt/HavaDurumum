
package com.seyda.havadurumum.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.seyda.havadurumum.model.WeatherResponse
import com.seyda.havadurumum.utils.getWeatherDescription
import com.seyda.havadurumum.utils.getWeatherIcon

@Composable
fun WeatherCard(
    cityName: String,
    weather: WeatherResponse,
    modifier: Modifier = Modifier
) {
    val current = weather.currentWeather

    val description = getWeatherDescription(current.weatherCode)
    val icon = getWeatherIcon(current.weatherCode)

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = cityName,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = description,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Text(
                    text = icon,
                    style = MaterialTheme.typography.headlineLarge
                )
            }

            Text(
                text = "${current.temperature}°C",
                style = MaterialTheme.typography.displayMedium,
                fontWeight = FontWeight.Bold
            )

            HorizontalDivider()

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                WeatherDetail(
                    label = "Hissedilen",
                    value = "${current.feelsLike}°C"
                )

                WeatherDetail(
                    label = "Nem",
                    value = "%${current.humidity}"
                )

                WeatherDetail(
                    label = "Rüzgâr",
                    value = "${current.windSpeed} km/sa"
                )
            }
        }
    }
}

@Composable
private fun WeatherDetail(
    label: String,
    value: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall
        )

        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.SemiBold
        )
    }
}
