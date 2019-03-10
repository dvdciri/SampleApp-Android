package dev.dvdciri.sampleapp.postdetails.mapper

import android.content.res.Resources
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import dev.dvdciri.sampleapp.R
import dev.dvdciri.sampleapp.domain.model.User
import dev.dvdciri.sampleapp.postdetails.model.AuthorUiModel
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import kotlin.test.assertEquals

class UserToAuthorUiModelMapperTest {

    private lateinit var cut: UserToAuthorUiModelMapper

    @Mock private lateinit var resources: Resources

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        cut = UserToAuthorUiModelMapper(resources)
    }

    @Test
    fun `given author when mapToPresentation then author ui model`() {
        // Given
        val author = mock<User> {
            on { it.id } doReturn 1234
            on { it.name } doReturn "name"
            on { it.username } doReturn "username"
            on { it.email } doReturn "email"
        }

        whenever(resources.getString(R.string.author_email_text)).thenReturn("Email: %s")
        whenever(resources.getString(R.string.author_title_text)).thenReturn("%1\$s (%2\$s)")

        // When
        val authorUiModel = cut.mapToPresentation(author)

        // Then
        val expected = AuthorUiModel(
            "1234",
            "name (username)",
            "Email: email",
            "https://api.adorable.io/avatars/180/1234.png"
        )
        assertEquals(expected, authorUiModel)
    }
}