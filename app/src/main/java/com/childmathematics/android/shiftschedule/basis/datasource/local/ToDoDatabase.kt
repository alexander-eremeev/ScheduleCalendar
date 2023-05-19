package com.childmathematics.android.shiftschedule.basis.datasource.local
/*
import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.childmathematics.android.shiftschedule.basis.datasource.local.model.ToDoGroupDb
import com.childmathematics.android.shiftschedule.basis.datasource.local.model.ToDoListDb
import com.childmathematics.android.shiftschedule.basis.datasource.local.model.ToDoStepDb
import com.childmathematics.android.shiftschedule.basis.datasource.local.model.ToDoTaskDb
import com.childmathematics.android.shiftschedule.basis.datasource.local.model.ToDoTaskFtsDb
import com.childmathematics.android.shiftschedule.basis.wrapper.DateTimeProviderImpl
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(
    entities = [
        ToDoGroupDb::class,
        ToDoListDb::class,
        ToDoTaskDb::class,
        ToDoTaskFtsDb::class,
        ToDoStepDb::class
    ],
    version = 2,
    autoMigrations = [
        AutoMigration(from = 1, to = 2)
    ],
)
@TypeConverters(DateConverter::class)
abstract class ToDoDatabase : RoomDatabase() {
    abstract fun toDoWriteDao(): ToDoWriteDao
    abstract fun toDoReadDao(): ToDoReadDao

    @DelicateCoroutinesApi
    companion object {
        private const val TODO_DB_NAME = "todo-db"

        @Volatile
        private var INSTANCE: ToDoDatabase? = null

        fun getInstance(context: Context): ToDoDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        }

        private fun buildDatabase(context: Context): ToDoDatabase {
            val db = Room.databaseBuilder(
                context,
                ToDoDatabase::class.java,
                TODO_DB_NAME
            )
                .addCallback(
                    object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)

                            GlobalScope.launch(Dispatchers.IO) {
                                initPrePopulateDefaultGroup(context)
                            }
                        }
                    }
                )
                .fallbackToDestructiveMigration()

            return db.build()
        }

        private suspend fun initPrePopulateDefaultGroup(context: Context) {
            val currentDate = DateTimeProviderImpl().now()
            val defaultGroup = ToDoGroupDb(
                id = ToDoGroupDb.DEFAULT_ID,
                name = "Others",
                createdAt = currentDate,
                updatedAt = currentDate
            )
            val writeDao = getInstance(context).toDoWriteDao()

            writeDao.insertGroup(listOf(defaultGroup))
        }

    }
}

 */
