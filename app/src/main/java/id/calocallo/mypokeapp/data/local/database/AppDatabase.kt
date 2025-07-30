package id.calocallo.mypokeapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import id.calocallo.mypokeapp.data.local.dao.CurrentUserDao
import id.calocallo.mypokeapp.data.local.dao.UserDao
import id.calocallo.mypokeapp.data.local.entity.CurrentUserEntity
import id.calocallo.mypokeapp.data.local.entity.UserEntity


@Database(
    entities = [UserEntity::class, CurrentUserEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase(){
    abstract fun userDao(): UserDao
    abstract fun currentUserDao(): CurrentUserDao
}