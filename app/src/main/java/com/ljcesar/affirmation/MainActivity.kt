package com.ljcesar.affirmation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ljcesar.affirmation.data.DataSource
import com.ljcesar.affirmation.model.Country
import kotlin.math.exp

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
        Column {
            AppBar()
            CountriesList(
                countriesList = DataSource().loadCountries()
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(){
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(R.drawable.mboka_logo),
                    contentDescription = "M'bokas"
                )
                Text("M'bokas", style = MaterialTheme.typography.displayMedium)
            }
        }
    )
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
    CountryCard(Country(R.string.country1,R.string.capital1, R.string.code1, R.string.description1, R.drawable.image1))
}

@Composable
fun CountryCard(country: Country, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
    Column {
        Row(
            modifier = Modifier.padding(15.dp)
        ) {
            Image(
                painter = painterResource(country.imageResourceId),
                contentDescription = stringResource(country.stringNameResourceId),
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(50.dp)),
                contentScale = ContentScale.Crop,
            )
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Box(modifier = Modifier.weight(1f)) {
                    Column(
                        modifier = Modifier.padding(5.dp)
                    ) {
                        Text(
                            text = LocalContext.current.getString(country.stringNameResourceId),
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            text = "${LocalContext.current.getString(country.stringCapitalResourceId)} / ${
                                LocalContext.current.getString(
                                    country.stringCodeResourceId
                                )
                            }",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
                Image(
                    modifier = Modifier
                        .size(20.dp)
                        .clickable { expanded = !expanded },
                    painter = painterResource(if(expanded) R.drawable.arrow_top else R.drawable.arrow_bottom),
                    contentDescription = if(expanded) "Voir moins" else "Voir plus")

            }

        }

        if(expanded){
            Box(
                modifier = Modifier.padding(horizontal = 15.dp)
            ) {
                Text(stringResource(
                    country.stringDescriptionResourceId),
                    style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}