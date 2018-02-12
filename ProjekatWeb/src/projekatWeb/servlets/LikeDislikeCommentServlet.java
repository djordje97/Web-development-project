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

import model.Comment;
import model.LikeDislike;
import model.User;
import projekatWeb.dao.CommentDAO;
import projekatWeb.dao.LikeDislikeDAO;
import projekatWeb.dao.VideoDAO;


public class LikeDislikeCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int id=Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		Comment comment = CommentDAO.getComment(id);
		if(loggedInUser!= null) {
			LikeDislike li=LikeDislikeDAO.getCommentLikeByOwner(comment.getId(), loggedInUser.getUserName());
			if(li == null) {
				Date d=new Date();
				int likeId =LikeDislikeDAO.getLikeId();
				LikeDislike l=new LikeDislike(likeId, true, VideoDAO.dateToStringForWrite(d), null, comment, loggedInUser);
				LikeDislikeDAO.addLikeDislike(l);
				LikeDislikeDAO.addCommentLikeDislike(l.getId(),comment.getId());
			}
			else if(li!= null && li.isLiked() == false) {
				li.setLiked(true);
				LikeDislikeDAO.updateLike(li);
			}
		}
		int likeNumber =LikeDislikeDAO.getCommentLikeNumber(comment.getId());
		int dislikeNumber =LikeDislikeDAO.getCommentDislikeNumber(comment.getId());
		comment.setLikeNumber(likeNumber);
		comment.setDislikeNumber(dislikeNumber);
		CommentDAO.updateComment(comment);
		Map<String, Object> data = new HashMap<>();
		data.put("likeNumber", likeNumber);
		data.put("dislikeNumber", dislikeNumber);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(data);
		System.out.println(jsonData);

		response.setContentType("application/json");
		response.getWriter().write(jsonData);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		int id=Integer.parseInt(request.getParameter("id"));
	
		HttpSession session = request.getSession();
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		Comment comment = CommentDAO.getComment(id);
		if(loggedInUser!= null) {
			LikeDislike li=LikeDislikeDAO.getCommentLikeByOwner(comment.getId(), loggedInUser.getUserName());
			if(li == null) {
				Date d=new Date();
				int likeId =LikeDislikeDAO.getLikeId();
				LikeDislike l=new LikeDislike(likeId, false, VideoDAO.dateToStringForWrite(d), null, comment, loggedInUser);
				LikeDislikeDAO.addLikeDislike(l);
				LikeDislikeDAO.addCommentLikeDislike(l.getId(),comment.getId());
			}
			else if(li!= null && li.isLiked() == true) {
				li.setLiked(false);
				LikeDislikeDAO.updateLike(li);
			}
		}
		int likeNumber =LikeDislikeDAO.getCommentLikeNumber(comment.getId());
		int dislikeNumber =LikeDislikeDAO.getCommentDislikeNumber(comment.getId());
		comment.setLikeNumber(likeNumber);
		comment.setDislikeNumber(dislikeNumber);
		CommentDAO.updateComment(comment);
		Map<String, Object> data = new HashMap<>();
		data.put("likeNumber", likeNumber);
		data.put("dislikeNumber", dislikeNumber);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(data);
		System.out.println(jsonData);

		response.setContentType("application/json");
		response.getWriter().write(jsonData);
	}catch (Exception e) {}
	}

}
