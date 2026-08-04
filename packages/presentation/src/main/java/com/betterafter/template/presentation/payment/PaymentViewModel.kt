package com.betterafter.template.presentation.payment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.betterafter.template.domain.core.DataState
import com.betterafter.template.domain.payment.entity.PaymentEntity
import com.betterafter.template.domain.payment.usecase.PaymentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val paymentUseCase: PaymentUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<DataState<List<PaymentEntity>>>(DataState.Initial)
    val uiState: StateFlow<DataState<List<PaymentEntity>>> = _uiState.asStateFlow()

    init {
        loadPayments()
    }

    fun loadPayments() {
        viewModelScope.launch {
            _uiState.value = DataState.Loading()
            _uiState.value = paymentUseCase()
        }
    }
}
