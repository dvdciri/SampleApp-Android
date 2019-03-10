package dev.dvdciri.sampleapp.data

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import dev.dvdciri.sampleapp.data.mapper.UserDtoToUserMapper
import dev.dvdciri.sampleapp.data.model.UserDto
import dev.dvdciri.sampleapp.data.source.JsonPlaceholderNetworkDataSource
import dev.dvdciri.sampleapp.domain.model.User
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class UserRepositoryImplTest {

    private lateinit var cut: UserRepositoryImpl

    @Mock private lateinit var jsonPlaceholderNetworkDataSource: JsonPlaceholderNetworkDataSource
    @Mock private lateinit var userDtoToUserMapper: UserDtoToUserMapper

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        cut = UserRepositoryImpl(jsonPlaceholderNetworkDataSource, userDtoToUserMapper)
    }

    @Test
    fun `given user retuend by data source when getUser then user mapped and domain model returned`() {
        // Given
        val userId = 87687

        val userDto = mock<UserDto>()
        whenever(jsonPlaceholderNetworkDataSource.getUserDto(userId)).thenReturn(Single.just(userDto))

        val user = mock<User>()
        whenever(userDtoToUserMapper.mapToDomain(userDto)).thenReturn(user)

        // When
        val testObserver = cut.getUser(userId).test()

        // Then
        testObserver
            .assertComplete()
            .assertNoErrors()
            .assertValue(user)
    }
}
