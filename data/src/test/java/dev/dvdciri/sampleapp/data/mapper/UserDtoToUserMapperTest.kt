package dev.dvdciri.sampleapp.data.mapper

import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import dev.dvdciri.sampleapp.data.model.UserDto
import dev.dvdciri.sampleapp.domain.model.User
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals

class UserDtoToUserMapperTest {

    private lateinit var cut: UserDtoToUserMapper

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        cut = UserDtoToUserMapper()
    }

    @Test
    fun `given userDto when mapToDomain then correct domain model returned`() {
        // Given
        val name = "name"
        val userDto = mock<UserDto> {
            on { it.name } doReturn name
        }

        // When
        val user = cut.mapToDomain(userDto)

        // Then
        assertEquals(User(name), user)
    }
}