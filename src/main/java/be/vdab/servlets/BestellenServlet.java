package be.vdab.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.vdab.services.WijnService;
import be.vdab.utils.StringUtils;

/**
 * Servlet implementation class BestellenServlet
 */
@WebServlet("/bestellen.htm")
public class BestellenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/bestellen.jsp";
	private final WijnService wijnService = new WijnService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		setAttributeWijn(request);
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> fouten = new HashMap<>();
		String wijnidString = request.getParameter("wijnid");
		String aantalString = request.getParameter("aantal");
		if (StringUtils.isLong(wijnidString)) {
			long wijnid = Long.parseLong(wijnidString);
			setAttributeWijn(request);
			if (StringUtils.isPositiveLong(aantalString)) {
				long aantal = Long.parseLong(aantalString);
				request.setAttribute("aantal", aantal);
				addToWinkelmandje(request, wijnid, aantal);
				response.sendRedirect(response.encodeRedirectURL(request.getContextPath()));
			} else {
				fouten.put("aantal", "Gelieve minimaal 1 artikel te bestellen");
				request.setAttribute("fouten", fouten);
				request.getRequestDispatcher(VIEW).forward(request, response);
			}
		} else {
			response.sendError(404);
		}

	}

	private void addToWinkelmandje(HttpServletRequest request, long wijnid, long aantal) {
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		SortedMap<Long, Long> winkelmandje = (SortedMap<Long, Long>) session
				.getAttribute(WinkelmandjeServlet.WINKELMANDJE);
		if (winkelmandje == null) {
			winkelmandje = new TreeMap<>();
		}
		if (!winkelmandje.containsKey(wijnid)) {
			winkelmandje.put(wijnid, aantal);
		} else {
			long nieuwAantal = winkelmandje.get(wijnid) + aantal;
			winkelmandje.put(wijnid, nieuwAantal);
		}
		session.setAttribute(WinkelmandjeServlet.WINKELMANDJE, winkelmandje);
	}

	private void setAttributeWijn(HttpServletRequest request) {
		String wijnidString = request.getParameter("wijnid");
		if (StringUtils.isLong(wijnidString)) {
			long wijnid = Long.parseLong(wijnidString);
			wijnService.read(wijnid).ifPresent(wijn -> request.setAttribute("wijn", wijn));
		}
	}
}
