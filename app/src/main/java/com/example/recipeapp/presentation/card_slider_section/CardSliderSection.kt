package com.example.recipeapp.presentation.card_slider_section

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.recipeapp.domain.model.Recipe
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CardSliderSection(
    modifier: Modifier = Modifier,
    cardItems: List<Recipe>,
) {
    val pagerState = rememberPagerState()

    if (cardItems.isNotEmpty()) {
        LaunchedEffect(Unit) {
            while (true) {
                yield() // Allow other compositions to run
                delay(3000) // Delay for 3 seconds
                pagerState.animateScrollToPage(
                    page = (pagerState.currentPage + 1) % cardItems.size
                )
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        if (cardItems.isNotEmpty()) {
            HorizontalPager(
                count = cardItems.size,
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) { page ->
                CardItem(cardItems[page])
            }
            HorizontalPagerIndicator(
                pagerState = pagerState,
                activeColor = Color.Gray,
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