package com.hearity_capstone.hearity.ui.screens.article

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.hearity_capstone.hearity.ui.common.AppTopBar
import com.hearity_capstone.hearity.ui.theme.PaddingMedium

@Composable
fun ArticleScreen(navController: NavHostController) {
    Scaffold(
        topBar = { AppTopBar(navController, title = "Article") }
    ) {
        Column(Modifier
            .padding(it)
            .padding(horizontal = PaddingMedium)) {
            Text("Article")
        }
    }
}