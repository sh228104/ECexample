<%-- 
    Document   : mydataupdateresult
    Created on : 2018/10/09, 16:48:49
    Author     : shohei
--%>
<%@page import="kagoyume.JumsHelper"
        import="kagoyume.UserDataDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession hs = request.getSession();
    UserDataDTO loginUser = (UserDataDTO) hs.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>会員情報ー更新完了画面</title>
    </head>
    <body>
        <h1>会員情報ー更新完了画面</h1><br>
        ユーザー名:<%= loginUser.getName() %><br>
        パスワード:<%= loginUser.getPassword() %><br>
        メールアドレス:<%= loginUser.getMail() %><br>
        住所:<%= loginUser.getAddress() %><br><br>
        以上の内容で登録しました。<br>
        <%= JumsHelper.getInstance().home() %><br>
    </body>
</html>
