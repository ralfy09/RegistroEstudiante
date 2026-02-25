package com.example.registroestudiante.presentation.navigation.AppNavHost

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.registroestudiante.presentation.navigation.routes.Routes
import com.example.registroestudiante.presentation.tareas.edit.EditEstudianteScreen
import com.example.registroestudiante.presentation.tareas.list.ListEstudianteScreen
import com.example.registroestudiante.presentation.asignatura.Edit.EditAsignaturaScreen
import com.example.registroestudiante.presentation.asignatura.List.ListAsignaturaScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Routes.List.route,
        modifier = modifier
    ) {

        composable(Routes.List.route) {
            ListEstudianteScreen(
                onEditar = { id -> navController.navigate(Routes.Edit.createRoute(id)) },
                onAgregar = { navController.navigate(Routes.Edit.createRoute(0)) },
                onIrAsignaturas = { navController.navigate(Routes.ListAsignaturas.route) }
            )
        }

        composable(
            route = Routes.Edit.route,
            arguments = listOf(navArgument("id") { type = NavType.IntType; defaultValue = 0 })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            EditEstudianteScreen(estudianteId = id)
        }

        composable(Routes.ListAsignaturas.route) {
            ListAsignaturaScreen(
                onEditar = { id -> navController.navigate(Routes.EditAsignatura.createRoute(id)) },
                onAgregar = { navController.navigate(Routes.EditAsignatura.createRoute(0)) }
            )
        }

        composable(
            route = Routes.EditAsignatura.route,
            arguments = listOf(navArgument("id") { type = NavType.IntType; defaultValue = 0 })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            EditAsignaturaScreen(asignaturaId = id)
        }
    }
}