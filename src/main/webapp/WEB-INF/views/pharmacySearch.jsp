<%@ page import="ssg.middlepj.pharmafinder.dto.PharmacyExtDto" %>
<%@ page import="ssg.middlepj.pharmafinder.dto.PharmacyDto" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.util.Properties" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    InputStream input = application.getClassLoader().getResourceAsStream("properties/apikey.properties");
    Properties properties = new Properties();

    if (input != null) {
        properties.load(input);
    } else {
        throw new Exception("properties file not found");
    }

    String NHN_CLIENT_KEY = properties.getProperty("NCP_CLIENT_ID");

    List<PharmacyExtDto> pharmacyList = (List<PharmacyExtDto>) request.getAttribute("pharmacies");
    System.out.println(pharmacyList);

    List<PharmacyDto> list = pharmacyList.get(0).getItems();

    String keyword = "";
    keyword = request.getParameter("QN") == null ? "" : request.getParameter("QN");
    int pageNo = 1;
    pageNo = request.getParameter("pageNo") == null ? 1 : Integer.parseInt(request.getParameter("pageNo"));

    int pageSize = 10;
    int recordSize = 5;
    int allCount = pharmacyList.get(0).getAllCount();
    int startPage = 1;
    int endPage = allCount / pageSize + 1;
    int currentPage = pageNo;
    boolean existPrev = false;
    boolean existNext = false;

    if (currentPage > recordSize) {
        startPage = currentPage - recordSize / 2;
        endPage = currentPage + recordSize / 2;
    }
    if (currentPage <= recordSize) {
        startPage = 1;
        endPage = recordSize;
    }
    if (startPage < 1) {
        startPage = 1;
    }
    if (endPage > allCount / pageSize + 1) {
        endPage = allCount / pageSize + 1;
    }
    if (startPage > 1) {
        existPrev = true;
    }
    if (endPage < allCount / pageSize + 1) {
        existNext = true;
    }
    if (allCount > recordSize) {

    }
    startPage = endPage - recordSize + 1;
    if (currentPage < recordSize) {
        startPage = 1;
    }

%>

<%

%>
<script type="text/javascript"
        src="https://oapi.map.naver.com/openapi/v3/maps.js?ncpClientId=<%=NHN_CLIENT_KEY%>"></script>

<style>
    #full {
        justify-content: space-between;
        width: 100vw;
        height: 100vh;
        z-index: 0;
    }


    #container-left {
        position: absolute;
        left: calc(4rem + 1px);
        top: 0;
        width: 24rem;
        height: 100%;
        border: solid 1px #dbdbdb;

        #search-bar {
            align-items: center;
            align-content: center;
            height: 2.8rem;
            margin-top: 0.3rem;
            padding: 0 0.5rem;

        }

        #search-result {
            height: calc(100% - 3.1rem - 2rem);
            overflow-y: scroll;
        }

        #search-pagination {
            text-align: center;
            height: 2rem;
            font-size: large;
            text-decoration: none;
            border-radius: 50%;

            #pagination-list {
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100%;

                li {
                    margin: 0 0.2rem;
                    cursor: pointer;
                }

            }
        }
    }

    #container-collapse {
        padding: 0.5rem 0.5rem;
        position: absolute;
        left: calc(1px + 28rem);
        top: 0;
        background-color: white;
        width: 24rem;
        height: 100%;
        z-index: 1;
        overflow-x: hidden;
        visibility: hidden;
    }

    #detail {
        visibility: inherit;
        display: flex;
        padding-left: 1rem;
        padding-bottom: 1rem;
        flex-direction: column;
        font-size: small;


    }

    #container-right {
        position: absolute;
        right: 0;
        top: 0;
        width: calc(100% - (28rem + 1px));
        height: 100%;
    }
</style>

<div id="full">
    <div id="container-left">
        <div id="search-bar">
            <div class="field has-addons has-addons-centered">
                <div class="control">
                    <label>
                        <input class="input" type="text" name="keyword" placeholder="주변 약국 검색" value="<%=keyword%>">
                    </label>
                </div>
                <div class="control">
                    <button class="button is-info" onclick="handleSearch()">
                        검색
                    </button>
                </div>
            </div>
        </div>
        <ul id="search-result">
            <% for (PharmacyDto pharmacy : list) { %>
            <li class="p-2" style="border-top: solid 1px">
                <div class="has-text-black" id="test">
                    <a value="<%=pharmacy.getDutyName()%>" onclick="handleDetail(this)">
                        <%=pharmacy.getDutyName().length() > 15 ? pharmacy.getDutyName().substring(0, 15) + "..." : pharmacy.getDutyName()%>
                    </a>
                    <p><%=pharmacy.getDutyAddr()%>
                    </p>
                </div>
            </li>
            <% } %>
        </ul>
        <div id="search-pagination">
            <ul id="pagination-list">
                <% if (existPrev) { %>
                <li onclick="handlePagination(<%= currentPage - 1 %>)"><</li>
                <% } %>
                <%
                    for (int i = startPage; i <= endPage; i++) {
                %>
                <% if (i == currentPage) { %>
                <li style="color: steelblue" onclick="handlePagination(<%= i %>)"><%= i %>
                </li>
                <% } else { %>
                <li onclick="handlePagination(<%= i %>)"><%= i %>
                </li>
                <% } %>
                <% } %>
                <% if (existNext) { %>
                <li onclick="handlePagination(<%= currentPage + 1 %>)">></li>
                <% } %>
            </ul>
        </div>
    </div>

    <div id="container-collapse">
        <button onclick="closeCollapse()"
                style="float: right; border-radius: 50%; background-color: transparent; border: solid 1px black; width: 1.5rem; height: 1.5rem">
            X
        </button>
        <div id="detail" class="container">
        </div>
        <div id="stock-list">
            재고
        </div>
    </div>
    <div id="container-right">
        <div id="map" style="width: 100%; height: 100%"></div>
    </div>
</div>

<script>
    const currentPage = parseInt("<%=currentPage%>");
    const lastPage = parseInt("<%=endPage%>");
    let keyword = "<%=keyword%>"

    const handleSearch = () => {
        const keyword = document.querySelector('input[name="keyword"]').value;
        location.href = encodeURI("pharmacy.do?QN=" + keyword);
    }
    const handlePagination = (pageNo) => {
        location.href = encodeURI("pharmacy.do?QN=" + "<%=keyword%>" + "&pageNo=" + pageNo);
    }
    const handlePrev = () => {
        if (existPrev === "true") {
            location.href = encodeURI("pharmacy.do?" + "QN=" + "<%=keyword%>" + "&pageNo=" + (currentPage - 1));
        }
        if (currentPage !== 1) {
            location.href = encodeURI("pharmacy.do?" + "QN=" + "<%=keyword%>" + "&pageNo=" + (currentPage - 1));
        }
    }
    const handleNext = () => {
        if (existNext === "true") {
            location.href = encodeURI("pharmacy.do?" + "QN=" + "<%=keyword%>" + "&pageNo=" + (currentPage + 1));
        }
        if (currentPage !== lastPage) {
            location.href = encodeURI("pharmacy.do?" + "QN=" + "<%=keyword%>" + "&pageNo=" + (currentPage + 1));
        }
    }
    const handleDetail = (e) => {
        handlePharmacy(e.getAttribute('value'));
        openCollapse();
    }
    const openCollapse = () => {
        document.getElementById("container-collapse").style.visibility = "visible";
    }
    const closeCollapse = () => {
        document.getElementById("container-collapse").style.visibility = "hidden";
    }

    const handlePharmacy = async (name) => {
        const detailDiv = document.getElementById('detail')
        detailDiv.innerHTML = "";

        const centerDiv = document.createElement('div')
        centerDiv.id = "centerDiv"
        centerDiv.style.textAlign = "start";
        centerDiv.style.paddingTop = "2rem";
        detailDiv.append(centerDiv)

        await fetch(`/pharmacydetail.do?QN=` + name)
            .then((response) => response.json())
            .then((data) => {
                const pharmacyName = data.dutyName;
                const pharmacyAddr = data.dutyAddr;
                const pharmacyTel = data.dutyTel1;
                const dutyTime1s = data.dutyTime1s;
                const dutyTime1c = data.dutyTime1c;
                const dutyTime2s = data.dutyTime2s;
                const dutyTime2c = data.dutyTime2c;
                const dutyTime3s = data.dutyTime3s;
                const dutyTime3c = data.dutyTime3c;
                const dutyTime4s = data.dutyTime4s;
                const dutyTime4c = data.dutyTime4c;
                const dutyTime5s = data.dutyTime5s;
                const dutyTime5c = data.dutyTime5c;
                const dutyTime6s = data.dutyTime6s;
                const dutyTime6c = data.dutyTime6c;
                const dutyTime7s = data.dutyTime7s;
                const dutyTime7c = data.dutyTime7c;
                const dutyTime8s = data.dutyTime8s;
                const dutyTime8c = data.dutyTime8c;

                const h2 = document.createElement('h2')
                h2.id = "dutyName"
                h2.style.fontSize = "1.5rem"
                h2.style.paddingBottom = "1rem"
                h2.innerHTML = pharmacyName;
                centerDiv.append(h2)

                const addr = document.createElement('p')
                addr.id = "dutyAddr"
                addr.innerHTML = "📍 " + pharmacyAddr;
                addr.classList.add("bold")
                centerDiv.append(addr)

                const tel = document.createElement('p')
                tel.id = "dutyTel1"
                addr.classList.add("bold")
                tel.innerHTML = "📞 " + pharmacyTel;
                centerDiv.append(tel)

                const timeContainer = document.createElement('div')
                timeContainer.style.display = "flex"
                timeContainer.style.justifyContent = "space-between"
                timeContainer.style.alignItems = "center"

                const time = document.createElement('b')
                time.id = "dutyTime"
                time.classList.add("bold")
                let timeText = "";
                const today = new Date().getDay();

                switch (today) {
                    case 1:
                        timeText = dutyTime1c && dutyTime1s ? "⏰ 월요일 " + dutyTime1s + " ~ " + dutyTime1c : "⏰ 월요일 휴무";
                        break;
                    case 2:
                        timeText = dutyTime2c && dutyTime2s ? "⏰ 화요일 " + dutyTime2s + " ~ " + dutyTime2c : "⏰ 화요일 휴무";
                        break;
                    case 3:
                        timeText = dutyTime3c && dutyTime3s ? "⏰ 수요일 " + dutyTime3s + " ~ " + dutyTime3c : "⏰ 수요일 휴무";
                        break;
                    case 4:
                        timeText = dutyTime4c && dutyTime4s ? "⏰ 목요일 " + dutyTime4s + " ~ " + dutyTime4c : "⏰ 목요일 휴무";
                        break;
                    case 5:
                        timeText = dutyTime5c && dutyTime5s ? "⏰ 금요일 " + dutyTime5s + " ~ " + dutyTime5c : "⏰ 금요일 휴무";
                        break;
                    case 6:
                        timeText = dutyTime6c && dutyTime6s ? "⏰ 토요일 " + dutyTime6s + " ~ " + dutyTime6c : "⏰ 토요일 휴무";
                        break;
                    case 0:
                        timeText = dutyTime7c && dutyTime7s ? "⏰ 일요일 " + dutyTime7s + " ~ " + dutyTime7c : "⏰ 일요일 휴무";
                        break;
                    default:
                        break;
                }

                time.innerHTML = timeText;
                timeContainer.append(time);

                const toggle = document.createElement('p')
                toggle.id = "toggle"
                toggle.innerHTML = "▼"
                toggle.onclick = function () {
                    if (allOpeTime.style.display === "none") {
                        allOpeTime.style.display = "block";
                        toggle.innerHTML = "▲";
                    } else {
                        allOpeTime.style.display = "none";
                        toggle.innerHTML = "▼";
                    }
                };

                timeContainer.append(toggle);
                centerDiv.append(timeContainer);

                const allOpeTime = document.createElement("p")
                allOpeTime.id = "allOpeTime"
                allOpeTime.style.display = "none"
                allOpeTime.innerHTML = (dutyTime1c && dutyTime1s ? "⏰ 월요일 " + dutyTime1s + " ~ " + dutyTime1c : "⏰ 월요일 휴무") +
                    (dutyTime2c && dutyTime2s ? "<br>⏰ 화요일 " + dutyTime2s + " ~ " + dutyTime2c : "<br>⏰ 화요일 휴무") +
                    (dutyTime3c && dutyTime3s ? "<br>⏰ 수요일 " + dutyTime3s + " ~ " + dutyTime3c : "<br>⏰ 수요일 휴무") +
                    (dutyTime4c && dutyTime4s ? "<br>⏰ 목요일 " + dutyTime4s + " ~ " + dutyTime4c : "<br>⏰ 목요일 휴무") +
                    (dutyTime5c && dutyTime5s ? "<br>⏰ 금요일 " + dutyTime5s + " ~ " + dutyTime5c : "<br>⏰ 금요일 휴무") +
                    (dutyTime6c && dutyTime6s ? "<br>⏰ 토요일 " + dutyTime6s + " ~ " + dutyTime6c : "<br>⏰ 토요일 휴무") +
                    (dutyTime7c && dutyTime7s ? "<br>⏰ 일요일 " + dutyTime7s + " ~ " + dutyTime7c : "<br>⏰ 일요일 휴무");

                centerDiv.append(allOpeTime);
            });
    }


    const mapOptions = {
        center: new naver.maps.LatLng(37.3595704, 127.105399),
        zoom: 10
    };
    const map = new naver.maps.Map('map', mapOptions);


</script>

