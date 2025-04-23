package com.sample.presentation.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.sample.core.utils.NavRoute
import com.sample.presentation.viewmodel.UsersViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(viewModel: UsersViewModel, navController: NavController) {
    LaunchedEffect(Unit) {
        viewModel.fetchData(10)
    }
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "List App", color = Color.White) },
            colors = TopAppBarDefaults.topAppBarColors(Color.Blue),
            navigationIcon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home", tint = Color.White
                )
            }
        )
    }){paddingValues ->
        var countInput by remember { mutableStateOf("") }
        val items by viewModel.usersList.collectAsState()
        Column(modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

            OutlinedTextField(modifier = Modifier.padding(8.dp),
                value = countInput,
                onValueChange = { countInput = it },
                label = { Text("Enter number of users") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(modifier = Modifier.padding(8.dp), onClick = {
                viewModel.fetchData(countInput.toIntOrNull()?:10) }) {
                Text("Fetch Users")
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (viewModel.isLoading) {
                CircularProgressIndicator()
            } else {
                LazyColumn {
                    items(items){
                            user ->
                        Card(modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable {
                                viewModel.setSelectedUser(user)
                                navController.navigate(NavRoute.Details.route)
                            }) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                AsyncImage(
                                    model = user.profilePicture,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(64.dp)
                                        .clip(CircleShape)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Column {
                                    Text("${user.firstName} ${user.lastName}", fontWeight = FontWeight.Bold)
                                    Text(user.address)
                                }
                            }
                        }
                    }
                }

            }
        }
    }

}
