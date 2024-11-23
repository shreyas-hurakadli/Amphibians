package com.example.amphibians.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibians.R
import com.example.amphibians.model.Amphibian

@Composable
fun HomeScreen(
    retryAction: () -> Unit,
    amphibiansUiState: AmphibiansUiState,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    modifier: Modifier = Modifier
) {
    when (amphibiansUiState) {
        AmphibiansUiState.Error -> ErrorScreen(
            retryAction = retryAction,
            modifier = modifier
                .fillMaxSize()
                .padding(contentPadding)
        )
        AmphibiansUiState.Loading -> LoadingScreen(
            modifier = modifier
                .fillMaxSize()
                .padding(contentPadding)
        )
        is AmphibiansUiState.Success -> AmphibiansDataScreen(
            data = amphibiansUiState.amphibiansData,
            modifier = modifier
                .fillMaxSize()
                .padding(contentPadding)
        )
    }
}

@Composable
fun ErrorScreen(
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(200.dp),
            painter = painterResource(id = R.drawable.ic_broken_image),
            contentDescription = stringResource(R.string.error_message)
        )
        Text(
            text = stringResource(id = R.string.error_message),
            modifier = Modifier.padding(16.dp)
        )
        Button(onClick = retryAction) {
            Text(text = stringResource(R.string.error_button_text))
        }
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        Image(
            painter = painterResource(id = R.drawable.loading_img),
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )
    }
}

@Composable
fun AmphibiansDataScreen(
    data: List<Amphibian>,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        LazyColumn {
            items(data) { amphibian ->
                AmphibianCard(amphibian = amphibian)
            }
        }
    }
}

@Composable
fun AmphibianCard(
    amphibian: Amphibian,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.padding(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Column {
            Text(
                text = "${amphibian.name} (${amphibian.type})",
                modifier = Modifier.padding(8.dp),
            )
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(amphibian.imgSrc)
                    .crossfade(true)
                    .build(),
                error = painterResource(id = R.drawable.ic_broken_image),
                placeholder = painterResource(id = R.drawable.loading_img),
                contentDescription = null,
                modifier = modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = amphibian.description,
                modifier = Modifier.padding(4.dp),
            )
        }
    }
}