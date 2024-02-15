<%@page import="ssg.middlepj.pharmafinder.dto.MemberDto" %>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    System.out.println(request.getAttribute("errorMessage"));
    System.out.println(request.getAttribute("successMessage"));
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Pharmacy Registration</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
    <script src="https://code.jquery.com/jquery-3.7.1.js"
            integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
    <!-- 주소 API 스크립트 추가 -->
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=3771a8dd6cc1b648191095305a583773&libraries=services"></script>
    <!-- Kakao 지도 API를 사용하려면, 여기서 'Your-App-Key'를 실제 앱 키로 교체해야 합니다. -->
</head>
<body>

<div class="columns is-centered">
    <div class="column is-half">
        <section class="section">
            <div class="container">
                <h1 class="title has-text-centered">PharmaFinder</h1>
                <h2 class="subtitle has-text-centered">회원가입</h2>
                <form action="pharmacyRegiAf.do" method="post" id="regiForm">
                    <div class="field">
                        <label class="label" for="username">아이디</label>
                        <div class="control">
                            <input class="input" type="text" name="username" id="username" placeholder="아이디를 입력해주세요"
                                   required>
                        </div>
                        <p id="usernamecheck"></p>
                    </div>
                    <div class="field">
                        <label class="label" for="email">이메일</label>
                        <div class="control">
                            <input class="input" type="email" name="email" id="email" placeholder="이메일을 입력해주세요"
                                   required>
                        </div>
                        <p id="emailcheck"></p>
                    </div>
                    <div class="field">
                        <label class="label" for="password">비밀번호</label>
                        <div class="control">
                            <input class="input" type="password" name="password" id="password"
                                   placeholder="비밀번호를 입력해주세요" required>
                        </div>
                        <p id="passwordcheck"></p>
                    </div>
                    <!-- Remaining form fields -->
                    <div class="field">
                        <label class="label" for="dutyName">약국이름</label>
                        <div class="control">
                            <input class="input" type="text" name="dutyName" id="dutyName" placeholder="약국이름을 입력해주세요"
                                   required>
                        </div>
                    </div>
                    <div class="field">
                        <label class="label" for="dutyTel1">전화번호</label>
                        <div class="control">
                            <input class="input" type="text" name="dutyTel1" id="dutyTel1" placeholder="010-1234-5678"
                                   required>
                        </div>
                    </div>
                    <div class="field">
                        <label class="label" for="bnRegNum">사업자번호</label>
                        <div class="control">
                            <input class="input" type="text" name="bnRegNum" id="bnRegNum" placeholder="123-45-67890"
                                   required>
                        </div>
                    </div>
                    <div class="field">
                        <label class="label">우편번호</label>
                        <div class="control is-grouped">
                            <!-- 우편번호 입력 -->
                            <div class="control is-expanded">
                                <input class="input" type="text" id="sample4_postcode" name="postcode" placeholder="우편번호" required>
                            <!-- 도로명 주소 검색 버튼 -->
                            <div class="control">
                                <button class="button is-info" type="button" onclick="sample4_execDaumPostcode()">도로명 주소 검색</button>
                            </div>
                        </div>
                    </div>


                    <div class="field">
                        <label class="label" for="sample4_dutyAddr">도로명주소</label>
                        <div class="control">
                            <input class="input" type="text" id="sample4_dutyAddr" name="dutyAddr" placeholder="도로명주소"
                                   required>
                        </div>
                    </div>
                    <!-- 위도 및 경도 입력 필드 추가 -->
                    <div class="field">
                        <label class="label" for="wgs84Lat">위도</label>
                        <div class="control">
                            <input class="input" type="text" id="wgs84Lat" name="wgs84Lat" placeholder="위도" required
                                   readonly>
                        </div>
                    </div>
                    <div class="field">
                        <label class="label" for="wgs84Lon">경도</label>
                        <div class="control">
                            <input class="input" type="text" id="wgs84Lon" name="wgs84Lon" placeholder="경도" required
                                   readonly>
                        </div>
                    </div>
                    <div class="field">
                        <label class="label" for="dutyTime1s">월요일</label>
                        <div class="control">
                            <input class="input" type="time" name="dutyTime1s" id="dutyTime1s" value="09:00" required>
                            <span> ~ </span>
                            <input class="input" type="time" name="dutyTime1c" id="dutyTime1c" value="18:00" required>
                        </div>
                    </div>
                    <div class="field">
                        <label class="label" for="dutyTime2s">화요일</label>
                        <div class="control">
                            <input class="input" type="time" name="dutyTime2s" id="dutyTime2s" value="09:00" required>
                            <span> ~ </span>
                            <input class="input" type="time" name="dutyTime2c" id="dutyTime2c" value="18:00" required>
                        </div>
                    </div>
                    <!-- Repeat the same pattern for the remaining days -->
                    <!-- ... -->
                    <!-- Remaining form fields for day 3 (수요일) -->
                    <div class="field">
                        <label class="label" for="dutyTime3s">수요일</label>
                        <div class="control">
                            <input class="input" type="time" name="dutyTime3s" id="dutyTime3s" value="09:00" required>
                            <span> ~ </span>
                            <input class="input" type="time" name="dutyTime3c" id="dutyTime3c" value="18:00" required>
                        </div>
                    </div>

                    <!-- Remaining form fields for day 4 (목요일) -->
                    <div class="field">
                        <label class="label" for="dutyTime4s">목요일</label>
                        <div class="control">
                            <input class="input" type="time" name="dutyTime4s" id="dutyTime4s" value="09:00" required>
                            <span> ~ </span>
                            <input class="input" type="time" name="dutyTime4c" id="dutyTime4c" value="18:00" required>
                        </div>
                    </div>

                    <!-- Remaining form fields for day 5 (금요일) -->
                    <div class="field">
                        <label class="label" for="dutyTime5s">금요일</label>
                        <div class="control">
                            <input class="input" type="time" name="dutyTime5s" id="dutyTime5s" value="09:00" required>
                            <span> ~ </span>
                            <input class="input" type="time" name="dutyTime5c" id="dutyTime5c" value="18:00" required>
                        </div>
                    </div>

                    <!-- Remaining form fields for day 6 (토요일) -->
                    <div class="field">
                        <label class="label" for="dutyTime6s">토요일</label>
                        <div class="control">
                            <input class="input" type="time" name="dutyTime6s" id="dutyTime6s" value="09:00" required>
                            <span> ~ </span>
                            <input class="input" type="time" name="dutyTime6c" id="dutyTime6c" value="18:00" required>
                        </div>
                    </div>

                    <!-- Remaining form fields for day 7 (일요일) -->
                    <div class="field">
                        <label class="label" for="dutyTime7s">일요일</label>
                        <div class="control">
                            <input class="input" type="time" name="dutyTime7s" id="dutyTime7s" value="09:00" required>
                            <span> ~ </span>
                            <input class="input" type="time" name="dutyTime7c" id="dutyTime7c" value="18:00" required>
                        </div>
                    </div>

                    <!-- Remaining form fields for day 8 (공휴일) -->
                    <div class="field">
                        <label class="label" for="dutyTime8s">공휴일</label>
                        <div class="control">
                            <input class="input" type="time" name="dutyTime8s" id="dutyTime8s" value="09:00" required>
                            <span> ~ </span>
                            <input class="input" type="time" name="dutyTime8c" id="dutyTime8c" value="18:00" required>
                        </div>
                    </div>

                    <!-- Submit button -->
                    <div class="field">
                        <div class="control">
                            <input class="button is-primary is-fullwidth" type="submit" value="회원가입">
                        </div>
                    </div>
                </form>
            </div>
        </section>
    </div>
</div>


<script type="text/javascript">
    $(document).ready(function () {
        // Username Validation & Duplication Check
        $('#username').keyup(function () {
            var username = $(this).val().trim();
            var idPattern = /^[a-z0-9]{5,15}$/;
            if (username === "") {
                $("#usernamecheck").empty();
            } else {
                if (!idPattern.test(username)) {
                    $("#usernamecheck").text("아이디는 5~15글자의 영문 소문자와 숫자로 이루어져야 합니다.").css("color", "#ff0000");
                } else {
                    $.ajax({
                        url: "usernamecheck.do",
                        type: "POST",
                        data: {"username": username},
                        success: function (response) {
                            if (response.trim() === "YES") {
                                $("#usernamecheck").text("사용 가능한 아이디입니다.").css("color", "#00ff00");
                            } else {
                                $("#usernamecheck").text("이미 사용 중인 아이디입니다.").css("color", "#ff0000");
                            }
                        }
                    });
                }
            }
        });


// 이메일 유효성 검사 및 중복 확인
        $('#email').keyup(function () {
            var email = $(this).val().trim();
            var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            if (email === "") {
                $("#emailcheck").empty();
            } else {
                if (!emailPattern.test(email)) {
                    $("#emailcheck").text("유효하지 않은 이메일 형식입니다.").css("color", "#ff0000");
                } else {
                    $.ajax({
                        url: "emailcheck.do",
                        type: "POST",
                        data: {"email": email},
                        success: function (response) {
                            if (response.trim() === "YES") {
                                $("#emailcheck").text("사용 가능한 이메일입니다.").css("color", "#00ff00");
                            } else {
                                $("#emailcheck").text("이미 사용 중인 이메일입니다.").css("color", "#ff0000");
                            }
                        }
                    });
                }
            }
        });

// 비밀번호 유효성 검사
        $('#password').keyup(function () {
            var password = $(this).val();
            var passwordPattern = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*]).{5,15}$/;
            if (!passwordPattern.test(password)) {
                $("#passwordcheck").text("비밀번호는 5~15글자이며, 영문, 숫자, 특수문자를 최소한 하나씩 포함해야 합니다.").css("color", "#ff0000");
            } else {
                $("#passwordcheck").text("사용 가능한 비밀번호입니다.").css("color", "#00ff00");
            }
        });

// 전화번호 형식 변환 (010-1234-5678)
        $('#dutyTel1').keyup(function () {
            var val = this.value.replace(/\D/g, '');
            var newVal = '';
            if (val.length > 3) {
                newVal += val.substr(0, 3) + '-';
                val = val.substr(3);
            }
            if (val.length > 4) {
                newVal += val.substr(0, 4) + '-';
                val = val.substr(4);
            }
            newVal += val;
            this.value = newVal.substring(0, 13); // 최대 입력 가능한 문자 수 제한
        });

// 사업자등록번호 형식 변환 (123-45-67890)
        $('#bnRegNum').keyup(function () {
            var val = this.value.replace(/\D/g, '');
            var newVal = '';
            if (val.length > 3) {
                newVal += val.substr(0, 3) + '-';
                val = val.substr(3);
            }
            if (val.length > 2) {
                newVal += val.substr(0, 2) + '-';
                val = val.substr(2);
            }
            newVal += val;
            this.value = newVal.substring(0, 12); // 최대 입력 가능한 문자 수 제한
        });
    });

</script>

<!-- 도로명 주소 검색 기능 -->
<script>
    function sample4_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function (data) {
                // 팝업에서 검색결과 항목을 클릭했을 때 실행할 코드를 작성하는 부분.
                // 도로명 주소나 지번 주소 등을 필요한 input field에 값으로 설정
                document.getElementById('sample4_postcode').value = data.zonecode;
                document.getElementById('sample4_dutyAddr').value = data.roadAddress;

                // Kakao 지도 API를 사용하여 위도와 경도를 조회
                var geocoder = new kakao.maps.services.Geocoder();
                geocoder.addressSearch(data.roadAddress, function (result, status) {
                    if (status === kakao.maps.services.Status.OK) {
                        var lat = result[0].y; // 위도
                        var lon = result[0].x; // 경도
                        document.getElementById('wgs84Lat').value = lat;
                        document.getElementById('wgs84Lon').value = lon;
                    }
                });
            }
        }).open();
    }
</script>

</body>
</html>