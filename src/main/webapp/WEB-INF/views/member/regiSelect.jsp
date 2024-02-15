<%@ page language="java" contentType="text/html; charset=UTF-8"
              pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>select Register</title>
    <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css"
    />
    <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"
    />
</head>

<style>
    .btn-hov:hover {
        background-color: #e3e4e6;
        transform: scale(1.05);
    }
    </style>
<body>
<div class="center">
    <div
            class="container"
            style="
          display: flex;
          flex-direction: column;
          align-items: center;
          justify-content: center;
          width: 100vw;
          height: 100vh;
        "
    >
        <h1 class="title is-1" style="font-weight: bold; margin-bottom: 0">
            PharmaFinder
        </h1>
        <p style="font-size: x-large">Register</p>
        <div
                class="btn-container"
                style="
            display: flex;
            align-items: center;
            justify-content: center;
            padding-top: 40px;
          "
        >
            <div
                    class="btn-hov"
                    onclick="location.href='userRegi.do'"
<%--                    href="userRegi.do"--%>
                    style="
              background-color: #f3f4f6;
              display: flex;
              flex-direction: column;
              align-items: center;
              justify-content: center;
              padding: 10px 20px;
              margin: 10px;
              border-radius: 5px;
              color: #404040;
              text-decoration: none;
              font-size: 20px;
              font-weight: bold;
              cursor: pointer;
              transition: all 0.3s ease;
              width: 15rem;
              height: 16rem;
            "
            >
                <i class="fa fa-user fa-4x"></i>
                <span style="padding-top: 1rem; font-size: xx-large">일반회원</span>
            </div>
            <div
                    class="btn-hov"
                    onclick="location.href='pharmacyRegi.do'"
            <%--                    href="userRegi.do"--%>
                    style="
              background-color: #f3f4f6;
              display: flex;
              flex-direction: column;
              align-items: center;
              justify-content: center;
              padding: 10px 20px;
              margin: 10px;
              border-radius: 5px;
              color: #404040;
              text-decoration: none;
              font-size: 20px;
              font-weight: bold;
              cursor: pointer;
              transition: all 0.3s ease;
              width: 15rem;
              height: 16rem;
            "
            >
                <i class="fa fa-pills fa-4x"></i>
                <span style="padding-top: 1rem; font-size: xx-large">약국회원</span>
            </div>

        </div>
        <div style="padding-top: 1rem">
            이미 계정이 있으신가요? <a href="login.do">로그인</a>
        </div>
    </div>
</div>
</body>
</html>
