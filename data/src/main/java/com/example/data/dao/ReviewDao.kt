package com.example.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.data.entities.room.review.ReviewCompound
import com.example.data.entities.room.review.ReviewEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
interface ReviewDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(review: ReviewEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(reviews: List<ReviewEntity>): Completable

    @Transaction
    @Query("""
        SELECT * FROM ReviewEntity WHERE advertisementId = :advertisementId
    """)
    fun getReviews(advertisementId: Long): Flowable<List<ReviewCompound>>

}
