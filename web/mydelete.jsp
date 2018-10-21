<%-- 
    Document   : mydelete
    Created on : 2018/10/09, 15:39:16
    Author     : shohei
--%>
<%@page import="kagoyume.UserDataDTO"
        %>
<%
    HttpSession hs = request.getSession();
    UserDataDTO loginUser = (UserDataDTO) hs.getAttribute("loginUser");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>会員情報ー削除画面</title>
    </head>
    <body>
        <h1>会員情報ー削除画面</h1><br>
        ユーザー名:<%= loginUser.getName() %><br>
        パスワード:<%= loginUser.getPassword() %><br>
        メールアドレス:<%= loginUser.getMail() %><br>
        住所:<%= loginUser.getAddress() %><br><br>
        このユーザーを削除いたします。本当によろしいですか？<br>
        <form action="Mydeleteresult" method="POST">
            <input type="submit" name="yes" value="はい">
        </form>
        <form action="Top" method="POST">
            <input type="submit" name="no" value="いいえ">
        </form>
    </body>
</html>
