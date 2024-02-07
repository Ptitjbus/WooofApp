package com.mathisviollet.wooofapp.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mathisviollet.wooofapp.R
import com.mathisviollet.wooofapp.montserratFont


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    placeholder: String = "Chercher"
) {
    var text by remember { mutableStateOf("") }
    val cornerRadius = 12.dp

    TextField(
        modifier = Modifier
            .shadow(
                elevation = 20.dp,
                shape = RoundedCornerShape(cornerRadius),
                spotColor = Color(0x08001226),
                ambientColor = Color(0x08001226)
            ),
        value = text,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "searchIcon",
                tint = colorResource(id = R.color.grey).copy(alpha = 0.5f)
            )
        },
        onValueChange = { text = it },
        placeholder= {
            Text(
                text = placeholder,
                color = colorResource(id = R.color.grey).copy(alpha = 0.5f),
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontFamily = montserratFont,
                    fontWeight = FontWeight.Medium
                )
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White,
            textColor =  Color(0xFF313131),
            disabledTextColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        )
    )
}