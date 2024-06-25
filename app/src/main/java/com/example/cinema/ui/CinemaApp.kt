import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cinema.ui.components.IconButtonCinema
import com.example.cinema.ui.screens.details.DetailsScreen
import com.example.cinema.ui.theme.Primary
import com.example.cinema.ui.theme.White
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CinemaApp() {
    val rotas = listOf("home", "details")
    var currentIndex by remember { mutableStateOf(0) }
    val navController = rememberNavController()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        modifier = Modifier.clip(RectangleShape),
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                LazyColumn {
                    itemsIndexed(rotas){index, item ->
                        NavigationDrawerItem(
                            label = { Text(text = item)},
                            selected = currentIndex == index,
                            onClick = {
                                navController.navigate(item)
                                currentIndex = index
                                scope.launch {
                                    drawerState.apply {
                                        close()
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    ) {
        Scaffold(
            topBar = { CinemaTopAppBar(scrollBehavior, scope, drawerState) },
        ){
            Column(Modifier.padding(it)) {
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
                    composable("details"){
                        DetailsScreen()
                    }
                }
            }
        }
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

@Preview(showBackground = true)
@Composable
fun PreviewApp(){
    CinemaApp()
}

