package com.musashi.weatherapp.ui.screen.summary.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.musashi.weatherapp.domain.model.CityModel

@Composable
fun WeatherSearchBar(
    modifier: Modifier = Modifier,
    label: String,
    textValue: String,
    onValueChange: (String) -> Unit,
    onSuggestionSelect: (String) -> Unit,
    onAddFavoriteClick: () -> Unit,
    isLoading: Boolean,
    expanded: Boolean,
    expandedChange: () -> Unit,
    onClearClicked: () -> Unit,
    isEnabled: Boolean,
    cities: List<CityModel> = emptyList(),
    countries: List<String> = emptyList()
) {
    var visibilityFavoriteIcon by remember{ mutableStateOf(false)}
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){

        TextField(
            value = textValue,
            label = { Text(text = label) },
            onValueChange = { onValueChange(it) },
            modifier = Modifier.width(300.dp),
            leadingIcon = {
                if(textValue.isEmpty()) {
                    Icon(
                        imageVector = Icons.Filled.Place,
                        contentDescription = "Place"
                    )
                }else{
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Clear",
                        modifier = Modifier.clickable {
                            onClearClicked()
                        }
                    )
                }
            },
            trailingIcon = {
                if (isLoading) {
                    CircularProgressIndicator(modifier = Modifier.size(25.dp))
                }
                if(label == "City"){
                    cities.forEach { city ->
                        visibilityFavoriteIcon = (city.cityName == textValue) && textValue.isNotEmpty()
                        AnimatedVisibility(
                            visible = visibilityFavoriteIcon,
                            modifier = Modifier.padding(end = 25.dp)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .clip(shape = MaterialTheme.shapes.large)
                                    .clickable {
                                        onAddFavoriteClick()
                                        visibilityFavoriteIcon = false
                                        onClearClicked()
                                    }
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Add,
                                    contentDescription = "Add to Favorites",
                                )
                                Text(text = "Add", style = MaterialTheme.typography.labelSmall)
                            }
                        }
                    }
                }
            },
            shape = MaterialTheme.shapes.extraLarge,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            enabled = isEnabled
        )
        AnimatedVisibility(visible = expanded) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.2f)
                )
            ) {
                LazyColumn(modifier = Modifier.height(100.dp)) {
                    if (textValue.isNotEmpty()) {
                        if (cities.isNotEmpty()) {
                            items(
                                cities.filter {
                                    it.cityName.lowercase().contains(textValue.lowercase())
                                }.also { if(it.isEmpty()) expandedChange() }
                            ) { city ->
                                SuggestionListItem(
                                    title = city.cityName,
                                    onSelect = { onSuggestionSelect(it) }
                                )
                            }
                        }else if(countries.isNotEmpty()){
                            items(
                                countries.filter {
                                    it.lowercase().contains(textValue.lowercase())
                                }.also { if(it.isEmpty()) expandedChange() }
                            ) { country ->
                                SuggestionListItem(
                                    title = country,
                                    onSelect = { onSuggestionSelect(it) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}