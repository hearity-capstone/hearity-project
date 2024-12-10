package com.hearity_capstone.hearity.ui.screens.authentication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hearity_capstone.hearity.R
import com.hearity_capstone.hearity.graphs.navigateToLogin
import com.hearity_capstone.hearity.ui.common.AppButton
import com.hearity_capstone.hearity.ui.common.AppEmailTextField
import com.hearity_capstone.hearity.ui.common.AppPasswordTextField
import com.hearity_capstone.hearity.ui.common.AppTextField
import com.hearity_capstone.hearity.ui.screens.authentication.components.AuthType
import com.hearity_capstone.hearity.ui.screens.authentication.components.AuthWithGoogleButton
import com.hearity_capstone.hearity.ui.screens.authentication.components.OrDivider
import com.hearity_capstone.hearity.ui.theme.PaddingMedium
import com.hearity_capstone.hearity.ui.theme.SpacingItem
import com.hearity_capstone.hearity.ui.theme.SpacingSection
import com.hearity_capstone.hearity.ui.theme.SpacingSectionLarge
import com.hearity_capstone.hearity.util.ValidatorUtils


@Composable
fun SignUpScreen(
    navController: NavController
) {


    Scaffold { it ->
        Column(
            modifier = Modifier
                .imePadding()
                .verticalScroll(rememberScrollState())
                .padding(it)
                .padding(PaddingMedium),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_logo_colored),
                contentDescription = "Logo",
                modifier = Modifier
                    .height(200.dp)
                    .width(200.dp)
            )
            Spacer(Modifier.height(SpacingItem))
            Text(
                text = "Sign Up",
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                ),
            )
            Spacer(Modifier.height(SpacingItem))
            Text(
                text = "Create your account",
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.bodyMedium,
            )
            Spacer(Modifier.height(SpacingSection))

            SignUpForm(
                navController = navController,
                modifier = Modifier.fillMaxWidth(),
            )


            Spacer(Modifier.height(SpacingSectionLarge))
            OrDivider()
            Spacer(Modifier.height(SpacingSectionLarge))
            AuthWithGoogleButton(authType = AuthType.SIGN_UP, onClick = {})
        }
    }
}

@Composable
private fun SignUpForm(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var repeatPassword by remember { mutableStateOf("") }

    var isPasswordVisible by remember { mutableStateOf(false) }
    var isRepeatPasswordVisible by remember { mutableStateOf(false) }

    var isNameValid by remember { mutableStateOf(true) }
    var isEmailValid by remember { mutableStateOf(true) }
    var isPasswordValid by remember { mutableStateOf(true) }
    var isRepeatPasswordValid by remember { mutableStateOf(true) }


    fun validateForm() {
        isNameValid = ValidatorUtils.validateName(name)
        isEmailValid = ValidatorUtils.validateEmail(email)
        isPasswordValid = ValidatorUtils.validatePassword(password)
        isRepeatPasswordValid = repeatPassword == password
    }

    Column(
        modifier = modifier,
    ) {
        AppTextField(
            value = name,
            label = "Name",
            onValueChange = { value ->
                name = value
                isNameValid = ValidatorUtils.validateName(value)
            },
            modifier = Modifier.fillMaxWidth(),
            isError = !isNameValid,
            leadingIcon = {
                Icon(Icons.Filled.Person, contentDescription = "Person Icon")
            }
        )
        AppEmailTextField(
            value = email,
            onValueChange = { value ->
                email = value
                isEmailValid = ValidatorUtils.validateEmail(value)
            },
            isError = !isEmailValid,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(Modifier.height(SpacingItem))
        AppPasswordTextField(
            value = password,
            onValueChange = { value ->
                password = value
                isPasswordValid = ValidatorUtils.validatePassword(value)
            },
            modifier = Modifier.fillMaxWidth(),
            isPasswordVisible = isPasswordVisible,
            isError = !isPasswordValid,
            onPasswordVisibilityChange = { isPasswordVisible = !isPasswordVisible }
        )
        Spacer(Modifier.height(SpacingItem))
        AppPasswordTextField(
            value = repeatPassword,
            label = "Repeat Password",
            onValueChange = { value ->
                repeatPassword = value
                isRepeatPasswordValid = repeatPassword == password
            },
            modifier = Modifier
                .fillMaxWidth(),
            isPasswordVisible = isRepeatPasswordVisible,
            isError = !isRepeatPasswordValid,
            onPasswordVisibilityChange = { isRepeatPasswordVisible = !isRepeatPasswordVisible }
        )
        Spacer(Modifier.height(SpacingSectionLarge))

        AppButton(
            onClick = {
                validateForm()
                if (isNameValid && isEmailValid && isPasswordValid && isRepeatPasswordValid) {
                    navController.navigateToLogin()
                }
            },
            enabled = isNameValid && isEmailValid && isPasswordValid && isRepeatPasswordValid,
            modifier = Modifier.fillMaxWidth(),
            label = "Sign Up",
        )

    }
}
