package testing.steven.cathaytest.viewmodels.bases

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel

open class APIBasedViewModel<T> : ViewModel() {
    //1 requesting , 0 , completed , -1 failure
   lateinit var apiStatus : LiveData<Int>

    lateinit var  uiStateLiveData : MediatorLiveData<Int>


     fun initUIStateLiveData( liveData: LiveData<List<T>>){
        uiStateLiveData=  MediatorLiveData<Int>().apply {
            addSource(liveData){
                    centerLiveData->
                Log.e("apiStatus",apiStatus.value.toString())
                value = if(centerLiveData.isEmpty() &&apiStatus.value==1){
                    0
                }else if(centerLiveData.isEmpty() &&apiStatus.value==-1){
                    -1
                }else{
                    1
                }
            }
            addSource(apiStatus){
                apiStatus->
                    if (liveData.value.isNullOrEmpty() && apiStatus == -1) {
                        value = -1
                    }

            }
        }
    }

}