package com.groupthree.wondernest

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class AccountActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { AccountScreenWithNav() }
    }
}

@Composable
fun AccountScreenWithNav() {
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
                    selected = false,
                    onClick = { context.startActivity(Intent(context, SearchActivity::class.java)) },
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
                    selected = true,
                    onClick = {},
                    icon = { Icon(Icons.Default.Person, null) },
                    label = { Text("Account") }
                )
            }
        }
    ) { padding ->
        AccountScreenContent(Modifier.padding(padding))
    }
}

@Composable
fun AccountScreenContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFEAF6F9))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Avatar
        Image(
            painter = painterResource(id = R.drawable.avatar_mia),
            contentDescription = null,
            modifier = Modifier
                .size(140.dp)
                .clip(CircleShape)
                .background(Color.White),
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.height(8.dp))
        Text("Mia Reyes", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Text("Mommy", fontSize = 16.sp, color = Color.Gray)
        Spacer(Modifier.height(24.dp))

        // Device section
        Text("Devices", fontSize = 18.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.align(Alignment.Start))
        Spacer(Modifier.height(12.dp))

        DeviceCard("Tori Reyes", "5 y/o", active = true, R.drawable.avatar_tori)
        Spacer(Modifier.height(8.dp))
        DeviceCard("Francis Reyes", "2 y/o", active = false, R.drawable.avatar_francis)

        Spacer(Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("＋ Add Device", fontWeight = FontWeight.Medium)
            Text("Manage Devices", fontWeight = FontWeight.Medium)
        }

        Spacer(Modifier.height(24.dp))

        // Buttons
        AccountButton("Edit Account Information")
        Spacer(Modifier.height(8.dp))
        AccountButton("Legal and Policies")
        Spacer(Modifier.height(8.dp))
        AccountButton("Sign Out", bg = Color(0xFFAEE3EA))
    }
}

@Composable
fun DeviceCard(name: String, age: String, active: Boolean, avatarRes: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFDCEEEF), RoundedCornerShape(12.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = avatarRes),
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color.White)
            )
            Spacer(Modifier.width(12.dp))
            Column {
                Text(name, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                Text(age, fontSize = 14.sp, color = Color.Gray)
            }
        }
        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = if (active) "● Active" else "● Inactive",
                color = if (active) Color(0xFF4CAF50) else Color.Gray,
                fontSize = 12.sp
            )
            Box(
                modifier = Modifier
                    .padding(top = 4.dp)
                    .background(Color(0xFFAEE3EA), RoundedCornerShape(50))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text("Connect to Device", fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun AccountButton(text: String, bg: Color = Color(0xFFCCCCCC)) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(bg, RoundedCornerShape(16.dp))
            .padding(vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text, fontWeight = FontWeight.SemiBold)
    }
}
