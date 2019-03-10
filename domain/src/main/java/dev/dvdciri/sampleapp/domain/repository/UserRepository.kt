package dev.dvdciri.sampleapp.domain.repository

import dev.dvdciri.sampleapp.domain.model.User
import io.reactivex.Single

interface UserRepository {

    fun getUser(userId: Int): Single<User>
}