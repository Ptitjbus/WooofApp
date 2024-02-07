package com.mathisviollet.wooofapp.modules.mapView

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mathisviollet.wooofapp.R
import com.mathisviollet.wooofapp.montserratFont
import com.mathisviollet.wooofapp.ui.components.MapComponent
import com.mathisviollet.wooofapp.ui.theme.WooofAppTheme

@Composable
fun MapView( onBack: () -> Unit = {} ) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { onBack() },
                    modifier = Modifier
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = colorResource(id = R.color.black)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Près de toi",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = montserratFont,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.grey),
                    )
                )
                Spacer(modifier = Modifier.weight(1f))
            }
        }
        Row {
            MapComponent()
        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MapPreView() {
    WooofAppTheme {
        MapView()
    }
}