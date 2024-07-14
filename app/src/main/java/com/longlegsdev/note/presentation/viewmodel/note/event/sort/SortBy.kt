package com.longlegsdev.note.presentation.viewmodel.note.event.sort

sealed class SortBy(val orderBy: OrderBy) {
    class Title(orderBy: OrderBy): SortBy(orderBy)
    class Update(orderBy: OrderBy): SortBy(orderBy)
    class Create(orderBy: OrderBy): SortBy(orderBy)

    fun copy(orderBy: OrderBy): SortBy {
        return when(this) {
            is Title -> Title(orderBy)
            is Update -> Update(orderBy)
            is Create -> Create(orderBy)
        }
    }

    override fun toString(): String {
        return when(this) {
            is Title -> "Title $orderBy"
            is Update -> "Update $orderBy"
            is Create -> "Create $orderBy"
        }
    }
}