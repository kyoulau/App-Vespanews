package com.diegohenrick.vespanews

import com.diegohenrick.vespanews.feature.data.local.entity.NewsEntity
import com.diegohenrick.vespanews.feature.data.local.entity.NewsList
import org.junit.Assert.assertEquals
import org.junit.Test

class NewsEntityTest {

    @Test
    fun `create NewsEntity with valid data`() {
        val news = NewsEntity(
            title = "Sample News Title",
            description = "Sample description for news.",
            image_url = "https://example.com/image.jpg"
        )

        assertEquals("Sample News Title", news.title)
        assertEquals("Sample description for news.", news.description)
        assertEquals("https://example.com/image.jpg", news.image_url)
        assertEquals(0L, news.id)
    }

    @Test
    fun `create NewsList with multiple NewsEntity`() {
        val news1 = NewsEntity(
            title = "First News",
            description = "Description for first news.",
            image_url = "https://example.com/image1.jpg"
        )
        val news2 = NewsEntity(
            title = "Second News",
            description = "Description for second news.",
            image_url = "https://example.com/image2.jpg"
        )

        val newsList = NewsList(data = listOf(news1, news2))

        assertEquals(2, newsList.data.size)
        assertEquals("First News", newsList.data[0].title)
        assertEquals("Second News", newsList.data[1].title)
    }
}
