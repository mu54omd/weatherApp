package com.musashi.weatherapp.ui.screen.summary.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.musashi.weatherapp.R
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
            modifier = Modifier
                .width(300.dp)
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(50.dp)
                ),
            leadingIcon = {
                if(textValue.isEmpty()) {
                    Icon(
                        imageVector = Icons.Filled.Place,
                        contentDescription = stringResource(R.string.place)
                    )
                }else{
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = stringResource(R.string.clear),
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
                if(label == stringResource(id = R.string.search_bar_city)){
                    cities.forEach { city ->
                        visibilityFavoriteIcon = (city.cityName == textValue || city.cityNameFa == textValue) && textValue.isNotEmpty()
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
                                    contentDescription = stringResource(R.string.add_to_favorites),
                                )
                                Text(text = stringResource(R.string.add), style = MaterialTheme.typography.labelSmall)
                            }
                        }
                    }
                }
            },
            shape = MaterialTheme.shapes.extraLarge,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
            ),
            enabled = isEnabled
        )
        AnimatedVisibility(visible = expanded) {
            Box(modifier = Modifier) {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.2f)
                    )
                ) {
                    val isLtr = LocalLayoutDirection.current == LayoutDirection.Ltr
                    LazyColumn(modifier = Modifier.height(100.dp)) {
                        if (textValue.isNotEmpty()) {
                            if (cities.isNotEmpty()) {
                                if (isLtr) {
                                    items(
                                        cities.filter {
                                            it.cityName.lowercase().contains(textValue.lowercase())
                                        }.also { if (it.isEmpty()) expandedChange() }
                                    ) { city ->
                                        SuggestionListItem(
                                            title = city.cityName,
                                            onSelect = { onSuggestionSelect(it) }
                                        )
                                    }
                                } else {
                                    items(
                                        cities.filter {
                                            it.cityNameFa?.lowercase()
                                                ?.contains(textValue.lowercase()) == true
                                        }.also { if (it.isEmpty()) expandedChange() }
                                    ) { city ->
                                        city.cityNameFa?.let {
                                            SuggestionListItem(
                                                title = it,
                                                onSelect = { item -> onSuggestionSelect(item) }
                                            )
                                        }
                                    }
                                }
                            } else if (countries.isNotEmpty()) {
                                items(
                                    countries.filter {
                                        it.lowercase().contains(textValue.lowercase())
                                    }.also { if (it.isEmpty()) expandedChange() }
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
}