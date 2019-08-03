package dev.dvdciri.sampleapp.data

import dev.dvdciri.sampleapp.data.mapper.UserDtoToUserMapper
import dev.dvdciri.sampleapp.data.source.JsonPlaceholderNetworkDataSource
import dev.dvdciri.sampleapp.domain.model.User
import dev.dvdciri.sampleapp.domain.repository.UserRepository
import io.reactivex.Single


class UserRepositoryImpl
constructor(
    private val jsonPlaceholderNetworkDataSource: JsonPlaceholderNetworkDataSource,
    private val userDtoToUserMapper: UserDtoToUserMapper
) : UserRepository {

    override fun getUser(userId: Int): Single<User> {
        return jsonPlaceholderNetworkDataSource.getUserDto(userId)
            .map(userDtoToUserMapper::mapToDomain)
    }
}