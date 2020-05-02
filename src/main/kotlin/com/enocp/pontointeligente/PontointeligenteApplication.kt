package com.enocp.pontointeligente

import com.enocp.pontointeligente.documents.Empresa
import com.enocp.pontointeligente.documents.Funcionario
import com.enocp.pontointeligente.enums.PerfilEnum
import com.enocp.pontointeligente.repositories.EmpresaRepository
import com.enocp.pontointeligente.repositories.FuncionarioRepository
import com.enocp.pontointeligente.repositories.LancamentoRepository
import com.enocp.pontointeligente.utils.SenhaUtils
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

//@SpringBootApplication
@SpringBootApplication(exclude = arrayOf(SecurityAutoConfiguration::class))
class PontointeligenteApplication(val empresaRepository: EmpresaRepository,
																	val funcionarioRepository: FuncionarioRepository,
																	val lancamentoRepository: LancamentoRepository): CommandLineRunner {
	
	override fun run(vararg args: String?){
		empresaRepository.deleteAll()
		funcionarioRepository.deleteAll()
		lancamentoRepository.deleteAll()
		
		var empresa: Empresa = Empresa("RazaoSocial", "71593640000109")
		empresa = empresaRepository.save(empresa)
		
		var admin: Funcionario = Funcionario("Admin", "admin@teste.com.br", SenhaUtils().gerarBcrypt("123456"), "12920460702",
			PerfilEnum.ROLE_ADMIN, empresa.id!!)
		admin = funcionarioRepository.save(admin)
		
		var funcionario: Funcionario = Funcionario("Usuario", "usuario@teste.com.br", SenhaUtils().gerarBcrypt("123456"), "12920460702",
			PerfilEnum.ROLE_USUARIO, empresa.id!!)
		funcionario = funcionarioRepository.save(funcionario)
		
		System.out.println("Empresa ID: "+ empresa.id)
		System.out.println("Admin ID: "+ admin.id)
		System.out.println("Funcionario ID: "+ funcionario.id)
		
	}
}


//gerarBCrypt
	fun main(args: Array<String>) {
	runApplication<PontointeligenteApplication>(*args)
}
