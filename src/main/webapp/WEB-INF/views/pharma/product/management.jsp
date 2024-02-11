<%@page import="ssg.middlepj.pharmafinder.dto.PharmaProductWithProductDto" %>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
	List<PharmaProductWithProductDto> list = (List<PharmaProductWithProductDto>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Search Modal</title>
    <style>
        /* 스타일링을 위한 CSS */
        body {
            font-family: Arial, sans-serif;
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
            z-index:1;
        }

        .modal-contents {
            background: #fff;
            padding: 20px;
            border-radius: 5px;
            max-width:80%;
            max-height:60%;
            overflow:auto;
        }

        .close {
            float: right;
            cursor: pointer;
        }

        .pharma-products-container {
            margin: 0 auto;
            overflow: auto;
            height: 300px
        }
    </style>
</head>

<body>
    <!-- 리스트 구역 START -->
    <div class="pharma-products-container">
        <table class="table" id="pharma-products">
            <thead>
                <tr>
                    <td>id</td>
                    <td>제품코드</td>
                    <td>제품명</td>
                    <td>입고단가</td>
                    <td>출고단가</td>
                    <td>제품등록일</td>
                    <td>수정</td>
                </tr>
            </thead>
            <tbody id="pharma-product-tbody">
                <% if (list !=null && list.size()> 0) {
                    for (PharmaProductWithProductDto item : list) {
                    %>
                    <tr>
                        <td>
                            <%=item.getId()%>
                        </td>
                        <td>
                            <%=item.getProductId()%>
                        </td>
                        <td>
                            <%=item.getItemName()%>
                        </td>
                        <td>
                            <%=item.getInputPrice()%>
                        </td>
                        <td>
                            <%=item.getOutputPrice()%>
                        </td>
                        <td>
                            <%=item.getCreatedAt().substring(0, 10)%>
                        </td>
                        <td><button type="button"
                                onclick="updatePharmaProduct(<%=item.getId()%>)">수정</button></td>
                    </tr>
                    <% } } %>
            </tbody>
        </table>
    </div>
    <hr />
    <!-- 리스트 구역 END -->

    <!-- 검색 모달창 START-->
    <div id="searchModal">
    <!-- 실제창 -->
        <div class="modal-contents">
            <span class="close" onclick="closeSearchModal()">&times;</span>
            <h2>검색</h2>
            <input type="text" id="keyword" placeholder="검색어를 입력하세요"> <select id="searchType"
                class="form-control" style="width: auto;">
                <option value="id">제품코드</option>
                <option value="itemName">제품명</option>
            </select>
            <button onclick="performSearch()">검색</button>
            <div id="result"></div>
        </div>
        <!-- 실제창 END -->
    </div>
    <!-- 검색 모달창 -->

    <!-- 제품 추가창 -->
    <form>
    	<div class="field is-grouped">
    		<label for="insertId" class="label" style="width:20%;margin-right:2%">제품코드</label>
    		<label for="insertItemName" class="label" style="width:20%">제품명</label>
    	</div>
    	<div class="field is-grouped">
    		<input type="text"  name="id" readonly id="insertId" class="input" style="width:20%;margin-right:2%" />
    		<input type="text"  name="itemName" readonly id="insertItemName" class="input" style="width:20%;margin-right:2%"/>
    		<!-- 검색 버튼 클릭 시 검색 모달창 띄우기 -->
    		<button type="button"  class="button is-dark" onclick="openSearchModal()">검색창 열기</button>
    	</div>
    	<div class="field is-grouped">
    		<img src="" id="productImage">
    	</div>
        <div class="field is-grouped">
        	<label for="insertInputPrice" class="label">입고단가</label>
        </div>
        <div class="field is-grouped">
        	<input type="text" name="inputPrice" id="insertInputPrice" class="input" style="width:20%" />
        </div>
        <div class="field is-grouped">
        	<label for="insertOutputPrice" class="label" style="width:20%;margin-right:2%">출고단가</label>
        	<label for="insertVAT" class="label" style="width:20%" readonly>부가세</label>
        </div>
        <div class="field is-grouped">
        	<input type="text" name="outputPrice" id="insertOutputPrice" class="input" oninput="calVAT()" style="width:20%;margin-right:2%" />
        	<input type="text" id="insertVAT" class="input" style="width:20%" />
        </div>
        <div class="control">
        	<button type="button" class="button is-link" id="registerProduct" onclick="registerPharmaProduct()">제품등록</button>
        </div>
    </form>
    <!--  -->

    <script>
	const noImageUrl = "https://st4.depositphotos.com/14953852/22772/v/450/depositphotos_227725020-stock-illustration-image-available-icon-flat-vector.jpg";

          function calVAT() {
              let outputPrice = $("#insertOutputPrice").val();

              if (Number(outputPrice) === NaN) {
                  return;
              }

              let supplyValue = Math.floor(Number(outputPrice) / 1.1);

              let vat = outputPrice - supplyValue;

              $("#insertVAT").val(vat);
          }
          // JavaScript를 사용하여 모달창 열고 닫기
          function openSearchModal() {
              document.getElementById('searchModal').style.display = 'flex';
              $("#result").html("");
          }

          function closeSearchModal() {
              document.getElementById('searchModal').style.display = 'none';
          }

          // 실제 검색 로직은 여기에 추가
          function performSearch() {
              $.ajax({
                  url: "./pharma-product-common-search.do",
                  type: "get",
                  data: { searchType: $("#searchType").val(), keyword: $("#keyword").val() },
                  success: function (data) {
                      if (data != null) {
                          $("#result").html("");
                          let table = $(`<table class="table"></table>`);

                          $("#result").append(table);
                          let rowHeader = $(`<thead><tr><th>제품코드</th><th>제품명</th><th>비고</th></tr></thead>`);

                        $("#result > table").append(rowHeader);
                        for (let i = 0; i < data.length; i++) {
                            let newElement = $(
                                "<tr>" +
                                	"<td>" + data[i].id + "</td>" +
                                	"<td>" + data[i].itemName + "</td>" +
                                	`<td>
										<button onclick="selectionProduct(this)" type='button'
											data-object='{"id":"`+ data[i].id + `", "itemName":"` + data[i].itemName + `", "itemImage":"` + data[i].itemImage + `"}'>
											선택
										</button>
									</td>
								</tr>`);
                            $("#result>table").append(newElement)
                        }
                    }
                },
                error: function () {
                    alert("error");
                }
            });
        }
        function registerPharmaProduct() {
            let id = $("#insertId").val();
            let itemName = $("#insertItemName").val();
            let insertInputPrice = $("#insertInputPrice").val();
            let insertOutputPrice = $("#insertOutputPrice").val();
            if (id == '') {
                alert("제품코드를 입력해 주십시오");
                return;
            }
            // validation 검사
            if (isNaN(insertOutputPrice.trim())) {
                alert("입고단가에 올바른 입력값을 입력해주세요.");
                return;
            }
            // validation 검사
            if (isNaN(insertInputPrice.trim())) {
                alert("출고단가에 올바른 입력값을 입력해주세요.");
                return;
            }

            // ajax 송신
            $.ajax({
                url: "./pharma-product-register.do",
                type: "get",
                data: {
                    "storeId": <%=request.getSession().getAttribute("storeId") %>,
                    "productId": $("#insertId").val(),
                    "inputPrice": $("#insertInputPrice").val(),
                    "outputPrice": $("#insertOutputPrice").val(),
                },
                success: function (data) {
                    if (data.msg === 'SUCCESS') {
                        alert("추가완료");
                        refreshPharmaProduct(true);
                        resetinsertInputs();
                    } else if (data.msg === 'FAIL') {
                        alert(data.errorMsg);
                    }
                },
                error: function () {
                    alert("error");
                },
            });
        }
        function selectionProduct(target) {
            let jsonObj = JSON.parse(target.getAttribute("data-object"));
            let id = jsonObj.id;
            let itemName = jsonObj.itemName;
            let itemImage = jsonObj.itemImage;

            $("#insertId").val(id);
            $("#insertItemName").val(itemName);

            // null or undefined
			if(itemImage === "null"){
	            $("#productImage").attr("src", noImageUrl);
	            $("#productImage").attr("width", "200px");
				$("#productImage").attr("height", "200px");
			}else{
				$("#productImage").attr("src", itemImage);
				$("#productImage").attr("width", "200px");
				$("#productImage").attr("height", "200px");
			}

            closeSearchModal();
        }
        function refreshPharmaProduct(isSelectLast) {
            $.ajax({
                url: "./select-pharma-products.do",
                type: "get",
                success: function (data) {
                    refreshTable(data, isSelectLast);
                },
                error: function () {
                    alert("error");
                },
            });
        }
        function refreshTable(tableDataList, isSelectLast) {
            let tbody = $("#pharma-product-tbody");
            tbody.html("");

            if (tableDataList == null || tableDataList.length == 0) {
                return;
            }

            tableDataList.forEach((item, index) => {
                let row;
                if (index === tableDataList.length - 1 && isSelectLast) {
                    row = $("<tr class='is-selected'></tr>");
                } else {
                    row = $("<tr></tr>");
                }
                let id = $("<td>" + item.id + "</td>");
                let productId = $("<td>" + item.productId + "</td>");
                let itemName = $("<td>" + item.itemName + "</td>");
                let inputPrice = $("<td>" + item.inputPrice + "</td>");
                let outputPrice = $("<td>" + item.outputPrice + "</td>");
                let createdAt = $("<td>" + item.createdAt.substring(0, 10) + "</td>");
                let btnDelete = $("<td><button type='button' onclick='updatePharmaProduct(" + item.id + ")' >수정</button></td>");
                row.append(id);
                row.append(productId);
                row.append(itemName);
                row.append(inputPrice);
                row.append(outputPrice);
                row.append(createdAt);
                row.append(btnDelete);
                tbody.append(row);
            });
        }
        function resetinsertInputs() {
            $("#insertId").val("");
            $("#insertItemName").val("");
            $("#insertInputPrice").val("");
            $("#insertOutputPrice").val("");
            $("#insertVAT").val("");
            $("#productImage").attr("src","");
            $("#productImage").removeAttr("width");
            $("#productImage").removeAttr("height");
        }
        function updatePharmaProduct(id) {
            location.href = "./pharma-product-update.do?id=" + id;
        }
    </script>

</body>

</html>