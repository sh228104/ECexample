<%@page import="kagoyume.JumsHelper"
        import="kagoyume.UserData"%>
<%-- 
    Document   : registration
    Created on : 2018/09/29, 22:57:23
    Author     : shohei
--%>

<%
    //registrationconfirmから戻った場合、値を保持して記入済みにする
    JumsHelper jh = JumsHelper.getInstance();
    HttpSession hs = request.getSession();
    UserData registData = null;
    boolean reinput = false;
    if(request.getParameter("mode") != null && request.getParameter("mode").equals("REINPUT")){
        reinput = true;
        registData = (UserData) hs.getAttribute("registData");
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        
        <title>登録フォーム</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">

            <ul class="navbar-nav">
                <li class="nav-item"><a class="nav-link" href="Login?returnAdd=Top">ログイン</a></li>
            </ul>
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
        
        <h1>登録フォーム</h1><br>
        
        <form action="Registrationconfirm" method="POST">
            <div class="form-group">
                <label>ユーザー名</label>
                <input type="text" name="userID" class="form-control" value="<% if(reinput) {out.print(registData.getName());} %>"><br>
            </div>
            <div class="form-group">
                <label>パスワード</label>
                <input type="password" name="password" class="form-control" value="<% if(reinput) {out.print(registData.getPassword());} %>"><br>
            </div>
            <div class="form-group">
                <label>メールアドレス</label>
                <input type="txt" name="mail" class="form-control" value="<% if(reinput) {out.print(registData.getMail());} %>"><br>
            </div>
            <div class="form-group">
                <label>住所</label>
                <input type="txt" name="address" class="form-control" value="<% if(reinput) {out.print(registData.getAddress());} %>"><br>
            </div>
            <button type="reset" name="resetBtn" class="btn btn-default">リセット</button>
            <button type="submit" name="submitBtn" class="btn btn-primary">確認</button><br><br>
        </form>
        <%= JumsHelper.getInstance().home() %>
            
        <!---->          
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    </body>
</html>
