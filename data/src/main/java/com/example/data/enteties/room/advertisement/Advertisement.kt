package com.example.data.enteties.room.advertisement

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.enteties.room.advertisement.Advertisement.Fields.ADVERTISEMENT_TABLE_NAME
import com.example.domain.enteties.advertisement.DomainAdvertisement

@Entity(tableName = ADVERTISEMENT_TABLE_NAME)
data class Advertisement(
    @PrimaryKey val id: Long,
    val title: String,
    val rating: Float,
    val price: Int,
    val city: String,
    val category: String,
    val description: String,
    val images: Collection<String>,
    val isFavorite: Boolean
) {

    fun toDomain(): DomainAdvertisement {
        return DomainAdvertisement(
            id,
            title,
            rating,
            price,
            city,
            category,
            description,
            images.toList(),
            isFavorite
        )
    }

    companion object Fields {

        fun toDomainAdvertisementsList(advertisement: List<Advertisement>)
            = advertisement.map(Advertisement::toDomain)

        const val CATEGORY = "category"
        const val ADVERTISEMENT_TABLE_NAME = "advertisement_table_name"
    }
}