package testing.steven.cathaytest.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import testing.steven.cathaytest.repo.PlantsRepository
import testing.steven.cathaytest.dao.PlantsDao
import testing.steven.cathaytest.datamodel.CenterDataModel
import testing.steven.cathaytest.datamodel.PlantsDataModel
import testing.steven.cathaytest.viewmodels.bases.APIBasedViewModel

/***********
 * 園區詳情使用的ViewModel,包含植物列表
 */
class CenterDetailViewModel(centerDataModel : CenterDataModel,context : Context, centerDao: PlantsDao ) : APIBasedViewModel<PlantsDataModel>() {
    private val repository: PlantsRepository = PlantsRepository(viewModelScope,centerDataModel,context,centerDao)
    val plantsLiveData: LiveData<List<PlantsDataModel>>
    val centerDetailLiveData: MutableLiveData<CenterDataModel> by lazy {
        MutableLiveData<CenterDataModel>()
    }
    init {
        plantsLiveData = repository.plantsLiveData
        centerDetailLiveData.value= centerDataModel
        apiStatus = repository.apiStatus
        initUIStateLiveData(plantsLiveData)
    }

}