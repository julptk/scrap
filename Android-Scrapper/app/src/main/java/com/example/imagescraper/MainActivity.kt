package com.example.imagescraper

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val url = "https://example.com"

        CoroutineScope(Dispatchers.IO).launch {
            val scraper = ImageScraper()
            val imageUrls = scraper.fetchImageUrls(url)

            for (imageUrl in imageUrls) {
                Log.d("ImageScraper", imageUrl)
            }
        }
    }
}