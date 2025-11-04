package com.ne.samplenewars

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import com.ne.samplenewars.SuffName.FavName
import com.ne.samplenewars.SuffName.SuffName

class SharedNamesViewModel(application: Application) : AndroidViewModel(application) {

    private val db = SuffDatabase.getDatabase(application)
    private val suffDao = db.suffDao()
    private val favDao = db.favDao()

    private val _suffNames = MutableLiveData<List<SuffName>>()
    val suffNames: LiveData<List<SuffName>> = _suffNames

    private val _favNames = MutableLiveData<List<FavName>>()
    val favNames: LiveData<List<FavName>> = _favNames

    fun loadSuffNames() {
        viewModelScope.launch {
            _suffNames.postValue(suffDao.getAllSuffNames())
        }
    }

    fun loadFavNames() {
        viewModelScope.launch {
            _favNames.postValue(favDao.getAllFavNames())
        }
    }

    fun addToFav(name: String) {
        viewModelScope.launch {
            favDao.insertFavName(FavName(name = name))
            _favNames.postValue(favDao.getAllFavNames())
        }
    }

    fun deleteFromFav(id: Long) {
        viewModelScope.launch {
            favDao.deleteFavById(id)
            _favNames.postValue(favDao.getAllFavNames())
        }
    }

    fun addStaticSuffNames() {
        viewModelScope.launch {
            if (suffDao.getAllSuffNames().isEmpty()) {
                val sampleNames = listOf(
                    SuffName(name = "Ae-Cha"),
                    SuffName(name = "Ari"),
                    SuffName(name = "Min-Ji"),
                    SuffName(name = "Hana"),
                    SuffName(name = "Soo-Yeon")
                )
                sampleNames.forEach { suffDao.insertSuffName(it) }
                _suffNames.postValue(suffDao.getAllSuffNames())
            }
        }
    }
}
