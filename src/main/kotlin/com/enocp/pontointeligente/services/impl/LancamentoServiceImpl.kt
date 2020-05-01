package com.enocp.pontointeligente.services.impl

import com.enocp.pontointeligente.documents.Lancamento
import com.enocp.pontointeligente.repositories.LancamentoRepository
import com.enocp.pontointeligente.services.LancamentoService
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class LancamentoServiceImpl (val lancamentoRepository: LancamentoRepository) : LancamentoService {
  override fun buscarPorFuncionarioId(funcionarioId: String, pageRequest: PageRequest) =
    lancamentoRepository.findByFuncionarioId(funcionarioId, pageRequest)
  
  override fun buscarPorId(id: String) = lancamentoRepository.findById(id).get()
  
  override fun persistir(lancamento: Lancamento) = lancamentoRepository.save(lancamento)
  
  override fun remover(id: String) = lancamentoRepository.deleteById(id)
}

