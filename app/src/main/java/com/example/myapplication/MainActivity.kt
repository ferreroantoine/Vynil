package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LibraryMusic
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.myapplication.app.ShrinkOnPressBox
import com.example.myapplication.data.Home
import com.example.myapplication.data.Leaderboard
import com.example.myapplication.data.Library
import com.example.myapplication.data.MainTab
import com.example.myapplication.data.Profile
import com.example.myapplication.data.Search
import com.example.myapplication.presentation.CreateMenu
import com.example.myapplication.presentation.HomePage
import com.example.myapplication.presentation.LeaderboardPage
import com.example.myapplication.presentation.LibraryPage
import com.example.myapplication.presentation.ProfilePage
import com.example.myapplication.presentation.SearchPage
import com.example.myapplication.ui.theme.ColorTheme
import com.example.myapplication.ui.theme.MyApplicationTheme

private const val NAV_BAR_ALPHA = 0.8f
private val CLOSE_CREATE_MENU_SIZE = 48.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                VynilAppMain()
            }
        }
    }
}
@Composable
fun VynilAppMain(){
    var selectedTab by rememberSaveable { mutableStateOf(MainTab.HOME)}
    var isCreateMenuOpen by rememberSaveable { mutableStateOf(false) }
    Scaffold(
        bottomBar = {
            VynilBottomBar(
                selectedTab = selectedTab,
                isCreateMenuOpen = isCreateMenuOpen,
                onTabSelected = {
                    selectedTab = it
                    isCreateMenuOpen = false
                },
                onCreateClick = { isCreateMenuOpen = !isCreateMenuOpen }
            )
        }
    ) { padding ->
        val bottomBarHeight = padding.calculateBottomPadding()
        val homeBackStack = rememberNavBackStack(Home)
        val searchBackStack = rememberNavBackStack(Search)
        val libraryBackStack = rememberNavBackStack(Library)
        val profileBackStack = rememberNavBackStack(Profile)
        val leaderboardBackStack = rememberNavBackStack(Leaderboard)
        Box(modifier = Modifier.padding(top = padding.calculateTopPadding())){
            when(selectedTab){
                MainTab.HOME -> NavDisplay(
                    backStack = homeBackStack,
                    entryProvider = entryProvider {
                        entry<Home>{HomePage()}
                    }
                )
                MainTab.SEARCH -> NavDisplay(
                    backStack = searchBackStack,
                    entryProvider = entryProvider {
                        entry<Search>{SearchPage(bottomPadding = bottomBarHeight)}
                    }
                )
                MainTab.LIBRARY -> NavDisplay(
                    backStack = libraryBackStack,
                    entryProvider = entryProvider {
                        entry<Library>{LibraryPage()}
                    }
                )
                MainTab.PROFILE -> NavDisplay(
                    backStack = profileBackStack,
                    entryProvider = entryProvider {
                        entry<Profile>{ProfilePage()}
                    }
                )

                MainTab.LEADERBOARD -> NavDisplay(
                    backStack = leaderboardBackStack,
                    entryProvider = entryProvider {
                        entry<Leaderboard>{LeaderboardPage()}
                    }
                )
            }
            if (isCreateMenuOpen) {
                CreateMenu(
                    bottomPadding = bottomBarHeight,
                    onDismiss = { isCreateMenuOpen = false },
                    onCreateBlindTest = { isCreateMenuOpen = false },
                    onCreateLobby = { isCreateMenuOpen = false }
                )
            }
        }
    }
}
@Composable
private fun VynilBottomBar(
    selectedTab: MainTab,
    isCreateMenuOpen: Boolean,
    onTabSelected: (MainTab) -> Unit,
    onCreateClick: () -> Unit
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background.copy(alpha = NAV_BAR_ALPHA)
    ) {
        VynilNavItem(
            tab = MainTab.HOME,
            selectedTab = selectedTab,
            onTabSelected = onTabSelected,
            icon = Icons.Outlined.Home,
            selectedIcon = Icons.Filled.Home,
            label = stringResource(R.string.nav_home)
        )
        VynilNavItem(
            tab = MainTab.SEARCH,
            selectedTab = selectedTab,
            onTabSelected = onTabSelected,
            icon = Icons.Outlined.Search,
            selectedIcon = Icons.Filled.Search,
            label = stringResource(R.string.nav_search)
        )
        VynilNavItem(
            tab = MainTab.LIBRARY,
            selectedTab = selectedTab,
            onTabSelected = onTabSelected,
            icon = Icons.Outlined.LibraryMusic,
            selectedIcon = Icons.Filled.LibraryMusic,
            label = stringResource(R.string.nav_library)
        )
        VynilCreateItem(
            isMenuOpen = isCreateMenuOpen,
            onClick = onCreateClick
        )
    }
}

@Composable
private fun RowScope.VynilCreateItem(
    isMenuOpen: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .weight(1f)
            .align(Alignment.CenterVertically),
        contentAlignment = Alignment.Center
    ) {
        ShrinkOnPressBox(onClick = onClick) {
            if (isMenuOpen) {
                Box(
                    modifier = Modifier
                        .size(CLOSE_CREATE_MENU_SIZE)
                        .background(
                            color = MaterialTheme.colorScheme.onBackground,
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Close,
                        contentDescription = stringResource(R.string.nav_close_create_menu),
                        tint = MaterialTheme.colorScheme.background
                    )
                }
            } else {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Outlined.Add,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = stringResource(R.string.nav_create),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
    }
}

@Composable
private fun RowScope.VynilNavItem(
    tab: MainTab,
    selectedTab: MainTab,
    onTabSelected: (MainTab) -> Unit,
    icon: ImageVector,
    selectedIcon: ImageVector,
    label: String
) {
    val isSelected = selectedTab == tab

    NavigationBarItem(
        selected = isSelected,
        onClick = { onTabSelected(tab) },
        icon = {
            Icon(
                imageVector = if (isSelected) selectedIcon else icon,
                contentDescription = null
            )
        },
        label = { Text(label) },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = ColorTheme.logoGreen,
            selectedTextColor = ColorTheme.logoGreen,
            unselectedIconColor = MaterialTheme.colorScheme.onBackground,
            unselectedTextColor = MaterialTheme.colorScheme.onBackground,
            indicatorColor = Color.Transparent
        )
    )
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}

