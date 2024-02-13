<%@ page import="ssg.middlepj.pharmafinder.dto.Pagination" %>
<%@ page import="ssg.middlepj.pharmafinder.util.PaginationUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.util.Properties" %>
<%@ page import="ssg.middlepj.pharmafinder.dto.ProductResDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    InputStream input = application.getClassLoader().getResourceAsStream("properties/apikey.properties");
    Properties properties = new Properties();

    if (input != null) {
        properties.load(input);
    } else {
        throw new Exception("properties file not found");
    }

    String NHN_CLIENT_KEY = properties.getProperty("NCP_CLIENT_ID");
%>
<script type="text/javascript"
        src="https://oapi.map.naver.com/openapi/v3/maps.js?ncpClientId=<%=NHN_CLIENT_KEY%>&submodules=geocoder"></script>
<%
    String searchType = "";
    String keyword = "";
    Pagination pagination = (Pagination) request.getAttribute("pagination");
    Integer currentPage = pagination.getPaginationParam().getPage();
    Integer lastPage = pagination.getTotalPageCount();
    List<ProductResDto> productList = (List<ProductResDto>) request.getAttribute("products");
    searchType = pagination.getPaginationParam().getSearchType();
    keyword = pagination.getPaginationParam().getKeyword();
%>

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

        #detail {
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

            > img {
                display: block;
                margin: 0 auto;
                padding-top: 1rem;
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
        }
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
                    <div class="select">
                        <label for="searchType">
                            <select id="searchType" value="<%=searchType%>">
                                <option value="entpName">제조사</option>
                                <option value="itemName">이름</option>
                                <option value="efcyQes">효능</option>
                            </select>
                        </label>
                    </div>
                </div>
                <div class="control">
                    <label>
                        <input class="input" type="text" name="keyword" placeholder="검색할 단어 입력" value="<%=keyword%>">
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
            <%
                if (productList.isEmpty()) {
            %>
            <li class="p-2" style="border-top: solid 1px">
                <div class="has-text-black mt-4" style="text-align: center">
                    검색 결과가 없습니다.
                </div>
            </li>
            <%
                }
                for (ProductResDto product : productList) {
            %>
            <li class="p-2" style="border-top: solid 1px">
                <div class="has-text-black">
                    <a value="<%=product.getId()%>" onclick="handleDetail(this)">
                        <%-- 이름이 15자 이상이면 자르기 --%>
                        <%=product.getItemName().length() > 15 ? product.getItemName().substring(0, 15) + "..." : product.getItemName()%>
                        <span class="has-text-grey is-size-7"><%= product.getEntpName().length() > 15 ? product.getEntpName().substring(0, 15) + "..." : product.getEntpName()%></span>
                    </a>
                    <span>
                        <%
                            if (product.isBookmark()) {
                        %>
                        <img src="${pageContext.request.contextPath}/resources/Bookmarked.svg" alt="북마크"
                             value="<%=product.getId()%>" onclick="handleBookmark(this, '<%=product.isBookmark()%>')"
                             style="width: 1.3rem; height: 1.3rem; float: right;"/>
                        <%
                        } else {
                        %>
                        <img src="${pageContext.request.contextPath}/resources/Bookmark.svg" alt="북마크"
                             value="<%=product.getId()%>" onclick="handleBookmark(this, '<%=product.isBookmark()%>')"
                             style="width: 1.3rem; height: 1.3rem; float: right;"/>
                        <%
                            }
                        %>
                    </span>
                    <p>
                        &nbsp;<%=product.getEfcyQes().length() > 30 ? product.getEfcyQes().substring(0, 30) + "..." : product.getEfcyQes()%>
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
        <div id="detail" class="content has-text-black"></div>
        <div id="detail-collapse" style="display: none">
            <div id="detail-extra" class="content has-text-black"></div>
        </div>
        <div id="collapse-extend" style="text-align: center; border-bottom: solid 1px #e5e5e5"
             onclick="handleDetailExtand()">
            <img id="img-collapse-extend" src="${pageContext.request.contextPath}/resources/CollapseExtend.svg"
                 alt="Collapse Extend"
                 style="width: 1.3rem; height: 1.3rem;"/>
            <img id="img-collapse-reduce" src="${pageContext.request.contextPath}/resources/CollapseReduce.svg"
                 alt="Collapse Reduce"
                 style="width: 1.3rem; height: 1.3rem;"/>
        </div>
        <ul id="pharmacy-list">
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
    let choice = document.getElementById("searchType");
    choice.value = "<%=searchType %>";
    choice.selected = true;

    const handleSearch = () => {
        const searchType = document.querySelector('select').value;
        const keyword = document.querySelector('input[name="keyword"]').value;
        location.href = encodeURI("main.do?searchType=" + searchType + "&keyword=" + keyword);
    }

    const handlePagination = (page) => {
        location.href = encodeURI("main.do?searchType=" + "<%=searchType%>" + "&keyword=" + "<%=keyword%>" + "&page=" + page);
    }

    const handlePrev = () => {
        if (existPrev === "true") {
            location.href = encodeURI("main.do?searchType=" + "<%=searchType%>" + "&keyword=" + "<%=keyword%>" + "&page=" + (currentPage - 1));
        }
        if (currentPage !== 1) {
            location.href = encodeURI("main.do?searchType=" + "<%=searchType%>" + "&keyword=" + "<%=keyword%>" + "&page=" + (currentPage - 1));
        }
    }

    const handleNext = () => {
        if (existNext === "true") {
            location.href = encodeURI("main.do?searchType=" + "<%=searchType%>" + "&keyword=" + "<%=keyword%>" + "&page=" + (currentPage + 1));
        }
        if (currentPage !== lastPage) {
            location.href = encodeURI("main.do?searchType=" + "<%=searchType%>" + "&keyword=" + "<%=keyword%>" + "&page=" + (currentPage + 1));
        }
    }

    const handleDetail = (e) => {
        handleProduct(e.getAttribute("value"))
        openCollapse();
    }

    const openCollapse = () => {
        deleteMarkers()
        document.getElementById("container-collapse").style.visibility = "visible";
    }

    const closeCollapse = () => {
        deleteMarkers()
        document.getElementById("container-collapse").style.visibility = "hidden";
    }

    const handleProduct = async (id) => {
        const detailDiv = document.getElementById('detail')
        detailDiv.innerHTML = ""

        const titleDiv = document.createElement('div')
        titleDiv.style.paddingTop = "2rem"
        titleDiv.innerHTML = ""

        const detailExtraDiv = document.getElementById('detail-extra')
        detailExtraDiv.innerHTML = ""

        detailDiv.append(titleDiv)

        const pharmacyList = document.getElementById('pharmacy-list')
        pharmacyList.innerHTML = ""

        await fetch(`/product/detail.do?productId=` + id)
            .then(res => res.json())
            .then(data => {
                Object.entries(data["product"]).forEach(([key, value]) => {
                    if (key === "itemName") {
                        const sp = document.createElement("span")
                        sp.innerText = value
                        sp.className = "is-size-4"
                        titleDiv.prepend(sp)
                    }
                    if (key === "entpName") {
                        const sp = document.createElement("span")
                        sp.innerText = value
                        sp.className = "is-size-6 has-text-grey pl-2"
                        titleDiv.append(sp)
                    }
                    if (key === "efcyQes") {
                        const p = document.createElement('p')
                        p.innerText = value
                        p.className = "pt-4"
                        detailDiv.append(p)
                    }
                    if (key === "useMethodQes") {
                        detailDiv.append(creatDetailH4("용법", value))
                    }
                    if (key === "atpnWarnQes") {
                        detailExtraDiv.append(creatDetailH4("주의사항", value))
                    }
                    if (key === "intrcQes") {
                        detailExtraDiv.append(creatDetailH4("복용시 주의사항", value))
                    }
                    if (key === "seQes") {
                        detailExtraDiv.append(creatDetailH4("부작용", value))
                    }
                    if (key === "depositMethodQes") {
                        detailExtraDiv.append(creatDetailH4("보관방법", value))
                    }
                    if (key === "itemImage") {
                        if (value === null) {
                            return
                        }
                        const img = document.createElement('img')
                        img.src = value
                        img.style.width = "50%"
                        detailDiv.childNodes[0].after(img)
                    }
                })
                Object.entries(data["pharmaciesWithQty"]).forEach(([key, value]) => {
                    createMarker(value)
                    const li = document.createElement('li')
                    li.className = "p-2"
                    li.style.borderBottom = "solid 1px"
                    const div = document.createElement('div')
                    div.className = "has-text-black"
                    const a = document.createElement('a')
                    a.onclick = () => handlePharmacyDetail(value["dutyName"])
                    a.innerText = value["dutyName"].length > 15 ? value["dutyName"].substring(0, 15) + "..." : value["dutyName"]
                    const p = document.createElement('p')
                    p.innerText = value["dutyAddr"]
                    div.append(a)
                    div.append(p)
                    li.append(div)
                    pharmacyList.append(li)
                })
            })
            .catch(err => console.error(err))
    }

    const creatDetailH4 = (title, content) => {
        if (content === null) {
            return ""
        }

        const rootP = document.createElement('p')
        const div = document.createElement('div')
        const check = document.createElement('img')

        check.src = "${pageContext.request.contextPath}/resources/Check.svg"
        check.style.width = "1.4rem"
        check.style.height = "1.4rem"


        div.innerHTML = "<span>" + title + "</span>"
        div.style.display = "flex"
        div.style.alignItems = "center"

        div.prepend(check)
        rootP.append(div)

        const p = document.createElement('p')
        p.className
        p.innerText = content.replaceAll("<br>", " ").replaceAll("\n", " ")
        rootP.append(p)

        return rootP
    }

    const createMarker = (pharmacy) => {
        const marker = new naver.maps.Marker({
            position: new naver.maps.LatLng(parseFloat(pharmacy["wgs84Lat"]), parseFloat(pharmacy["wgs84Lon"])),
            title: pharmacy["dutyName"],
            map
        });
        markers.push(marker)
    }

    const handleDetailExtand = () => {
        const detailCollapse = document.getElementById('detail-collapse')
        const imgCollapseExtend = document.getElementById('img-collapse-extend')
        const imgCollapseReduce = document.getElementById('img-collapse-reduce')
        if (detailCollapse.style.display === "none") {
            detailCollapse.style.display = "block"
            imgCollapseExtend.style.display = "none"
            imgCollapseReduce.style.display = "inline-block"
        } else {
            detailCollapse.style.display = "none"
            imgCollapseExtend.style.display = "inline-block"
            imgCollapseReduce.style.display = "none"
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

    const handlePharmacyDetail = (dutyName) => {
        location.href = encodeURI("pharmacy.do?QN=" + dutyName)
    }

    const handleBookmark = (element, isBookmark) => {
        const productId = parseInt(element.getAttribute("value"))
        if (isBookmark === "true") {
            fetch('/bookmark/product.do?targetId=' + productId, {
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
        fetch('/bookmark/product.do?targetId=' + productId, {
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

    const deleteMarkers = () => {
        for (let i = 0; i < markers.length; i++) {
            markers[i].setMap(null);
        }
        markers.length = 0;
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

