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
        font-size: 2rem;
    }

    .menu-list li a {
        width: 6rem;
        height: 6rem;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
    }

    #logo {
        width: 100%;
        height: 100%;
        display: flex;
        justify-content: center;
        align-items: center;
        padding-top: 1rem;
    }

    #nav-bottom {
        position: absolute;
        bottom: 0;
    }
</style>

<div id="nav" class="px-1">
    <ul class="menu-list is-centered" id="nav-item">
        <li class="has-text-centered ">
            <a href="<%="main.do"%>" class="is-unselectable" id="logo">
                <img src="/resources/P.svg" alt="PharmaFinder Logo" style="width: 70px; height: 70px;"/>
            </a>
        </li>
        <li class="has-text-centered">
            <a>
                <span class="icon pb-3"><i class="fas fa-pills"></i></span>
                제품 검색
            </a>
        </li>
        <li class="has-text-centered">
            <a href="" class="" style="flex-direction: column; align-items: center;">
                <span class="icon pb-3"><i class="fas fa-hospital"></i></span>
                <br>약국 검색
            </a>
        </li>
        <li class="has-text-centered">
            <a href="#" class="">
                <span class="icon pb-3"><i class="fas fa-calendar"></i></span>
                <br>입출고<br>관리
            </a>
        </li>
        <li class="has-text-centered">
            <a href="#" class="">
                <span class="icon pb-3"><i class="fas fa-cubes"></i></span>
                <br>재고 관리
            </a>
        </li>
        <li id="nav-bottom" class="has-text-centered">
            <a>
                설정
            </a>
            <a>
                로그아웃
            </a>
        </li>

    </ul>

</div>


