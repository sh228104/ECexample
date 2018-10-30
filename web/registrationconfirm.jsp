<%@page import="kagoyume.JumsHelper"
        import="kagoyume.UserData"
        import="java.util.ArrayList"%>
<%-- 
    Document   : registrationconfirm
    Created on : 2018/10/03, 14:53:41
    Author     : shohei
--%>

<%
    HttpSession hs = request.getSession();
    UserData registData = (UserData) hs.getAttribute("registData");
    JumsHelper jh = JumsHelper.getInstance();
    //不足している項目をチェック
    ArrayList<String> chkList = registData.chkproperties();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        
        <title>会員登録－確認画面</title>
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
        
        <h1>会員登録-確認画面</h1><br>
        
        <% if (chkList.size() == 0) { %>
            <h1>会員登録－確認画面</h1><br>
            ユーザー名:<%= registData.getName() %><br>
            パスワード:<%= registData.getPassword() %><br>
            メールアドレス:<%= registData.getMail() %><br>
            住所:<%= registData.getAddress() %><br><br>
            上記の内容で登録いたします。よろしいですか？<br>
            <form action="Registrationcomplete" method="POST">
                <input type="submit" name="yes" value="はい">
            </form>
        <% } else { %>
            <h1>入力が不完全です</h1>
            <%= jh.chkinput(chkList) %>
        <% } %>
        <form action="Registration" method="POST">
            <input type="submit" name="no" value="戻る">
            <input type="hidden" name="mode" value="REINPUT">
        </form>
        <%= jh.home() %>
        
        <!---->          
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    </body>
</html>
