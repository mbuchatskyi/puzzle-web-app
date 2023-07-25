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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/pages/home.jsp");

		if (request.getParameter("image") == null) {
			requestDispatcher.forward(request, response);
		}

		if (request.getParameter("image") != null) {
			switch (request.getParameter("image")) {
			case "1":
				chooseImageToSplit(1, request, response);
				break;
			case "2":
				chooseImageToSplit(2, request, response);
				break;
			case "3":
				chooseImageToSplit(3, request, response);
				break;
			case "4":
				chooseImageToSplit(4, request, response);
				break;
			default:
				chooseImageToSplit(1, request, response);
			}
		}
	}

	private void chooseImageToSplit(int index, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		ServletContext context = getServletContext();
		InputStream inputStream = context.getResourceAsStream("WEB-INF/baseimage_" + index + ".jpg");
		// execute split command
		new SplitImageCommand().execute(inputStream, request, response);
		response.sendRedirect("/puzzle");
	}
}
