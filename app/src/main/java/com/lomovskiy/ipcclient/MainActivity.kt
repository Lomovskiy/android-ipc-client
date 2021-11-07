package com.lomovskiy.ipcclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.lomovskiy.ipcclient.ui.theme.AndroidipcclientTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidipcclientTheme {
                Surface(color = MaterialTheme.colors.background) {
                    ScreenRoot()
                }
            }
        }
    }
}

@Composable
fun ScreenRoot() {
    
    val navController: NavHostController = rememberNavController()
    val currentBackStackEntry = navController.currentBackStackEntryAsState()
    val currentNavItem: NavigationItem = NavigationItem.fromRoute(
        route = currentBackStackEntry.value?.destination?.route
    )

    Scaffold(
        bottomBar = {
            BottomNavScreen(
                items = NavigationItem.values(),
                currentItem = currentNavItem
            ) {
                navController.navigate(it.route)
            }
        }
    ) {
        NavHost(navController = navController, startDestination = NavigationItem.AIDL.route) {
            composable(NavigationItem.AIDL.route) {
                ScreenAidl()
            }
            composable(NavigationItem.MESSANGER.route) {
                ScreenMessanger()
            }
            composable(NavigationItem.BROADCAST.route) {
                ScreenBroadcast()
            }
        }
    }
    
}

enum class NavigationItem(
    val route: String,
    val icon: ImageVector,
    val title: String
) {

    AIDL(
        route = "/aidl",
        icon = Icons.Default.KeyboardArrowLeft,
        title = "AIDL"
    ),
    MESSANGER(
        route = "/messanger",
        icon = Icons.Default.KeyboardArrowUp,
        title = "Messanger"
    ),
    BROADCAST(
        route = "/broadcast",
        icon = Icons.Default.KeyboardArrowRight,
        title = "Broadcast"
    );

    companion object {

        fun fromRoute(route: String?): NavigationItem {
            val result: NavigationItem
            when (route) {
                AIDL.route -> {
                    result = AIDL
                }
                MESSANGER.route -> {
                    result = MESSANGER
                }
                BROADCAST.route -> {
                    result = BROADCAST
                }
                else -> {
                    result = AIDL
                }
            }
            return result
        }

    }

}

@Composable
fun BottomNavScreen(
    items: Array<NavigationItem>,
    currentItem: NavigationItem,
    onClick: (NavigationItem) -> Unit
) {

    BottomNavigation {
        items.forEach {
            BottomNavigationItem(
                selected = it == currentItem,
                onClick = { onClick(it) },
                icon = { Icon(imageVector = it.icon, contentDescription = "") },
                label = { Text(text = it.title) }
            )
        }
    }

}

@Composable
fun ScreenAidl() {

}

@Composable
fun ScreenMessanger() {

}

@Composable
fun ScreenBroadcast() {

}
