package com.raccy.racplanner.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.raccy.racplanner.repository.DetalleCarreraRepository

class DetalleCarreraVMFactory(
    private val repository: DetalleCarreraRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(DetalleCarreraVM::class.java)) {
            return DetalleCarreraVM(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}