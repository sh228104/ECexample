<%-- 
    Document   : search
    Created on : 2018/09/29, 17:13:03
    Author     : shohei
--%>
<%@ page import="kagoyume.JsonProcessing"
         import="kagoyume.JumsHelper"
         import="kagoyume.UserDataDTO"
         import="java.util.Objects"
         import="java.util.ArrayList"
         %>
<%
    HttpSession hs = request.getSession();
    JumsHelper jh = (JumsHelper) hs.getAttribute("jums");
    UserDataDTO loginUser = (UserDataDTO) hs.getAttribute("loginUser");
    //セッションスコープに保存された商品情報を取得する
    ArrayList<String> nameList = (ArrayList<String>) hs.getAttribute("nameList");
    ArrayList<String> imageList = (ArrayList<String>) hs.getAttribute("imageList");
    ArrayList<String> priceList = (ArrayList<String>) hs.getAttribute("priceList");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>検索結果</title>
    </head>
    <body>
        <h1>検索結果</h1>
        <% if (!Objects.equals(loginUser, null)) { %>
            ようこそ<%= loginUser.getName() %>さん！<br>
            <form action="Login" method="GET">
                <input type="submit" name="logoutBtn" value="ログアウト">
            </form>
        <% } else { %>
            <form actipn="Login" method="POST">
                <input type="submit" name="btnSubmit" value="ログイン">
                <input type="hidden" name="returnAdd" value="search.jsp">
            </form>
        <% } %>
        <form action="Cart" method="POST">
            <input type="submit" name="cartBtn" value="買い物かご"><br>
        </form>
        キーワード：<%= jh.getSearchKeyword() %> <%= nameList.size() %>件の検索結果<br>
        <% for (int i=0; i < nameList.size(); i++) { %>
            <img src="<%= imageList.get(i) %>">
            <a href=Item?id=<%= i %>><%= nameList.get(i) %></a><br>
            <p> 価格：<%= Integer.parseInt(priceList.get(i)) %>円（税込）</p><br>
        <% } %>
        <%= jh.home() %>
    </body>
</html>
