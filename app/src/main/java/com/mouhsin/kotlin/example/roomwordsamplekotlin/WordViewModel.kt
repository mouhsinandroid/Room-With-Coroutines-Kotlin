package com.mouhsin.kotlin.example.roomwordsamplekotlin

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.mouhsin.kotlin.example.roomwordsamplekotlin.dao.Word
import com.mouhsin.kotlin.example.roomwordsamplekotlin.dao.WordRepository
import com.mouhsin.kotlin.example.roomwordsamplekotlin.dao.WordRoomDatabase
import kotlinx.coroutines.launch

class WordViewModel(application: Application): AndroidViewModel(application) {

    private val repository: WordRepository
    val allWords: LiveData<List<Word>>

    init {
        val wordsDoa =  WordRoomDatabase.getDatabase(application , viewModelScope).wordDao()
        repository = WordRepository(wordsDoa)
        allWords = repository.allWords
    }

    fun insert(word: Word)= viewModelScope.launch {
        repository.insert(word)
    }
}