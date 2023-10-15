package com.codezilla.cleanarchitecturenoteapp.feature_note.domain.util

sealed class OrderType{
    // sealed classes are used to define classes that may have variations
    object  Ascending:OrderType()
    object  Descending:OrderType()
}
