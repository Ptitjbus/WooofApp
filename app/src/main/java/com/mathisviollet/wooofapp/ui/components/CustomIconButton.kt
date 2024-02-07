package com.mathisviollet.wooofapp.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.mathisviollet.wooofapp.R

@Composable
fun CustomIconButton(
    imageVector: ImageVector = Icons.Default.List,
    onClick: () -> Unit = {},
    backgroundColor: Color = colorResource(id = R.color.purple_200),
    textColor: Color = colorResource(id = R.color.white)
) {
    val buttonSize = 48.dp
    val cornerRadius = 12.dp

    IconButton(
        modifier = Modifier
            .size(buttonSize)
            .shadow(
                elevation = 12.dp,
                shape = RoundedCornerShape(cornerRadius),
                spotColor = Color(0x6EBAA9FF),
                ambientColor = Color(0x6EBAA9FF)
            ),
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = backgroundColor,
            contentColor = textColor
        )
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = "Settings Icon",
            tint = textColor,
        )
    }
}