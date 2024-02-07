package com.mathisviollet.wooofapp.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
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
fun TextInput(
    placeholder: String = "Chercher",
    text:String = "",
    onTextChange: (String) -> Unit = {}
) {

    var isFocused by remember { mutableStateOf(false) }

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .border(
                width = 1.79272.dp,
                color = if (isFocused) colorResource(id = R.color.purple_200) else  colorResource(id = R.color.purple_50),
                shape = RoundedCornerShape(size = 8.96359.dp)
            )
            .onFocusChanged { focusState ->
                isFocused = focusState.isFocused
            },
        value = text,
        onValueChange = { onTextChange(it) },
        placeholder= {
            Text(
                text = placeholder,
                style = TextStyle(
                    fontSize = 14.34.sp,
                    fontFamily = montserratFont,
                    fontWeight = FontWeight(500),
                    textAlign = TextAlign.Center,
                )
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = colorResource(id = R.color.purple_50),
            textColor =  colorResource(id = R.color.lightGrey),
            disabledTextColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        )
    )
}