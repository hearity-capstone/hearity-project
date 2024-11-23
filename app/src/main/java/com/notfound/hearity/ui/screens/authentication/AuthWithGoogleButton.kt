package com.notfound.hearity.ui.screens.authentication

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Text
import com.notfound.hearity.R
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
    OutlinedButton(
        modifier = modifier.fillMaxWidth().height(48.dp),
        shape = MaterialTheme.shapes.large,
        onClick = { onClick() }
    ){
        Icon(
            painter = painterResource(id = R.drawable.ic_google),
            modifier = Modifier.size(IconSizeMedium),
            tint = Color.Unspecified,
            contentDescription = "google icon"
        )
        Spacer(Modifier.weight(1f))
        Text(text, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurface)
        Spacer(Modifier.weight(1f))
    }
}