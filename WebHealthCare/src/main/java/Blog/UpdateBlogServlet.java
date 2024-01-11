package Blog;

import jakarta.servlet.ServletException;
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

/**
 * Servlet implementation class UpdateBlogServlet
 */
public class UpdateBlogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateBlogServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int blogId = Integer.parseInt(request.getParameter("blogId"));

        DAOFactory daoFactory = DAOFactory.getInstance();
        BlogDAO blogDAO = daoFactory.getBlogDAO();
        Blog blog = blogDAO.getBlogById(blogId);

        request.setAttribute("blog", blog);
        request.getRequestDispatcher("edit-blog.jsp").forward(request, response);
    }

    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	String blogIdParameter = request.getParameter("blogId");
    	int blogId = (blogIdParameter != null && !blogIdParameter.isEmpty()) ? Integer.parseInt(blogIdParameter) : 0;

        String title = request.getParameter("title");
        String description = request.getParameter("description");

       
        DAOFactory daoFactory = DAOFactory.getInstance();
        BlogDAO blogDAO = daoFactory.getBlogDAO();
       

        String newImagePathInDatabase = null;  

        Part newImagePart = request.getPart("image");
        if (newImagePart != null && newImagePart.getSize() > 0) {
            
            String newFileName = Paths.get(newImagePart.getSubmittedFileName()).getFileName().toString();

            
            try (InputStream newImageContent = newImagePart.getInputStream()) {
                // Chemin de la nouvelle image
                Path newImagePath = Paths.get("C:\\Users\\pc\\git\\repository9\\WebHealthCare\\src\\main\\webapp\\pics", newFileName);

                // Copier le flux d'entrée vers le fichier de destination
                Files.copy(newImageContent, newImagePath, StandardCopyOption.REPLACE_EXISTING);

                //  le chemin de l'image dans ma base de données ou où je veux stocker les informations du blog
                newImagePathInDatabase = "pics/" + newFileName;
            }
        }
        System.out.println("Blog ID: " + blogId);
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("New Image Path: " + newImagePathInDatabase);
       
        Blog updatedBlog = new Blog();
        updatedBlog.setID(blogId);
        updatedBlog.setTitle(title);
        updatedBlog.setDescription(description);
        updatedBlog.setImage(newImagePathInDatabase);

      
        blogDAO.updateBlog(updatedBlog);

      
        response.sendRedirect("UserBlogsServlet");
    }


}
