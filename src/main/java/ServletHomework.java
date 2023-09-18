import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletHomework extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("euc-kr");

		PrintWriter out = resp.getWriter();
		out.println("<html><body>");

		if (req.getMethod().equals("POST")) {
			out.println("<br><br>");
			out.println("<h1>회원가입이 완료되었습니다!</h1>");
		}

		String chk_cook = req.getParameter("cook");

		Cookie valueCook;

		Enumeration<String> enumer = req.getParameterNames();
		while (enumer.hasMoreElements()) {
			String name = enumer.nextElement();
			if (name != null) {
				String[] values = req.getParameterValues(name);
				out.println("<h4> [" + name + "] </h4>");

				for (String val : values) {
					out.print("<h4>" + val + "</h4>");
				}

				if (chk_cook != null) {
					for (String val : values) {
						// Cookie valueCook = new Cookie(name, val);
						valueCook = new Cookie(name, val);
						valueCook.setMaxAge(60 * 60 * 24);
						resp.addCookie(valueCook);
					}
				}
			}
		}

		if (chk_cook != null) {
			out.println("<form method=\"get\" action=\"/HomeWork/homework.action\">");
			out.println("<input type=\"submit\" value=\"저장된 쿠키 확인\" />");
			out.println("</form>");
		}

		if (req.getMethod().equals("GET")) {
			out.println("<h3>저장된 쿠키</h3>");
			Cookie[] usercook = req.getCookies();
			for (int i = 0; i < usercook.length; i++) {
				valueCook = usercook[i];
				String c_name = valueCook.getName();
				String c_value = valueCook.getValue();
				out.println(c_name + " = " + c_value);
			}
		}

		out.println("</body></html>");

	}

}
