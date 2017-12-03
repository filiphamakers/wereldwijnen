package be.vdab.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.vdab.entities.Bestelbon;
import be.vdab.services.BestelbonService;
import be.vdab.services.WijnService;
import be.vdab.utils.StringUtils;
import be.vdab.valueobjects.Bestelbonlijn;

/**
 * Servlet implementation class WinkelmandjeServlet
 */
@WebServlet("/winkelmandje.htm")
public class WinkelmandjeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/winkelmandje.jsp";
	private static final String REDIRECT_URL = "%s/bevestigd.htm";
	public static final String WINKELMANDJE = "winkelmandje";
	public static final String LAATSTE_BESTELBON = "laatsteBestelbon";
	private final WijnService wijnService = new WijnService();
	private final BestelbonService bestelbonService = new BestelbonService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Bestelbonlijn> bestelbonlijnen = converteerNaarBestelbonlijnen(request, getWinkelmandje(request));
		request.setAttribute("bestelbonlijnen", bestelbonlijnen);
		request.setAttribute("totaal", berekenTotaal(bestelbonlijnen));
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> fouten = new HashMap<>();
		String naam = setStringAttribute("naam", request, fouten);
		String straat = setStringAttribute("straat", request, fouten);
		String huisnummer = setStringAttribute("huisnummer", request, fouten);
		String postcode = setStringAttribute("postcode", request, fouten);
		String gemeente = setStringAttribute("gemeente", request, fouten);
		Integer bestelwijze = setIntAttribute("bestelwijze", request, fouten);
		if (!fouten.isEmpty()) {
			request.setAttribute("fouten", fouten);
			doGet(request, response);
		} else if (!getWinkelmandje(request).isEmpty())

		{
			Long bonid = bestelbonService.createBestelbon(new Bestelbon(naam, straat, huisnummer, postcode, gemeente,
					bestelwijze, converteerNaarBestelbonlijnen(request, getWinkelmandje(request))));
			request.getSession().setAttribute(LAATSTE_BESTELBON, bonid);
			response.sendRedirect(response.encodeRedirectURL(String.format(REDIRECT_URL, request.getContextPath())));
		} else
			response.sendRedirect(response.encodeRedirectURL(String.format(REDIRECT_URL, request.getContextPath())));
		
	}

	@SuppressWarnings("unchecked")
	private Map<Long, Long> getWinkelmandje(HttpServletRequest request) {
		Map<Long, Long> winkelmandje = new TreeMap<>();
		HttpSession session = request.getSession(false);
		if (session != null) {
			winkelmandje = (Map<Long, Long>) session.getAttribute(WINKELMANDJE);
		}
		return winkelmandje;
	}

	private String setStringAttribute(String parameter, HttpServletRequest request, Map<String, String> fouten) {
		request.setAttribute(parameter, valideerStringParameter(parameter, request.getParameter(parameter), fouten));
		return request.getParameter(parameter);
	}

	private Integer setIntAttribute(String parameter, HttpServletRequest request, Map<String, String> fouten) {
		try {
			Integer parameterInt = Integer.parseInt(request.getParameter(parameter));
			if (parameterInt >= 0 && parameterInt <= 1) {
				request.setAttribute(parameter, parameterInt);
				return parameterInt;
			}
			throw new NumberFormatException();
		} catch (NumberFormatException ex) {
			fouten.put("bestelwijze", "Gelieve een bestelwijze te kiezen");
			return null;
		}

	}

	private String valideerStringParameter(String parameter, String teValiderenString, Map<String, String> fouten) {
		if (StringUtils.isEmptyOrNull(teValiderenString)) {
			fouten.put(parameter, "Dit veld mag niet leeg zijn");
			return null;
		}
		return teValiderenString;
	}

	private List<Bestelbonlijn> converteerNaarBestelbonlijnen(HttpServletRequest request,
			Map<Long, Long> winkelmandje) {
		List<Bestelbonlijn> bestelbonlijnen = new ArrayList<>();
		if (winkelmandje != null) {
			winkelmandje.keySet().stream().forEach(wijnid -> bestelbonlijnen
					.add(new Bestelbonlijn(wijnService.read(wijnid).get(), winkelmandje.get(wijnid))));
		}
		return bestelbonlijnen;
	}

	private BigDecimal berekenTotaal(List<Bestelbonlijn> bestelbonlijnen) {
		if (bestelbonlijnen != null) {
			BigDecimal totaal = bestelbonlijnen.stream().map(Bestelbonlijn::getSubtotaal).reduce(BigDecimal.ZERO,
					(subtotaalA, subtotaalB) -> subtotaalA.add(subtotaalB));
			return totaal;
		}
		return null;
	}

}
