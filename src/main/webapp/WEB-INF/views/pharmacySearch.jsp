<%@ page import="ssg.middlepj.pharmafinder.dto.PharmacyResDto" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.util.Properties" %>
<%@ page import="ssg.middlepj.pharmafinder.dto.Pagination" %>
<%@ page import="ssg.middlepj.pharmafinder.util.PaginationUtil" %>

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

    List<PharmacyResDto> list = (List<PharmacyResDto>) request.getAttribute("pharmacies");
    Pagination pagination = (Pagination) request.getAttribute("pagination");

    String keyword = "";
    Integer currentPage = pagination.getPaginationParam().getPage();
    Integer lastPage = pagination.getTotalPageCount();
    keyword = pagination.getPaginationParam().getKeyword();

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
            <% for (PharmacyResDto pharmacy : list) { %>
            <li class="p-2" style="border-top: solid 1px">
                <div class="has-text-black" id="test">
                    <a value="<%=pharmacy.getId()%>" onclick="handleDetail(this)">
                        <%=pharmacy.getDutyName().length() > 15 ? pharmacy.getDutyName().substring(0, 15) + "..." : pharmacy.getDutyName()%>
                    </a>
                    <span>
                        <%
                            if (pharmacy.isBookmark()) {
                        %>
                        <img src="${pageContext.request.contextPath}/resources/Bookmarked.svg" alt="북마크"
                             value="<%=pharmacy.getId()%>" onclick="handleBookmark(this, '<%=pharmacy.isBookmark()%>')"
                             style="width: 1.3rem; height: 1.3rem; float: right;"/>
                        <%
                        } else {
                        %>
                        <img src="${pageContext.request.contextPath}/resources/Bookmark.svg" alt="북마크"
                             value="<%=pharmacy.getId()%>" onclick="handleBookmark(this, '<%=pharmacy.isBookmark()%>')"
                             style="width: 1.3rem; height: 1.3rem; float: right;"/>
                        <%
                            }
                        %>
                    </span>
                    <p><%=pharmacy.getDutyAddr()%>
                    </p>
                </div>
            </li>
            <% } %>
        </ul>
        <div id="search-pagination">
            <ul id="pagination-list">
                <%
                    if (currentPage > 1) {
                %>
                <li onclick="handlePrev()"><</li>
                <% } %>
                <%=PaginationUtil.CreatePaginationList(currentPage, pagination)%>
                <%
                    if (currentPage < lastPage) {
                %>
                <li onclick="handleNext()">></li>
                <% } %>
            </ul>
        </div>
    </div>

    <div id="container-collapse">
        <img src="${pageContext.request.contextPath}/resources/Close.svg" alt="Close Button"
             style="width: 1.3rem; height: 1.3rem; float: right" onclick="closeCollapse()"/>
        <div id="detail" class="content has-text-black">

        </div>
        <ul id="pharmacy-list" style="border-top: solid #dbdbdb 1px">

        </ul>
    </div>
    <div id="container-right">
        <div id="map" style="width: 100%; height: 100%"></div>
    </div>
</div>

<script>
    const existPrev = "<%=pagination.getExistPrev()%>";
    const existNext = "<%=pagination.getExistNext()%>";
    const currentPage = parseInt("<%=currentPage%>");
    const lastPage = parseInt("<%=lastPage%>");
    const markers = [];
    let map;
    let keyword = "<%=keyword%>"

    const handleSearch = () => {
        const keyword = document.querySelector('input[name="keyword"]').value;
        location.href = encodeURI("pharmacy.do?QN=" + keyword);
    }
    const handlePagination = (pageNo) => {
        location.href = encodeURI("pharmacy.do?QN=" + "<%=keyword%>" + "&page=" + pageNo);
    }
    const handlePrev = () => {
        if (existPrev === "true") {
            location.href = encodeURI("pharmacy.do?" + "QN=" + "<%=keyword%>" + "&page=" + (currentPage - 1));
        }
        if (currentPage !== 1) {
            location.href = encodeURI("pharmacy.do?" + "QN=" + "<%=keyword%>" + "&page=" + (currentPage - 1));
        }
    }
    const handleCurrentPageNum = () => {
        const paginationList = document.getElementById('pagination-list')
        paginationList.childNodes.forEach((node) => {
            if (parseInt(node.innerText) === currentPage) {
                node.style.color = "steelblue"
            }
        })
    }
    const handleNext = () => {
        if (existNext === "true") {
            location.href = encodeURI("pharmacy.do?" + "QN=" + "<%=keyword%>" + "&page=" + (currentPage + 1));
        }
        if (currentPage !== lastPage) {
            location.href = encodeURI("pharmacy.do?" + "QN=" + "<%=keyword%>" + "&page=" + (currentPage + 1));
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

    const handlePharmacy = async (storeId) => {
        const detailDiv = document.getElementById('detail')
        detailDiv.innerHTML = "";

        const centerDiv = document.createElement('div')
        centerDiv.id = "centerDiv"
        centerDiv.style.textAlign = "start";
        centerDiv.style.paddingTop = "2rem";
        detailDiv.append(centerDiv)

        const listDiv = document.getElementById('pharmacy-list')
        listDiv.innerHTML = "";

        await fetch(`/pharmacydetail.do?storeId=` + parseInt(storeId))
            .then((response) => response.json())
            .then((data) => {
                const pharmacyDetail = data["pharmacy"];
                const pharmacyName = pharmacyDetail.dutyName;
                const pharmacyAddr = pharmacyDetail.dutyAddr;
                const pharmacyTel = pharmacyDetail.dutyTel1;
                const dutyTime1s = pharmacyDetail.dutyTime1s;
                const dutyTime1c = pharmacyDetail.dutyTime1c;
                const dutyTime2s = pharmacyDetail.dutyTime2s;
                const dutyTime2c = pharmacyDetail.dutyTime2c;
                const dutyTime3s = pharmacyDetail.dutyTime3s;
                const dutyTime3c = pharmacyDetail.dutyTime3c;
                const dutyTime4s = pharmacyDetail.dutyTime4s;
                const dutyTime4c = pharmacyDetail.dutyTime4c;
                const dutyTime5s = pharmacyDetail.dutyTime5s;
                const dutyTime5c = pharmacyDetail.dutyTime5c;
                const dutyTime6s = pharmacyDetail.dutyTime6s;
                const dutyTime6c = pharmacyDetail.dutyTime6c;
                const dutyTime7s = pharmacyDetail.dutyTime7s;
                const dutyTime7c = pharmacyDetail.dutyTime7c;
                const dutyTime8s = pharmacyDetail.dutyTime8s;
                const dutyTime8c = pharmacyDetail.dutyTime8c;

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

                time.onclick = function () {
                    if (allOpeTime.style.display === "none") {
                        allOpeTime.style.display = "block";
                        toggle.innerHTML = "▲";
                    } else {
                        allOpeTime.style.display = "none";
                        toggle.innerHTML = "▼";
                    }
                };

                const productList = data["productsWithQty"];
                console.log(productList)
                if (productList.length === 0) {
                    const noProduct = document.createElement('p')
                    noProduct.innerHTML = "등록된 제품이 없습니다."
                    noProduct.style.paddingTop = "1rem"
                    noProduct.style.textAlign = "center"
                    listDiv.append(noProduct)
                } else {
                    productList.forEach((product) => {
                        console.log(product)

                        const productName = product.itemName;
                        const entpName = product.entpName;
                        const qty = product.qty;
                        // const price = product.price;
                        const li = document.createElement('li');
                        li.className = "p-2";
                        li.style.borderBottom = "solid 1px #dbdbdb";

                        const div = document.createElement('div');
                        div.className = "has-text-black";

                        const productNameA = document.createElement('a');
                        productNameA.innerText = productName.length > 15 ? productName.substring(0, 15) + "..." : productName;

                        const entpNameSpan = document.createElement('span');
                        entpNameSpan.innerText = entpName;
                        entpNameSpan.style.fontSize = "small";
                        entpNameSpan.style.color = "gray";
                        entpNameSpan.style.paddingLeft = "0.5rem";

                        // // 단가
                        // const priceSpan = document.createElement('span');
                        // priceSpan.innerText = price + "원";
                        // priceSpan.style.float = "right";
                        // priceSpan.style.fontSize = "small";


                        const qtySpan = document.createElement('span');
                        qtySpan.innerText = "(재고:" + qty + ")";
                        qtySpan.style.float = "right";
                        qtySpan.style.fontSize = "small";

                        div.append(productNameA);
                        div.append(entpNameSpan);
                        div.append(qtySpan);
                        // div.append(priceSpan);

                        li.append(div);
                        listDiv.append(li);

                    })
                }
            })
    }

    const handleBookmark = (element, isBookmark) => {
        const pharmacyId = parseInt(element.getAttribute("value"))
        if (isBookmark === "true") {
            fetch('/bookmark/pharmacy.do?targetId=' + pharmacyId, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                }
            }).then((res) => res.json()).then((data) => {
                if (data !== true) {
                    throw new Error("북마크 삭제 실패")
                }
                location.reload()
            }).catch((err) => console.error(err))
            return
        }
        fetch('/bookmark/pharmacy.do?targetId=' + pharmacyId, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }
        }).then((res) => res.json()).then((data) => {
            if (data !== true) {
                throw new Error("북마크 추가 실패")
            }
            location.reload()
        }).catch((err) => console.error(err))
    }


    const mapOptions = {
        center: new naver.maps.LatLng(37.3595704, 127.105399),
        zoom: 10
    };

    map = new naver.maps.Map('map', mapOptions);

    const onSuccessGeolocation = (position) => {
        var location = new naver.maps.LatLng(position.coords.latitude, position.coords.longitude);

        map.setCenter(location); // 얻은 좌표를 지도의 중심으로 설정합니다.
        map.setZoom(13); // 지도의 줌 레벨을 변경합니다.
    }

    const onErrorGeolocation = () => {
        var center = map.getCenter();
    }

    $(window).on('load', () => {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(onSuccessGeolocation, onErrorGeolocation);
        } else {
            alert('geolocation not supported');
        }
        handleCurrentPageNum()
    })


</script>

