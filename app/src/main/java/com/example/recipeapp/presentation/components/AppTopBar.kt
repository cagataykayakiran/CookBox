package com.example.recipeapp.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.recipeapp.presentation.ui.theme.MainColorPrimary
import com.example.recipeapp.work_manager.MyViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(modifier: Modifier = Modifier, workerViewModel: MyViewModel = hiltViewModel()) {

    val dataState by workerViewModel.dataState.collectAsState()

    val header = buildAnnotatedString {
        append("Discovery Your \nFavourite ")
        withStyle(style = SpanStyle(color = MainColorPrimary)) {
            append("Recipes !")
        }
    }

    TopAppBar(
        modifier = modifier.padding(top = 15.dp),
        title = {
            Text(text = header)
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.White
        ),
        actions = {
            BadgeInteractiveExample(dataState)
        }
    )
}

@Composable
fun BadgeInteractiveExample(dataState: Int) {
        BadgedBox(
            modifier = Modifier.padding(end = 20.dp),
            badge = {
                if (dataState > 0 ) {
                    Badge(
                        containerColor = Color.White,
                        contentColor = Color.Red
                    ) {
                        Text(text = "$dataState", fontSize = 14.sp)
                    }
                } else {
                    Badge(
                        containerColor = Color.White,
                        contentColor = Color.Red
                    ) {
                        Text(text = "$dataState", fontSize = 14.sp)
                    }
                }
            }
        ) {
            Icon(
                imageVector = Icons.Filled.Notifications,
                contentDescription = "Shopping cart",
            )
        }
    }
