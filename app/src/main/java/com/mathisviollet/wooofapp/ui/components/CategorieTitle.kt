package com.mathisviollet.wooofapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.mathisviollet.wooofapp.R
import com.mathisviollet.wooofapp.montserratFont


@Composable
fun CategoryTitle(
    title:String,
    linkTitle: String,
    onClick: () -> Unit = {}
){
    Text(
        text = title,
        style = TextStyle(
            fontSize = 20.sp,
            fontFamily = montserratFont,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.black),
        )
    )
    Spacer(modifier = Modifier.fillMaxWidth(0.6f))
    Text(
        text = linkTitle,
        style = TextStyle(
            fontSize = 13.sp,
            lineHeight = 16.sp,
            fontFamily = montserratFont,
            fontWeight = FontWeight.Normal,
            color = Color(0xFF989898),
            textAlign = TextAlign.Right,
        ),
        modifier = Modifier.clickable { onClick() }
    )
}