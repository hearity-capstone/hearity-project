package com.notfound.hearity.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation


@Composable
fun AppTextField(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    readOnly: Boolean = false,
    onValueChange: (String) -> Unit,
    maxLine: Int = 1,
    singleLine: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    OutlinedTextField(
        modifier = modifier,
        onValueChange = { onValueChange(it) },
        label = { Text(text = label, style = MaterialTheme.typography.bodyMedium) },
        value = value,
        readOnly = readOnly,
        shape = MaterialTheme.shapes.large,
        maxLines = maxLine,
        singleLine = singleLine,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon
    )
}

@Composable
fun AppPasswordTextField(
    modifier: Modifier = Modifier,
    value: String,
    label: String = "Password",
    onValueChange: (String) -> Unit,
    isPasswordVisible: Boolean = false,
    onPasswordVisibilityChange: (Boolean) -> Unit = {}
) {
    AppTextField(
        modifier = modifier.fillMaxWidth(),
        onValueChange = { onValueChange(it) },
        label = label,
        value = value,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        leadingIcon =  { Icon(Icons.Default.Lock, contentDescription = "password icon") },
        trailingIcon = { Icon(if(isPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility, contentDescription = "password icon",
            modifier = Modifier.clickable { onPasswordVisibilityChange(!isPasswordVisible) }
        ) }
    )
}

@Composable
fun AppEmailTextField(
    modifier: Modifier = Modifier,
    value: String,
    label: String = "Email",
    onValueChange: (String) -> Unit,
) {
    AppTextField(
        modifier = modifier.fillMaxWidth(),
        onValueChange = { onValueChange(it) },
        value = value,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        label = label,
        leadingIcon =  { Icon(Icons.Default.Email, contentDescription = "password icon") }
    )
}