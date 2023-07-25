package mbuchatskyi.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mbuchatskyi.service.SubImageService;

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
		// display on JSP-page the amount of the images
		request.setAttribute("priorities", numbers);
		
		// gets the service instance
		SubImageService service = SubImageService.getInstance();

		// get input parameters from JSP
		if (request.getParameter("prio") != null && request.getParameter("prio2") != null) {
			int firstpuzzle = Integer.parseInt(request.getParameter("prio"));
			int secondpuzzle = Integer.parseInt(request.getParameter("prio2"));
			
			// swap a given sub-images
			service.swap(firstpuzzle - 1, secondpuzzle - 1);
		}
	
		if (service.isPuzzleSolved()) {
			request.setAttribute("truePuzzle", "e");
			request.removeAttribute("falsePuzzle");
		}
		
		if (!service.isPuzzleSolved()) {
			request.setAttribute("falsePuzzle", "e");
			request.removeAttribute("truePuzzle");
		}
		
		requestDispatcher.forward(request, response);
	}
}
