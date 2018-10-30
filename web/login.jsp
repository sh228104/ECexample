<%-- 
    Document   : login
    Created on : 2018/09/29, 22:57:10
    Author     : shohei
--%>
<%@ page import="kagoyume.UserDataDTO"
         import="kagoyume.JumsHelper"
         import="java.util.Objects"
         %>
<% 
    HttpSession hs = request.getSession();
    JumsHelper jh = (JumsHelper) hs.getAttribute("jums");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <title>ログイン</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">

            <ul class="navbar-nav">
                <li class="nav-item"><a class="nav-link" href="Top">トップ</a></li>
            </ul>
            <ul class="navbar-nav">
                <li class="nav-item"><a class="nav-link" href="Cart">買い物かご</a></li>
            </ul>
            <form class="form-inline my-2 my-lg-0 ml-auto" action="Search" method="GET">
                <input class="form-control mr-sm-2" type="Search" placeholder="Search" name="keyword">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form>

        </nav>
        
        <h1>ログイン</h1><br>
        
        <% if (jh.getWrongFlg() == 1) { %>
            ユーザー名またはパスワードが違います
        <% } %>
        <form action="LoginProcessing" method="POST">
            ユーザー名：
            <input type="text" name="username" maxlength="20"><br><br>
            パスワード：
            <input type="password" name="password" maxlength="20"><br><br>
            <input type="reset" name="resetBtn">
            <input type="submit" name="submitBtn" value="ログイン"><br>
        </form>
        <form action="Registration" method="POST">
            <input type="submit" name="registrationBtn" value="新規会員登録"><br>
        </form>
        <%= jh.home() %>
        
        <!---->          
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    </body>
</html>
