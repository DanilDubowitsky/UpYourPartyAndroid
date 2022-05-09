package com.example.data.service.advertisements

import com.example.data.NetworkApi
import com.example.data.enteties.network.advertisement.NetFullAdvertisement
import com.example.domain.enteties.advertisement.DomainFullAdvertisement
import com.example.domain.preferences.IPreferencesContract
import com.example.domain.service.IService
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class AdvertisementsService @Inject constructor(
    private val api: NetworkApi,
    private val userPreferences: IPreferencesContract.IUserPreferences
) : IService.IAdvertisementsService {

    override fun getMyAdvertisements(): Single<List<DomainFullAdvertisement>> {
        val token = userPreferences.authToken
        return api.getMyAdvertisements(token).map { fullAdvertisements ->
            fullAdvertisements.map(NetFullAdvertisement::toDomain)
        }
    }

}