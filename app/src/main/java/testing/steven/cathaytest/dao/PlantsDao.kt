package testing.steven.cathaytest.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import testing.steven.cathaytest.datamodel.CenterDataModel
import testing.steven.cathaytest.datamodel.PlantsDataModel

@Dao
interface PlantsDao {
    @Query("SELECT * FROM plants_table WHERE location LIKE '%' || :centerName  || '%'  OR location LIKE '%' || '全園普遍分佈'  || '%' ORDER BY nameEn ASC")
    fun getPlants(centerName : String) : LiveData<List<PlantsDataModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(plantsDataModel: ArrayList<PlantsDataModel>)
}