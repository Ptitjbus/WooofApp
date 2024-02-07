package com.mathisviollet.wooofapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mathisviollet.wooofapp.R
import com.mathisviollet.wooofapp.montserratFont
import com.mathisviollet.wooofapp.ui.theme.WooofAppTheme

@Composable
fun CustomButton(
    label: String,
    onClick: () -> Unit,
) {
    val cornerRadius = 6.dp

    Button(
        modifier = Modifier
            .height(48.dp)
            .fillMaxWidth()
            .background(
                color = colorResource(id = R.color.purple_200),
                shape = RoundedCornerShape(size = 12.dp)
            )
            .shadow(
                elevation = 20.dp,
                shape = RoundedCornerShape(cornerRadius),
                spotColor = Color(0x08001226),
                ambientColor = Color(0x08001226)
            ),
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.purple_200),
            contentColor = colorResource(id = R.color.white)
        )
    ) {
        Text(
            text = label,
            style = TextStyle(
                fontSize = 14.sp,
                lineHeight = 17.sp,
                fontFamily = montserratFont,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.white),
                letterSpacing = 0.14.sp,
            )
        )
    }
}


@Composable
fun CustomTransparentButton(
    label : String,
    onClick : () -> Unit,
) {

    Button(
        modifier = Modifier
            .height(32.dp)
            .fillMaxWidth()
            .background(color = Color.Transparent, shape = RoundedCornerShape(size = 12.dp)),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = colorResource(id = R.color.black)
        )
    ) {
        Text(
            text = label,
            style = TextStyle(
                fontSize = 12.55.sp,
                fontFamily = montserratFont,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
            )
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun CustomButtonPreView() {
    WooofAppTheme {
        CustomButton( label = "test") { /*TODO*/ }
    }
}