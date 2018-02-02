package projekatWeb.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.User;
import model.Video;
import projekatWeb.dao.UserDAO;
import projekatWeb.dao.VideoDAO;;

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("userName");
		User user = UserDAO.get(username);
		ArrayList<Video> videos=VideoDAO.userVideo(user.getUserName()); 
		ArrayList<User> subs=UserDAO.findSubscribed(username);
		Map<String, Object> data = new HashMap<>();
		data.put("user", user);
		data.put("subNumber", user.getSubscribers().size());
		data.put("videos", videos);
		data.put("subs", subs);
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(data);
		response.setContentType("application/json");
		response.getWriter().write(jsonData);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
