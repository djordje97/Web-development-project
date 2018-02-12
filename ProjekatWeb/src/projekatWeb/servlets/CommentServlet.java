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

import model.Comment;
import model.User;
import model.Video;
import projekatWeb.dao.CommentDAO;
import projekatWeb.dao.UserDAO;
import projekatWeb.dao.VideoDAO;

public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String status = request.getParameter("status");
		if (status.equals("add")) {
			String owner = request.getParameter("owner");
			String content = request.getParameter("content");
			int video = Integer.parseInt(request.getParameter("video"));
			User u = UserDAO.get(owner);
			Video v = VideoDAO.getVideo(video);
			Date d = new Date();
			String date = VideoDAO.dateToStringForWrite(d);
			int id = CommentDAO.getCommentId();
			Comment c = new Comment(id, content, date, u, v, 0, 0, false);
			CommentDAO.addComment(c);

			Map<String, Object> data = new HashMap<>();
			data.put("status", "success");
			data.put("owner", owner);
			data.put("date", date);
			data.put("content", content);
			data.put("id", c.getId());
			data.put("likeNumber", 0);
			data.put("dislikeNumber", 0);
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(data);
			System.out.println(jsonData);
			response.setContentType("application/json");
			response.getWriter().write(jsonData);
		}else if(status.equals("delete")) {
			String stat="success";
			try {
				
				int id =Integer.parseInt(request.getParameter("id"));
				Comment comment=CommentDAO.getComment(id);
				comment.setDeleted(true);
				CommentDAO.updateComment(comment);
				
			} catch (Exception e) {stat="faliuer";}
			Map<String, Object> data = new HashMap<>();
			data.put("stat", stat);
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(data);
			System.out.println(jsonData);
			response.setContentType("application/json");
			response.getWriter().write(jsonData);
		}else if(status.equals("edit")) {
			String stat="success";
			String date="";
			try {
				
				int id =Integer.parseInt(request.getParameter("id"));
				String newContent=request.getParameter("content");
				Comment comment=CommentDAO.getComment(id);
				if(newContent!= null)
				{
					comment.setContent(newContent);
					Date d=new Date();
					String newDate=VideoDAO.dateToString(d);
					date=VideoDAO.dateToString(d);
					comment.setDate(newDate);
					CommentDAO.updateComment(comment);
				}
					
				
			} catch (Exception e) {stat="faliuer";}
			Map<String, Object> data = new HashMap<>();
			data.put("stat", stat);
			data.put("newDate", date);
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(data);
			System.out.println(jsonData);
			response.setContentType("application/json");
			response.getWriter().write(jsonData);
		}
	}

}
