package com.example.coffeeStore.security.service

import com.example.coffeeStore.security.UserDetailsImpl
import com.example.coffeeStore.user.repository.AdminRepository
import com.example.coffeeStore.user.repository.AuthorityRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserDetailsServiceImpl(
    private val adminRepository: AdminRepository,
    private val authorityRepository: AuthorityRepository
): UserDetailsService {

    @Transactional(readOnly = true)
    override fun loadUserByUsername(username: String?): UserDetails {
        val user = username?.let { adminRepository.findAdminByUsername(username) }
            ?: throw UsernameNotFoundException("User not found.")
        return UserDetailsImpl(user = user, authorities = authorityRepository.findAuthoritiesByAdmin(user))
    }
}