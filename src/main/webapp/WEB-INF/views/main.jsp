<%@ page import="ssg.middlepj.pharmafinder.dto.ProductDto" %>
<%@ page import="ssg.middlepj.pharmafinder.dto.Pagination" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    String keyword = "";
    Pagination pagination = (Pagination) request.getAttribute("pagination");
    List<ProductDto> productList = (List<ProductDto>) request.getAttribute("products");
    if (pagination != null) {
        keyword = pagination.getKeyword();
    }
%>

<div>
    <div class="pt-4 pr-2" style="border: solid black; height: 100vh">
        <div class="pb-2" style="border-bottom: solid black;">
            <form>
                <div class="field has-addons">
                    <div class="control">
                        <div class="select">
                            <select>
                                <option>제조사</option>
                                <option>이름</option>
                                <option>효능</option>
                            </select>
                        </div>
                    </div>
                    <div class="control">
                        <input class="input" type="text" name="keyword" placeholder="검색할 단어 입력" value="<%=keyword%>">
                    </div>
                    <div class="control">
                        <button class="button is-info">
                            검색
                        </button>
                    </div>
                </div>
            </form>
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

    function handleSearch() {
        const search = document.querySelector('input[name="keyword"]').value;
        location.href = encodeURI("search.do?keyword=" + keyword);
    }
</script>