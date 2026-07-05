package com.raccy.racplanner.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.raccy.racplanner.repository.CarreraRepository

class BuscadorVMFactory(
    private val repository: CarreraRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {
        if (modelClass.isAssignableFrom(BuscadorVM::class.java)) {
            return BuscadorVM(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}