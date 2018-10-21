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
        <title>会員登録－確認画面</title>
    </head>
    <body>
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
    </body>
</html>
