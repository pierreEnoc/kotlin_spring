package com.enocp.pontointeligente.services

import com.enocp.pontointeligente.documents.Funcionario
import org.springframework.stereotype.Service


interface FuncionarioService {
  fun persistir(funcionario: Funcionario): Funcionario
  fun buscarPorCpf(cpf: String): Funcionario?
  fun buscarPorEmail(email: String): Funcionario?
  fun buscarPorId(id: String): Funcionario?
}

