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
import projekatWeb.dao.VideoDAO;;

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		String username=request.getParameter("userName");
		User owner = UserDAO.get(username);
		String status="visiter";
		String isSubscribed="unsuscribed";
		if(loggedInUser != null) {
			status="logedUser";
			int	isSub=UserDAO.findSubscribed(owner.getUserName(),loggedInUser.getUserName());
			System.out.println(loggedInUser.getUserName());

				if(isSub > 0) {
					isSubscribed="subscribe";
				}
		}
		
		
		ArrayList<Video> videos=VideoDAO.userVideo(owner.getUserName()); 
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
		doGet(request, response);
	}

}
