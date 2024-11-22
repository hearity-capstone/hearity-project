package com.notfound.hearity.ui.screens.authentication

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.notfound.hearity.R
import com.notfound.hearity.graphs.navigateToLogin
import com.notfound.hearity.ui.common.AppButton
import com.notfound.hearity.ui.common.AppButtonVariant
import com.notfound.hearity.ui.common.AppEmailTextField
import com.notfound.hearity.ui.common.AppPasswordTextField
import com.notfound.hearity.ui.theme.IconSizeMedium
import com.notfound.hearity.ui.theme.PaddingMedium
import com.notfound.hearity.ui.theme.SpacingItem
import com.notfound.hearity.ui.theme.SpacingSection
import com.notfound.hearity.ui.theme.SpacingSectionLarge


@Composable
fun SignUpScreen(
   navController: NavController
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Scaffold { it ->
        Column(
            modifier = Modifier
                .fillMaxSize()
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
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface),
            )
            Spacer(Modifier.height(SpacingItem))
            Text(
                text = "Create your account",
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.bodyMedium,
            )
            Spacer(Modifier.height(SpacingSection))

            SignUpForm(
                modifier = Modifier.fillMaxWidth(),
                email = email,
                password = password,
                onEmailChange = { email = it },
                onPasswordChange = { password = it },
            )
            Spacer(Modifier.height(SpacingSectionLarge))

            AppButton(
                onClick = { navController.navigateToLogin() },
                modifier = Modifier.fillMaxWidth(),
                label = "Sign Up",
            )

            Spacer(Modifier.height(SpacingSectionLarge))
            OrDivider()
            Spacer(Modifier.height(SpacingSectionLarge))
            AppButton(
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                label = "Sign up with Google",
                variant = AppButtonVariant.Neutral,
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_google),
                        modifier = Modifier.size(IconSizeMedium),
                        tint = Color.Unspecified,
                        contentDescription = "google icon"
                    )
                },
            )
        }
    }
}
@Composable
private fun SignUpForm(
    modifier: Modifier = Modifier,
    email: String,
    password: String,
    onPasswordChange: (String) -> Unit = {},
    onEmailChange: (String) -> Unit = {},
) {
    var isPasswordVisible by remember { mutableStateOf(false) }
    Column(
        modifier = modifier,
    ) {
        AppEmailTextField(
            value = email,
            onValueChange = { value -> onEmailChange(value) },
            label = "Email",
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(Modifier.height(SpacingItem))
        AppPasswordTextField(
            value = password,
            onValueChange = { value -> onPasswordChange(value) },
            label = "Password",
            modifier = Modifier.fillMaxWidth(),
            isPasswordVisible = isPasswordVisible,
            onPasswordVisibilityChange = { isPasswordVisible = !isPasswordVisible }
        )
    }
}

@Composable
private fun OrDivider() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(
            Modifier.weight(1f),
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.outlineVariant
        )
        Spacer(Modifier.width(SpacingItem))
        Text("Or", style = MaterialTheme.typography.bodyMedium)
        Spacer(Modifier.width(SpacingItem))
        HorizontalDivider(
            Modifier.weight(1f), thickness = 1.dp,
            color = MaterialTheme.colorScheme.outlineVariant
        )
    }
}
