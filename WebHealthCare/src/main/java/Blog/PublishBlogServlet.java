package Blog;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import DAO.DAOFactory;
import User.User;

import java.sql.Date;
/**
 * Servlet implementation class PublishBlogServlet
 */
@WebServlet("/publish_blog")
@MultipartConfig
public class PublishBlogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublishBlogServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
DAOFactory daoFactory = DAOFactory.getInstance();
        
       
        User user = (User) request.getSession().getAttribute("user");
       
        int userId = user.getid(); 
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        Part filePart = request.getPart("image");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        try (InputStream fileContent = filePart.getInputStream()) {
           
            Path imagePath = Paths.get("C:\\Users\\pc\\git\\repository9\\WebHealthCare\\src\\main\\webapp\\pics", fileName);
Files.copy(fileContent, imagePath, StandardCopyOption.REPLACE_EXISTING);

          
            String imagePathInDatabase = "pics/" + fileName;

            Blog blog = new Blog();
            blog.setUserID(userId);
            blog.setTitle(title);
            blog.setDescription(description);
            blog.setImage(imagePathInDatabase);

            BlogDAO blogdao = daoFactory.getBlogDAO();

            blogdao.createBlog(blog);
        }

        response.sendRedirect( "UserBlogsServlet");
    }
	}


