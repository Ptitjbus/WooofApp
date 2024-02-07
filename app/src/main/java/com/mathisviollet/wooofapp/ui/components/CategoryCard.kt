package com.mathisviollet.wooofapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mathisviollet.wooofapp.R
import com.mathisviollet.wooofapp.models.Category
import com.mathisviollet.wooofapp.montserratFont


@Composable
fun CategoryCard(
    category: Category,
    onCardClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCardClick() }
            .shadow(
                elevation = 20.dp,
                spotColor = Color(0x08001226),
                ambientColor = Color(0x08001226)
            )
            .padding(vertical = 8.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.white))
                .shadow(
                    elevation = 20.dp,
                    spotColor = Color(0x08001226),
                    ambientColor = Color(0x08001226)
                )
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = category.icon,
                contentDescription = null,
                modifier = Modifier.size(40.dp),
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = category.title,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = montserratFont,
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(id = R.color.grey),
                    )
                )
                Text(
                    text = "24 disponibles",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = montserratFont,
                        fontWeight = FontWeight.Medium,
                        color = colorResource(id = R.color.grey).copy(alpha = 0.5f),
                    )
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Navigate",
                modifier = Modifier.size(24.dp),
                tint = Color.Gray
            )
        }
    }
}