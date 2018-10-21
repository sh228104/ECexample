<%-- 
    Document   : buycomplete
    Created on : 2018/10/09, 11:15:46
    Author     : shohei
--%>

<%@page import="kagoyume.JumsHelper"
        import="kagoyume.UserDataDTO"
        import="java.util.Objects"%>
<%
    HttpSession hs = request.getSession();
    
    UserDataDTO loginUser = (UserDataDTO) hs.getAttribute("loginUser");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>購入ー完了画面</title>
    </head>
    <body>
        <h1>購入ー完了画面</h1><br>
        <% if (!Objects.equals(loginUser, null)) { %>
            ようこそ<%= loginUser.getName() %>さん！<br>
            <form action="Login" method="GET">
                <input type="submit" name="logoutBtn" value="ログアウト">
            </form>
        <% } else { %>
            <form action="Login" method="POST">
                <input type="submit" name="loginBtn" value="ログイン">
                <input type="hidden" name="returnAdd" value="Top">
            </form>
        <% } %>
        購入が完了しました<br>
        <%= JumsHelper.getInstance().home() %>
    </body>
</html>
