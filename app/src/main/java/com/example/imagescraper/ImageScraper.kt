package com.example.imagescraper

import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup
import java.io.IOException

class ImageScraper {
    private val client = OkHttpClient()

    fun fetchImageUrls(url: String): List<String> {
        val imageUrls = mutableListOf<String>()

        try {
            val request = Request.Builder().url(url).build()
            val response = client.newCall(request).execute()
            val htmlContent = response.body?.string() ?: ""

            val document = Jsoup.parse(htmlContent)
            val images = document.select("img[src]")

            for (img in images) {
                val imageUrl = img.absUrl("src")
                if (imageUrl.isNotEmpty()) {
                    imageUrls.add(imageUrl)
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return imageUrls
    }
}