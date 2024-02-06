<%--
  Created by IntelliJ IDEA.
  User: kimjinsun
  Date: 2/6/24
  Time: 9:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="ssg.middlepj.pharmafinder.dto.PharmacyDto" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String dutyName = "";
    if(request.getParameter("dutyName") != null) {
        dutyName = request.getParameter("dutyName");
    }
    List<PharmacyDto> pharmacyList = (List<PharmacyDto>) request.getAttribute("pharmacyList");

//    String dutyName = request.getParameter("dutyName");
%>

<div>

</div>