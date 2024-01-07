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
        
        // Récupération de l'utilisateur depuis la session
        User user = (User) request.getSession().getAttribute("user");
        
        // Récupération des paramètres du formulaire
        int userId = user.getid(); // Vous pouvez utiliser directement user.getId() au lieu de demander "userId" depuis le formulaire
        String title = request.getParameter("title");
        String description = request.getParameter("description");

        // Récupération du fichier image
        Part filePart = request.getPart("image");

        // Extraire le nom du fichier
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        // Enregistrer le fichier dans un emplacement sur le serveur
        try (InputStream fileContent = filePart.getInputStream()) {
            // Chemin où vous souhaitez enregistrer l'image (ajustez selon votre structure de projet)
            Path imagePath = Paths.get("C:\\Users\\pc\\git\\repository9\\WebHealthCare\\src\\main\\webapp\\pics", fileName);

            // Copier le flux d'entrée vers le fichier de destination
            Files.copy(fileContent, imagePath, StandardCopyOption.REPLACE_EXISTING);

            // Enregistrez le chemin de l'image dans votre base de données ou où vous stockez les informations du blog
            String imagePathInDatabase = "pics/" + fileName;


            // Créer un objet Blog
            Blog blog = new Blog();
            blog.setUserID(userId);
            blog.setTitle(title);
            blog.setDescription(description);
            blog.setImage(imagePathInDatabase);

            // Récupération du DAO pour les blogs
            BlogDAO blogdao = daoFactory.getBlogDAO();

            // Ajouter le blog à la base de données
            blogdao.createBlog(blog);
        }

        // Rediriger vers la page d'affichage des blogs
        response.sendRedirect( "UserBlogsServlet");
    }
	}


