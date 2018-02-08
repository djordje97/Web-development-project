package projekatWeb.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.Comment;
import model.User;
import model.Video;
import model.Video.Visibility;
import projekatWeb.dao.CommentDAO;
import projekatWeb.dao.LikeDislikeDAO;
import projekatWeb.dao.UserDAO;
import projekatWeb.dao.VideoDAO;


public class VideoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		Video video = VideoDAO.getVideo(id);
		String status="visiter";
		String isSubscribed="unsuscribed";
		if(loggedInUser != null) {
			status="logedUser";
			int	isSub=UserDAO.findSubscribed(video.getOwner().getUserName(),loggedInUser.getUserName());

				if(isSub > 0) {
					isSubscribed="subscribe";
				}
		}
		 int videoLikeNumber=LikeDislikeDAO.getVideosLikeNumber(video.getId());
		video.setNumberOfviews(video.getNumberOfviews()+1);
		VideoDAO.updateVideo(video);
		ArrayList<Comment> comments=CommentDAO.getComments(video.getId());
		Map<String, Object> data = new HashMap<>();
		data.put("video", video);
		data.put("status", status);
		data.put("user", loggedInUser);
		data.put("isSubscribed",isSubscribed);
		data.put("comments",comments);
		data.put("videoLikeNumber",videoLikeNumber);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(data);
		System.out.println(jsonData);

		response.setContentType("application/json");
		response.getWriter().write(jsonData);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String status=request.getParameter("status");
		HttpSession session = request.getSession();
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		if(status.equals("add")) {
			String url=request.getParameter("url");
			String name=request.getParameter("name");
			String visibil=request.getParameter("visibility");
			boolean allowComments=Boolean.parseBoolean(request.getParameter("allowComments"));
			boolean allowRating=Boolean.parseBoolean(request.getParameter("allowRating"));
			String description=request.getParameter("description");
			Visibility visibility;
			if(visibil.equals("public")) {
				visibility=Visibility.PUBLIC;
			}
			else if(visibil.equals("private")) {
				visibility=Visibility.PRIVATE;
			}
			else {
				visibility=Visibility.UNLISTED;
			}
			Date d=new Date();
			
			int id=VideoDAO.getVideoId();
			Video v=new Video(id, url, "photos/slika.jpg", name, description, visibility, allowComments, 0, 0, false, allowRating, 0, VideoDAO.dateToString(d), loggedInUser, false);
			VideoDAO.addVideo(v);
			Map<String, Object> data = new HashMap<>();
			
			data.put("status", "success");
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(data);
			System.out.println(jsonData);

			response.setContentType("application/json");
			response.getWriter().write(jsonData);
		}
		else if(status.equals("edit")) {
			int id=Integer.parseInt(request.getParameter("videoId"));
			Video video = VideoDAO.getVideo(id);
			String visibil=request.getParameter("visibility");
			boolean allowComments=Boolean.parseBoolean(request.getParameter("allowComments"));
			boolean allowRating=Boolean.parseBoolean(request.getParameter("allowRating"));
			String description=request.getParameter("description");
			Visibility visibility;
			if(visibil.equals("public")) {
				visibility=Visibility.PUBLIC;
			}
			else if(visibil.equals("private")) {
				visibility=Visibility.PRIVATE;
			}
			else {
				visibility=Visibility.UNLISTED;
			}
			video.setVisibility(visibility);
			video.setAllowComments(allowComments);
			video.setAllowRating(allowRating);
			video.setDescription(description);
			VideoDAO.updateVideo(video);
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
