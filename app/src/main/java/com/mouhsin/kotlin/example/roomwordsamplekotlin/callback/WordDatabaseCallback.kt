package com.mouhsin.kotlin.example.roomwordsamplekotlin.callback

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mouhsin.kotlin.example.roomwordsamplekotlin.dao.Word
import com.mouhsin.kotlin.example.roomwordsamplekotlin.dao.WordDao
import com.mouhsin.kotlin.example.roomwordsamplekotlin.dao.WordRoomDatabase.Companion.INSTANCE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.reflect.KParameter

class WordDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {

    override fun onOpen(db: SupportSQLiteDatabase) {
        super.onOpen(db)
        INSTANCE?.let { database ->
            scope.launch {
                populateDatabase(database.wordDao())
            }
        }
    }

    suspend fun populateDatabase(wordDao: WordDao) {
        // Delete all content here.
        wordDao.deleteAll()

        // Add sample words.
        var word = Word("Hello")
        wordDao.insert(word)
        word = Word("World!")
        wordDao.insert(word)

        // TODO: Add your own words!
    }

}