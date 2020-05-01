package com.enocp.pontointeligente.service

import com.enocp.pontointeligente.documents.Lancamento
import com.enocp.pontointeligente.enums.TipoEnum
import com.enocp.pontointeligente.repositories.LancamentoRepository
import com.enocp.pontointeligente.services.LancamentoService
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
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import java.util.*
import kotlin.collections.ArrayList

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureDataMongo
@RunWith(SpringRunner::class)
class LancamentoServiceTest {
  
  @MockBean
  private val lancamentoRepository: LancamentoRepository? = null
  
  @Autowired
  private val lancamentoService: LancamentoService? = null
  
  private val id: String = "1"
  
  @Before
  @Throws(Exception::class)
  fun setUp() {
    BDDMockito
      .given<Page<Lancamento>>(lancamentoRepository?.findByFuncionarioId(id, PageRequest.of(0, 10)))
      .willReturn(PageImpl(ArrayList<Lancamento>()))
    BDDMockito.given(lancamentoRepository?.findById("1")).willReturn(Optional.of(lancamento()))
    BDDMockito.given(lancamentoRepository?.save(Mockito.any(Lancamento::class.java)))
      .willReturn(lancamento())
  }
  
  @Test
  fun testBuscarLancamentoPorFuncionarioId() {
    val lancamento: Page<Lancamento>? = lancamentoService?.buscarPorFuncionarioId(id, PageRequest.of(0, 10))
    Assertions.assertThat(lancamento)
  }
  
  @Test
  fun testBuscarLancamentoPorId() {
    val lancamento: Lancamento? = lancamentoService?.buscarPorId(id)
    Assertions.assertThat(lancamento)
  }
  
  @Test
  fun testPersistirLancamento() {
    val lancamento: Lancamento? = lancamentoService?.persistir(lancamento())
    Assertions.assertThat(lancamento)
  }
  
  private fun lancamento(): Lancamento = Lancamento(Date(), TipoEnum.INICIO_TRABALHO, id)
}