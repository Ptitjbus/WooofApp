package com.mathisviollet.wooofapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.mathisviollet.wooofapp.managers.ProductsManager
import com.mathisviollet.wooofapp.modules.onboarding.OnboardingScreen
import com.mathisviollet.wooofapp.ui.theme.WooofAppTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mathisviollet.wooofapp.managers.UserManager.checkIfUserIsConnected
import com.mathisviollet.wooofapp.modules.feed.FeedView
import com.mathisviollet.wooofapp.modules.mapView.MapView
import com.mathisviollet.wooofapp.modules.productDetail.ProductDetailView
import com.mathisviollet.wooofapp.modules.signIn.LogInForm
import com.mathisviollet.wooofapp.modules.signIn.SignInForm

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WooofAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainView()
                }
            }
        }
    }
}

val montserratFont = FontFamily(
    Font(R.font.montserrat_thin, FontWeight.Thin),
    Font(R.font.montserrat_light, FontWeight.Light),
    Font(R.font.montserrat_regular, FontWeight.Normal),
    Font(R.font.montserrat_medium, FontWeight.Medium),
    Font(R.font.montserrat_semibold, FontWeight.SemiBold),
    Font(R.font.montserrat_bold, FontWeight.Bold)
)

@Composable
fun MainView() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "onboarding") {
        composable("onboarding") { OnboardingScreen(onClose = {
            checkIfUserIsConnected( callbackYes = {
                navController.navigate("map")
            }, callbackNo = {
                navController.navigate("logIn")
            })
        })}
        composable("feedView") {
            FeedView(onProductSelected = { product ->
                ProductsManager.updateSelectedProduct(product = product)
                navController.navigate("productDetail")
            }, onLogOut = {
                navController.navigate("logIn")
            }, onViewMap = {
                navController.navigate("map")
            })
        }
        composable("productDetail") { ProductDetailView( onBack = {
            navController.navigate("feedView")
        }) }
        composable("signIn") { SignInForm( onAlreadySignIn = {
            navController.navigate("logIn")
        }, onSignedIn = {
            navController.navigate("feedView")
        })}
        composable("logIn") { LogInForm( onNewAccount = {
            navController.navigate("signIn")
        }, onPasswordForgot = {
            /* TODO */
        }, onLoggedIn = {
            navController.navigate("feedView")
        })}
        composable("map") { MapView( onBack = {
            navController.navigate("feedView")
        })}
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WooofAppTheme {
        MainView()
    }
}