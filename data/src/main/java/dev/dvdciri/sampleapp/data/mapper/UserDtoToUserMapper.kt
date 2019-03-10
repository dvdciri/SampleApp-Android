package dev.dvdciri.sampleapp.data.mapper

import dev.dvdciri.sampleapp.data.model.UserDto
import dev.dvdciri.sampleapp.domain.model.User
import javax.inject.Inject

class UserDtoToUserMapper @Inject constructor() {

    fun mapToDomain(userDto: UserDto): User {
        return with(userDto) {
            User(
                id = id,
                name = name,
                email = email,
                username = username
            )
        }
    }
}