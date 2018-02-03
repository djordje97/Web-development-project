package projekatWeb.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import projekatWeb.dao.UserDAO;


public class SubsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public SubsServlet() {

    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String status="failuer";
		String channel=request.getParameter("channel");
		String subs=request.getParameter("subscriber");
		UserDAO.deleteSubs(channel,subs);
		status="success";
		Map<String, Object> data = new HashMap<>();
		data.put("status",status);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(data);
		System.out.println(jsonData);

		response.setContentType("application/json");
		response.getWriter().write(jsonData);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String status="failuer";
		String channel=request.getParameter("channel");
		String subs=request.getParameter("subscriber");
		UserDAO.addSubs(channel,subs);
		status="success";
		
		Map<String, Object> data = new HashMap<>();
		data.put("status",status);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(data);
		System.out.println(jsonData);

		response.setContentType("application/json");
		response.getWriter().write(jsonData);
	}

}
