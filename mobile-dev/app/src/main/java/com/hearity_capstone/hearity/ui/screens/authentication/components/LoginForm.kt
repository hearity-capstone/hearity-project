package com.hearity_capstone.hearity.ui.screens.authentication.components

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.hearity_capstone.hearity.graphs.navigateToMainGraphAndClearBackStack
import com.hearity_capstone.hearity.ui.common.AppButton
import com.hearity_capstone.hearity.ui.common.AppEmailTextField
import com.hearity_capstone.hearity.ui.common.AppPasswordTextField
import com.hearity_capstone.hearity.ui.common.LoadingDialog
import com.hearity_capstone.hearity.ui.screens.authentication.AuthViewModel
import com.hearity_capstone.hearity.ui.theme.SpacingItem
import com.hearity_capstone.hearity.ui.theme.SpacingSectionLarge
import com.hearity_capstone.hearity.util.ValidatorUtils


@Composable
fun LoginForm(
    navController: NavController,
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var isPasswordVisible by remember { mutableStateOf(false) }

    var isEmailValid by remember { mutableStateOf(true) }
    var isPasswordValid by remember { mutableStateOf(true) }

    val isLoading by authViewModel.isLoading.collectAsState()
    val isLoggedIn by authViewModel.isLoggedIn.collectAsState()
    val errorState by authViewModel.errorState.collectAsState()

    fun validateForm() {
        isEmailValid = ValidatorUtils.validateEmail(email)
        isPasswordValid = ValidatorUtils.validatePassword(password)
    }

    if (isLoading) {
        LoadingDialog()
    }

    LaunchedEffect(errorState) {
        errorState?.let { e ->
            Toast.makeText(navController.context, e, Toast.LENGTH_SHORT).show()
            authViewModel.clearErrorState()
        }
    }

    LaunchedEffect(isLoggedIn) {
        if (isLoggedIn) {
            navController.navigateToMainGraphAndClearBackStack()
        }
    }

    fun onLogin() {
        validateForm()
        if (isEmailValid && isPasswordValid) {
            authViewModel.login(email, password)
        }
    }


    Column(
        modifier = modifier,
    ) {
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

        Spacer(Modifier.height(SpacingSectionLarge))

        AppButton(
            onClick = { onLogin() },
            enabled = isPasswordValid && isEmailValid,
            modifier = Modifier.fillMaxWidth(),
            label = "Login",
        )
    }
}
