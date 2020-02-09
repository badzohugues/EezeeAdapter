package com.badzohugues.sample.ui.home

import com.badzohugues.sample.model.Person

interface View {
    fun fillPersons(persons: List<Person>)
}

interface Presenter {
    fun fetchPersons()
}