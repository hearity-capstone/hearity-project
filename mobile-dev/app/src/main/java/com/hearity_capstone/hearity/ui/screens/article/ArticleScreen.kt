package com.hearity_capstone.hearity.ui.screens.article

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.hearity_capstone.hearity.ui.common.AppTopBar
import com.hearity_capstone.hearity.ui.screens.article.components.ArticleCard
import com.hearity_capstone.hearity.ui.theme.PaddingMedium
import com.hearity_capstone.hearity.ui.theme.SpacingItem


@Composable
fun ArticleScreen(navController: NavHostController) {
    val articles = listOf(
        Article(
            "What is Audiometry?",
            "Hearing loss comes with age but can affect anyone. According to a study in American Family Physician, at least 25 percent of people over 50 experience hearing loss, and 50 percent of people over 80 experience it. One way to test for hearing loss is through the use of audiometry.\n" + "\n" + "An audiometry exam tests how well your hearing functions. It tests both the intensity and the tone of sounds, balance issues, and other issues related to the function of the inner ear. A doctor who specializes in diagnosing and treating hearing loss called an audiologist administers the test.\n" + "\n" + "The unit of measure for sound intensity is the decibel (dB). A healthy human ear can hear quiet sounds such as whispers. These are about 20 dB. A loud sound such as a jet engine is between 140 and 180 dB.\n" + "\n" + "The tone of a sound is measured in cycles per second. The unit of measure for tone is Hertz (Hz). Low bass tones measure around 50 Hz. Humans can hear tones between 20-20,000 Hz. Human speech generally falls in the 500-3,000 Hz range.",
            "healthline.com"
        ),
        Article(
            "Types of Audiometry Testing",
            "Pure tone audiometry. This test may already be familiar to you if you’ve already had standard screening for your hearing before. You sit in a quiet room and put on a set of headphones. The person administering the test plays a series of sounds at different pitches and volumes in one ear at a time.\n" + "\n" + "Speech audiometry. This is similar to pure tone testing in that you're seated in a quiet room with headphones. You hear words played at different volumes, and you repeat each word or point to a picture representing the word\n" + "\n" + "Impedance audiometry. Your doctor inserts a probe into your ear. The probe pushes air against your eardrum, and this makes a sound. The doctor measures the way your eardrum moves in response to the stimulus.",
            "webmd.com/a-to-z-guides/what-to-know-audiometry"
        ),
        Article(
            "How Audiometry Is Performed ?",
            "There are a few tests involved in audiometry. A pure tone test measures the quietest sound you can hear at different pitches. It involves using an audiometer, which is a machine that plays sounds via headphones. Your audiologist or an assistant will play a variety of sounds, such as tones and speech, at different intervals into one ear at a time, to determine your range of hearing. The audiologist will give you instructions for each sound. Most likely, they’ll ask you to raise your hand when a sound becomes audible.\n" + "\n" + "Another hearing test allows your audiologist to assess your ability to distinguish speech from background noise. A sound sample will be played for you and you’ll be asked to repeat the words you hear. Word recognition can be helpful in diagnosing hearing loss.\n" + "\n" + "A tuning fork may be used to determine how well you hear vibrations through your ears. Your audiologist will put this metal device against the bone behind your ear, the mastoid, or use a bone oscillator to determine how well vibrations pass through the bone to your inner ear. A bone oscillator is a mechanical device that transmits vibrations similar to a tuning fork.\n" + "\n" + "This test doesn’t cause any pain or discomfort and takes about an hour.",
            "healthline.com"
        )
    )


    Scaffold(
        topBar = { AppTopBar(navController, title = "Article") }
    ) {
        Column(
            Modifier
                .padding(it)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = PaddingMedium)
        ) {
            // Card
            articles.forEach { article ->
                ArticleCard(article = article)
                Spacer(Modifier.height(SpacingItem))
            }
        }
    }
}

class Article(
    val title: String,
    val content: String,
    val source: String,
)