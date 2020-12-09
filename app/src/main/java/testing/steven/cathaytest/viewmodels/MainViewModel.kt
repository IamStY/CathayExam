package testing.steven.cathaytest.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import testing.steven.cathaytest.repo.CenterRepository
import testing.steven.cathaytest.dao.CenterDao
import testing.steven.cathaytest.datamodel.CenterDataModel
import testing.steven.cathaytest.viewmodels.bases.APIBasedViewModel
/***********
 * 園區列表使用的ViewModel
 */
class MainViewModel(context : Context, centerDao: CenterDao) : APIBasedViewModel<CenterDataModel>() {
    private val repository: CenterRepository = CenterRepository(context,centerDao)
    val centerLiveData: LiveData<List<CenterDataModel>>
    // 0 顯示requesting , -1 顯示資料失敗　, 1正常顯示列表
    init {
        centerLiveData = repository.centerLiveData
        apiStatus = repository.apiStatus
        initUIStateLiveData(centerLiveData)
    }


}