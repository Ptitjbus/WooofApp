package com.mathisviollet.wooofapp.modules.feed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mathisviollet.wooofapp.R
import com.mathisviollet.wooofapp.managers.ProductsManager.getProducts
import com.mathisviollet.wooofapp.managers.UserManager.logOut
import com.mathisviollet.wooofapp.models.Category
import com.mathisviollet.wooofapp.models.Product
import com.mathisviollet.wooofapp.ui.components.CategoryCard
import com.mathisviollet.wooofapp.ui.components.CustomIconButton
import com.mathisviollet.wooofapp.ui.components.SearchBar
import com.mathisviollet.wooofapp.ui.components.SetStatusBarColor
import com.mathisviollet.wooofapp.ui.components.CategoryTitle
import com.mathisviollet.wooofapp.ui.components.ProductCard
import com.mathisviollet.wooofapp.ui.theme.WooofAppTheme

val categories : List<Category> = listOf(
    Category(
        title = "Promenade",
        icon = Icons.Default.Info,
    ),
    Category(
        title = "Nourriture",
        icon = Icons.Default.Info,
    ),
    Category(
        title = "Toilettage",
        icon = Icons.Default.Info,
    ),
)

@Composable
fun FeedView(onProductSelected: (Product) -> Unit = {}, onLogOut: () -> Unit = {}, onViewMap: () -> Unit = {}) {

    val products: List<Product> = getProducts().collectAsState().value

    SetStatusBarColor(topColor = colorResource(id = R.color.white), bottomColor = colorResource(id = R.color.white))
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
        ){
            Box(modifier = Modifier.weight(1f)) {
                SearchBar()
            }
            Spacer(modifier = Modifier.width(20.dp))
            Box {
                CustomIconButton( onClick = {
                    logOut( onLogOut = onLogOut)
//                    addProduct()
                })
            }
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ){
            CategoryTitle(title= "Près de toi", linkTitle = "Sur la carte >", onClick = onViewMap)
        }
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(items = products, itemContent = { product ->
                ProductCard(product, onClick = { onProductSelected(product) })
            })
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ){
            CategoryTitle(title= "Catégories", linkTitle = "Voir tout >")
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
        ) {
            categories.forEach { category ->
                CategoryCard(category = category, onCardClick = {  })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FeedViewPreview() {
    WooofAppTheme {
        FeedView()
    }
}