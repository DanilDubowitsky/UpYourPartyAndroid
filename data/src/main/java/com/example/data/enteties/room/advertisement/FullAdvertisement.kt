package com.example.data.enteties.room.advertisement

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.enteties.network.advertisement.AdsProfile
import com.example.data.enteties.room.advertisement.FullAdvertisement.Companion.FULL_ADVERTISEMENT_TABLE_NAME
import com.example.domain.enteties.advertisement.DomainFullAdvertisement
import com.google.gson.annotations.SerializedName

@Entity(tableName = FULL_ADVERTISEMENT_TABLE_NAME)
data class FullAdvertisement(
    @PrimaryKey
    val id: Long,
    val email: String,
    val phoneNumber: String,
    val title: String,
    val description: String,
    val price: Int,
    val city: String,
    val category: String,
    val rating: String
) {

    fun toDomain(): DomainFullAdvertisement = DomainFullAdvertisement(
        id,
        email,
        phoneNumber,
        title,
        description,
        price,
        city,
        category,
        rating
    )

    companion object {

        fun toEntity(item: DomainFullAdvertisement): FullAdvertisement =
            item.run {
                FullAdvertisement(
                    id,
                    email,
                    phoneNumber,
                    title,
                    description,
                    price,
                    city,
                    category,
                    rating
                )
            }

        const val ID = "id"

        const val FULL_ADVERTISEMENT_TABLE_NAME = "full_advertisement_table_name"
    }

}


