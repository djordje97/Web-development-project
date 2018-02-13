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
import model.User.Role;
import model.Video;
import projekatWeb.dao.UserDAO;
import projekatWeb.dao.VideoDAO;;

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		String username=request.getParameter("userName");
		User owner = UserDAO.get(username);
		ArrayList<Video> videos = null;
		String status="visiter";
		String isSubscribed="unsuscribed";
		if(loggedInUser != null) {
			status="logedUser";
			int	isSub=UserDAO.findSubscribed(owner.getUserName(),loggedInUser.getUserName());
			System.out.println(loggedInUser.getUserName());

				if(isSub > 0) {
					isSubscribed="subscribe";
				}
				if(loggedInUser.getUserName().equals(username) || loggedInUser.getRole().toString().equals("ADMIN")) {
					videos=VideoDAO.userVideo(owner.getUserName()); 
				}
				
		}else {
			videos=VideoDAO.userPublicVideo(username);
		}
		
		
		 
		ArrayList<User> subs=UserDAO.subscribedOn(username);
		int subNumber=UserDAO.getSubsNumber(username);
		Map<String, Object> data = new HashMap<>();
		data.put("owner", owner);
		data.put("subNumber", subNumber);
		data.put("videos", videos);
		data.put("subs", subs);
		data.put("status", status);
		data.put("user", loggedInUser);
		data.put("isSubscribed",isSubscribed);
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(data);
		response.setContentType("application/json");
		response.getWriter().write(jsonData);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String status=request.getParameter("status");
		if(status.equals("edit")) {
			String name=request.getParameter("name");
			String surname=request.getParameter("surname");
			String password=request.getParameter("password");
			String description=request.getParameter("description");
			String role=request.getParameter("role");
			boolean blocked=Boolean.parseBoolean(request.getParameter("blocked"));
			String userName=request.getParameter("userName");
			Role r;
			if(role.equals("user")) {
				r=Role.USER;
			}
			else {
				r=Role.ADMIN;
			}
			User u =UserDAO.get(userName);
			u.setName(name);
			u.setSurname(surname);
			u.setPassword(password);
			u.setChanneDescription(description);
			u.setBlocked(blocked);
			u.setRole(r);
			UserDAO.updateUser(u);
			Map<String, Object> data = new HashMap<>();
			
			data.put("status", "success");
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(data);
			System.out.println(jsonData);

			response.setContentType("application/json");
			response.getWriter().write(jsonData);
		}else if(status.equals("delete")) {
			String userName=request.getParameter("userName");
			User u =UserDAO.get(userName);
			u.setDeleted(true);
			UserDAO.updateUser(u);
			Map<String, Object> data = new HashMap<>();
			
			data.put("status", "success");
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(data);
			System.out.println(jsonData);

			response.setContentType("application/json");
			response.getWriter().write(jsonData);
		}
	}

}
