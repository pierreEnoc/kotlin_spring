package com.enocp.pontointeligente.service

import com.enocp.pontointeligente.documents.Empresa
import com.enocp.pontointeligente.repositories.EmpresaRepository
import com.enocp.pontointeligente.services.EmpresaService
import junit.framework.TestCase.assertNotNull
import org.assertj.core.api.Assert
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureDataMongo
@RunWith(SpringRunner::class)
class EmpresaServiceTest {
  
  @Autowired
  val empresaService: EmpresaService? = null
  
  @MockBean
  private val empresaRepository: EmpresaRepository? = null
  
  private val CNPJ = "51463645000100"
  
  @Before
  @Throws(Exception::class)
  fun setUp() {
    BDDMockito.given(empresaRepository?.findByCnpj(CNPJ)).willReturn(empresa())
    BDDMockito.given(empresaRepository?.save(empresa())).willReturn(empresa())
  }
  
  @Test
  fun testBuscarEmpresaPorCnpj() {
    val empresa: Empresa? = empresaService?.buscarPorCnpj(CNPJ)
    Assertions.assertThat(empresa)
  }
  
  @Test
  fun testPersistirEmpresa() {
    val empresa: Empresa? = empresaService?.persistir(empresa())
    Assertions.assertThat(empresa)
  }
  
  private fun empresa(): Empresa = Empresa("Razão Social", CNPJ, "1")
}