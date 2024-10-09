package com.diegohenrick.vespanews

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.diegohenrick.vespanews.feature.data.local.dao.UserDao
import com.diegohenrick.vespanews.feature.data.local.entity.User
import com.diegohenrick.vespanews.feature.data.local.viewModel.RegisterViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.runInterruptible
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.time.delay
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.time.Duration
import kotlin.time.Duration.Companion.seconds

@ExperimentalCoroutinesApi
class RegisterViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: RegisterViewModel
    private lateinit var userDao: UserDao

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        userDao = mock(UserDao::class.java)

        viewModel = RegisterViewModel(userDao)

        Dispatchers.setMain(testDispatcher)

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `registerUser with empty username or email triggers onError`() = runTest {
        var errorMessage: String? = null
        viewModel.registerUser("","t@t.com",
            "password",
            "password",
            onSuccess = {},
            onError = { errorMessage = it }
        )

        Assert.assertEquals("Please insert your email and password", errorMessage)
    }

    @Test
    fun `registerUser with non-matching passwords triggers onError`() = runTest {
        var errorMessage: String? = null
        viewModel.registerUser(
            "username",
            "t@t.com",
            "password",
            "differentPassword",
            onSuccess = { },
            onError = { errorMessage = it }
        )

        Assert.assertEquals("Passwords aren't matching", errorMessage)
    }

    @Test
    fun `registerUser when user already exists triggers onError`() = runTest {
        `when`(userDao.getUserByEmail("test@example.com")).thenReturn(
            User(
                id = 1,
                "username",
                "test@example.com",
                "password"
            )
        )

        var errorMessage: String? = null
        val viewModel = RegisterViewModel(userDao)

            viewModel.registerUser(
                "username", "test@example.com", "password", "password",
                onSuccess = { },
                onError = {
                    errorMessage = it
                    println("Error message: $it")
                    Assert.assertEquals("Email already registered!", errorMessage)

                }
            )
    }




    @Test
    fun `registerUser when new user is successfully inserted triggers onSuccess`() = runTest {
        `when`(userDao.getUserByEmail("test@example.com")).thenReturn(null)

        var successCalled = false
        viewModel.registerUser(
            "username", "test@example.com", "password", "password",
            onSuccess = { successCalled = true
                Assert.assertTrue(successCalled)
                },
            onError = { }
        )

        testDispatcher.scheduler.advanceUntilIdle()


    }

    @Test
    fun `registerUser triggers onError on database exception`() = runTest {
        `when`(userDao.getUserByEmail("test@example.com")).thenThrow(RuntimeException("Database error"))

        var errorMessage: String? = null
        viewModel.registerUser(
            "username", "test@example.com", "password", "password",
            onSuccess = { },
            onError = { errorMessage = it
                Assert.assertEquals("Occurred an error: Database error", errorMessage)
            }
        )

        testDispatcher.scheduler.advanceUntilIdle()

    }

}