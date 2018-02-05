package projekatWeb.servlets;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.LikeDislike;
import model.User;
import model.Video;
import projekatWeb.dao.LikeDislikeDAO;
import projekatWeb.dao.VideoDAO;


public class LikeDislikeVideoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		Video video = VideoDAO.getVideo(id);
		String status="liked";
		if(loggedInUser!= null) {
			LikeDislike li=LikeDislikeDAO.getVideosLikeByOwner(video.getId(), loggedInUser.getUserName());
			if(li == null) {
				status="notLiked";
				Date d=new Date();
				int likeId =LikeDislikeDAO.getLikeId();
				LikeDislike l=new LikeDislike(likeId, true, VideoDAO.dateToString(d), video, null, loggedInUser);
				LikeDislikeDAO.addLikeDislike(l);
				LikeDislikeDAO.addVideoLikeDislike(l.getId(),video.getId());
			}
			else if(li!= null && li.isLiked() == false) {
				li.setLiked(true);
				LikeDislikeDAO.updateLike(li);
			}
		}
		int likeNumber =LikeDislikeDAO.getVideosLikeNumber(video.getId());
		int dislikeNumber =LikeDislikeDAO.getVideosDislikeNumber(video.getId());
		video.setNumberOfDislikes(dislikeNumber);
		video.setNumberOfLikes(likeNumber);
		VideoDAO.updateVideo(video);
		Map<String, Object> data = new HashMap<>();
		data.put("status",status);
		data.put("likeNumber", likeNumber);
		data.put("dislikeNumber", dislikeNumber);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(data);
		System.out.println(jsonData);

		response.setContentType("application/json");
		response.getWriter().write(jsonData);
	}
		

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int id=Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		Video video = VideoDAO.getVideo(id);
		String status="disliked";
		if(loggedInUser!= null) {
			LikeDislike li=LikeDislikeDAO.getVideosLikeByOwner(video.getId(), loggedInUser.getUserName());
			if(li == null) {
				status="notDisliked";
				Date d=new Date();
				int likeId =LikeDislikeDAO.getLikeId();
				LikeDislike l=new LikeDislike(likeId, false, VideoDAO.dateToString(d), video, null, loggedInUser);
				LikeDislikeDAO.addLikeDislike(l);
				LikeDislikeDAO.addVideoLikeDislike(l.getId(),video.getId());
			}
			else if(li!= null && li.isLiked() == true) {
				li.setLiked(false);
				LikeDislikeDAO.updateLike(li);
			}
		}
		int dislikeNumber =LikeDislikeDAO.getVideosDislikeNumber(video.getId());
		int likeNumber =LikeDislikeDAO.getVideosLikeNumber(video.getId());
		video.setNumberOfLikes(likeNumber);
		video.setNumberOfDislikes(dislikeNumber);;
		VideoDAO.updateVideo(video);
		Map<String, Object> data = new HashMap<>();
		data.put("status",status);
		data.put("dislikeNumber", dislikeNumber);
		data.put("likeNumber", likeNumber);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(data);
		System.out.println(jsonData);

		response.setContentType("application/json");
		response.getWriter().write(jsonData);
		
	}

}
