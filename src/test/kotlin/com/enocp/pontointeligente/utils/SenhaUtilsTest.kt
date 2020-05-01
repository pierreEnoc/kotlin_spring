package com.enocp.pontointeligente.utils

import junit.framework.Assert.assertTrue
import org.assertj.core.api.Assertions
import org.junit.Assert
import org.junit.Assert.assertTrue
import org.junit.Test
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class SenhaUtilsTest {
  private val SENHA = "123456"
  private val bCryptEncoder = BCryptPasswordEncoder()
  
  @Test
  fun testGerarHashSenha() {
    val hash = SenhaUtils().gerarBCrypt(SENHA)
    Assert.assertTrue(bCryptEncoder.matches(SENHA, hash))
  }
}