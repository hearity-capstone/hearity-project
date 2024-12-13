package com.hearity_capstone.hearity.ui.screens.authentication

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.hearity_capstone.hearity.R
import com.hearity_capstone.hearity.graphs.navigateToSignUp
import com.hearity_capstone.hearity.ui.screens.authentication.components.AuthType
import com.hearity_capstone.hearity.ui.screens.authentication.components.AuthWithGoogleButton
import com.hearity_capstone.hearity.ui.screens.authentication.components.LoginForm
import com.hearity_capstone.hearity.ui.screens.authentication.components.OrDivider
import com.hearity_capstone.hearity.ui.theme.PaddingMedium
import com.hearity_capstone.hearity.ui.theme.SpacingItem
import com.hearity_capstone.hearity.ui.theme.SpacingMedium
import com.hearity_capstone.hearity.ui.theme.SpacingSection
import com.hearity_capstone.hearity.ui.theme.SpacingSectionLarge
import com.hearity_capstone.hearity.viewModel.AuthViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    authViewModel: AuthViewModel
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
                text = "Login",
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                ),
            )
            Spacer(Modifier.height(SpacingItem))
            Text(
                text = "Login to your account",
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.bodyMedium,
            )
            Spacer(Modifier.height(SpacingSection))

            LoginForm(
                navController = navController,
                modifier = Modifier.fillMaxWidth(),
                authViewModel = authViewModel
            )


            Spacer(Modifier.height(SpacingSectionLarge))
            OrDivider()
            Spacer(Modifier.height(SpacingSectionLarge))
            AuthWithGoogleButton(authType = AuthType.LOGIN, onClick = {})

            Spacer(Modifier.height(SpacingSection))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "Don't have an account?",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(Modifier.width(SpacingMedium))
                Text(
                    "Sign Up",
                    style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.primary),
                    modifier = Modifier.clickable { navController.navigateToSignUp() }
                )
            }
        }
    }
}
