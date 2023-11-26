package com.hogent.androidproject.ui

import com.hogent.androidproject.data.ApiGameRepository
import com.hogent.androidproject.fake.FakeDataSource
import com.hogent.androidproject.fake.FakeGameApiService
import com.hogent.androidproject.network.asDomainObjects
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class ApiGameRepositoryTest {

    @Test
    fun `get correct games from apiGameRepository`() =
        runTest {
            val repository = ApiGameRepository(FakeGameApiService())
            assertEquals(FakeDataSource.games.asDomainObjects(),repository.getGames("pc","shooter"))
    }
}