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
    int loginFlg =0;
    HttpSession hs = request.getSession();
    //ログインしている場合、ユーザーのデータを読み込む
    UserDataDTO loginUser = (UserDataDTO) hs.getAttribute("loginUser");
    if (!Objects.equals(loginUser, null)) {
        loginFlg = 1;
    }
    //商品情報をセッションから取得する
    ItemData id = (ItemData) hs.getAttribute("itemData");
    
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        
        <title>商品詳細</title>
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
                    <li class="nav-item"><a class="nav-link" href="Login?returnAdd=Item">ログイン</a></li>
                </ul>
            <% } %>
            <ul class="navbar-nav">
                <li class="nav-item"><a class="nav-link" href="Top">トップ</a></li>
            </ul>
            <ul class="navbar-nav">
                <li class="nav-item"><a class="nav-link" href="Cart">買い物かご</a></li>
            </ul>
            <form class="form-inline my-2 my-lg-0 ml-auto" action="Search" method="GET">
                <input class="form-control mr-sm-2" type="Search" placeholder="Search" name="keyword">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form>

        </nav>
            
        <h1>商品詳細</h1><br>
        
        <% if (loginFlg == 1) { %>
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
        
        <!---->          
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    </body>
</html>
