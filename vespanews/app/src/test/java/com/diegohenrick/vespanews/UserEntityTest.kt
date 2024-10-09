package com.diegohenrick.vespanews

import com.diegohenrick.vespanews.feature.data.local.entity.User
import org.junit.Assert.assertEquals
import org.junit.Test

class UserTest {

    @Test
    fun `create User with valid data`() {
        val user = User(
            username = "testUser",
            email = "test@example.com",
            password = "password123"
        )

        assertEquals("testUser", user.username)
        assertEquals("test@example.com", user.email)
        assertEquals("password123", user.password)
        assertEquals(0, user.id)
    }

    @Test
    fun `create User with auto-generated id`() {
        val userWithId = User(
            id = 5,
            username = "anotherUser",
            email = "another@example.com",
            password = "password456"
        )

        assertEquals(5, userWithId.id)
        assertEquals("anotherUser", userWithId.username)
        assertEquals("another@example.com", userWithId.email)
        assertEquals("password456", userWithId.password)
    }
}
