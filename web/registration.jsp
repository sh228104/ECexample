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
        <title>登録フォーム</title>
    </head>
    <body>
        <h1>登録フォーム</h1><br>
        <form action="Registrationconfirm" method="POST">
            ユーザー名:
            <input type="text" name="userID" value="<% if(reinput) {out.print(registData.getName());} %>"><br><br>
            パスワード:
            <input type="password" name="password" value="<% if(reinput) {out.print(registData.getPassword());} %>"><br><br>
            メールアドレス:
            <input type="txt" name="mail" value="<% if(reinput) {out.print(registData.getMail());} %>"><br><br>
            住所:
            <input type="txt" name="address" value="<% if(reinput) {out.print(registData.getAddress());} %>"><br><br>
            <input type="reset" name="resetBtn">
            <input type="submit" name="submitBtn" value="確認"><br><br>
        </form>
            <%= JumsHelper.getInstance().home() %>
    </body>
</html>
