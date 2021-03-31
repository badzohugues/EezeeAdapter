package com.badzohugues.sample.ui.home.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.badzohugues.sample.model.Person

class HomeViewModel: ViewModel() {
    private val _persons = MutableLiveData<List<Person>>()
    val persons: LiveData<List<Person>> get() = _persons

    fun buildList() {
        with(ArrayList<Person>()) {
            add(Person("Alana", "Haim", 27))
            add(Person("Alanis", "Morissette", 45))
            add(Person("Danielle", "Haim", 30))
            add(Person("Estelle", "Haim", 32))
            add(Person("Kobe", "Bryant", 41))
            add(Person("Justin", "Timberlake", 39))
            _persons.value = this
        }
    }
}