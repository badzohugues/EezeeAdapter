package com.badzohugues.eezeeadapter

/* Builder Class Example:
 class Car( //add private constructor if necessary
        val model: String?,
        val year: Int) {

    private constructor(builder: Builder) : this(builder.model, builder.year)

    class Builder {
        var model: String? = null
            private set

        var year: Int = 0
            private set

        fun model(model: String) = apply { this.model = model }

        fun year(year: Int) = apply { this.year = year }

        fun build() = Car(this)
    }
}

Usage: val car = Car.Builder().model("X").build()

Shortened EXAMPLE

class Car (
        val model: String?,
        val year: Int
) {

    private constructor(builder: Builder) : this(builder.model, builder.year)

    companion object {
        inline fun build(block: Builder.() -> Unit) = Builder().apply(block).build()
    }

    class Builder {
        var model: String? = null
        var year: Int = 0

        fun build() = Car(this)
    }
}

Usage: val car = Car.build { model = "X" }

WHAT I WANT

val adapter = EezeeAdapter.build {
    items = ArrayList<Item>,
    holder = R.id.item_recyclerview,
    bind(holder, position) = { holder, position ->
        val person = items[position]
        val context= holder.itemView.context

        holder.firstnameTxv.text = "${person.firstname} ${person.lastname}"
        holder.ageTxv.text = context.getString(R.string.age_text, person.age)
    }
}


*/