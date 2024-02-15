<%@page import="util.PharmaInOutCalendarUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String year = (String) request.getAttribute("year");
    String month = (String) request.getAttribute("month");
    String day = (String) request.getAttribute("day");

    String twoMonth = PharmaInOutCalendarUtil.two(month);
    String twoDay = PharmaInOutCalendarUtil.two(day);

    String formatedDate = year + "-" + twoMonth + "-" + twoDay;
%>

<style>
    /* 스타일링을 위한 CSS */
    #outwrite {
        height: 100%;

        display: flex;
        justify-content: center;
        itmes-align: center;
        padding: 10px;
    }

    hr {
        margin: 0
    }

    #searchModal {
        display: none;
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background: rgba(0, 0, 0, 0.5);
        justify-content: center;
        align-items: center;
        z-index: 1;
    }

    .modal-contents {
        background: #fff;
        padding: 20px;
        border-radius: 5px;
        max-width: 80%;
        max-height: 60%;
        overflow: auto;
    }

    .close {
        float: right;
        cursor: pointer;
    }

    .pharma-products-container {
        margin: auto;
        overflow: auto;
        height: 1000px;
    }

    .add-pharma-product {
        /*border: solid 2px black;*/
    }
</style>

<div id="outwrite">
    <div>
        <div style="height: 90%; width: 85vw">
            <p><%=formatedDate%>
            </p>
            <p class=" " style="padding-top: 20px; padding-bottom: 10px; font-size: x-large; font-weight: bold">
                출고 등록
            </p>
            <table class='table' style='width: 100%; text-align: center; '>
                <thead>
                <tr>
                    <th style="text-align: center;">제품코드</th>
                    <th style="text-align: center;">제품명</th>
                    <th style="text-align: center;">입고가</th>
                    <th style="text-align: center;" colspan="3">수량</th>
                    <th style="text-align: center;">삭제</th>
                </tr>
                </thead>

                <!-- 검색 모달창 START-->
                <div id="searchModal">
                    <!-- 실제창 -->
                    <div class="modal-contents">
                        <span class="close" onclick="closeSearchModal()">&times;</span>
                        <h2>검색</h2>
                        <input type="text" id="keyword" placeholder="검색어를 입력하세요"/>
                        <select id="searchType" class="form-control" style="width: auto">
                            <option value="id">제품코드</option>
                            <option value="itemName">제품명</option>
                        </select>
                        <button onclick="performSearch()">검색</button>
                        <div id="result"></div>
                    </div>
                    <!-- 실제창 END -->
                </div>

                <tbody id="pharmaProductAddList" class="pharma-tbody">
                <tr>
                    <td colspan="7" id="table-text">등록된 제품이 없습니다.</td>
                </tr>
                </tbody>
                <!-- 검색 모달창 -->
                <!-- 약국제품 추가창 START -->
                <%--                <div id="pharmaProductAddList"></div>--%>
            </table>
        </div>
        <button type="button" class="button is-light" onclick="openSearchModal()">추가</button>
        <button class="button is-light" type="button" style="align-self: end" onclick="submitPharmaProductIn()" >등록</button>
        <!-- 약국제품 추가창 END -->
    </div>
    <script>
        // JavaScript를 사용하여 모달창 열고 닫기
        function openSearchModal() {
            document.getElementById("searchModal").style.display = "flex";
            $("#result").html("");
        }

        function closeSearchModal() {
            document.getElementById("searchModal").style.display = "none";
        }

        function performSearch() {
            let searchType = $("#searchType").val();
            let keyword = $("#keyword").val();

            let productId = 0;
            let itemName = null;

            if (searchType === "id") {
                if (isNaN(keyword)) {
                    alert("제품코드이므로 숫자입력바람");
                    return;
                } else {
                    productId = keyword;
                }
            } else if (searchType === "itemName") {
                itemName = keyword;
            }
            $.ajax({
                url: "./select-specific-pharma-products.do",
                type: "get",
                data: {
                    productId: productId,
                    itemName: itemName
                },
                success: function (data) {
                    if (data != null) {
                        $("#result").html("");
                        let table = $(`<table class="table"></table>`);

                        $("#result").append(table);
                        let rowHeader = $(`<thead><tr><th style="text-align: center">제품코드</th><th style="text-align: center">제품명</th><th style="text-align: center">비고</th></tr></thead>`);

                        $("#result > table").append(rowHeader);
                        for (let i = 0; i < data.length; i++) {

                            let jsonStr = JSON.stringify(data[i]);
                            let newTr = $("<tr>");
                            let newProductIdTd = $("<td>"
                                + data[i].productId + "</td>");
                            let newItemNameTd = $("<td>"
                                + data[i].itemName + "</td>");
                            let newBtnTd = $("<td>");
                            let newBtn = $("<button class='button is-gray' type='selectionProduct(this)'>선택</button>");
                            $(newBtn).attr("data-object", jsonStr);
                            $(newBtn).attr("onclick",
                                "selectionProduct(this)");
                            newBtnTd.append(newBtn);

                            newTr.append(newProductIdTd);
                            newTr.append(newItemNameTd);
                            newTr.append(newBtnTd);

                            $("#result>table").append(newTr);
                        }
                    }
                },
                error: function () {
                    alert("error");
                },
            });
        }

        function selectionProduct(selectionProduct) {
            let jsonStr = $(selectionProduct).attr("data-object");
            let json = JSON.parse(jsonStr);

            let elements = document
                .getElementsByClassName("add-pharma-product");

            if (elements.length > 0) {
                let isduplicate = false;
                for (let i = 0; i < elements.length; i++) {
                    let element = elements[i];
                    let productId = $(element).find(".productId").text();

                    if (json.productId == productId) {
                        alert("이미 추가된 제품입니다.");
                        return;
                    }
                }
            }

            addPharmaProduct(jsonStr, $("#pharmaProductAddList"));
            closeSearchModal();
        }

        function addPharmaProduct(jsonStr, container) {
            let json = JSON.parse(jsonStr);
            let addIndex = $(".add-pharma-product").length;

            document.getElementById("table-text").style.display = "none";
            let tbody = $(".pharma-tbody");

            let productId = $("<td>");
            productId.text(json.productId);
            productId.append($("</td>"))

            let itemName = $("<td>");
            itemName.text(json.itemName);
            let inputPrice = $(`<td class="input-price" style="align-items: end">` + json.inputPrice + `원</td>`)

            let closeIcon = $(`<td onclick="doCloseIcon(this)"><i class="fas fa-times-circle "></i></td>`);

            let hr = $("<hr>")
            let br = $("<br>");
            let openDiv = $("<div style='display: flex; '>");

            let plusIcon = $(`<td onclick="doPlus(this)"><a class="fas fa-plus-circle"></a></td>`);
            let count = $("<td class='count'>1</td>");
            let minusIcon = $(`<td onclick="doMinus(this)"><a class="fas fa-minus-circle" ></a></td>`);


            // let plusIcon = $(`<td onclick="doPlus(this)" > + </td>`);
            // let count = $("<td class='count'>1</td>");
            // let minusIcon = $(`<td onclick="doMinus(this)""> - </td>`);


            tbody.append($("<tr>"));
            tbody.append(productId);
            tbody.append(itemName);
            tbody.append(inputPrice);
            tbody.append(plusIcon);
            tbody.append(count);
            tbody.append(minusIcon);
            tbody.append(closeIcon);
            tbody.append($("</tr>"));

            container.append(tbody);

        }

        function doCloseIcon(icon) {
            $(icon).parent().remove();
        }

        function doPlus(iconAnchor) {
            let countSpan = $(iconAnchor).parent().find(".count");
            let countNum = Number($(countSpan).text());
            countNum++;

            countSpan.text(countNum);
        }

        function doMinus(iconAnchor) {
            let countSpan = $(iconAnchor).parent().find(".count");
            let countNum = Number($(countSpan).text());

            if (countNum > 1) {
                countNum--;
                countSpan.text(countNum);
            }
        }

        function submitPharmaProductIn() {
            let elements = document.getElementsByClassName("add-pharma-product");

            let pharmaOutDto = {
                id: -1,
                storeId: -1,
                currentOutputDate: "<%= formatedDate%>",
                products: []
            }

            for (let i = 0; i < elements.length; i++) {
                let element = elements[i];
                let count = $(element).find(".count").text();
                let pharmaProductJson = JSON.parse($(element).attr("data-object"));
                let dailyOutputProduct = {
                    id: -1,
                    dailyOutputId: -1,
                    storeProductId: pharmaProductJson.id,
                    outputCnt: count
                }

                pharmaOutDto.products[i] = dailyOutputProduct;
            }

            // 500에러 방지를 위한 url 변환
            pharmaOutDto = serializeTwoLevelsNestedJson(pharmaOutDto);

            $.ajax({
                url: "pharma-out-writeAf.do",
                type: "get",
                data: pharmaOutDto,
                success: function (data) {
                    if (data.msg === "SUCCESS") {
                        alert("성공")

                        location.href = "/pharma-inout-calendar.do";
                    } else {
                        alert("실패");
                    }
                },
                error: function () {
                    alert("error");
                }
            })
        }

        // List를 가진 중첩 Dto에러 방지
        function serializeTwoLevelsNestedJson(json) {
            return Object.keys(json).map(function (key) {
                if (typeof json[key] === "object") {
                    if (Array.isArray(json[key])) {
                        return json[key].map((arrayItem, index) => {
                            return Object.keys(arrayItem).map(function (arrayItemKey) {
                                return encodeURIComponent(key) + encodeURIComponent('[') + index + encodeURIComponent('].') + encodeURIComponent(arrayItemKey) + '=' + encodeURIComponent(arrayItem[arrayItemKey]);
                            }).join('&')
                        }).join('&');
                    } else {
                        return Object.keys(json[key]).map(function (subJsonKey) {
                            return encodeURIComponent(key) + '.' + encodeURIComponent(subJsonKey) + '=' + encodeURIComponent(json[key][subJsonKey]);
                        }).join('&');
                    }
                } else {
                    return encodeURIComponent(key) + '=' + encodeURIComponent(json[key])
                }
            }).join('&');
        }
    </script>
</div>