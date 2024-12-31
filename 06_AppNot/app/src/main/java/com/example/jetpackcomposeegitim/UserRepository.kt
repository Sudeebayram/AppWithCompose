package com.example.jetpackcomposeegitim

import javax.inject.Inject

interface UserRepository {
    suspend fun getUser(username: String, password: String): User?
    suspend fun insertUser(user: User)
}

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {
    override suspend fun getUser(username: String, password: String): User? {

        return userDao.getUser(username, password)
    }

    override suspend fun insertUser(user: User) {

        userDao.insertUser(user)
    }
}

