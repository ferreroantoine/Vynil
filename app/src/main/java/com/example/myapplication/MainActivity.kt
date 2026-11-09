package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Leaderboard
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.myapplication.data.Home
import com.example.myapplication.data.Leaderboard
import com.example.myapplication.data.MainTab
import com.example.myapplication.data.Profile
import com.example.myapplication.data.Search
import com.example.myapplication.presentation.HomePage
import com.example.myapplication.presentation.LeaderboardPage
import com.example.myapplication.presentation.ProfilePage
import com.example.myapplication.presentation.SearchPage
import com.example.myapplication.ui.theme.ColorTheme
import com.example.myapplication.ui.theme.MyApplicationTheme

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
    Scaffold(
        bottomBar = {
            VynilBottomBar(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it }
            )
        }
    ) { padding ->
        val homeBackStack = rememberNavBackStack(Home)
        val searchBackStack = rememberNavBackStack(Search)
        val profileBackStack = rememberNavBackStack(Profile)
        val leaderboardBackStack = rememberNavBackStack(Leaderboard)
        Box(modifier = Modifier.padding(padding)){
            when(selectedTab){
                MainTab.HOME -> NavDisplay(
                    backStack = homeBackStack,
                    entryProvider = entryProvider {
                        entry<Home> {
                            HomePage(
                                onProfileClick = { selectedTab = MainTab.PROFILE }
                            )
                        }
                    }
                )
                MainTab.SEARCH -> NavDisplay(
                    backStack = searchBackStack,
                    entryProvider = entryProvider {
                        entry<Search>{SearchPage()}
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
        }
    }
}
@Composable
private fun VynilBottomBar(
    selectedTab: MainTab,
    onTabSelected: (MainTab) -> Unit
) {
    NavigationBar(containerColor = Color.White) {
        VynilNavItem(
            tab = MainTab.HOME,
            selectedTab = selectedTab,
            onTabSelected = onTabSelected,
            icon = Icons.Outlined.Home,
            label = stringResource(R.string.nav_home)
        )
        VynilNavItem(
            tab = MainTab.SEARCH,
            selectedTab = selectedTab,
            onTabSelected = onTabSelected,
            icon = Icons.Outlined.Search,
            label = stringResource(R.string.nav_explore)
        )
        VynilNavItem(
            tab = MainTab.LEADERBOARD,
            selectedTab = selectedTab,
            onTabSelected = onTabSelected,
            icon = Icons.Outlined.Leaderboard,
            label = stringResource(R.string.nav_leaderboard)
        )
    }
}

@Composable
private fun RowScope.VynilNavItem(
    tab: MainTab,
    selectedTab: MainTab,
    onTabSelected: (MainTab) -> Unit,
    icon: ImageVector,
    label: String
) {
    NavigationBarItem(
        selected = selectedTab == tab,
        onClick = { onTabSelected(tab) },
        icon = { Icon(icon, contentDescription = null) },
        label = { Text(label) },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = ColorTheme.logoGreen,
            selectedTextColor = ColorTheme.logoGreen,
            unselectedIconColor = Color.Black,
            unselectedTextColor = Color.Black,
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

