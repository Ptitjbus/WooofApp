package com.mathisviollet.wooofapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mathisviollet.wooofapp.R
import com.mathisviollet.wooofapp.models.Product
import com.mathisviollet.wooofapp.montserratFont


@Composable
fun ProductCard(product : Product, onClick : () -> Unit) {

    Card(
        shape = RoundedCornerShape(28.dp),
        modifier = Modifier.clickable(onClick = { onClick() })
    ) {
        Column(
            modifier = Modifier
                .width(264.dp)
                .background(color = colorResource(id = R.color.white))
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .border(
                        border = BorderStroke(
                            width = 0.dp,
                            color = colorResource(id = R.color.white)
                        ),
                        shape = RoundedCornerShape(12.dp)
                    )
            ) {
                AsyncImage(
                    model = product.imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(228.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 14.dp)
                        .align(Alignment.TopStart),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Pour le "+ product.date,
                        style = TextStyle(
                            fontSize = 15.sp,
                            lineHeight = 16.sp,
                            fontFamily = montserratFont,
                            fontWeight = FontWeight.Normal,
                            color = colorResource(id = R.color.white),
                            textAlign = TextAlign.Right,
                        )
                    )
                    if (product.isFavourite) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Like",
                            tint = colorResource(id = R.color.white),
                            modifier = Modifier.size(24.dp)
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.FavoriteBorder,
                            contentDescription = "Like",
                            tint = colorResource(id = R.color.white),
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 14.dp)
                        .align(Alignment.BottomStart),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier
                            .background(color = Color(0x4DFFFFFF), shape = RoundedCornerShape(size = 30.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Card(
                            shape = CircleShape,
                            modifier = Modifier
                                .size(20.dp)
                                .background(
                                    color = Color(0x4DFFFFFF),
                                    shape = RoundedCornerShape(size = 30.dp)
                                )
                        ) {
                            AsyncImage(
                                model = product.author.profilePictureUrl,
                                contentDescription = "Profile picture",
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = product.author.firstname + " " + product.author.lastname,
                            color = colorResource(id = R.color.white),
                            fontWeight = FontWeight.Bold
                        )
                        if (product.author.isCertified) {
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = "Certified",
                                tint = colorResource(id = R.color.green),
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = product.title,
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 22.sp,
                        fontFamily = montserratFont,
                        fontWeight = FontWeight.Medium,
                        color = colorResource(id = R.color.black),

                        )
                )
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "Info",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}