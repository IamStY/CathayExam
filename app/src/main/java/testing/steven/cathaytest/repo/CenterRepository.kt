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
import testing.steven.cathaytest.datamodel.CenterDataModel
import testing.steven.cathaytest.exceptions.NoNetwork

/***********
 * 園區相關知識庫
 * 包含資料庫dao操作
 */
class CenterRepository(private val context :Context, private val centerDao : CenterDao){
    var centerLiveData    = centerDao.getCenters()
    val apiStatus: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>().apply { value = 0 }
    }
    init{
        fireFetchServerData()
    }
    suspend fun insert(datas:ArrayList<CenterDataModel>){
        centerDao.insert(datas)
    }
    fun fireFetchServerData (){
        GlobalScope.launch(Dispatchers.IO)  {
              var dataModel=  ApiRequestManager.getServerCenters(apiStatus,context)
                insert(dataModel)
        }

    }
}