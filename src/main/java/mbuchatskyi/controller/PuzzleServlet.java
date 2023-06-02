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
import mbuchatskyi.repository.SubImageRepository;

@WebServlet("/puzzle")
public class PuzzleServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/pages/puzzle.jsp");
		int[] numbers = new int[16];
		for (int i = 1; i < numbers.length + 1; i++) {
			numbers[i - 1] = i;
		}
		// display on JSP-page the list of Priorities
		request.setAttribute("priorities", numbers);
		
		SubImageRepository repo = SubImageRepository.getInstance();
		// get input parameters from JSP
		if (request.getParameter("prio") != null && request.getParameter("prio2") != null) {
			int firstpuzzle = Integer.parseInt(request.getParameter("prio"));
			int secondpuzzle = Integer.parseInt(request.getParameter("prio2"));
			System.out.println(firstpuzzle);
			System.out.println(secondpuzzle);
			repo.swap(firstpuzzle - 1, secondpuzzle - 1);
		}
		
		if (repo.isPuzzleSolved()) {
			request.setAttribute("truePuzzle", "e");
			request.removeAttribute("falsePuzzle");
		}
		
		if (!repo.isPuzzleSolved()) {
			request.setAttribute("falsePuzzle", "e");
			request.removeAttribute("truePuzzle");
		}
		
		requestDispatcher.forward(request, response);
	}
}
