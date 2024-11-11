package com.hackaton.monologue.mypage

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hackaton.monologue.BottomNavItem
import com.hackaton.monologue.auth.AuthNavigation
import com.hackaton.monologue.auth.login.LoginScreen
import com.hackaton.monologue.auth.signup.SignupScreen
import com.hackaton.monologue.mypage.picture.MyPagePictureScreen
import com.hackaton.monologue.ui.ConfirmDialog

@Composable
fun MyPageNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = BottomNavItem.MyPage.screenRoute
    ) {
        composable(BottomNavItem.MyPage.screenRoute) {
            MyPageScreen(
                onClickLikeFrame = {
                    navController.navigate("likeFrame")
                },
                onClickMyFrame = {
                    navController.navigate("myFrame")
                },
                onClickPicture = {
                    navController.navigate("picture")
                },
                onClickProfile = {
                    navController.navigate("profile")
                },
                onClickLogout = {
                    navController.navigate("logout")
                },
                onClickMemberOut = {
                    navController.navigate("logout")
                })
        }
        composable("likeFrame") {
            // 좋아요한 프레임
        }
        composable("myFrame") {
            // 내가 만든 프레임
        }
        composable("picture") {
            MyPagePictureScreen()
        }
        composable("profile") {
            // 프로필 수정
        }
        composable("logout") {
            AuthNavigation()
        }
    }
}