package com.example.horrorclubapp.presentation.utils

import android.content.Context
import android.widget.Toast

class Utils {

    fun showToast(context: Context, text: String?) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}