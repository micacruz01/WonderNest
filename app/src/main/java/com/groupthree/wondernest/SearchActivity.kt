package com.groupthree.wondernest

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource

class SearchActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { GenreScreenWithNav() }
    }
}

@Composable
fun GenreScreenWithNav() {
    val context = LocalContext.current

    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = Color(0xFFEAEAEA)) {
                NavigationBarItem(
                    selected = false,
                    onClick = { context.startActivity(Intent(context, HomeActivity::class.java)) },
                    icon = { Icon(Icons.Default.Home, null) },
                    label = { Text("Home") }
                )
                NavigationBarItem(
                    selected = true,
                    onClick = {},
                    icon = { Icon(Icons.Default.Search, null) },
                    label = { Text("Search") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { context.startActivity(Intent(context, ChatActivity::class.java)) },
                    icon = { Icon(Icons.Default.MailOutline, null) },
                    label = { Text("Chat") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { context.startActivity(Intent(context, AccountActivity::class.java)) },
                    icon = { Icon(Icons.Default.Person, null) },
                    label = { Text("Account") }
                )
            }
        }
    ) { padding ->
        GenreScreenContent(Modifier.padding(padding))
    }
}

@Composable
fun GenreScreenContent(modifier: Modifier = Modifier) {
    val genres = listOf(
        "Adventure" to R.drawable.ic_genre_adventure,
        "Alphabet" to R.drawable.ic_genre_alphabet,
        "Animals" to R.drawable.ic_genre_animals,
        "Games" to R.drawable.ic_genre_games,
        "Learning" to R.drawable.ic_genre_learning,
        "Numbers" to R.drawable.ic_genre_numbers,
        "Shapes" to R.drawable.ic_genre_shapes,
        "Story Telling" to R.drawable.ic_genre_story
    )

    // add verticalScroll
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Search bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(2.dp, RoundedCornerShape(16.dp))
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("🔍  Search Genre...", color = Color.Gray, fontSize = 14.sp)
        }

        Spacer(Modifier.height(16.dp))

        Text("Choose Video Genre", fontSize = 22.sp, fontWeight = Bold)
        Spacer(Modifier.height(16.dp))

        for (pair in genres.chunked(2)) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                for ((name, icon) in pair) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .background(Color(0xFFAEE3EA), RoundedCornerShape(16.dp))
                            .padding(12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(
                                painter = painterResource(id = icon),
                                contentDescription = null,
                                modifier = Modifier.size(64.dp),
                                contentScale = ContentScale.Fit
                            )
                            Spacer(Modifier.height(8.dp))
                            Text(name, fontSize = 16.sp, fontWeight = SemiBold)
                        }
                    }
                }
            }
            Spacer(Modifier.height(12.dp))
        }
        Spacer(Modifier.height(24.dp))
    }
}