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
import model.Video;
import projekatWeb.dao.UserDAO;
import projekatWeb.dao.VideoDAO;

public class GetVideosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ArrayList<Video> videos= null;
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		if(loggedInUser!= null) {
			if(loggedInUser.getRole().toString().equals("ADMIN")) {
				videos=VideoDAO.allVideo();
			}
			else {
				videos =VideoDAO.publicVideo();
			}
		}
		else {
			videos =VideoDAO.publicVideo();
		}
		
		Map<String, Object> data = new HashMap<>();
		ArrayList<User> users=UserDAO.getAll();
		data.put("users", users);
		data.put("videos", videos);
		data.put("user", loggedInUser);
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(data);
		System.out.println(jsonData);

		response.setContentType("application/json");
		response.getWriter().write(jsonData);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
