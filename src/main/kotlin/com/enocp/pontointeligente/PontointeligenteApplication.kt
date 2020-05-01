package com.enocp.pontointeligente

import com.enocp.pontointeligente.documents.Empresa
import com.enocp.pontointeligente.documents.Funcionario
import com.enocp.pontointeligente.enums.PerfilEnum
import com.enocp.pontointeligente.repositories.EmpresaRepository
import com.enocp.pontointeligente.repositories.FuncionarioRepository
import com.enocp.pontointeligente.utils.SenhaUtils
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PontointeligenteApplication(val empresaRepository: EmpresaRepository,
																	val funcionarioRepository: FuncionarioRepository) : CommandLineRunner {
	override fun run (vararg args: String?){
		empresaRepository.deleteAll()
		funcionarioRepository.deleteAll()
		
	val empresa: Empresa = Empresa("Empresa", "10443887000146")
		empresaRepository.save(empresa)
		
		val admin: Funcionario = Funcionario("admin","admin@empresa.com",
			SenhaUtils().gerarBCrypt("123456"), "25708317000",
			PerfilEnum.ROLE_ADMIN, empresa.id!!)
		funcionarioRepository.save(admin)
		
		val funcinario: Funcionario= Funcionario("Funcionario",
			"funcinario@empresa.com", SenhaUtils().gerarBCrypt("123456"),
			"44325441557", PerfilEnum.ROLE_USUARIO, empresa.id!!)
		funcionarioRepository.save(funcinario)
		
		System.out.println("Empresa ID: " + empresa.id)
		System.out.println("Admin ID: " + admin.id)
		System.out.println("Funcionario ID " + funcinario.id)
	}
	
}

	fun main(args: Array<String>) {
	runApplication<PontointeligenteApplication>(*args)
}
