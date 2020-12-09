package testing.steven.cathaytest.vmfactory

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import testing.steven.cathaytest.api.Statics
import testing.steven.cathaytest.database.AppDatabase
import testing.steven.cathaytest.datamodel.CenterDataModel
import testing.steven.cathaytest.datamodel.PlantsDataModel
import testing.steven.cathaytest.viewmodels.CenterDetailViewModel

/***********
 * 需要以bundle帶入頁面使用的VmFactory
 * 可有可無
 * 專案大會需要用到
 * bundle拆解邏輯可以放這裡
 */
class BundleFactory(private val context: Context , private val bundle : Bundle) : ContextFactory(context){
    val plantsDao = AppDatabase.getDatabase(context.applicationContext).plantsDao()

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CenterDetailViewModel::class.java)){
            var centerDataModel = bundle.getSerializable(Statics.centerData) as CenterDataModel
            return CenterDetailViewModel(centerDataModel,context,plantsDao) as T
        }
        throw  IllegalArgumentException("Unknown ViewModel class")
    }
}