package com.betterafter.template.data.payment.mapper

import com.betterafter.template.data.payment.dto.PaymentDto
import com.betterafter.template.data.payment.local.PaymentEntityRoom
import com.betterafter.template.domain.payment.entity.PaymentEntity
import javax.inject.Inject

class PaymentMapper @Inject constructor() {

    fun toEntity(dto: PaymentDto): PaymentEntity = PaymentEntity(
        id = dto.id,
        amount = dto.amount,
        status = dto.status,
    )

    fun toEntityList(dtos: List<PaymentDto>): List<PaymentEntity> =
        dtos.map(::toEntity)

    fun toRoom(entity: PaymentEntity): PaymentEntityRoom = PaymentEntityRoom(
        id = entity.id,
        amount = entity.amount,
        status = entity.status,
    )

    fun toRoomList(entities: List<PaymentEntity>): List<PaymentEntityRoom> =
        entities.map(::toRoom)

    fun fromRoom(room: PaymentEntityRoom): PaymentEntity = PaymentEntity(
        id = room.id,
        amount = room.amount,
        status = room.status,
    )

    fun fromRoomList(rooms: List<PaymentEntityRoom>): List<PaymentEntity> =
        rooms.map(::fromRoom)
}
