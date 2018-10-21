<%-- 
    Document   : mydata
    Created on : 2018/10/03, 14:57:38
    Author     : shohei
--%>
<%@page import="kagoyume.JumsHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>会員情報ー閲覧画面</title>
    </head>
    <body>
        <h1>会員情報ー閲覧画面</h1><br>
        <form action="Myhistory" method="POST">
            <input type="submit" name="history" value="購入履歴">
        </form><br>
        <form action="Mydataupdate" method="POST">
            <input type="submit" name="update" value="登録情報の更新">
        </form><br>
        <form action="Mydelete" method="POST">
            <input type="submit" name="delete" value="登録情報の削除">
        </form>
            <%= JumsHelper.getInstance().home() %>
    </body>
</html>
