package projekatWeb.servlets;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.User;
import model.User.Role;
import projekatWeb.dao.UserDAO;
import projekatWeb.dao.VideoDAO;


public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String email = request.getParameter("email");
		
		String status = "success";
		String message ="You sign up successfully";
		try {
			User user = UserDAO.get(userName);
			if (user != null) throw new Exception("Error");
			Date d=new Date();
			String date=VideoDAO.dateToString(d);
			User newUser=new User(userName, password, name, surname, email, "", Role.USER, date, false, null, null, null);
			UserDAO.addUser(newUser);
			
		} catch (Exception ex) {
			status = "failure";
			message="User already exists";
		}
		
		Map<String, Object> data = new HashMap<>();
		data.put("status", status);
		data.put("message", message);
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(data);
		System.out.println(jsonData);

		response.setContentType("application/json");
		response.getWriter().write(jsonData);
	}
	

}
