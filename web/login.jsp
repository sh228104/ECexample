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
        <title>ログインページ</title>
    </head>
    <body>
        <h1>ログインページ</h1><br>
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
    </body>
</html>
