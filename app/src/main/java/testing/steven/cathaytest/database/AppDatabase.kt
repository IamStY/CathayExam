package testing.steven.cathaytest.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import testing.steven.cathaytest.dao.CenterDao
import testing.steven.cathaytest.dao.PlantsDao
import testing.steven.cathaytest.datamodel.CenterDataModel
import testing.steven.cathaytest.datamodel.PlantsDataModel

@Database(entities = [CenterDataModel::class, PlantsDataModel::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun centerDao(): CenterDao
    abstract fun plantsDao(): PlantsDao

    private class WordDatabaseCallback(  ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(
            context: Context
        ): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "application_database"
                )
                    .addCallback(WordDatabaseCallback())
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}