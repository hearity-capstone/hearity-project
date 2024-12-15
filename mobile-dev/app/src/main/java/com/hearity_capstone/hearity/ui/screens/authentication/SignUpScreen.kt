package com.hearity_capstone.hearity.ui.screens.authentication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
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
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
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
    navController: NavController,
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
    val genderOptions = listOf("Male", "Female")

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("Male") }
    var address by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var repeatPassword by remember { mutableStateOf("") }

    var isPasswordVisible by remember { mutableStateOf(false) }
    var isRepeatPasswordVisible by remember { mutableStateOf(false) }
    var isFirstNameValid by remember { mutableStateOf(true) }
    var isLastNameValid by remember { mutableStateOf(true) }
    var isEmailValid by remember { mutableStateOf(true) }
    var isPhoneNumberValid by remember { mutableStateOf(true) }
    var isAddressValid by remember { mutableStateOf(true) }
    var isCityValid by remember { mutableStateOf(true) }
    var isPasswordValid by remember { mutableStateOf(true) }
    var isRepeatPasswordValid by remember { mutableStateOf(true) }


    fun validateForm() {
        isFirstNameValid = ValidatorUtils.validateName(firstName)
        isLastNameValid = ValidatorUtils.validateName(lastName)
        isEmailValid = ValidatorUtils.validateEmail(email)
        isPhoneNumberValid = ValidatorUtils.validatePhoneNumber(phoneNumber)
        isAddressValid = ValidatorUtils.validateAddress(address)
        isCityValid = ValidatorUtils.validateCity(city)
        isPasswordValid = ValidatorUtils.validatePassword(password)
        isRepeatPasswordValid = repeatPassword == password
    }

    Column(
        modifier = modifier,
    ) {
        AppTextField(
            value = firstName,
            label = "First Name",
            onValueChange = { value ->
                firstName = value
                isFirstNameValid = ValidatorUtils.validateName(value)
            },
            modifier = Modifier.fillMaxWidth(),
            isError = !isFirstNameValid,
            leadingIcon = {
                Icon(Icons.Filled.Person, contentDescription = "Person Icon")
            }
        )
        AppTextField(
            value = lastName,
            label = "Last Name",
            onValueChange = { value ->
                lastName = value
                isFirstNameValid = ValidatorUtils.validateName(value)
            },
            modifier = Modifier.fillMaxWidth(),
            isError = !isLastNameValid,
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

        Spacer(Modifier.height(SpacingSection))
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
        Spacer(Modifier.height(SpacingSection))
        AppTextField(
            value = phoneNumber,
            label = "Phone Number",
            onValueChange = { value ->
                phoneNumber = value
                isPhoneNumberValid = ValidatorUtils.validatePhoneNumber(value)
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
            modifier = Modifier.fillMaxWidth(),
            isError = !isPhoneNumberValid,
            leadingIcon = {
                Icon(Icons.Filled.Phone, contentDescription = "Phone Icon")
            }
        )
        AppTextField(
            value = address,
            label = "Address",
            onValueChange = { value ->
                address = value
                isAddressValid = ValidatorUtils.validateAddress(value)
            },
            modifier = Modifier.fillMaxWidth(),
            isError = !isAddressValid,
            leadingIcon = {
                Icon(Icons.Filled.Home, contentDescription = "Address Icon")
            })
        AppTextField(
            value = city,
            label = "City",
            onValueChange = { value ->
                city = value
                isCityValid = ValidatorUtils.validateAddress(value)
            },
            modifier = Modifier.fillMaxWidth(),
            isError = !isCityValid,
            leadingIcon = {
                Icon(Icons.Filled.LocationCity, contentDescription = "City Icon")
            })
        Spacer(Modifier.height(SpacingItem))
        GenderRadio(
            genderOptions = genderOptions,
            selectedOption = gender,
            onOptionSelected = { gender = it }
        )
        Spacer(Modifier.height(SpacingSectionLarge))

        AppButton(
            onClick = {
                validateForm()
                if (isFirstNameValid && isEmailValid && isPasswordValid && isRepeatPasswordValid) {
                    navController.navigateToLogin()
                }
            },
            enabled = isFirstNameValid && isEmailValid && isPasswordValid && isRepeatPasswordValid,
            modifier = Modifier.fillMaxWidth(),
            label = "Sign Up",
        )
        Spacer(Modifier.height(SpacingItem))
        AppButton(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth(),
            label = "Back To Login",
        )

    }
}


@Composable
private fun GenderRadio(
    genderOptions: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
) {

    Column(Modifier.selectableGroup()) {
        Text("Gender", style = MaterialTheme.typography.titleSmall)
        genderOptions.forEach { text ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = {
                            onOptionSelected(text)
                        },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = null
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyMedium.merge(),
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}