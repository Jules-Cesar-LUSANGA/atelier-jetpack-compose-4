package com.ljcesar.affirmation.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Country(
    @StringRes val stringNameResourceId: Int,
    @StringRes val stringCapitalResourceId: Int,
    @StringRes val stringCodeResourceId: Int,
    @StringRes val stringDescriptionResourceId: Int,
    @DrawableRes val imageResourceId: Int
)