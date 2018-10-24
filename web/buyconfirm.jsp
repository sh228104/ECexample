<%-- 
    Document   : buyconfirm
    Created on : 2018/10/09, 10:22:20
    Author     : shohei
--%>

<%@page import="kagoyume.UserDataDTO"
        import="kagoyume.ItemData"
        import="kagoyume.JumsHelper"
        import="java.util.ArrayList"
        import="java.util.Objects"
        
        %>

<%
    int loginFlg = 0;
    
    HttpSession hs = request.getSession();
    JumsHelper jh = new JumsHelper();
    
    //ログインしている場合、ユーザーのデータを読み込む
    UserDataDTO loginUser = (UserDataDTO) hs.getAttribute("loginUser");
    if (!Objects.equals(loginUser, null)) {
        loginFlg = 1;
    }
    //cartdataの読み込み
    ArrayList<ItemData> cartData = (ArrayList<ItemData>) hs.getAttribute("userCartData");
    
    //カートの合計価格を計算する
    int sumPrice = 0;
    if (!Objects.equals(cartData, null)) {
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
        <title>購入ー確認画面</title>
    </head>
    <body>
        <h1>購入ー確認画面</h1><br>
        <% if (!Objects.equals(loginUser, null)) { %>
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
        <% for (int i=0; i < cartData.size(); i++) { %>
            <img src="<%= cartData.get(i).getImage() %>"<br>
            <a href=Item?id=<%= i %>><%= cartData.get(i).getName() %></a><br>
            <p><%= cartData.get(i).getPrice() %>円(税込)</p><br><br>
        <% } %>
        <h3>カートの合計：<%= sumPrice %>円(税込)</h3><br>
        <form action="Buycomplete" method="POST">
            <% for (int i=1; i<=3; i++) { %>
                <input type="radio" name="type" value="<%= i %>"><%= jh.exTypenum(i) %><br>
            <% } %>
            <input type="submit" name="buy" value="上記の内容で購入する">
        </form>
        <form action="Cart" method="POST">
            <input type="submit" name="cart" value="カートに戻る">
        </form>
    </body>
</html>
