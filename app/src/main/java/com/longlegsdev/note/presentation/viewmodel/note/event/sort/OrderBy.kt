package com.longlegsdev.note.presentation.viewmodel.note.event.sort

sealed class OrderBy {
    object Ascending: OrderBy()
    object Descending: OrderBy()

    override fun toString(): String {
        return when(this) {
            is Ascending -> "Ascending"
            is Descending -> "Descending"
        }
    }
}