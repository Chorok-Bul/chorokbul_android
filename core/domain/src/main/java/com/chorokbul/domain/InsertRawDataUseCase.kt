package com.chorokbul.domain

import com.chorokbul.data.repository.InsertRawDataRepository
import javax.inject.Inject

class InsertRawDataUseCase @Inject constructor(
    private val insertRawDataRepository: InsertRawDataRepository
) {
    suspend operator fun invoke() {
        insertRawDataRepository.insertSoundSignalRawData()
    }
}
