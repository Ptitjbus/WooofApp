package com.mathisviollet.wooofapp.modules.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.mathisviollet.wooofapp.R
import com.mathisviollet.wooofapp.models.OnboardingPage
import com.mathisviollet.wooofapp.montserratFont
import com.mathisviollet.wooofapp.ui.components.SetStatusBarColor
import com.mathisviollet.wooofapp.ui.theme.WooofAppTheme
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    onClose: () -> Unit
) {
    SetStatusBarColor(topColor = colorResource(id = R.color.gold), bottomColor = colorResource(id = R.color.white))
    val scope = rememberCoroutineScope()
    val onboardingPages = listOf(
        OnboardingPage(
            title = "Garde ces petites créatures",
            description = "De temps en temps, et autour de chez toi.",
            imageDrawable = R.drawable.landing_dog1
        ),
        OnboardingPage(
            title = "Fait 2 heureux. Le maître, la bête.",
            description = "Permets aux maîtres d’animaux de faire des activités.",
            imageDrawable = R.drawable.landing_dog2
        ),
        OnboardingPage(
            title = "Soit rémunéré, rapidement.",
            description = "En moins de 24h, l’argent est transféré sur ton compte.",
            imageDrawable = R.drawable.landing_dog3
        ),
    )
    val pagerState = rememberPagerState(initialPage = 0, pageCount = {
        onboardingPages.count()
    })

    Column(
        modifier = Modifier
            .background(Color(0xFFE6B75A))
            .fillMaxSize()
    ) {
        OnboardingProgress(pagerState = pagerState, nbPages = onboardingPages.count())
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.End,
        ){
            SkipOnboarding(onClose = onClose)
        }
        HorizontalPager(
            state = pagerState
        ) { page ->
            OnboardingPageView(
                currentPage = onboardingPages[page],
                onNavigateLeft = {
                    if (pagerState.currentPage > 0) {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    }
                },
                onNavigateRight = {
                    if (pagerState.currentPage < onboardingPages.size - 1) {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingProgress(pagerState: PagerState, nbPages: Int) {

    val indicatorWidth = 120.dp
    val indicatorHeight = 3.dp
    val activeIndicatorColor = Color.White
    val inactiveIndicatorColor = Color.White.copy(alpha = 0.6f)

    Row(
        Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .background(Color.Transparent)
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(nbPages) { iteration ->
            val color = if (pagerState.currentPage == iteration) activeIndicatorColor else inactiveIndicatorColor
            Box(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .size(width = indicatorWidth, height = indicatorHeight)
                    .background(color, RoundedCornerShape(50))
            )
        }
    }
}

@Composable
fun SkipOnboarding(
    onClose: () -> Unit
) {
    IconButton(onClick = onClose) {
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "close",
            tint = Color.White
        )
    }
}

@Composable
fun OnboardingPageView(
    currentPage:OnboardingPage,
    onNavigateLeft: () -> Unit,
    onNavigateRight: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures { offset ->
                    val size = size.toSize()
                    val middleX = size.width / 2

                    if (offset.x < middleX) {
                        onNavigateLeft()
                    } else {
                        onNavigateRight()
                    }
                }
            },
        contentAlignment = Alignment.Center,
    ){
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = -50.dp)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Color.White.copy(alpha = 0.3f),
                            Color(0xFFE6B75A)
                        )
                    )
                ),
            painter = painterResource(id = currentPage.imageDrawable),
            contentDescription = "description",
            contentScale = ContentScale.FillWidth
        )
        Box(
            contentAlignment = Alignment.BottomCenter
        ){
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 50.dp),
                painter = painterResource(id = R.drawable.onboarding_background_footer),
                contentDescription = "description",
                contentScale = ContentScale.FillWidth
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(40.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    modifier = Modifier,
                    text = currentPage.title,
                    color = Color.Black,
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 30.sp,
                    style = TextStyle(
                        fontFamily = montserratFont,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    text = currentPage.description,
                    color = Color.Black.copy(alpha = 0.5f),
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 20.sp,
                    style = TextStyle(
                        fontFamily = montserratFont,
                        fontWeight = FontWeight.Normal
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardingPreview() {
    WooofAppTheme {
        OnboardingScreen(onClose = {})
    }
}