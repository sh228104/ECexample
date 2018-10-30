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
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        
        <title>カート</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">

            <% if (loginFlg == 1) { %>
                <a class="navbar-brand" href="Mydata">ようこそ<%= loginUser.getName() %>さん！</a>
                <ul class="navbar-nav">
                    <li class="nav-item"><a class="nav-link" href="Login?logoutBtn=1">ログアウト</a></li>
                </ul>
            <% } else { %>
                <ul class="navbar-nav">
                    <li class="nav-item"><a class="nav-link" href="Login?returnAdd=Cart">ログイン</a></li>
                </ul>
            <% } %>
            <ul class="navbar-nav">
                <li class="nav-item"><a class="nav-link" href="Top">トップ</a></li>
            </ul>
            <form class="form-inline my-2 my-lg-0 ml-auto" action="Search" method="GET">
                <input class="form-control mr-sm-2" type="Search" placeholder="Search" name="keyword">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form>

        </nav>
        
        <h1>カート</h1><br>
        
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
        
        <!---->          
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    </body>
</html>
