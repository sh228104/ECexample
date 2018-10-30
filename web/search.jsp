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
    int loginFlg = 0;
    HttpSession hs = request.getSession();
    JumsHelper jh = (JumsHelper) hs.getAttribute("jums");
    
    UserDataDTO loginUser = (UserDataDTO) hs.getAttribute("loginUser");
    if (!Objects.equals(loginUser, null)) {
        loginFlg = 1;
    }
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
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        
        <title>検索結果</title>
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
                    <li class="nav-item"><a class="nav-link" href="Login?returnAdd=Top">ログイン</a></li>
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
            
        <h1>検索結果</h1>
        
        キーワード：<%= jh.getSearchKeyword() %> <%= nameList.size() %>件の検索結果<br>
        <% for (int i=0; i < nameList.size(); i++) { %>
            <img src="<%= imageList.get(i) %>">
            <a href=Item?id=<%= i %>><%= nameList.get(i) %></a><br>
            <p> 価格：<%= Integer.parseInt(priceList.get(i)) %>円（税込）</p><br>
        <% } %>
        <%= jh.home() %>
        
        <!---->          
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    </body>
</html>
