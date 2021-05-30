package com.mutualmobile.tweetify.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mutualmobile.tweetify.ui.components.TweetifySurface
import com.mutualmobile.tweetify.ui.home.feeds.ComposeTweetAdvertisementBanner
import com.mutualmobile.tweetify.ui.home.feeds.ComposeTweet
import com.mutualmobile.tweetify.ui.home.feeds.data.TweetsViewModel
import com.mutualmobile.tweetify.ui.home.stories.ComposeStoriesHome
import com.mutualmobile.tweetify.ui.home.stories.UserStoriesRepository
import com.mutualmobile.tweetify.ui.theme.AlphaNearTransparent
import com.mutualmobile.tweetify.ui.theme.TweetifyTheme

@Composable
fun HomeScreen(tweetsViewModel: TweetsViewModel = TweetsViewModel()) {

    val tweetsLiveData by tweetsViewModel.tweetsLiveData.observeAsState(listOf())

    TweetifySurface(Modifier.fillMaxSize()) {
        LazyColumn {
            item {
                Column {
                    Spacer(modifier = Modifier.height(2.dp))
                    ComposeStoriesHome(UserStoriesRepository.fetchStories())
                    Spacer(modifier = Modifier.height(2.dp))
                }
            }
            item {
                Column {
                    Divider(
                        color = TweetifyTheme.colors.uiBorder.copy(AlphaNearTransparent),
                        thickness = 5.dp
                    )
                    ComposeTweetAdvertisementBanner()
                    Divider(
                        color = TweetifyTheme.colors.uiBorder.copy(AlphaNearTransparent),
                        thickness = 5.dp
                    )
                }
            }
            items(items = tweetsLiveData) { tweet ->
                ComposeTweet(tweet, tweetsViewModel)
            }
        }

    }
}




