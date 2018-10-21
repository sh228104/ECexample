<%-- 
    Document   : error
    Created on : 2018/09/29, 22:45:01
    Author     : shohei
--%>
<%@page import="java.util.Objects"
        import="kagoyume.JumsHelper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String error = (String)request.getAttribute("error");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>error</title>
    </head>
    <body>
        エラーが発生しました。以下の項目を確認してください。<br>
        <% 
            if (Objects.equals("IllegalMonitorStateException", error)) {
                out.print("データベースが正常に起動していない可能性があります");
            } else {
                out.print(error);
            }
        %><br><br>
        <%=JumsHelper.getInstance().home() %>
    </body>
</html>
