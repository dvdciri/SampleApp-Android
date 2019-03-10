package dev.dvdciri.sampleapp.postdetails.mapper

import android.content.res.Resources
import dev.dvdciri.sampleapp.R
import dev.dvdciri.sampleapp.domain.model.User
import dev.dvdciri.sampleapp.postdetails.model.AuthorUiModel
import javax.inject.Inject

private const val USER_AVATAR_TEMPLATE = "https://api.adorable.io/avatars/180/%d.png"

class UserToAuthorUiModelMapper
@Inject constructor(
    private val resources: Resources
) {

    fun mapToPresentation(author: User): AuthorUiModel {
        return with(author) {
            val titleText = resources.getString(R.string.author_title_text).format(name, username)
            val emailText = resources.getString(R.string.author_email_text).format(email)

            AuthorUiModel(
                id = id.toString(),
                titleText = titleText,
                emailText = emailText,
                avatarUrl = USER_AVATAR_TEMPLATE.format(id)
            )
        }
    }
}