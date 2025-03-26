package com.example.btvntuan5_01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.btvntuan5_01.ui.theme.Btvntuan501Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Btvntuan501Theme {
                AppNavigation()
            }
        }
    }
}

// Điều chỉnh nút
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home_screen") {
        composable("home_screen") { HomeScreen(navController) }
        composable("second_screen") { SecondScreen(navController) }
        composable("third_screen") { ThirdScreen(navController) }
    }
}

// Màn hình 1
@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.image1),
            contentDescription = "Logo",
            modifier = Modifier.size(300.dp)
        )
        Text(
            text = stringResource(id = R.string.text1),
            modifier = Modifier.padding(top = 10.dp).width(200.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Text(
            text = stringResource(id = R.string.text2),
            modifier = Modifier.padding(top = 16.dp).width(300.dp),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(100.dp))
        Button(
            onClick = { navController.navigate("second_screen") },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF33CCFF)),
            modifier = Modifier.width(300.dp)
        ) {
            Text(text = "PUSH", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun SecondScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { navController.navigate("home_screen") },
                modifier = Modifier
                    .size(90.dp)
                    .padding(top = 40.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                contentPadding = PaddingValues(0.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.home1),
                    contentDescription = "Home Image",
                    modifier = Modifier.size(50.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "LazyColumn",
                color = Color(0xFF33CCFF),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 40.dp, start = 30.dp),
                fontSize = 25.sp
            )
        }

        // Đặt LazyColumn bên trong Column
        LazyColumn(
            modifier = Modifier.padding(top = 20.dp)
        ) {
            items(1_000_000) { index ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFD0E8FF), shape = RoundedCornerShape(8.dp))
                        .padding(16.dp)
                ) {
                    Text(
                        text = stringResource(id = if (index % 2 == 0) R.string.text7 else R.string.text8),
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(end = 60.dp)
                    )
                    Button(
                        onClick = { navController.navigate("third_screen") },
                        contentPadding = PaddingValues(0.dp),
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .size(50.dp),
                        colors = ButtonDefaults.buttonColors(Color.Transparent)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.button2),
                            contentDescription = "Back Image",
                            modifier = Modifier.size(50.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}



// Màn hình 3
@Composable
fun ThirdScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { navController.navigate("second_screen") },
                modifier = Modifier.size(90.dp).padding(top = 40.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                contentPadding = PaddingValues(0.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.home1),
                    contentDescription = "Home Image",
                    modifier = Modifier.size(50.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Detail",
                color = Color(0xFF33CCFF),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 40.dp, start = 60.dp),
                fontSize = 25.sp
            )
        }
        Text(
            stringResource(id = R.string.text9),
            modifier = Modifier.padding(top = 20.dp, start = 65.dp),
            textAlign = TextAlign.Center
        )
        Image(
            painter = painterResource(id = R.drawable.picture),
            contentDescription = "Picture",
            modifier = Modifier.padding(top = 20.dp).size(500.dp)
        )
        Button(
            onClick = { navController.navigate("home_screen") },
            modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 40.dp).size(width = 200.dp, height = 50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF33CCFF)),
        ) {
            Text(
                text = "BACK TO ROOT",
                color = Color.White,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

// Hiển thị màn hình 1 qua design
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    Btvntuan501Theme {
        HomeScreen(navController)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SecondScreenPreview() {
    val navController = rememberNavController()
    Btvntuan501Theme {
        SecondScreen(navController)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ThirdScreenPreview() {
    val navController = rememberNavController()
    Btvntuan501Theme {
        ThirdScreen(navController)
    }
}
