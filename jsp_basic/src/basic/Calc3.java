package basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.sun.xml.internal.ws.api.pipe.Engine;

@WebServlet("/calc3")
public class Calc3 extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		Cookie [] cookies = request.getCookies();
		
		String num = request.getParameter("value");
		String op = request.getParameter("operator");
		String dot = request.getParameter("dot");
		String exp = "";
		
		if(cookies != null) {
			for(Cookie c : cookies) {
				if(c.getName().equals("exp")) {
					exp = c.getValue();
					break;
				}
			}
		}
		if(op != null && op.equals("=")) {
		//스크립트 사용
			ScriptEngine en = new ScriptEngineManager().getEngineByName("nashorn");
			try {
				exp = String.valueOf(en.eval(exp));
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(op!=null && op.equals("c")){
			exp = "";
		}else {
	//num, op, dot 값 exp변수에 넣기
			exp += (num == null)?"": num;
			exp += (op == null)?"": op;
			exp += (dot == null)?"": dot;
		}
		
		Cookie expCookie = new Cookie("exp", exp);
		
		response.addCookie(expCookie);
		if(op!=null && op.equals("c"))
			expCookie.setMaxAge(0);
		response.sendRedirect("calculator.jsp");
	}
}
