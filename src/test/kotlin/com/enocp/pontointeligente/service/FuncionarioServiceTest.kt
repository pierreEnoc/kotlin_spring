package com.enocp.pontointeligente.service

import com.enocp.pontointeligente.documents.Funcionario
import com.enocp.pontointeligente.enums.PerfilEnum
import com.enocp.pontointeligente.repositories.FuncionarioRepository
import com.enocp.pontointeligente.services.FuncionarioService
import com.enocp.pontointeligente.utils.SenhaUtils

import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import java.util.*

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureDataMongo
@RunWith(SpringRunner::class)
class FuncionarioServiceTest {
  
  @MockBean
  private val funcionarioRepository: FuncionarioRepository? = null
  
  @Autowired
  private val funcionarioService: FuncionarioService? = null
  
  private val email: String = "email@email.com"
  private val cpf: String = "34234855948"
  private val id: String = "1"
  
  @Before
  //@BeforeEach
  @Throws(Exception::class)
  fun setUp() {
    BDDMockito.given(funcionarioRepository?.save(Mockito.any(Funcionario::class.java)))
      .willReturn(funcionario())
    //TODO BDDMockito.given(funcionarioRepository?.findOne(id)).willReturn(funcionario())
    BDDMockito.given(funcionarioRepository?.findById(id)).willReturn(Optional.of(funcionario()))
    BDDMockito.given(funcionarioRepository?.findByEmail(email)).willReturn(funcionario())
    BDDMockito.given(funcionarioRepository?.findByCpf(cpf)).willReturn(funcionario())
  }
  
  @Test
  fun testPersistirFuncionario() {
    val funcionario: Funcionario? = this.funcionarioService?.persistir(funcionario())
    Assertions.assertThat(funcionario)
  }
  
  @Test
  fun testBuscarFuncionarioPorId() {
    val funcionario: Funcionario? = this.funcionarioService?.buscarPorId(id)
    Assertions.assertThat(funcionario)
  }
  
  @Test
  fun testBuscarFuncionarioPorEmail() {
    val funcionario: Funcionario? = this.funcionarioService?.buscarPorEmail(email)
    Assertions.assertThat(funcionario)
  }
  
  @Test
  fun testBuscarFuncionarioPorCpf() {
    val funcionario: Funcionario? = this.funcionarioService?.buscarPorCpf(cpf)
    Assertions.assertThat(funcionario)
  }
  
  private fun funcionario(): Funcionario =
    Funcionario("Nome", email, SenhaUtils().gerarBCrypt("123456"),
      cpf, PerfilEnum.ROLE_USUARIO, id)

}