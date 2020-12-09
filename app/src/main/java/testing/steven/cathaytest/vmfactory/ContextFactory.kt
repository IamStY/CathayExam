package testing.steven.cathaytest.vmfactory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import testing.steven.cathaytest.database.AppDatabase
import testing.steven.cathaytest.viewmodels.MainViewModel

open class ContextFactory(private val context: Context) : ViewModelProvider.Factory{
    val centerDao = AppDatabase.getDatabase(context.applicationContext).centerDao()

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(context,centerDao) as T
        }
        throw  IllegalArgumentException("Unknown ViewModel class")
    }
}