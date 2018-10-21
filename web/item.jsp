<%-- 
    Document   : item
    Created on : 2018/09/29, 22:56:50
    Author     : shohei
--%>
<%@ page import="kagoyume.ItemData"
         import="kagoyume.JumsHelper"
         import="kagoyume.UserDataDTO"
         import="java.util.Objects"
         %>
<%
    HttpSession hs = request.getSession();
    ItemData id = (ItemData) hs.getAttribute("itemData");
    //ログインしている場合、ユーザーのデータを読み込む
    UserDataDTO loginUser = (UserDataDTO) hs.getAttribute("loginUser");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>商品詳細</title>
    </head>
    <body>
        <h1>商品詳細</h1><br>
        <% if (!Objects.equals(loginUser, null)) { %>
            ようこそ<%= loginUser.getName() %>さん！<br>
            <form action="Login" method="GET">
                <input type="submit" name="logoutBtn" value="ログアウト">
            </form>
        <% } else { %>
            <form actipn="Login" method="POST">
                <input type="submit" name="btnSubmit" value="ログイン">
                <input type="hidden" name="returnAdd" value="item.jsp">
            </form>
        <% } %>
        <form action="Cart" method="POST">
            <input type="submit" name="cartBtn" value="買い物かご"><br>
        </form>
        <img src=<%= id.getImage() %>><br>
        商品名:<%= id.getName() %><br>
        概要:<%= id.getDescription() %><br>
        価格:<%= id.getPrice() %>円(税込)<br>
        評価値：<%= id.getRate() %><br>
        <form action="Add" method="POST">
            <input type="submit" name="submitBtn" value="カートに追加する"><br>
        </form>
        <%= JumsHelper.getInstance().home() %>
    </body>
</html>
