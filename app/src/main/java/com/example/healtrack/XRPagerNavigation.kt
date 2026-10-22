package com.example.healtrack

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.coroutines.launch

data class NavigationItem(
    val title: String,
    val icon: ImageVector
)
@Composable
fun XRPagerNavigation(modifier: Modifier = Modifier) {
    val navItems = listOf(
        NavigationItem("New Wounds", Icons.Default.Add),//temp
        NavigationItem("Logged Wounds", Icons.Default.List),//temp
        NavigationItem("Settings", Icons.Default.Settings)//temp
    )

    val pagerState = rememberPagerState(pageCount = { navItems.size })
    val coroutineScope = rememberCoroutineScope()

    NavigationSuiteScaffold(
        modifier = modifier,
        navigationSuiteItems = {
            navItems.forEachIndexed { index, item ->
                item(
                    icon = { Icon(item.icon, contentDescription = item.title) },
                    label = { Text(item.title) },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }
                )
            }
        }
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                //this is where kyle checks for the page and adds the whole pages and locks in and stuff
                Text(
                    text = "Content for: ${navItems[page].title}",
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }
    }
}