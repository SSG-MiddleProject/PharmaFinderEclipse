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
        /*display: flex;*/
        justify-content: space-between;
        width: 100vw;
        height: 100vh;
        z-index: 0;
    }

    #container-left {
        /*flex-shrink: 0;*/
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

        #entpName #itemName {
            text-align: center;
        }

        #itemName {
            margin-top: 1rem;
            margin-bottom: 2rem;
        }

        #entpName {
            color: #4a4a4a;
        }
    }

    #detail-collapse {
        display: none;

        #detail-extra {
            display: inherit;
        }
    }

    #collapse-extend {
        cursor: pointer;
        padding: 0.5rem 0;
    }

    #img-collapse-reduce {
        display: none;
    }

    #pharmacy-list {
        width: 100%;
        background-color: #f5f5f5;
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
        <div id="detail" class="content has-text-black" style="background-color: lightgray">

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
        // console.log(e.getAttribute('value'));
        openCollapse();
    }
    const openCollapse = () => {
        document.getElementById("container-collapse").style.visibility = "visible";
    }
    const closeCollapse = () => {
        document.getElementById("container-collapse").style.visibility = "hidden";
    }

    const handlePharmacy = async (name) => {
        console.log(name)
        const detailDiv = document.getElementById('detail')
        detailDiv.innerHTML = "dmdkdkdmdkd";

        const centerDiv = document.createElement('div')
        centerDiv.style.textAlign = "center";
        centerDiv.style.paddingTop = "1rem";
        detailDiv.append(centerDiv)

        await fetch(`/pharmacydetail.do?QN=`+name)
            .then((response) => response.json())
            .then((data) => {
                console.log(data)
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

                const span = document.createElement('span')
                span.id = "dutyName"
                span.innerHTML = pharmacyName;
                centerDiv.append(span)

                const span2 = document.createElement('span')
                span2.id = "dutyAddr"
                span2.innerHTML = pharmacyAddr;
                centerDiv.append(span2)

                const span3 = document.createElement('span')
                span3.id = "dutyTel1"
                span3.innerHTML = pharmacyTel;
                centerDiv.append(span3)

                // 운영시간 00시 ~ 00시
                const span4 = document.createElement('span')
                span4.id = "dutyTime1"
                span4.innerHTML = dutyTime1s + " ~ " + dutyTime1c;
                centerDiv.append(span4)

                const span5 = document.createElement('span')
                span5.id = "dutyTime2"
                span5.innerHTML = dutyTime2s + " ~ " + dutyTime2c;
                centerDiv.append(span5)


                }

            );
    }


    const mapOptions = {
        center: new naver.maps.LatLng(37.3595704, 127.105399),
        zoom: 10
    };
    const map = new naver.maps.Map('map', mapOptions);


</script>

