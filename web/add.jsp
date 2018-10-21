<%-- 
    Document   : add
    Created on : 2018/09/29, 22:57:37
    Author     : shohei
--%>
<%@ page import="kagoyume.ItemData"
         import="kagoyume.JumsHelper"
         import="kagoyume.UserDataDTO"
         import="java.util.Objects"
         import="java.util.ArrayList"
         %>
<%
    HttpSession hs = request.getSession();
    ItemData id = (ItemData) hs.getAttribute("itemData");
    //カートの商品情報を読み込む
    ArrayList<ItemData> cartData = (ArrayList<ItemData>) hs.getAttribute("cartData");
    //新しくカートに追加する商品のArrayListにおけるインデックスの値
    int index = cartData.size() -1;
    //ログインしている場合、ユーザーの情報を読み込む
    UserDataDTO loginUser = (UserDataDTO) hs.getAttribute("loginUser");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>カートに追加</title>
    </head>
    <body>
        <h1>カートに追加しました</h1>
        <% if (!Objects.equals(loginUser, null)) { %>
            ようこそ<%= loginUser.getName() %>さん！<br>
            <form action="Login" method="GET">
                <input type="submit" name="logoutBtn" value="ログアウト">
            </form>
        <% } else { %>
            <form action="Login" method="POST">
                <input type="submit" name="loginBtn" value="ログイン">
                <input type="hidden" name="returnAdd" value="cart.jsp">
            </form>
        <% } %>
        <form action="Cart" method="POST">
            <input type="submit" name="cartBtn" value="買い物かご"><br>
        </form>
        <img src="<%= cartData.get(index).getImage() %>"><br>
        商品名:<%= cartData.get(index).getName() %><br><br>
        <%= JumsHelper.getInstance().home() %>
    </body>
</html>
