package com.mathisviollet.wooofapp.modules.signIn

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mathisviollet.wooofapp.R
import com.mathisviollet.wooofapp.managers.UserManager.logIn
import com.mathisviollet.wooofapp.montserratFont
import com.mathisviollet.wooofapp.ui.components.CustomButton
import com.mathisviollet.wooofapp.ui.components.CustomIconButton
import com.mathisviollet.wooofapp.ui.components.CustomTransparentButton
import com.mathisviollet.wooofapp.ui.components.TextInput
import com.mathisviollet.wooofapp.ui.theme.WooofAppTheme

@Composable
fun LogInForm(onNewAccount : () -> Unit = {}, onPasswordForgot : () -> Unit = {}, onLoggedIn: () -> Unit = {}) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 28.dp, vertical = 56.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Row {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(56.dp),
            ){
                Row {
                    Column (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            text = "Connexion",
                            modifier = Modifier.padding(bottom = 12.dp),
                            style = TextStyle(
                                fontSize = 27.21.sp,
                                fontFamily = montserratFont,
                                fontWeight = FontWeight.Bold,
                                color = colorResource(id = R.color.purple_200),
                                textAlign = TextAlign.Center,
                            )
                        )
                        Text(
                            text = "Te re-voilà ! Plein d’animaux ont besoin de toi !",
                            style = TextStyle(
                                fontSize = 12.7.sp,
                                fontFamily = montserratFont,
                                fontWeight = FontWeight.SemiBold,
                                color = colorResource(id = R.color.black) ,
                                textAlign = TextAlign.Center,
                            )
                        )
                    }
                }
                Row {
                    Column (
                        verticalArrangement = Arrangement.spacedBy(23.dp),
                    ){
                        TextInput(placeholder = "Email", text = email, onTextChange = { email = it })
                        TextInput(placeholder = "Mot de passe" , text = password, onTextChange = { password = it })
                        Row (
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End,
                        ){
                            Text(
                                modifier = Modifier.clickable(onClick = onPasswordForgot),
                                text = "Mot de passe oublié ?",
                                style = TextStyle(
                                    fontSize = 12.72.sp,
                                    fontFamily = montserratFont,
                                    fontWeight = FontWeight.SemiBold,
                                    color = colorResource(id = R.color.purple_200),
                                    textAlign = TextAlign.Center,
                                )
                            )
                        }
                    }
                }
                Row {
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(23.dp),
                    ){
                        CustomButton(label = "Me connecter") { logIn(email, password, onLoggedIn = onLoggedIn) }
                        CustomTransparentButton(label = "Créer un compte", onClick = onNewAccount)
                    }
                }
            }
        }
        Row {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(23.dp),
            ){
                Text(
                    text = "Ou continue avec",
                    style = TextStyle(
                        fontSize = 12.72.sp,
                        fontFamily = montserratFont,
                        fontWeight = FontWeight.SemiBold,
                        color = colorResource(id = R.color.purple_200),
                        textAlign = TextAlign.Center,
                    )
                )
                Row (
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                ){
                    CustomIconButton(
                        imageVector = Icons.Default.Person,
                        backgroundColor = colorResource(id = R.color.whiteGrey),
                        textColor = colorResource(id = R.color.black),
                        onClick = { /*TODO*/ }
                    )
                    CustomIconButton(
                        imageVector = Icons.Default.Person,
                        backgroundColor = colorResource(id = R.color.whiteGrey),
                        textColor = colorResource(id = R.color.black),
                        onClick = { /*TODO*/ }
                    )
                    CustomIconButton(
                        imageVector = Icons.Default.Person,
                        backgroundColor = colorResource(id = R.color.whiteGrey),
                        textColor = colorResource(id = R.color.black),
                        onClick = { /*TODO*/ }
                    )
                }
            }
        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun LogInFormPreview() {
    WooofAppTheme {
        LogInForm()
    }
}