<%--<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>--%>
<%--<%--%>
<%--    String pageTitle = (String)request.getParameter("title");--%>
<%--    String contentPage = request.getParameter("content");--%>
<%--    if(pageTitle == null){--%>
<%--        pageTitle = "PharmaFinder";--%>
<%--    }--%>
<%--    if(contentPage == null){--%>
<%--        contentPage = "WEB-INF/views/main.jsp";--%>
<%--    }--%>

<%--%>--%>
<%--<!DOCTYPE html>--%>
<%--<html lang="en">--%>

<%--<head>--%>
<%--    <meta charset="UTF-8">--%>
<%--    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">--%>
<%--    <meta http-equiv="X-UA-Compatible" content="ie=edge">--%>
<%--    <title><%= pageTitle%></title>--%>
<%--    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">--%>
<%--    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>--%>
<%--</head>--%>



<%--<body>--%>
<%--<form name="template">--%>
<%--    <table>--%>
<%--        <tr colspan="2">--%>
<%--            <td>--%>
<%--                <aside class="column is-2 is-narrow-mobile is-fullheight section is-hidden-mobile">--%>
<%--                    <a href="#">--%>
<%--                        <span id="logo"><img src="./resources/P.svg" alt="PharmaFinder Logo" /></span>--%>
<%--                        pharma--%>
<%--                    </a>--%>
<%--                    <jsp:include page="nav.jsp" flush="false" />--%>
<%--                </aside>--%>
<%--            </td>--%>
<%--            <td>--%>
<%--               <div id="content">--%>

<%--                <jsp:include page="<%= contentPage %>" flush="false"/>--%>
<%--               </div>--%>

<%--            </td>--%>
<%--        </tr>--%>
<%--    </table>--%>
<%--</form>--%>
<%--</body>--%>

<%--</html>--%>
<%
    response.sendRedirect("main.do");
%>
