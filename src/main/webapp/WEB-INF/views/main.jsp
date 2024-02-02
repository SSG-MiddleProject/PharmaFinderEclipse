<%@ page import="ssg.middlepj.pharmafinder.dto.ProductDto" %>
<%@ page import="ssg.middlepj.pharmafinder.dto.Pagination" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    String searchType = "";
    String keyword = "";
    Pagination pagination = (Pagination) request.getAttribute("pagination");
    List<ProductDto> productList = (List<ProductDto>) request.getAttribute("products");
    if (pagination != null) {
        searchType = pagination.getSearchType();
        keyword = pagination.getKeyword();
    }
%>

<div>
    <div class="pt-4 pr-2" style="height: 100vh">
        <div class="pb-2 mx-4" style="border-bottom: solid 1px">
            <div class="field has-addons">
                <div class="control">
                    <div class="select">
                        <select id="searchType" value="<%=searchType%>">
                            <option value="entpName">제조사</option>
                            <option value="itemName">이름</option>
                            <option value="efcyQes">효능</option>
                        </select>
                    </div>
                </div>
                <div class="control">
                    <input class="input" type="text" name="keyword" placeholder="검색할 단어 입력" value="<%=keyword%>">
                </div>
                <div class="control">
                    <button class="button is-info" onclick="handleSearch()">
                        검색
                    </button>
                </div>
            </div>
        </div>
        <div class="">
            <%--    search result    --%>
            <ul>
                <% for (ProductDto product : productList) { %>
                <li>
                    <p><%=product.getItemName()%>
                    </p>
                </li>
                <% } %>
            </ul>
        </div>
    </div>
    <div>
        Map
    </div>
</div>

<script>
    let keyword = "<%=keyword%>"
    let choice = document.getElementById("searchType");
    choice.value = "<%=searchType %>";
    choice.selected = true;

    function handleSearch() {
        const searchType = document.querySelector('select').value;
        const keyword = document.querySelector('input[name="keyword"]').value;
        location.href = encodeURI("main.do?searchType=" + searchType + "&keyword=" + keyword);
    }
</script>