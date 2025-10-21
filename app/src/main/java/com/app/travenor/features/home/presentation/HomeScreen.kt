package com.app.travenor.features.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.app.travenor.R
import com.app.travenor.features.onboarding.domain.getMainPageText
import com.app.travenor.features.popular.presentation.PopularPlaceItem
import com.app.travenor.sample_data.DetailPlace
import com.app.travenor.sample_data.Place
import com.app.travenor.sample_data.getProfileUrl
import com.app.travenor.sample_data.placesList
import com.app.travenor.sample_data.toDetailPlace

sealed class HomeSection {
    data class Header(val userName: String, val profileUrl: String) : HomeSection()
    data class Title(val text: String) : HomeSection()
    data class DestinationsCarousel(val title: String, val places: List<Place>) : HomeSection()
    data class PopularPlacesGrid(val title: String, val places: List<Place>, val columns: Int = 2) : HomeSection()
}

val homeSections = listOf(
    HomeSection.Header(userName = "Leonardo", profileUrl = getProfileUrl()),
    HomeSection.Title(text = getMainPageText()),
    HomeSection.DestinationsCarousel(title = "Mejores Destinos", places = placesList.take(5)),
    HomeSection.PopularPlacesGrid(title = "Lugares Populares", places = placesList.shuffled().take(4), columns = 2),
    HomeSection.DestinationsCarousel(title = "Ofertas Especiales", places = placesList.shuffled().take(5)),
    HomeSection.PopularPlacesGrid(title = "Recomendado para ti", places = placesList.drop(5).take(6), columns = 2)
)

@Composable
fun HomeScreen(
    innerPadding: PaddingValues,
    onNotificationClick: () -> Unit,
    onProfileClick: () -> Unit,
    onItemClick: (DetailPlace) -> Unit,
    navigateToPopular: () -> Unit,
    sections: List<HomeSection> = homeSections
) {

    LazyColumn(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(items = sections, key = { it.javaClass.simpleName + it.hashCode() }) { section ->
            when (section) {
                is HomeSection.Header -> HeaderSection(
                    userName = section.userName,
                    profileUrl = section.profileUrl,
                    onProfileClick = onProfileClick,
                    onNotificationClick = onNotificationClick
                )

                is HomeSection.Title -> TitleSection(title = section.text)
                is HomeSection.DestinationsCarousel -> DestinationsCarouselSection(
                    title = section.title,
                    places = section.places,
                    onItemClick = onItemClick,
                    navigateToPopular = navigateToPopular
                )

                is HomeSection.PopularPlacesGrid -> PopularPlacesGridSection(
                    title = section.title,
                    places = section.places,
                    columns = section.columns,
                    onItemClick = onItemClick,
                    navigateToPopular = navigateToPopular
                )
            }
        }
    }
}

@Composable
fun HeaderSection(
    userName: String,
    profileUrl: String,
    onProfileClick: () -> Unit,
    onNotificationClick: () -> Unit
) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier
                .background(MaterialTheme.colorScheme.onTertiary, CircleShape)
                .clip(CircleShape)
                .clickable { onProfileClick() }
                .padding(4.dp)
                .padding(end = 8.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(profileUrl)
                    .crossfade(true)
                    .build(),
                modifier = Modifier
                    .size(37.dp)
                    .clip(CircleShape),
                placeholder = painterResource(id = R.drawable.ic_profile_img),
                error = painterResource(id = R.drawable.ic_profile_img),
                fallback = painterResource(id = R.drawable.ic_profile_img),
                contentDescription = "Ícono de perfil"
            )
            Text(
                text = userName,
                fontSize = 14.sp,
                lineHeight = 16.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        IconButton(
            modifier = Modifier
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.onTertiary),
            onClick = onNotificationClick
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_notification_icon),
                contentDescription = "Ícono de notificación"
            )
        }
    }
}

@Composable
fun TitleSection(title: String) {
    Text(
        modifier = Modifier
            .padding(top = 26.dp),
        text = title,
        fontSize = 38.sp,
        lineHeight = 50.sp,
        color = MaterialTheme.colorScheme.onSurface
    )
}

@Composable
fun DestinationsCarouselSection(
    title: String,
    places: List<Place>,
    onItemClick: (DetailPlace) -> Unit,
    navigateToPopular: () -> Unit
) {
    Column {
        Row(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                fontSize = 20.sp,
                lineHeight = 28.sp,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "Ver todo",
                fontSize = 20.sp,
                lineHeight = 28.sp,
                color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .clip(RoundedCornerShape(100))
                    .clickable { navigateToPopular() }
                    .padding(vertical = 2.dp, horizontal = 10.dp)
            )
        }

        LazyRow(
            contentPadding = PaddingValues(vertical = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(items = places, key = { it.id }) { place ->
                DestinationItem(
                    place = place,
                    onItemClick = { onItemClick(place.toDetailPlace()) },
                    onBookmarkClick = { })
            }
        }
    }
}


@Composable
fun PopularPlacesGridSection(
    title: String,
    places: List<Place>,
    columns: Int,
    onItemClick: (DetailPlace) -> Unit,
    navigateToPopular: () -> Unit
) {
    Column {
        Row(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                fontSize = 20.sp,
                lineHeight = 28.sp,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "Ver todo",
                fontSize = 20.sp,
                lineHeight = 28.sp,
                color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .clip(RoundedCornerShape(100))
                    .clickable { navigateToPopular() }
                    .padding(vertical = 2.dp, horizontal = 10.dp)
            )
        }

        Column(
            modifier = Modifier.padding(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            places.chunked(columns).forEach { rowPlaces ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    rowPlaces.forEach { place ->
                        Box(modifier = Modifier.weight(1f)) {
                            PopularPlaceItem(
                                place = place,
                                onItemClick = { onItemClick(place.toDetailPlace()) })
                        }
                    }
                    if (rowPlaces.size < columns) {
                        for (i in 1..(columns - rowPlaces.size)) {
                            Box(modifier = Modifier.weight(1f)) {}
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun HomeScreenPreView() {
    HomeScreen(PaddingValues(0.dp), {}, {}, {}, {}, homeSections)
}
