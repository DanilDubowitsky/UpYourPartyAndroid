package com.example.data.repository.user

import com.example.data.converters.local.toEntities
import com.example.data.converters.local.toEntity
import com.example.data.dao.UserDao
import com.example.domain.RxDataSource
import com.example.domain.model.user.User
import com.example.domain.repository.IRxRepositoryContract
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val dao: UserDao
) : RxDataSource(), IRxRepositoryContract.IUserRepository {

    override fun addUser(user: User): Completable =
        dao.insertOrUpdate(user.toEntity())
            .processIOCompletable()

    override fun addUsers(users: List<User>): Completable =
        dao.insertOrUpdate(users.toEntities())
            .processIOCompletable()
}