package be.vdab.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.services.LandService;
import be.vdab.services.SoortService;

/**
 * Servlet implementation class SoortServlet
 */
@WebServlet("/soorten.htm")
public class SoortServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/soorten.jsp";
	private final SoortService soortService = new SoortService();
	private final LandService landService = new LandService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String landid = request.getParameter("landid");
		if (landid != null && !landid.trim().isEmpty()) {
			landService.read(Long.parseLong(landid)).ifPresent(land -> request.setAttribute("landnaam", land.getNaam()));
			request.setAttribute("soorten", soortService.findByLand(Long.parseLong(landid)));
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

}
