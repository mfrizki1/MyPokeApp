package id.calocallo.mypokeapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.calocallo.mypokeapp.data.local.entity.CurrentUserEntity
import id.calocallo.mypokeapp.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow
import java.nio.charset.CodingErrorAction.REPLACE

@Dao
interface CurrentUserDao {
    @Query(
        """
            SELECT u.* FROM users u
            INNER JOIN current_user cu ON u.id = cu.userId
            LIMIT 1
        """
    )
    fun getCurrentUser(): Flow<UserEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setCurrentUser(currentUser: CurrentUserEntity)

    @Query("SELECT COUNT(*) > 0 FROM current_user")
    suspend fun hasCurrentUser() : Boolean
}