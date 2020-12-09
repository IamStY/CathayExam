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
import java.lang.Exception

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
        apiStatus.value = 1
        GlobalScope.launch(Dispatchers.IO)  {
            var dataModel= GlobalScope.async {
                ApiRequestManager.getServerCenters(context)
            }
            try {
                insert(dataModel.await())
                apiStatus.postValue(0)
            }catch (e:Exception){
                if(e is NoNetwork){
                    apiStatus.postValue(-1)
                } //...
            }
        }

    }
}