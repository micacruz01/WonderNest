package com.groupthree.wondernest // Use your package name


import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


// NOTE: You must have drawables named 'ic_rhyme', 'sample_toy_thumb', etc.,
// and Activity classes like SearchActivity, ChatActivity, and AccountActivity defined.
// For this example, I'll use placeholders for missing resources.


// Placeholder resource IDs (Assume these exist in your project's res/drawable folder)
// If you don't have them, the code will not compile.
val R_drawable_sample_toy_thumb = android.R.drawable.btn_star_big_on // Placeholder image
val R_drawable_ic_rhyme = android.R.drawable.ic_lock_power_off // Placeholder icon
val R_drawable_ic_exploration = android.R.drawable.ic_delete // Placeholder icon
val R_drawable_ic_visual = android.R.drawable.ic_dialog_alert // Placeholder icon
val R_drawable_ic_anim = android.R.drawable.ic_menu_edit // Placeholder icon




class PlaylistsActivity : ComponentActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
       setContent { PlaylistsScreenWithNav() }
   }
}


// -----------------------------------------------------------------------------
// MAIN SCREEN COMPOSABLE
// -----------------------------------------------------------------------------


@Composable
fun PlaylistsScreenWithNav() {
   // In a real app, SearchActivity, ChatActivity, and AccountActivity should be defined.
   // For this example, we'll use placeholder class references.
   val context = LocalContext.current
   val navToSearch = { /* context.startActivity(Intent(context, SearchActivity::class.java)) */ }
   val navToChat = { /* context.startActivity(Intent(context, ChatActivity::class.java)) */ }
   val navToAccount = { /* context.startActivity(Intent(context, AccountActivity::class.java)) */ }


   Scaffold(
       topBar = { TopSearchBar() },
       bottomBar = {
           // Reusing the Navigation Bar structure from your previous code
           NavigationBar(containerColor = Color(0xFFEAEAEA)) {
               NavigationBarItem(
                   selected = true,
                   onClick = { /* Already on home/playlist screen */ },
                   icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                   label = { Text("Home") }
               )
               NavigationBarItem(
                   selected = false,
                   onClick = navToSearch,
                   icon = { Icon(Icons.Default.Search, contentDescription = "Search") },
                   label = { Text("Search") }
               )
               NavigationBarItem(
                   selected = false,
                   onClick = navToChat,
                   icon = { Icon(Icons.Default.MailOutline, contentDescription = "Chat") },
                   label = { Text("Chat") }
               )
               NavigationBarItem(
                   selected = false,
                   onClick = navToAccount,
                   icon = { Icon(Icons.Default.Person, contentDescription = "Account") },
                   label = { Text("Account") }
               )
           }
       }
   ) { paddingValues ->
       PlaylistsContent(Modifier.padding(paddingValues))
   }
}


// -----------------------------------------------------------------------------
// TOP BAR - REPLACED BY A SEPARATE COMPOSABLE FOR A CUSTOM LOOK
// -----------------------------------------------------------------------------


@Composable
fun TopSearchBar() {
   Row(
       modifier = Modifier
           .fillMaxWidth()
           .background(Color(0xFFE0FFFF)) // Light teal background for the header
           .padding(horizontal = 16.dp, vertical = 8.dp),
       verticalAlignment = Alignment.CenterVertically
   ) {
       // Search bar input area
       Row(
           modifier = Modifier
               .weight(1f)
               .height(48.dp)
               .clip(RoundedCornerShape(24.dp))
               .background(Color(0x99FFFFFF)) // Semi-transparent white
               .padding(horizontal = 16.dp),
           verticalAlignment = Alignment.CenterVertically
       ) {
           Icon(Icons.Default.Search, contentDescription = "Search", tint = Color.Gray)
           Spacer(Modifier.width(8.dp))
           Text("Search in Playlists...", color = Color.Gray, fontSize = 16.sp)
       }


       Spacer(Modifier.width(16.dp))


       // Menu Icon (Hamburger)
       IconButton(onClick = { /* Handle menu click */ }) {
           Icon(Icons.Default.Menu, contentDescription = "Menu", tint = Color.Black)
       }
   }
}


// -----------------------------------------------------------------------------
// MAIN SCREEN CONTENT
// -----------------------------------------------------------------------------


@Composable
fun PlaylistsContent(modifier: Modifier = Modifier) {
   Column(
       modifier = modifier
           .fillMaxSize()
           .background(Color(0xFFE0FFFF)) // Main background color (Light Teal)
           .padding(16.dp)
   ) {
       // "Playlists" Title
       Text(
           text = "Playlists",
           fontSize = 22.sp,
           fontWeight = FontWeight.Bold,
           modifier = Modifier.padding(bottom = 12.dp)
       )


       // Filter Chips/Pills
       Row(
           modifier = Modifier
               .fillMaxWidth()
               .padding(bottom = 24.dp),
           horizontalArrangement = Arrangement.SpaceBetween
       ) {
           FilterPill("Rhyme", R_drawable_ic_rhyme, true)
           FilterPill("Exploratio", R_drawable_ic_exploration, false)
           FilterPill("Visual", R_drawable_ic_visual, false)
           FilterPill("Anim", R_drawable_ic_anim, false)
           // Note: You would typically use a LazyRow for a large, scrollable list of filters
       }


       // Main Curated Playlist Card
       CuratedPlaylistCard(
           imageRes = R_drawable_sample_toy_thumb,
           title = "Curated Playlist",
           subtitle = "Age-appropriate"
       )


       Spacer(Modifier.height(24.dp))


       // Storage and Add New Cards (Lower Section)
       Row(
           modifier = Modifier.fillMaxWidth(),
           horizontalArrangement = Arrangement.spacedBy(16.dp)
       ) {
           StorageCard(
               modifier = Modifier.weight(1f),
               title = "Storage",
               usage = "Usage details"
           )
           AddNewCard(
               modifier = Modifier.weight(1f),
               title = "Add new"
           )
       }
   }
}


// -----------------------------------------------------------------------------
// REUSABLE COMPONENTS
// -----------------------------------------------------------------------------


@Composable
fun FilterPill(text: String, iconRes: Int, isSelected: Boolean) {
   val color = if (isSelected) Color(0xFF67B5C7) else Color(0x66FFFFFF) // Blue/White blend
   val textColor = if (isSelected) Color.White else Color.Black


   Row(
       modifier = Modifier
           .clip(RoundedCornerShape(12.dp))
           .background(color)
           .clickable { /* Handle filter click */ }
           .padding(horizontal = 12.dp, vertical = 8.dp),
       verticalAlignment = Alignment.CenterVertically
   ) {
       Icon(
           painter = painterResource(id = iconRes),
           contentDescription = text,
           modifier = Modifier.size(20.dp),
           tint = textColor
       )
       Spacer(Modifier.width(4.dp))
       Text(text, color = textColor, fontSize = 14.sp)
       if (isSelected) {
           // Star icon for selected/favorite
           Spacer(Modifier.width(4.dp))
           Icon(Icons.Default.Star, contentDescription = "Favorite", tint = Color.White, modifier = Modifier.size(16.dp))
       }
   }
}


@Composable
fun CuratedPlaylistCard(imageRes: Int, title: String, subtitle: String) {
   Column(
       modifier = Modifier
           .fillMaxWidth()
           .shadow(4.dp, RoundedCornerShape(16.dp))
           .clip(RoundedCornerShape(16.dp))
           .background(Color.White)
           .padding(bottom = 12.dp)
   ) {
       // Image and Close Button Overlay
       Box(
           modifier = Modifier
               .fillMaxWidth()
               .height(200.dp)
       ) {
           Image(
               painter = painterResource(id = imageRes),
               contentDescription = null,
               contentScale = ContentScale.Crop,
               modifier = Modifier.matchParentSize()
           )
           // Close Button (Top Right)
           Box(
               modifier = Modifier
                   .align(Alignment.TopEnd)
                   .padding(8.dp)
                   .clip(CircleShape)
                   .background(Color(0xAA000000)) // Semi-transparent black
                   .clickable { /* Handle close click */ }
                   .padding(4.dp)
           ) {
               Icon(Icons.Default.Close, contentDescription = "Close", tint = Color.White)
           }
       }


       // Card Details (Title, Subtitle, Actions)
       Row(
           modifier = Modifier
               .fillMaxWidth()
               .padding(horizontal = 16.dp, vertical = 8.dp),
           verticalAlignment = Alignment.CenterVertically,
           horizontalArrangement = Arrangement.SpaceBetween
       ) {
           Column(modifier = Modifier.weight(1f)) {
               Text(
                   text = title,
                   fontSize = 18.sp,
                   fontWeight = FontWeight.Bold,
                   color = Color.Black
               )
               Text(
                   text = subtitle,
                   fontSize = 14.sp,
                   color = Color.Gray
               )
               Spacer(Modifier.height(8.dp))
               // Co-watch Chip
               AssistChip(
                   onClick = { /* Handle co-watch click */ },
                   label = { Text("Co-watch", color = Color.Black) },
                   modifier = Modifier.height(24.dp),
                   // Using a light, off-white background for the chip
                   colors = AssistChipDefaults.assistChipColors(
                       containerColor = Color(0xFFF0F0F0)
                   )
               )
           }


           // Action Icons (Share, Delete, More)
           Row {
               Icon(Icons.Default.Share, contentDescription = "Share", modifier = Modifier.padding(4.dp))
               Icon(Icons.Default.Delete, contentDescription = "Delete", modifier = Modifier.padding(4.dp))
               Icon(Icons.Default.MoreVert, contentDescription = "More", modifier = Modifier.padding(4.dp))
           }
       }
   }
}


@Composable
fun StorageCard(modifier: Modifier = Modifier, title: String, usage: String) {
   Card(
       modifier = modifier
           .clip(RoundedCornerShape(16.dp))
           .fillMaxWidth()
           .height(100.dp),
       colors = CardDefaults.cardColors(containerColor = Color(0x66FFFFFF)) // Light background
   ) {
       Column(modifier = Modifier.padding(16.dp)) {
           Text(title, fontWeight = FontWeight.Bold)
           Spacer(Modifier.height(4.dp))
           Text(usage, fontSize = 12.sp, color = Color.Gray)
           // Progress Bar or visual usage detail line would go here
       }
   }
}


@Composable
fun AddNewCard(modifier: Modifier = Modifier, title: String) {
   Card(
       modifier = modifier
           .clip(RoundedCornerShape(16.dp))
           .fillMaxWidth()
           .height(100.dp),
       colors = CardDefaults.cardColors(containerColor = Color(0x66FFFFFF)) // Light background
   ) {
       Column(
           modifier = Modifier.fillMaxSize(),
           horizontalAlignment = Alignment.CenterHorizontally,
           verticalArrangement = Arrangement.Center
       ) {
           Icon(Icons.Default.FileUpload, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(32.dp))
           Spacer(Modifier.height(4.dp))
           Text(title, fontWeight = FontWeight.Bold)
       }
   }
}


// -----------------------------------------------------------------------------
// PREVIEW
// -----------------------------------------------------------------------------


@Preview(showBackground = true)
@Composable
fun PlaylistsScreenPreview() {
   // You must ensure the placeholder drawables (R_drawable_...) are valid in a real project
   PlaylistsScreenWithNav()
}



