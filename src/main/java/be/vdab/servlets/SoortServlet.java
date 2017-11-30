package be.vdab.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.services.LandService;
import be.vdab.services.SoortService;
import be.vdab.services.WijnService;

/**
 * Servlet implementation class SoortServlet
 */
@WebServlet("/soorten.htm")
public class SoortServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/soorten.jsp";
	// SERVICES
	private final SoortService soortService = new SoortService();
	private final LandService landService = new LandService();
	private final WijnService wijnService = new WijnService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		setAttributeLand(request);
		setAttributeSoorten(request);
		setAttributeWijnen(request);
		setAttributeSoort(request);
		request.getRequestDispatcher(VIEW).forward(request, response);

	}

	private void setAttributeLand(HttpServletRequest request) {
		String landidString = request.getParameter("landid");
		if (landidString != null && !landidString.trim().isEmpty()) {
			long landid = Long.parseLong(landidString);
			landService.read(landid).ifPresent(land -> request.setAttribute("land", land));
		}
	}

	private void setAttributeSoorten(HttpServletRequest request) {
		String landidString = request.getParameter("landid");
		if (landidString != null && !landidString.trim().isEmpty()) {
			long landid = Long.parseLong(landidString);
			request.setAttribute("soorten", soortService.findByLand(landid));
		}
	}

	private void setAttributeWijnen(HttpServletRequest request) {
		String soortidString = request.getParameter("soortid");
		if (soortidString != null && !soortidString.trim().isEmpty()) {
			long soortid = Long.parseLong(soortidString);
			request.setAttribute("wijnen", wijnService.findBySoort(soortid));
		}
	}
	
	private void setAttributeSoort(HttpServletRequest request) {
		String soortidString = request.getParameter("soortid");
		if (soortidString != null && !soortidString.trim().isEmpty()) {
			long soortid = Long.parseLong(soortidString);
			soortService.read(soortid).ifPresent(soort -> request.setAttribute("soort",soort));
		}
	}

}
