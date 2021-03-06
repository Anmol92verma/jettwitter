package com.mutualmobile.tweetify.ui.home.feeds.tweetdetails

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import com.google.accompanist.insets.navigationBarsPadding
import com.mutualmobile.tweetify.ui.components.TweetifySurface
import com.mutualmobile.tweetify.ui.home.feeds.ComposeTweet
import com.mutualmobile.tweetify.ui.home.feeds.data.Tweet
import com.mutualmobile.tweetify.ui.home.feeds.data.TweetState
import com.mutualmobile.tweetify.ui.theme.TweetifyTheme

@Composable
fun TwitterDetailsScreen(
    tweetId: String?,
    onBack: () -> Unit,
    hashTagNavigator: (String) -> Unit,
    tweetViewModel: TDViewModel
) {
    tweetViewModel.fetchTweetById(tweetId)
    val tweetState = tweetViewModel.tweetByIdState
    Scaffold(
        modifier = Modifier
            .navigationBarsPadding(),
        topBar = { TwitterDetailsTopBar(onBack) },
        backgroundColor = TweetifyTheme.colors.uiBackground,
        contentColor = TweetifyTheme.colors.textSecondary,
    ) { paddingExtras ->
        if (tweetState is TweetState.SuccessTweet) {
            TweetifySurface(modifier = Modifier
                .clickable { onBack.invoke() }
                .padding(paddingExtras)) {
                ComposeTweet(
                    tweet = tweetState.data,
                    onClickTweet = {

                    },
                    hashTagNavigator = hashTagNavigator,
                    onUrlRecognized = { tweet: Tweet, url: String ->
                    }
                )
            }
        }
    }
}

@Composable
fun TwitterDetailsTopBar(onBack: () -> Unit) {
    TweetifySurface(
        color = TweetifyTheme.colors.uiBackground,
        contentColor = TweetifyTheme.colors.accent,
        elevation = 4.dp
    ) {
        TopAppBar(
            title = {
                Text(text = "Tweet")
            },
            backgroundColor = TweetifyTheme.colors.uiBackground,
            navigationIcon = {
                IconButton(onClick = {
                    onBack()
                }) {
                    Icon(
                        Icons.Outlined.ArrowBack,
                        contentDescription = null,
                        tint = TweetifyTheme.colors.accent
                    )
                }
            }, elevation = 4.dp
        )
    }
}
