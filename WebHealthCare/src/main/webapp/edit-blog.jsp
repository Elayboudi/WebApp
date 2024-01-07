<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Blog</title>
</head>
<body>

    <h2>Edit Blog</h2>

    <p>Current Values:</p>
    <p><strong>Title:</strong> ${blog.title}</p>
    <p><strong>Description:</strong> ${blog.description}</p>
    <p><strong>Current Image:</strong></p>
    <img src="${blog.image}" alt="Current Image" width="100" height="100" />
    
    <hr/>

    <form action="UpdateBlogServlet" method="post" enctype="multipart/form-data">
        <input type="hidden" name="blogId" value="${blog.ID}" >
        
        <label for="title">New Title:</label>
        <input type="text" name="title" value="${blog.title}" required >
        <br/>

        <label for="description">New Description:</label>
        <textarea name="description" required>${blog.description}</textarea>
        <br/>

        <label for="image">New Image:</label>
        <input type="file" name="image" accept="image/*" >
        <br/>

        <img src="${blog.image}" alt="Current Image" width="100" height="100" >
        <br/>

        <input type="submit" value="Save Changes" >
    </form>

    <br/>
    <a href="UserBlogsServlet">Back to My Blogs</a>

</body>
</html>
