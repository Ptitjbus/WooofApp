package com.mathisviollet.wooofapp.modules.productDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mathisviollet.wooofapp.R
import com.mathisviollet.wooofapp.managers.ProductsManager
import com.mathisviollet.wooofapp.models.Product
import com.mathisviollet.wooofapp.montserratFont
import com.mathisviollet.wooofapp.ui.components.CustomButton
import com.mathisviollet.wooofapp.ui.components.CustomIconButton
import com.mathisviollet.wooofapp.ui.components.SetStatusBarColor
import com.mathisviollet.wooofapp.ui.theme.WooofAppTheme

@Composable
fun ProductDetailView(onBack : () -> Unit = {  }) {
    val product : State<Product?> = ProductsManager.selectedProduct.collectAsState()

    SetStatusBarColor(topColor = colorResource(id = R.color.white), bottomColor = colorResource(id = R.color.white))
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
        ) {
            AsyncImage(
                model = product.value?.imageUrl,
                contentDescription = "Product Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            IconButton(
                onClick = onBack,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp)) {
                Icon (
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = colorResource(id = R.color.white)
                )
            }
            IconButton(onClick = { /* TODO: handle share action */ },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "Share",
                    tint = colorResource(id = R.color.white)
                )
            }
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 14.dp)
                    .fillMaxWidth()
                    .align(Alignment.BottomStart),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier
                        .background(
                            color = Color(0x4DFFFFFF),
                            shape = RoundedCornerShape(size = 30.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Card(
                        shape = CircleShape,
                        modifier = Modifier
                            .size(40.dp)
                            .background(
                                color = colorResource(id = R.color.white).copy(alpha = 0.2f),
                                shape = RoundedCornerShape(size = 30.dp)
                            )
                    ) {
                        AsyncImage(
                            model = product.value?.author?.profilePictureUrl,
                            contentDescription = "Profile picture",
                            modifier = Modifier.size(40.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Row (
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Text(
                                text = product.value?.author?.firstname + " " + product.value?.author?.lastname,
                                color = colorResource(id = R.color.white),
                                fontWeight = FontWeight.Bold
                            )
                            if (product.value?.author?.isCertified == true) {
                                Spacer(modifier = Modifier.width(4.dp))
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = "Certified",
                                    tint = colorResource(id = R.color.green),
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                        Row (
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = "Rating",
                                tint = colorResource(id = R.color.white),
                                modifier = Modifier.size(13.dp)
                            )
                            Text(
                                text = "4.9",
                                style = TextStyle(
                                    fontSize = 13.sp,
                                    fontFamily = montserratFont,
                                    fontWeight = FontWeight.SemiBold,
                                    color = colorResource(id = R.color.white).copy(alpha = 0.7f),
                                )
                            )
                        }
                    }
                }
                Text(
                    text = product.value?.date ?: "",
                    color = colorResource(id = R.color.white)
                )
            }
        }
        // The title and description part of the page
        Column(
            modifier = Modifier
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Row {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                    ) {
                        Text(
                            text = product.value?.title?: "",
                            modifier = Modifier.weight(1f),
                            style = TextStyle(
                                fontSize = 26.sp,
                                fontFamily = montserratFont,
                                fontWeight = FontWeight.Bold,
                                color = colorResource(id = R.color.grey),
                            )
                        )
                        IconButton(onClick = { /*TODO*/ }) {
                            if (product.value?.isFavourite == true) {
                                Icon(
                                    imageVector = Icons.Default.Favorite,
                                    contentDescription = "Like",
                                    tint = colorResource(id = R.color.grey),
                                    modifier = Modifier.size(28.dp)
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.Default.FavoriteBorder,
                                    contentDescription = "Like",
                                    tint = colorResource(id = R.color.grey),
                                    modifier = Modifier.size(28.dp)
                                )
                            }
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                    ) {
                        Text(
                            text = product.value?.description?: "",
                            style = TextStyle(
                                fontSize = 16.sp,
                                lineHeight = 22.sp,
                                fontFamily = montserratFont,
                                fontWeight = FontWeight.Medium,
                                color = colorResource(id = R.color.grey),
                                letterSpacing = 0.16.sp,
                            )
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon (
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "Like",
                            tint = colorResource(id = R.color.grey),
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Rémunéré ${product.value?.price}€",
                            style = TextStyle(
                                fontSize = 14.sp,
                                lineHeight = 17.sp,
                                fontFamily = montserratFont,
                                fontWeight = FontWeight.SemiBold,
                                color = colorResource(id = R.color.grey),
                                letterSpacing = 0.14.sp,
                            )
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon (
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "Like",
                            tint = colorResource(id = R.color.grey),
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = (product.value?.place?.address ?: "").uppercase(),
                            style = TextStyle(
                                fontSize = 14.sp,
                                lineHeight = 17.sp,
                                fontFamily = montserratFont,
                                fontWeight = FontWeight.SemiBold,
                                color = colorResource(id = R.color.grey),
                                letterSpacing = 0.14.sp,
                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "400m d’ici",
                            style = TextStyle(
                                fontSize = 13.sp,
                                lineHeight = 17.sp,
                                fontFamily = montserratFont,
                                fontWeight = FontWeight.Medium,
                                color = colorResource(id = R.color.grey).copy(alpha = 0.5f),
                                letterSpacing = 0.13.sp,
                            )
                        )
                    }
                }
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                CustomIconButton(
                    imageVector = Icons.Default.MailOutline,
                    backgroundColor = colorResource(id = R.color.white),
                    textColor = colorResource(id = R.color.purple_200),
                )
                Spacer(modifier = Modifier.width(16.dp),)
                CustomButton(label = "Se proposer") { /*TODO*/ }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ProductDetailPreView() {
    WooofAppTheme {
        ProductDetailView()
    }
}