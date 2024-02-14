<%@page import="util.PharmaInOutCalendarUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String year = (String)request.getAttribute("year");
	String month = (String)request.getAttribute("month");
	String day = (String)request.getAttribute("day");

	String twoMonth = PharmaInOutCalendarUtil.two(month);
	String twoDay = PharmaInOutCalendarUtil.two(day);

	String formatedDate = year + "-" + twoMonth + "-" + twoDay;
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Search Modal</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
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
	border: solid 2px black;
}
</style>
</head>
<body>
	<h1>출고추가</h1>
	<input type="date" id="date" value="<%=formatedDate %>" readonly />
	<button type="button" onclick="openSearchModal()">검색창 열기</button>
	<!-- 검색 모달창 START-->
	<div id="searchModal">
		<!-- 실제창 -->
		<div class="modal-contents">
			<span class="close" onclick="closeSearchModal()">&times;</span>
			<h2>검색</h2>
			<input type="text" id="keyword" placeholder="검색어를 입력하세요" />
			<select id="searchType" class="form-control" style="width: auto">
				<option value="id">제품코드</option>
				<option value="itemName">제품명</option>
			</select>
			<button onclick="performSearch()">검색</button>
			<div id="result"></div>
		</div>
		<!-- 실제창 END -->
	</div>
	<!-- 검색 모달창 -->
	<!-- 약국제품 추가창 START -->
	<div id="pharmaProductAddList"></div>
	<button type="button" onclick="submitPharmaProductIn()" >출고등록</button>
	<!-- 약국제품 추가창 END -->

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
				if(isNaN(keyword)){
					alert("제품코드이므로 숫자입력바람");
					return;
				}else{
					productId = keyword;
				}
			} else if (searchType === "itemName") {
				itemName = keyword;
			}
					$.ajax({
						url : "./select-specific-pharma-products.do",
						type : "get",
						data : {
							productId : productId,
							itemName : itemName
						},
						success : function(data) {
							if (data != null) {
								$("#result").html("");
								let table = $(`<table class="table"></table>`);

								$("#result").append(table);
								let rowHeader = $(`<thead><tr><th>제품코드</th><th>제품명</th><th>비고</th></tr></thead>`);

								$("#result > table").append(rowHeader);
								for (let i = 0; i < data.length; i++) {

									let jsonStr = JSON.stringify(data[i]);
									let newTr = $("<tr>");
									let newProductIdTd = $("<td>"
											+ data[i].productId + "</td>");
									let newItemNameTd = $("<td>"
											+ data[i].itemName + "</td>");
									let newBtnTd = $("<td>");
									let newBtn = $("<button type='selectionProduct(this)'>선택</button>");
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
						error : function() {
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
						alert("같은 제품 추가 불가");
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
			let productDiv = $("<div>");

			productDiv.attr("id", addIndex + "addPharmaProductContainer");
			productDiv.attr("class", "add-pharma-product");
			productDiv.attr("data-object", jsonStr);

			let productId = $("<span class='productId'>");
			productId.text(json.productId);

			let itemName = $("<span>");
			itemName.text(json.itemName);

			let closeIcon = $(`<a onclick="doCloseIcon(this)"><i class="far fa-times-circle"></i></a>`);

			let hr = $("<hr>")
			let plusIcon = $(`<a onclick="doPlus(this)"><i class="fas fa-plus-circle"></a>`);
			let count = $("<span class='count'>1<span>");
			let minusIcon = $(`<a onclick="doMinus(this)"><i class="fas fa-minus-circle"></a>`);

			let outputPrice = $(`<span class="input-price">`+ json.outputPrice + `</span>`)

			productDiv.append(productId);
			productDiv.append(itemName);
			productDiv.append(closeIcon);
			productDiv.append(hr);
			productDiv.append(plusIcon);
			productDiv.append(count);
			productDiv.append(minusIcon);
			productDiv.append(outputPrice);
			productDiv.append($("<span>원</span>"));

			container.append(productDiv);
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

		function submitPharmaProductIn(){
			let elements = document.getElementsByClassName("add-pharma-product");

			let pharmaOutDto = {
				id:-1,
				storeId:-1,
				currentOutputDate:"<%= formatedDate%>",
				products:[]
			}

			for (let i = 0; i < elements.length; i++) {
				let element = elements[i];
				let count = $(element).find(".count").text();
				let pharmaProductJson = JSON.parse($(element).attr("data-object"));
				let dailyOutputProduct = {
					id:-1,
					dailyOutputId:-1,
					storeProductId:pharmaProductJson.id,
					outputCnt:count
				}

				pharmaOutDto.products[i] = dailyOutputProduct;
			}

			// 500에러 방지를 위한 url 변환
			pharmaOutDto = serializeTwoLevelsNestedJson(pharmaOutDto);

			$.ajax({
				url:"pharma-out-writeAf.do",
				type:"get",
				data: pharmaOutDto,
				success:function(data){
					if(data.msg === "SUCCESS"){
						alert("성공")

						location.href = "/pharma-inout-calendar.do";
					}else{
						alert("실패");
					}
				},
				error:function(){
					alert("error");
				}
			})
		}

		// List를 가진 중첩 Dto에러 방지
		function serializeTwoLevelsNestedJson(json) {
		    return Object.keys(json).map(function(key) {
		        if (typeof json[key] === "object") {
		            if (Array.isArray(json[key])) {
		                return json[key].map((arrayItem, index) => {
		                    return Object.keys(arrayItem).map(function(arrayItemKey) {
		                        return encodeURIComponent(key) +encodeURIComponent('[') + index + encodeURIComponent('].') + encodeURIComponent(arrayItemKey) + '=' + encodeURIComponent(arrayItem[arrayItemKey]);
		                    }).join('&')
		                }).join('&');
		            } else {
		                return Object.keys(json[key]).map(function(subJsonKey) {
		                    return encodeURIComponent(key) +'.' + encodeURIComponent(subJsonKey)+ '=' + encodeURIComponent(json[key][subJsonKey]);
		                }).join('&');
		            }
		        }
		        else {
		            return encodeURIComponent(key) +'=' + encodeURIComponent(json[key])
		        }
		    }).join('&');
		}
	</script>
</body>
</html>
