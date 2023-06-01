package mbuchatskyi.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mbuchatskyi.command.SplitImageCommand;


@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/pages/home.jsp");
	
		ServletContext context = getServletContext();
		InputStream inputStream = context
				.getResourceAsStream("WEB-INF/baseimage.jpg");
		
		new SplitImageCommand().execute(inputStream, request, response);	
		requestDispatcher.forward(request, response);
	}
}
