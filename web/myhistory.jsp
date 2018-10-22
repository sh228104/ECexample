<%-- 
    Document   : myhistory
    Created on : 2018/10/21, 13:40:09
    Author     : shohei
--%>
<%@ page import="kagoyume.UserDataDTO"
         import="kagoyume.ItemData"
         import="kagoyume.JumsHelper"
         import="java.util.ArrayList"
         import="java.util.Objects"
         %>
<%
    HttpSession hs = request.getSession();
    
    //ログインしている場合、ユーザーのデータを読み込む
    UserDataDTO loginUser = (UserDataDTO) hs.getAttribute("loginUser");
    
    ArrayList<ItemData> itemData = (ArrayList<ItemData>) request.getAttribute("itemData");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>会員情報ー購入履歴</title>
    </head>
    <body>
        <h1>会員情報ー購入履歴</h1><br>
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
        <% if (Objects.equals(itemData, null) || itemData.size() == 0) { %>
            購入履歴はありません<br>
        <% } else { %>
            <% for (int i=0; i < itemData.size(); i++) { %>
                <img src="<%= itemData.get(i).getImage() %>"<br>
                <p><%= itemData.get(i).getName() %></p><br>
                <p><%= itemData.get(i).getPrice() %>円(税込)</p><br>
            <% } %>
        <% } %>
        <%= JumsHelper.getInstance().home() %>
    </body>
</html>
