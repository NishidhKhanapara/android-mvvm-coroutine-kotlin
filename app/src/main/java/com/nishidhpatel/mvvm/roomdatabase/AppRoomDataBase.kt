package com.nishidhpatel.mvvm.roomdatabase

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper
import androidx.room.Room
import com.nishidhpatel.mvvm.models.Museums.Data


@Database(entities = arrayOf(Data::class),version = 1,exportSchema = false)
abstract class AppRoomDataBase :RoomDatabase()
{
    abstract fun databseDao():DataBaseDao

    companion object{

        @Volatile
        private var INSTANCE:AppRoomDataBase?=null

        fun getDatabase(context: Context):AppRoomDataBase{
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppRoomDataBase::class.java,
                    "category_db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }

    override fun createOpenHelper(config: DatabaseConfiguration?): SupportSQLiteOpenHelper {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

    }

    override fun createInvalidationTracker(): InvalidationTracker {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearAllTables() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}