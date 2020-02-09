package com.badzohugues.sample.ui.home.presenter

import com.badzohugues.sample.model.Person
import com.badzohugues.sample.ui.home.Presenter
import com.badzohugues.sample.ui.home.View

class HomePresenter(val view: View): Presenter {

    private val persons by lazy { ArrayList<Person>() }

    override fun fetchPersons() {
        view.fillPersons(buildList())
    }

    private fun buildList(): List<Person>{
        persons.add(Person("Alana", "Haim", 27))
        persons.add(Person("Alanis", "Morissette", 45))
        persons.add(Person("Danielle", "Haim", 30))
        persons.add(Person("Estelle", "Haim", 32))
        persons.add(Person("Kobe", "Bryant", 41))
        persons.add(Person("Justin", "Timberlake", 39))

        return persons
    }
}