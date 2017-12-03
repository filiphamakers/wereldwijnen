package be.vdab.servlets;

import java.io.IOException;
import java.util.NoSuchElementException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.vdab.entities.Bestelbon;
import be.vdab.services.BestelbonService;
import be.vdab.services.WijnService;

/**
 * Servlet implementation class BevestigdServlet
 */
@WebServlet("/bevestigd.htm")
public class BevestigdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/bevestigd.jsp";
	private final BestelbonService bestelbonService = new BestelbonService();
	private final WijnService wijnService = new WijnService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		try {
			Long bonid = (Long) session.getAttribute(WinkelmandjeServlet.LAATSTE_BESTELBON);
			Bestelbon bestelbon = bestelbonService.read(bonid.longValue()).get();
			bestelbon.getBestelbonlijnen().stream()
					.forEach(lijn -> wijnService.incrementInBestelling(lijn.getWijn().getId(), lijn.getAantal()));
			request.setAttribute("status", String.format("Uw mandje is bevestigd als bestelbon %d", bestelbon.getId()));
			session.invalidate();
		} catch (NullPointerException | NoSuchElementException ex) {
			request.setAttribute("status", "Uw mandje kon niet als bestelling worden bevestigd");
		}
		request.setAttribute("home", "index.htm");
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

}
