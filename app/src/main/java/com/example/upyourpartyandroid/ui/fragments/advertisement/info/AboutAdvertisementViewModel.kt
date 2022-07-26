package com.example.upyourpartyandroid.ui.fragments.advertisement.info

import com.example.android_nav.NavigationScreen
import com.example.domain.model.advertisement.DomainFullAdvertisement
import com.example.domain.model.review.Review
import com.example.domain.usecase.advertisement.ChangeFavoriteStatus
import com.example.domain.usecase.review.GetReviews
import com.example.upyourpartyandroid.navigation.IRouter
import com.example.upyourpartyandroid.ui.base.BaseMVIViewModel
import javax.inject.Inject

class AboutAdvertisementViewModel @Inject constructor(
    dataSource: IAboutAdvertisementWorkGroup,
    val router: IRouter
) : BaseMVIViewModel<AboutAdvertisementState, IAboutAdvertisementWorkGroup>(dataSource) {

    override fun createInitialState(): AboutAdvertisementState =
        AboutAdvertisementState(null, emptyList())

    fun loadData(id: Long) {
        dataSource.getAdvertisement(id).handleSubscribe(onSuccess = ::handleAdvertisementData)
    }

    fun loadReviewsData(
        id: Long
    ) {
        val arguments = GetReviews.Arguments(
            id,
            REVIEWS_LIMIT
        )
        dataSource.getReviews(arguments).handleMutedSubscribe(onSuccess = ::handleReviews)
    }

    private fun handleReviews(reviews: List<Review>) {
        reduce {
            copy(reviews = reviews)
        }
    }

    private fun handleAdvertisementData(advertisement: DomainFullAdvertisement) {
        reduce {
            copy(advertisement = advertisement)
        }
    }

    fun changeFavoriteStatus() {
        currentState.advertisement?.let { advertisement ->
            val arguments = ChangeFavoriteStatus.Arguments(
                advertisement.id,
                !advertisement.isFavorite
            )
            dataSource.changeFavoriteStatus(arguments).handleMutedSubscribe {}
        }
    }

    fun onSubmitReviewClick() {
        currentState.advertisement?.let { advertisement ->
            val screen = NavigationScreen.AdvertisementManager.SubmitReview(advertisement.id)
            router.navigateTo(screen)
        }
    }

    companion object {
        const val REVIEWS_LIMIT = 3
    }

}
