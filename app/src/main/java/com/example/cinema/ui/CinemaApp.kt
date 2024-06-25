import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CinemaApp() {

    Scaffold(
        topBar = { CinemaTopAppBar() }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            //val marsViewModel: MarsViewModel = viewModel()
            HomeScreen()
        }
    }
}

@Composable
fun CinemaTopAppBar() {

}
