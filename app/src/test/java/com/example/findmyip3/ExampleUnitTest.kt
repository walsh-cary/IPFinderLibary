package com.example.findmyip3

import com.example.findmyip3.model.IPFetcherResponseModel
import com.example.findmyip3.network.NetworkConstants.BASE_URL
import com.example.findmyip3.network.NetworkRequest
import com.example.findmyip3.repository.IPFetcherRepository
import com.example.findmyip3.viewmodel.IPFetcherViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import retrofit2.Response

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Mock
    private lateinit var api: NetworkRequest

    @Mock
    private lateinit var viewModel: IPFetcherViewModel

    private lateinit var repo: IPFetcherRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        api = Mockito.mock(NetworkRequest::class.java)
        viewModel = Mockito.mock(IPFetcherViewModel::class.java)
        repo = IPFetcherRepository(viewModel)
    }

    @Test
    fun `test call succeeds`() = runBlocking {
        val response = IPFetcherResponseModel(
            city = "Atlanta",
            region = "Georgia",
            country = "United States",
            ip = "192.168.0.1",
        )

        val result = api.getIpDetails()

        assertEquals(result.city, response.city)
        assertEquals(result.region, response.region)
        assertEquals(result.country, response.country)
        assertEquals(result.ip, response.ip)
    }
}