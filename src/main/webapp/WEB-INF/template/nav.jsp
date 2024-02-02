<%--
  Created by IntelliJ IDEA.
  User: kimjinsun
  Date: 2/1/24
  Time: 1:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="nav">
    <ul class="menu-list is-centered">
            <li class="has-text-centered">

                <button onclick="">
                    <span id="logo"><img src="/resources/P.svg" alt="PharmaFinder Logo"/></span>
                </button>
            </li> <li class="has-text-centered">

                <a>
                    <span class="icon"><i class="fas fa-pills"></i></span>
                    <br>제품 검색
                </a>
            </li>
            <li class="has-text-centered">
                <a href="" class="" style="flex-direction: column; align-items: center;">
                    <span class="icon"><i class="fas fa-hospital"></i></span>
                    <br>약국 검색
                </a>
            </li>
            <li class="has-text-centered">
                <a href="#" class="">
                    <span class="icon"><i class="fas fa-calendar"></i></span>
                    <br>입출고<br>관리
                </a>
            </li>
            <li class="has-text-centered">
                <a href="#" class="">
                    <span class="icon"><i class="fas fa-cubes"></i></span>
                    <br>재고 관리
                </a>
            </li>
        </ul>
</div>

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
    /* logo */
    #logo {
        width: 100%;
        height: 100%;
        display: flex;
        justify-content: center;
        align-items: center;
        padding-bottom: 3rem;;
    }
</style>