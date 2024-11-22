package com.notfound.hearity.ui.screens.authentication

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.notfound.hearity.R
import com.notfound.hearity.ui.common.AppButton
import com.notfound.hearity.ui.common.AppButtonVariant
import com.notfound.hearity.ui.theme.IconSizeMedium

enum class AuthType {
    LOGIN,
    SIGN_UP
}

@Composable
fun AuthWithGoogleButton(
    modifier: Modifier = Modifier,
    authType: AuthType,
    onClick: () -> Unit = {}
) {
    val text = when (authType) {
        AuthType.LOGIN -> "Login with Google"
        AuthType.SIGN_UP -> "Sign Up with Google"
    }
    AppButton(
        onClick = { onClick() },
        modifier = modifier.fillMaxWidth(),
        label = text,
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