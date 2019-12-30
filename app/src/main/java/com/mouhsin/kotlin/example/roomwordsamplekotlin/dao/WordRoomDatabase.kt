package com.mouhsin.kotlin.example.roomwordsamplekotlin.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mouhsin.kotlin.example.roomwordsamplekotlin.callback.WordDatabaseCallback
import kotlinx.coroutines.CoroutineScope

@Database(entities = arrayOf(Word::class), version = 1 , exportSchema = false)
public abstract class WordRoomDatabase : RoomDatabase(){
    abstract fun wordDao(): WordDao

    companion object{

        @Volatile
        var INSTANCE: WordRoomDatabase? = null

        fun getDatabase(context: Context ,  scope: CoroutineScope): WordRoomDatabase{
            val tempInstance = INSTANCE
            if(tempInstance !=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext, WordRoomDatabase::class.java,
                    "word_database"
                ).addCallback(WordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance

                return instance
            }
        }
    }

}