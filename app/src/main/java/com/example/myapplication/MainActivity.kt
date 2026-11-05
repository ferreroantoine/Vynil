package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
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
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VynilAppMain()
        }
    }
}
@Composable
fun VynilAppMain(){
    var selectedTab by rememberSaveable { mutableStateOf(MainTab.HOME)}
    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = selectedTab == MainTab.HOME,
                    onClick = { selectedTab = MainTab.HOME },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Localized description") },
                    label = { Text("Accueil") }
                )
                NavigationBarItem(
                    selected = selectedTab == MainTab.SEARCH,
                    onClick = { selectedTab = MainTab.SEARCH },
                    icon = { Icon(Icons.Default.Search, contentDescription = "Localized description") },
                    label = { Text("Recherche") }
                )
                NavigationBarItem(
                    selected = selectedTab == MainTab.PROFILE,
                    onClick = { selectedTab = MainTab.PROFILE },
                    icon = { Icon(Icons.Default.AccountCircle, contentDescription = "Localized description") },
                    label = { Text("Profil") }
                )
                NavigationBarItem(
                    selected = selectedTab == MainTab.LEADERBOARD,
                    onClick = { selectedTab = MainTab.LEADERBOARD },
                    icon = {Icon(Icons.Default.Leaderboard ,contentDescription = "Localized description")},
                    label = { Text("Leaderboard") }
                )
            }
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
                        entry<Home>{HomePage()}
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

