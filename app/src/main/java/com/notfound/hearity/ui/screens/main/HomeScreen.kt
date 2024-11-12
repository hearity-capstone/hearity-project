package com.notfound.hearity.ui.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.notfound.hearity.ui.screens.main.home.LineChart

@Composable
fun HomeScreen(onClick: () -> Unit) {
    Scaffold(
        contentWindowInsets = WindowInsets(0.dp), // To consume status bar
        topBar = { TopBar(onClick = onClick) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = it.calculateTopPadding(), horizontal = 16.dp),
        ) {
            Spacer(Modifier.height(16.dp))
            LineChart()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun TopBar(onClick: () -> Unit = {}) {
    TopAppBar(
        title = {
            Column {
                Row {
                    Text(
                        "Hello,",
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Medium)
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        "User",
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
                    )
                }
                Text("Have a nice day!", style = MaterialTheme.typography.labelSmall)
            }
        },
        actions = {
            IconButton(onClick = { onClick() }) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Profile",
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    )
}