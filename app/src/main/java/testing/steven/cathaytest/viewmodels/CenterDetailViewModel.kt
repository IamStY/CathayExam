package testing.steven.cathaytest.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import testing.steven.cathaytest.repo.PlantsRepository
import testing.steven.cathaytest.dao.PlantsDao
import testing.steven.cathaytest.datamodel.CenterDataModel
import testing.steven.cathaytest.datamodel.PlantsDataModel
import testing.steven.cathaytest.viewmodels.bases.APIBasedViewModel

class CenterDetailViewModel(centerDataModel : CenterDataModel,context : Context, centerDao: PlantsDao ) : APIBasedViewModel<PlantsDataModel>() {
    private val repository: PlantsRepository = PlantsRepository(centerDataModel,context,centerDao)
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