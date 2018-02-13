package projekatWeb.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.User;
import projekatWeb.dao.UserDAO;

public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			User loggedInUser = (User) session.getAttribute("loggedInUser");
			ArrayList<User> users = UserDAO.getAll();

			Map<String, Object> data = new HashMap<>();
			data.put("logged", loggedInUser);
			data.put("users", users);
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(data);
			response.setContentType("application/json");
			response.getWriter().write(jsonData);

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String status = request.getParameter("status");

		if(status.equals("delete")) {
		try {
			HttpSession session = request.getSession();
			User loggedInUser = (User) session.getAttribute("loggedInUser");
			String username = request.getParameter("userName");
			User user = UserDAO.get(username);
			user.setDeleted(true);
			UserDAO.updateUser(user);
			Map<String, Object> data = new HashMap<>();
			data.put("status", "success");
			data.put("logged", loggedInUser);
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(data);
			response.setContentType("application/json");
			response.getWriter().write(jsonData);

		} catch (Exception e) {
			System.out.println(e);
		}
		}else {
			String stat = "success";
			ArrayList<User> users=null;
			try {
				String column=request.getParameter("column");
				String ascDesc=request.getParameter("ascDesc");
				System.out.println(ascDesc);
				 users=UserDAO.getAllOrders(column, ascDesc);
				
			} catch (Exception e) {status="faliuer";}
			Map<String, Object> data = new HashMap<>();
			data.put("stat", stat);
			data.put("users", users);
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(data);
			System.out.println(jsonData);
			response.setContentType("application/json");
			response.getWriter().write(jsonData);	
		}
	}

}
