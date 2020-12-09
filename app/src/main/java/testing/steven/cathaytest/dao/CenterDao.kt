package testing.steven.cathaytest.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import testing.steven.cathaytest.datamodel.CenterDataModel

@Dao
interface CenterDao {
    @Query("SELECT * FROM center_table ORDER BY id ASC")
    fun getCenters() : LiveData<List<CenterDataModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(centerDataModel: List<CenterDataModel>)
}