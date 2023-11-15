package com.example.coffeeStore.user.service

import com.example.coffeeStore.user.domain.Admin
import com.example.coffeeStore.user.domain.Authority
import com.example.coffeeStore.user.domain.Role
import com.example.coffeeStore.user.dto.AdminRegistrationForm
import com.example.coffeeStore.user.repository.AdminRepository
import com.example.coffeeStore.user.repository.AuthorityRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminService(
    private val adminRepository: AdminRepository,
    private val authorityRepository: AuthorityRepository,
    private val encoder: PasswordEncoder
) {

    @Transactional
    fun processRegistration(form: AdminRegistrationForm) {
        require(!adminRepository.existsAdminByUsername(form.username)) {
            throw IllegalArgumentException("아이디가 중복되었습니다.")
        }
        val admin = adminRepository.save(
            Admin(
                username = form.username,
                password = encoder.encode(form.password)
            )
        )
        authorityRepository.save(Authority(admin = admin, role = Role.ROLE_ADMIN))
    }
}