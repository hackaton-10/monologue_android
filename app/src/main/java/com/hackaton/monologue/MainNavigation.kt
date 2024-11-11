package com.hackaton.monologue

import androidx.compose.foundation.Image
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hackaton.monologue.frame.FrameListScreen
import com.hackaton.monologue.mypage.MyPageNavigation
import com.hackaton.monologue.mypage.MyPageScreen
import com.hackaton.monologue.ui.theme.White

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "main"
    ) {
        composable("main") {
            MainScreen()
        }
    }
}

const val FRAME = "frame"
const val CAMERA = "camera"
const val MY_PAGE = "myPage"

sealed class BottomNavItem(
    val screenRoute: String,
    val unselectIcon: Int,
    val selectIcon: Int
) {
    data object Frame : BottomNavItem(FRAME, R.drawable.frame_unselect, R.drawable.frame_select)
    data object Camera: BottomNavItem(CAMERA, R.drawable.camera, R.drawable.camera)
    data object MyPage: BottomNavItem(MY_PAGE, R.drawable.mypage_unselect, R.drawable.mypage_select)
}

@Composable
fun MonoGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = FRAME
    ) {
        composable(BottomNavItem.Frame.screenRoute) {
            FrameListScreen()
        }
        composable(BottomNavItem.MyPage.screenRoute) {
            MyPageNavigation()
        }
        composable(BottomNavItem.Camera.screenRoute) {
            // 카메라
        }
    }
}

@Composable
fun BottomNavigation(
    navController: NavHostController
) {
    val navItems = listOf<BottomNavItem>(
        BottomNavItem.Frame,
        BottomNavItem.Camera,
        BottomNavItem.MyPage
    )

    androidx.compose.material3.NavigationBar(
        containerColor = White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        navItems.forEach { navItem ->
            NavigationBarItem(
                selected = currentRoute == navItem.screenRoute,
                onClick = {
                    navController.navigate(navItem.screenRoute) {
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) {saveState = true}
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Image(
                        painter = painterResource(id = if (navItem.screenRoute == currentRoute) navItem.selectIcon else navItem.unselectIcon),
                        contentDescription = null
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}