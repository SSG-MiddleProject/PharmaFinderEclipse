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
    List<PharmacyDto> list = pharmacyList.get(0).getItems();
    String keyword = "";
    keyword = request.getParameter("QN") == null ? "" : request.getParameter("QN");
    int pageNo = 1;
    pageNo = request.getParameter("pageNo") == null ? 1 : Integer.parseInt(request.getParameter("pageNo"));

    int pageSize=10;
    int recordSize=5;
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
    if(allCount>recordSize) {

    }
    startPage=endPage-recordSize+1;
    if(currentPage<recordSize) {
        startPage=1;
    }

%>
<script type="text/javascript"
        src="https://oapi.map.naver.com/openapi/v3/maps.js?ncpClientId=<%=NHN_CLIENT_KEY%>"></script>

<style>
    #full {
        /*display: flex;*/
        justify-content: space-between;
        width: 100vw;
        height: 100vh;
        z-index: 0;
    }

    #container-left {
        /*flex-shrink: 0;*/
        position: absolute;
        left: 104px;
        top: 0;
        width: 28rem;
        height: 100%;

        #search-bar {
            align-items: center;
            align-content: center;
            height: 2.8rem;
            margin-top: 0.3rem;

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
            transition: background-color .3s;
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
        position: absolute;
        display: none;
        left: 104px;
        top: 0;
        background-color: white;
        width: 28rem;
        height: 100%;
        z-index: 1;
        overflow-x: hidden;
    }

    #container-right {
        /*flex-shrink: 0;*/
        position: absolute;
        right: 0;
        top: 0;
        width: calc(100% - 28rem - 104px);
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
                    <a value="<%=pharmacy.getHpid()%>" onclick="handleDetail(this)">
                        <%=pharmacy.getDutyName().length() > 15 ? pharmacy.getDutyName().substring(0, 15) + "..." : pharmacy.getDutyName()%>
                    </a>
                    <p><%=pharmacy.getDutyAddr()%></p>
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
                <li style="color: steelblue" onclick="handlePagination(<%= i %>)"><%= i %></li>
                <% } else { %>
                <li onclick="handlePagination(<%= i %>)"><%= i %></li>
                <% } %>
                <% } %>
                <% if (existNext) { %>
                <li onclick="handlePagination(<%= currentPage + 1 %>)">></li>
                <% } %>
            </ul>
        </div>


    </div>
    <div id="container-collapse">
        <div id="detail"></div>
        <button onclick="closeNav()">
            X
        </button>
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
        handleProduct(e.getAttribute("value"))
        openNav();
    }
    const openNav = () => {
        document.getElementById("container-collapse").style.left = "calc(104px + 28rem)";
        document.getElementById("container-collapse").style.display = "block";

    }
    const closeNav = () => {
        document.getElementById("container-collapse").style.left = "104px";
        document.getElementById("container-collapse").style.display = "none";
    }

    const mapOptions = {
        center: new naver.maps.LatLng(37.3595704, 127.105399),
        zoom: 10
    };
    const map = new naver.maps.Map('map', mapOptions);


</script>

