<%@page import="kagoyume.JumsHelper"
        import="kagoyume.UserDataDTO"%>
<%-- 
    Document   : registrationcomplete
    Created on : 2018/10/03, 14:54:32
    Author     : shohei
--%>

<%
    UserDataDTO result = (UserDataDTO) request.getAttribute("result");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>登録完了</title>
    </head>
    <body>
        <h1>会員登録－完了画面</h1><br>
        ユーザー名:<%= result.getName() %><br>
        パスワード:<%= result.getPassword() %><br>
        メールアドレス:<%= result.getMail() %><br>
        住所:<%= result.getAddress() %><br><br>
        以上の内容で登録しました。<br>
        <%= JumsHelper.getInstance().home() %><br>
    </body>
</html>
