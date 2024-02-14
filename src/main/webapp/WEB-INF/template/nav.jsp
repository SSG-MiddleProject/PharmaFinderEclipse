<%--
  Created by IntelliJ IDEA.
  User: kimjinsun
  Date: 2/1/24
  Time: 1:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>

    .menu-list {
        display: flex;
        flex-direction: column;
        align-items: center;
    }

    .menu-list li a span.icon {
        font-size: 1.2rem;
    }

    .menu-list li {
        padding: 0;
    }

    .menu-list li a {
        width: 4rem;
        height: 4rem;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        font-size: 11px;
        font-family: "Noto Sans KR", sans-serif;
        padding: 0;
    }


    #nav-bottom {
        position: absolute;
        bottom: 0;

        a {
            padding-top: 5px;
        }

    }


    #nav-bottom a {
        width: auto;
        height: auto;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;

    }


</style>

<div id="nav">
    <ul class="menu-list is-centered " id="nav-item">
        <li class="has-text-centered " id="logo">
            <a href="<%="main.do"%>" class="is-unselectable">
                <img src="${pageContext.request.contextPath}/resources/P.svg" alt="PharmaFinder Logo"
                     style="width: 40%; height: 40%;"/>
            </a>
        </li>
        <li class="has-text-centered">
            <a href="${pageContext.request.contextPath}/">
                <span class="icon pb-1"><i class="fas fa-pills"></i></span>
                제품 검색
            </a>
        </li>
        <li class="has-text-centered">
            <a href="${pageContext.request.contextPath}/pharmacy.do"
               style="flex-direction: column; align-items: center;">
                <span class="icon pb-1"><i class="fas fa-hospital"></i></span>
                약국 검색
            </a>
        </li>
        <c:if test="${not empty sessionScope.member.storeId && not empty sessionScope.member.id}">
            <li class="has-text-centered">
                <a href="${pageContext.request.contextPath}/pharma-inventory-inquiry.do" class="">
                    <span class="icon pb-1"><i class="fas fa-list"></i></span>
                    <br>재고 조회
                </a>
            </li>
            <li class="has-text-centered">
                <a href="${pageContext.request.contextPath}/pharma-inout-calendar.do" class="">
                    <span class="icon pb-1"><i class="fas fa-calendar"></i></span>
                    입출고<br>내역
                </a>
            </li>
            <li class="has-text-centered">
                <a href="${pageContext.request.contextPath}/pharma-inout-calendar.do" class="">
                    <span class="icon pb-1"><i class="fas fa-cubes"></i></span>
                    <br>제품 관리
                </a>
            </li>
        </c:if>

        <li id="nav-bottom" class="has-text-centered py-2">
            <c:if test="${not empty sessionScope.member.id}">
                <a>
                    즐겨찾기
                </a>
                <a href="${pageContext.request.contextPath}/mypage.do">
                    마이페이지
                </a>
                <a href="${pageContext.request.contextPath}/logout.do">
                    로그아웃
                </a>
            </c:if>
            <c:if test="${empty sessionScope.member.id}">
                <a href="${pageContext.request.contextPath}/login.do">
                    로그인
                </a>
            </c:if>
        </li>
    </ul>
</div>