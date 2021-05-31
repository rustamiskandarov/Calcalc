<%--
  Created by IntelliJ IDEA.
  User: Rust
  Date: 31.05.2021
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Post</title>
    <p>${post.getAuthor()}</p>
    <p>${post.getTitle()}</p>
    <p>${post["content"]}</p>
    <p>${cookie.JSESSIONID}</p>
</head>
<body>

</body>
</html>
