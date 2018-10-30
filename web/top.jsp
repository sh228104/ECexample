<%-- 
    Document   : top
    Created on : 2018/09/29, 17:12:46
    Author     : shohei
--%>

<%@page import="java.util.Objects"
        import="kagoyume.UserDataDTO"%>
<% 
    HttpSession hs = request.getSession();
    //カートの中身に関する物以外のセッションが残っている場合は破棄する
    if (!Objects.equals(hs.getAttribute("nameList"), null)) {
        hs.removeAttribute("jums");
        hs.removeAttribute("itemData");
        hs.removeAttribute("id");
    }
    UserDataDTO loginUser = (UserDataDTO) hs.getAttribute("loginUser");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        
        <title>トップページ</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">

            <% if (!Objects.equals(loginUser, null)) { %>
                <a class="navbar-brand" href="Mydata">ようこそ<%= loginUser.getName() %>さん！</a>
                <ul class="navbar-nav">
                    <li class="nav-item"><a class="nav-link" href="Login?logoutBtn=1">ログアウト</a></li>
                </ul>
            <% } else { %>
                <ul class="navbar-nav">
                    <li class="nav-item"><a class="nav-link" href="Login?returnAdd=Top">ログイン</a></li>
                </ul>
            <% } %>
            <ul class="navbar-nav">
                <li class="nav-item"><a class="nav-link" href="Cart">買い物かご</a></li>
            </ul>
            <form class="form-inline my-2 my-lg-0 ml-auto" action="Search" method="GET">
                <input class="form-control mr-sm-2" type="Search" placeholder="Search" name="keyword">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form>

        </nav>
        
        <!---->          
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    </body>
</html>
