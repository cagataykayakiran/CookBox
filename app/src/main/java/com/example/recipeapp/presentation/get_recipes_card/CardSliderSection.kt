package com.example.recipeapp.presentation.get_recipes_card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.recipeapp.presentation.ui.theme.MainColorPrimary
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CardSliderSection(
    navController: NavController,
    modifier: Modifier = Modifier,
    recipeCardViewModel: RecipeCardViewModel,
) {
    val pagerState = rememberPagerState()
    val cardItemState by recipeCardViewModel.recipesState.collectAsState()

    if (cardItemState.data.isNotEmpty()) {
        LaunchedEffect(true) {
            while (true) {
                yield()
                delay(3000)
                pagerState.animateScrollToPage(
                    page = (pagerState.currentPage + 1) % cardItemState.data.size
                )
            }
        }
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        if (cardItemState.data.isNotEmpty()) {
            HorizontalPager(
                count = cardItemState.data.size,
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) { page ->
                CardItem(navController = navController, cardItemState.data[page])
            }
            HorizontalPagerIndicator(
                pagerState = pagerState,
                activeColor = MainColorPrimary,
                inactiveColor = Color.LightGray,
                indicatorShape = CircleShape,
                indicatorWidth = 8.dp,
                indicatorHeight = 8.dp,
                spacing = 8.dp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        } else {
            Text(
                text = "No items available",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}