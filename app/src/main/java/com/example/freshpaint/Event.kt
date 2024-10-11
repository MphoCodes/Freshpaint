package com.example.freshpaint

import androidx.annotation.DrawableRes

data class Event(val title: String, val date: String, val description: String, @DrawableRes val imageResId: Int )
