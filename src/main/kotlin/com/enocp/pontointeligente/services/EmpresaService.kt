package com.enocp.pontointeligente.services

import com.enocp.pontointeligente.documents.Empresa

interface EmpresaService {
  
  fun buscarPorCnpj(cnpj: String): Empresa?
  
  fun persistir(empresa: Empresa): Empresa
}
