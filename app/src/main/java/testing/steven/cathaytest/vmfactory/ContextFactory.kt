package testing.steven.cathaytest.vmfactory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import testing.steven.cathaytest.database.AppDatabase
import testing.steven.cathaytest.viewmodels.MainViewModel
/***********
 * 需要帶入context至ViewModel 或repo可使用這個factory
 * 可有可無
 * 專案大會需要用到
 * example.
 * 到repo可以客製化error msg (context.getString)
 * 到ViewModel可以放商業邏輯的文字顏色當liveData等等...
 */
open class ContextFactory(private val context: Context) : ViewModelProvider.Factory{
    val centerDao = AppDatabase.getDatabase(context.applicationContext).centerDao()

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(context,centerDao) as T
        }
        throw  IllegalArgumentException("Unknown ViewModel class")
    }
}