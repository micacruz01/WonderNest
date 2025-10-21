package com.groupthree.wondernest

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Text

class ChatActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { ChatScreenWithNav() }
    }
}

@Composable
fun ChatScreenWithNav() {
    val context = LocalContext.current

    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = Color(0xFFEAEAEA)) {
                NavigationBarItem(
                    selected = true,
                    onClick = {},
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { context.startActivity(Intent(context, SearchActivity::class.java)) },
                    icon = { Icon(Icons.Default.Search, contentDescription = "Search") },
                    label = { Text("Search") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { context.startActivity(Intent(context, ChatActivity::class.java)) },
                    icon = { Icon(Icons.Default.MailOutline, contentDescription = "Chat") },
                    label = { Text("Chat") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { context.startActivity(Intent(context, AccountActivity::class.java)) },
                    icon = { Icon(Icons.Default.Person, contentDescription = "Account") },
                    label = { Text("Account") }
                )
            }
        }
    ) { padding ->
        ChatScreenContent(Modifier.padding(padding))
    }
}

@Composable
fun ChatScreenContent(modifier: Modifier = Modifier) {
    var message by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        // Top bar
        Text("Messaging", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(16.dp))

        // Chat area
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            ChatBubble("Mommy", "Hello Tori, how is Francis?", isUser = false)
            ChatBubble("Tori", "He went to sleep mommy", isUser = true)
            ChatBubble("Tori", "Yaya is making egg sandwich", isUser = true)
            ChatBubble("Mommy", "Okay. That’s good. Take care baby", isUser = false)
        }

        Spacer(Modifier.height(8.dp))

        // Bottom input area
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(2.dp, RoundedCornerShape(32.dp))
                .clip(RoundedCornerShape(32.dp))
                .background(Color(0xFFF5F5F5))
                .padding(horizontal = 12.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.Add, contentDescription = null, tint = Color.Gray)
            Spacer(Modifier.width(8.dp))
            BasicTextField(
                value = message,
                onValueChange = { message = it },
                modifier = Modifier.weight(1f),
                singleLine = true,
                textStyle = LocalTextStyle.current.copy(fontSize = 16.sp),
                decorationBox = { innerTextField ->
                    if (message.isEmpty()) {
                        Text("Type a message", color = Color.Gray, fontSize = 14.sp)
                    }
                    innerTextField()
                }
            )
            Spacer(Modifier.width(8.dp))
            Icon(
                Icons.Default.PlayArrow,
                contentDescription = "Send",
                tint = Color(0xFF6BB1C9),
                modifier = Modifier
                    .size(24.dp)
            )
        }
    }
}

@Composable
fun ChatBubble(sender: String, text: String, isUser: Boolean) {
    val bubbleColor = if (isUser) Color(0xFFE1F5FE) else Color(0xFFECECEC)
    val alignment = if (isUser) Alignment.End else Alignment.Start

    Column(
        horizontalAlignment = alignment,
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
    ) {
        Row(
            horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (!isUser) {
                Image(
                    painter = painterResource(id = R.drawable.avatar_mia),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )
                Spacer(Modifier.width(8.dp))
            }
            Box(
                modifier = Modifier
                    .background(bubbleColor, RoundedCornerShape(24.dp))
                    .padding(horizontal = 16.dp, vertical = 10.dp)
                    .widthIn(max = 250.dp)
            ) {
                Text(text, fontSize = 15.sp)
            }
            if (isUser) {
                Spacer(Modifier.width(8.dp))
                Image(
                    painter = painterResource(id = R.drawable.avatar_tori),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )
            }
        }
    }
}
