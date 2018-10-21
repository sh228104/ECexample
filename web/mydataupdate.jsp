<%-- 
    Document   : mydataupdate
    Created on : 2018/10/09, 16:12:31
    Author     : shohei
--%>
<%@page import="kagoyume.UserDataDTO"
        import="kagoyume.JumsHelper"%>
<%
    HttpSession hs = request.getSession();
    UserDataDTO loginUser = (UserDataDTO) hs.getAttribute("loginUser");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>s
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>会員情報ー更新画面</title>
    </head>
    <body>
        <h1>会員情報ー更新画面</h1><br>
        <form action="Mydataupdateresult" method="POST">
            ユーザー名:
            <input type="text" name="userID" value="<%= loginUser.getName() %>"><br><br>
            パスワード:
            <input type="password" name="password" value="<%= loginUser.getPassword() %>"><br><br>
            メールアドレス:
            <input type="txt" name="mail" value="<%= loginUser.getMail() %>"><br><br>
            住所:
            <input type="txt" name="address" value="<%= loginUser.getAddress() %>"><br><br>
            <input type="submit" name="submitBtn" value="更新"><br><br>
        </form>
        <form action="Mydataupdate" method="POST">
            <input type="submit" name="reset" value="リセット">
        </form><br>
        <%= JumsHelper.getInstance().home() %>
    </body>
</html>
