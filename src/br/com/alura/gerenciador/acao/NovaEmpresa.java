package br.com.alura.gerenciador.acao;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.modelo.Banco;
import br.com.alura.gerenciador.modelo.Empresa;

public class NovaEmpresa implements Acao{

	public String executa(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String novaEmpresa = request.getParameter("nome");
		String dataEmpresa = request.getParameter("data");
		Date dataAbertura;	
				
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			dataAbertura = sdf.parse(dataEmpresa);
		} catch (ParseException e) {
			throw new ServletException(e);
		}
		
		Empresa empresa = new Empresa();
		empresa.setNome(novaEmpresa);
		empresa.setDataAbertura(dataAbertura);
		
		Banco banco = new Banco();
		banco.adiciona(empresa);
		
		request.setAttribute("empresa", empresa.getNome());
		
		return "redirect:entrada?acao=ListaEmpresas";
	} 
	
}
