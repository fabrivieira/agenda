package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO;
import model.JavaBeans;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class Controller.
 */
@WebServlet({ "/Controller", "/main", "/novoContato", "/jsonValue" })
public class Controller extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The conn. */
	private static Connection conn = null;
	
	/** The stm. */
	private static Statement stm = null;

	/**
	 * Instantiates a new controller.
	 *
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Do get.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		if (action.equals("/main") || action.equals("/Controller")) {
			listaContatos(request, response);
		}
		if (action.equals("/jsonValue")) {
			jsonValue(request, response);
		}
	}

	/**
	 * Do post.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		if (action.equals("/novoContato")) {
			novoContato(request, response);
		}
	}

	/**
	 * Novo contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void novoContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String fone = request.getParameter("fone");
		JavaBeans contato = new JavaBeans(nome, email, fone);
		try {
			String query = "INSERT INTO agenda(nome, email, fone) values('" + nome + "','" + email + "','" + fone
					+ "')";
			DAO.conn().createStatement().executeUpdate(query);
		} catch (Exception e) {
			// TODO: handle exception
		}
		listaContatos(request, response);

	}

	/**
	 * Lista contatos.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void listaContatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String query = "SELECT * FROM agenda order by nome";
			ResultSet rs = DAO.conn().createStatement().executeQuery(query);
			List<JavaBeans> contatos = new ArrayList<>();
			while (rs.next()) {
				contatos.add(new JavaBeans(rs.getString("id"), rs.getString("nome"), rs.getString("fone"),
						rs.getString("email")));
			}
			request.setAttribute("contatos", contatos);
			request.getRequestDispatcher("agenda.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Json value.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void jsonValue(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//JsonObject json = Json.createObjectBuilder().add("foo", "bar").build();
		response.setContentType("application/json");
		response.getWriter().append("[{teste:false}]");
		
	}

}

/*
 * response.setStatus(404);
 * response.getWriter().append("Servedd at: ").append(request.getContextPath());
 * stm = conn.createStatement(); stm.
 * executeUpdate("INSERT INTO agenda(nome, fone, email) values('TESTE', 'TESTE2', 'TESTE3')"
 * ); System.out.println(stm.toString());
 * 
 */
