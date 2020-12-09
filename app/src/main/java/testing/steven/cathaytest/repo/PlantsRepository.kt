package testing.steven.cathaytest.repo

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import testing.steven.cathaytest.api.ApiRequestManager
import testing.steven.cathaytest.dao.CenterDao
import testing.steven.cathaytest.dao.PlantsDao
import testing.steven.cathaytest.datamodel.CenterDataModel
import testing.steven.cathaytest.datamodel.PlantsDataModel
import testing.steven.cathaytest.exceptions.NoNetwork

/***********
 * 植物相關知識庫
 * 包含資料庫dao操作
 */
class PlantsRepository(
    centerDataModel: CenterDataModel,
    private val context: Context,
    private val plantsDao: PlantsDao
) {
    var plantsLiveData = plantsDao.getPlants(centerDataModel.name)

    val apiStatus: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    val apiFailure: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    init {
        fireFetchServerData()
    }

    suspend fun insert(datas: ArrayList<PlantsDataModel>) {
        plantsDao.insert(datas)
    }

    private fun fireFetchServerData() {
        apiStatus.value = 1
        GlobalScope.launch(Dispatchers.IO) {
            var dataModel = GlobalScope.async {
                ApiRequestManager.getPlants(context)
            }
            try {
                insert(dataModel.await())
                apiStatus.postValue(0)
            } catch (e: Exception) {
                if (e is NoNetwork) {
                    apiStatus.postValue(-1)
                } //...
            }
        }


    }
}