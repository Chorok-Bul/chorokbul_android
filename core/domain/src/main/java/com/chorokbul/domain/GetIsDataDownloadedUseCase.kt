package com.chorokbul.domain

import com.chorokbul.data.repository.GetAppPrefsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetIsDataDownloadedUseCase @Inject constructor(
    private val getAppPrefsRepository: GetAppPrefsRepository
) {
    fun getIsSoundSignalDownloaded(): Flow<Boolean> {
        return getAppPrefsRepository.getIsSoundSignalDownloaded()
    }
}
