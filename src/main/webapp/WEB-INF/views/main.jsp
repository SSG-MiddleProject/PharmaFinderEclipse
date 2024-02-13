<%@ page import="ssg.middlepj.pharmafinder.dto.ProductDto" %>
<%@ page import="ssg.middlepj.pharmafinder.dto.Pagination" %>
<%@ page import="ssg.middlepj.pharmafinder.util.PaginationUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.util.Properties" %>
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
        src="https://oapi.map.naver.com/openapi/v3/maps.js?ncpClientId=<%=NHN_CLIENT_KEY%>"></script>
<%
    String searchType = "";
    String keyword = "";
    Pagination pagination = (Pagination) request.getAttribute("pagination");
    Integer currentPage = pagination.getPaginationParam().getPage();
    Integer lastPage = pagination.getTotalPageCount();
    List<ProductDto> productList = (List<ProductDto>) request.getAttribute("products");
    searchType = pagination.getPaginationParam().getSearchType();
    keyword = pagination.getPaginationParam().getKeyword();
%>

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
            <% for (ProductDto product : productList) { %>
            <li class="p-2" style="border-top: solid 1px">
                <div class="has-text-black">
                    <a value="<%=product.getId()%>" onclick="handleDetail(this)">
                        <%-- 이름이 15자 이상이면 자르기 --%>
                        <%=product.getItemName().length() > 15 ? product.getItemName().substring(0, 15) + "..." : product.getItemName()%>
                        <span class="has-text-grey is-size-7"><%= product.getEntpName().length() > 15 ? product.getEntpName().substring(0, 15) + "..." : product.getEntpName()%></span>
                    </a>
                    <p>
                        &nbsp;<%=product.getEfcyQes().length() > 30 ? product.getEfcyQes().substring(0, 30) + "..." : product.getEfcyQes()%>
                    </p>
                </div>
            </li>
            <% } %>
        </ul>
        <div id="search-pagination">
            <ul id="pagination-list">
                <li onclick="handlePrev()"><</li>
                <%=PaginationUtil.CreatePaginationList(currentPage, pagination)%>
                <li onclick="handleNext()">></li>
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
    const existPrev = "<%=pagination.getExistPrev()%>";
    const existNext = "<%=pagination.getExistNext()%>";
    const currentPage = parseInt("<%=currentPage%>");
    const lastPage = parseInt("<%=lastPage%>");
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

    const handleProduct = async (id) => {
        const detailDiv = document.getElementById('detail')
        detailDiv.innerHTML = ""
        await fetch(`/product/detail.do?productId=` + id)
            .then(res => res.json())
            .then(data => {
                Object.entries(data).forEach(([key, value]) => {
                    if (key === 'itemImage') {
                        const img = document.createElement('img')
                        img.src = value
                        detailDiv.appendChild(img)
                        return
                    }
                    const div = document.createElement('div')
                    div.innerHTML = key + " : " + value + "<br>"
                    detailDiv.appendChild(div)
                })
            })
            .catch(err => console.error(err))
    }
</script>

