package com.example.data.database

import androidx.room.TypeConverter
import java.util.stream.Collectors

class TypeConverter {

    @TypeConverter
    fun fromCollectionToString(elements: Collection<String>): String =
        elements.stream().collect(Collectors.joining(","))

    @TypeConverter
    fun toCollectionString(string: String): List<String> = string.split(",")

}