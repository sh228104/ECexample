<%-- 
    Document   : cart
    Created on : 2018/10/03, 14:58:13
    Author     : shohei
--%>
<%@ page import="kagoyume.ItemData"
         import="kagoyume.JumsHelper"
         import="kagoyume.UserDataDTO"
         import="java.util.ArrayList"
         import="java.util.Objects"
         %>
<%
    int loginFlg = 0;
    
    HttpSession hs = request.getSession();
    
    //ログインしている場合、ユーザーのデータを読み込む
    UserDataDTO loginUser = (UserDataDTO) hs.getAttribute("loginUser");
    if (!Objects.equals(loginUser, null)) {
        loginFlg = 1;
    }
    
    //cartdataの読み込み
    ArrayList<ItemData> cartData = new ArrayList<ItemData>();
    
    if (loginFlg == 1) {
        cartData = (ArrayList<ItemData>) hs.getAttribute("userCartData");
    } else {
        cartData = (ArrayList<ItemData>) hs.getAttribute("cartData");
    }
    
    //カートの合計価格を計算する
    int sumPrice = 0;
    if (!cartData.isEmpty()) {
        for (int i=0; i < cartData.size(); i++) {
            sumPrice += cartData.get(i).getPrice();
        }
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>カート</title>
    </head>
    <body>
        <h1>カート</h1><br>
        <% if (loginFlg == 1) { %>
            ようこそ<%= loginUser.getName() %>さん！<br>
            <form action="Login" method="GET">
                <input type="submit" name="logoutBtn" value="ログアウト">
            </form>
        <% } else { %>
            <form action="Login" method="POST">
                <input type="submit" name="loginBtn" value="ログイン">
                <input type="hidden" name="returnAdd" value="Cart">
            </form>
        <% } %>
        <% if (Objects.equals(cartData, null) || cartData.size() == 0) { %>
            カートは空です<br>
        <% } else { %>
            <% for (int i=0; i < cartData.size(); i++) { %>
                <img src="<%= cartData.get(i).getImage() %>"<br>
                <a href=Item?id=<%= i %>&cartFlg=1><%= cartData.get(i).getName() %></a><br>
                <p><%= cartData.get(i).getPrice() %>円(税込)</p><br>
                <form action="Cart" method="GET">
                    <input type="submit" name="deleteBtn" value="削除">
                    <input type="hidden" name="deleteID" value="<% if (loginFlg == 1) {out.print(cartData.get(i).getCartID()); } else {out.print(i);} %>">
                </form>
            <% } %>
            <h3>カートの合計：<%= sumPrice %>円(税込)</h3><br>
            <% if (loginFlg == 1 && !Objects.equals(cartData, null) && cartData.size() != 0) { %>
                <form action="Buyconfirm" method="POST">
                    <input type="submit" name="buyBtn" value="購入する"> 
                </form>
            <% } %>
        <% } %>
        <%= JumsHelper.getInstance().home() %>
    </body>
</html>
