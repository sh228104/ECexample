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
        <title>トップページ</title>
        <style>
            
        </style>
    </head>
    <body>
        <h1>トップページ</h1>
        <% if (!Objects.equals(loginUser, null)) { %>
            ようこそ<a href="Mydata"><%= loginUser.getName() %></a>さん！<br>
            <form action="Login" method="GET">
                <input type="submit" name="logoutBtn" value="ログアウト">
            </form>
        <% } else { %>
            <form action="Login" method="POST">
                <input type="submit" name="loginBtn" value="ログイン">
                <input type="hidden" name="returnAdd" value="Top">
            </form>
        <% } %>
        <form action="Cart" method="POST">
            <input type="submit" name="cartBtn" value="買い物かご"><br>
        </form>
        <form action="Search" method="GET">
		<input type="text" name ="keyword">
		<input type="submit" name = "btnSubmit" value="検索"><br>
	</form>
    </body>
</html>
