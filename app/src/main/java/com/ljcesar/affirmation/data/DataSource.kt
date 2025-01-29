package com.ljcesar.affirmation.data

import com.ljcesar.affirmation.R
import com.ljcesar.affirmation.model.Country

class DataSource {
     fun loadCountries(): List<Country> {
         return listOf<Country>(
             Country(R.string.country1, R.string.capital1, R.string.code1, R.string.description1, R.drawable.image1),
             Country(R.string.country2, R.string.capital2, R.string.code2, R.string.description2, R.drawable.image2),
             Country(R.string.country3, R.string.capital3, R.string.code3, R.string.description3, R.drawable.image3),
             Country(R.string.country4, R.string.capital4, R.string.code4, R.string.description4, R.drawable.image4),
             Country(R.string.country5, R.string.capital5, R.string.code5, R.string.description5, R.drawable.image5),
             Country(R.string.country6, R.string.capital6, R.string.code6, R.string.description6, R.drawable.image6),
             Country(R.string.country7, R.string.capital7, R.string.code7, R.string.description7, R.drawable.image7),
             Country(R.string.country8, R.string.capital8, R.string.code8, R.string.description8, R.drawable.image8),
             Country(R.string.country9, R.string.capital9, R.string.code9, R.string.description9, R.drawable.image9),
             Country(R.string.country10, R.string.capital10, R.string.code10, R.string.description10, R.drawable.image10),)
     }
}