package com.example.coffeestore.global.domain

import org.springframework.data.jpa.domain.support.AuditingEntityListener
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
open class BaseTimeEntity : BaseEntity() {

}