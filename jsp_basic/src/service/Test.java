package service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hi")
public class Test extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");	//�ѱ۱�������
		response.setContentType("text/html; charset=utf-8");	//html����, utf-8�� �ؼ�
		PrintWriter out = response.getWriter();
		String cnt_ = request.getParameter("cnt");	// cnt_�� �ӽú���
		int cnt = 100;
		if(cnt_ != null && !cnt_.equals(""))
			cnt = Integer.parseInt(cnt_);
		for(int i=0; i<cnt; i++)
			out.println((i+1) + " : �ȳ� Hello~~! <br>");
	}
}
