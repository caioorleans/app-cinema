import android.annotation.SuppressLint
import android.util.LayoutDirection
import android.util.Size
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.Path
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cinema.ui.components.IconButtonCinema
import com.example.cinema.ui.theme.Primary
import com.example.cinema.ui.theme.White
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CinemaApp() {
    val navController = rememberNavController()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        modifier = Modifier.clip(RectangleShape),
                drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet { /* Drawer content */ }
        }
    ) {
        Scaffold(
            topBar = { CinemaTopAppBar(scrollBehavior, scope, drawerState) },
            content = { _ ->
                NavHost(navController, startDestination = "home") {
                    composable("home") {
                        HomeScreen()
                    }
                    composable("movies") {
                        //MoviesScreen()
                    }
                    composable("favorites") {
                        //FavoritesScreen()
                    }

                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CinemaTopAppBar(scrollBehavior: TopAppBarScrollBehavior, scope: CoroutineScope, drawerState: DrawerState) {


    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Primary,
            titleContentColor = White,
        ),
        title = {
            IconButtonCinema(Icons.Filled.Home, "Home", White, {})
        },
        navigationIcon = {
            IconButtonCinema(Icons.Filled.Menu, "Menu", White) {
                scope.launch {
                    drawerState.apply {
                        if (isClosed) open() else close()
                    }
                }

            }
        },
        actions = {
            IconButtonCinema(Icons.Filled.Favorite, "Favorite",White,{})
        },
        scrollBehavior = scrollBehavior,
    )

}

