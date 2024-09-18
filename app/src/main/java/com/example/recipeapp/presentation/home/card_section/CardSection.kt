package com.example.recipeapp.presentation.home.card_section

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.recipeapp.presentation.ui.theme.MainColorPrimary
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.yield
import com.example.recipeapp.presentation.home.card_section.CardContract.UiAction
import com.example.recipeapp.presentation.home.card_section.CardContract.UiEffect
import com.example.recipeapp.presentation.home.card_section.CardContract.UiState


@Composable
fun CardSection(
    uiState: UiState,
    uiEffect: Flow<UiEffect>,
    onAction: (UiAction) -> Unit,
    onNavigateDetail: (Int) -> Unit,
) {
    LaunchedEffect(Unit) {
        uiEffect.collect { effect ->
            when (effect) {
                is UiEffect.GoToDetail -> onNavigateDetail(effect.recipeId)
            }
        }
    }

    CardSectionContent(uiState = uiState, onAction = onAction)
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CardSectionContent(
    modifier: Modifier = Modifier,
    uiState: UiState,
    onAction: (UiAction) -> Unit,
) {

    val pagerState = rememberPagerState()

    if (uiState.data.isNotEmpty()) {
        LaunchedEffect(true) {
            while (true) {
                yield()
                delay(3000)
                pagerState.animateScrollToPage(
                    page = (pagerState.currentPage + 1) % uiState.data.size
                )
            }
        }
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        if (uiState.data.isNotEmpty()) {
            HorizontalPager(
                count = uiState.data.size,
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) { page ->
                CardItem(
                    uiState.data[page],
                    onRecipeClick = { recipe ->
                        onAction(UiAction.OnRecipeClick(recipe))
                    }
                )
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
        }
        if (uiState.error.isNotEmpty()) {
            Text(text = uiState.error)
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CardSectionScreenPreview(
    @PreviewParameter(CardSectionPreviewProvider::class) uiState: UiState
) {
    CardSectionContent(
        uiState = uiState,
        onAction = {}
    )
}