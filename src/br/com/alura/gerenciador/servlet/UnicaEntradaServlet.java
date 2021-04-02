package br.com.alura.gerenciador.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.alura.gerenciador.acao.Acao;


//@WebServlet("/entrada")
public class UnicaEntradaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String paramAcao = request.getParameter("acao");
		
		String nome = null;
		
		String nomeDaClasse = "br.com.alura.gerenciador.acao." + paramAcao;
		Class classe = null;
		try {
			classe = Class.forName(nomeDaClasse);
			Object obj = classe.newInstance();
			
			Acao acao = (Acao) obj;
			nome = acao.executa(request, response);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			throw new ServletException(e);
		} 
		
		String[] tipoEEndereco = nome.split(":");
		String tipo = tipoEEndereco[0];
		String endereco = tipoEEndereco[1];
		
		if(tipo.equals("forward")) {
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view/"+endereco);
			rd.forward(request, response);
		} else {
			response.sendRedirect(endereco);
		}
		
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
