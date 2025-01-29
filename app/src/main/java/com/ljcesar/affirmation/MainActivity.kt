package com.ljcesar.affirmation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ljcesar.affirmation.data.DataSource
import com.ljcesar.affirmation.model.Country

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CountriesApp()
        }
    }
}

@Composable
fun CountriesApp() {
    val layoutDirection = LocalLayoutDirection.current
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(
                start = WindowInsets.safeDrawing
                    .asPaddingValues()
                    .calculateStartPadding(layoutDirection),
                end = WindowInsets.safeDrawing
                    .asPaddingValues()
                    .calculateEndPadding(layoutDirection),
            ),
    ) {
        CountriesList(
            countriesList = DataSource().loadCountries()
        )
    }
}

@Composable
fun CountriesList(countriesList: List<Country>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(countriesList) { affirmation : Country ->
            CountryCard(
                country = affirmation,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview
@Composable
private fun CountryCardPreview() {
    CountryCard(Country(R.string.country1,R.string.capital1, R.string.code1, R.drawable.image1))
}

@Composable
fun CountryCard(country: Country, modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier.padding(15.dp)
    ) {
        Image(
            painter = painterResource(country.imageResourceId),
            contentDescription = stringResource(country.stringNameResourceId),
            modifier = Modifier.size(80.dp),
            contentScale = ContentScale.Crop,
        )
        Column(
            modifier = Modifier.padding(5.dp)
        ) {
            Text(
                text = LocalContext.current.getString(country.stringNameResourceId),
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = "${LocalContext.current.getString(country.stringCapitalResourceId)} / ${LocalContext.current.getString(country.stringCodeResourceId)}",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}