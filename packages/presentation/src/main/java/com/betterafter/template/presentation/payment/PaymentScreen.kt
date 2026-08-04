package com.betterafter.template.presentation.payment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.betterafter.template.design.theme.AccentSoft
import com.betterafter.template.design.theme.Danger
import com.betterafter.template.design.theme.Ink
import com.betterafter.template.design.theme.Slate
import com.betterafter.template.design.theme.Warning
import com.betterafter.template.domain.core.DataState
import com.betterafter.template.domain.payment.entity.PaymentEntity
import java.text.NumberFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen(
    viewModel: PaymentViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Payment") })
        },
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            when (val state = uiState) {
                is DataState.Initial,
                is DataState.Loading,
                -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                is DataState.Success -> {
                    if (state.data.isEmpty()) {
                        Text(
                            text = "결제 내역이 없습니다.",
                            color = Slate,
                            modifier = Modifier.align(Alignment.Center),
                        )
                    } else {
                        PaymentList(payments = state.data)
                    }
                }

                is DataState.Error -> {
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        Text(
                            text = state.message ?: "오류가 발생했습니다.",
                            color = Danger,
                        )
                        Button(onClick = viewModel::loadPayments) {
                            Text("다시 시도")
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun PaymentList(payments: List<PaymentEntity>) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(0.dp),
    ) {
        items(payments, key = { it.id }) { payment ->
            PaymentRow(payment)
            HorizontalDivider()
        }
    }
}

@Composable
private fun PaymentRow(payment: PaymentEntity) {
    val amountText = NumberFormat.getCurrencyInstance(Locale.KOREA).format(payment.amount)
    val statusColor = when (payment.status.uppercase(Locale.ROOT)) {
        "COMPLETED" -> Ink
        "PENDING" -> Warning
        else -> Danger
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(AccentSoft.copy(alpha = 0.25f))
            .padding(vertical = 14.dp, horizontal = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column {
            Text(text = amountText, color = Ink)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = payment.status, color = statusColor)
        }
        Text(text = payment.id, color = Slate)
    }
}
