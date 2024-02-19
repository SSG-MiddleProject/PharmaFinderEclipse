# PharmaFinder

### 프로젝트 소개
- 약국 정보와 복약 정보를 쉽고 편리하게 접근할 수 있도록 하여 약국 서비스를 효과적으로 이용
- 다양한 지역의 약국 정보를 제공함으로써, 사용자들이 자신에게 필요한 약국을 빠르고 쉽게 검색 가능

### 개발 스택
- BE : Spring Framework
- FE : JSP, JQuery, Bulma

### 팀원

<img width="555" alt="스크린샷 2024-02-19 10 19 55" src="https://github.com/SSG-MiddleProject/PharmaFinderEclipse/assets/122031650/cf3614c7-b168-40a0-bfa7-ab77ac0a14e5">

### 순서도

<img width="1057" alt="image" src="https://github.com/SSG-MiddleProject/PharmaFinderEclipse/assets/122031650/65135051-346f-4bea-b55d-1faf75164e85">

### DB 모델링

<img width="966" alt="image" src="https://github.com/SSG-MiddleProject/PharmaFinderEclipse/assets/122031650/b24094a5-1edc-4398-91bf-e62fa9f250af">

### 스토리보드

<img width="1034" alt="image" src="https://github.com/SSG-MiddleProject/PharmaFinderEclipse/assets/122031650/4b02c3d8-850f-49c9-8323-7e28e8640b7e">

### 화면 구성

#### 1. 로그인

<img width="643" alt="스크린샷 2024-02-19 10 21 02" src="https://github.com/SSG-MiddleProject/PharmaFinderEclipse/assets/122031650/7a9cd8f6-5066-4b0c-8b04-191ecc7567f9">

- 로그인 기능 제공
- 아이디/비밀번호 찾기 기능 제공

<img width="636" alt="스크린샷 2024-02-19 10 23 08" src="https://github.com/SSG-MiddleProject/PharmaFinderEclipse/assets/122031650/1e8d10ea-83d4-451c-b8b6-c0ff019e8154">

- 비밀번호 변경시 가입한 이메일로 임시 비밀번호 전송(스프링 `JavaMailSender` 인터페이스)
- 신규 비밀번호 변경시 암호화 프로세스 연결

#### 2. 회원가입

<img width="725" alt="스크린샷 2024-02-19 10 26 07" src="https://github.com/SSG-MiddleProject/PharmaFinderEclipse/assets/122031650/41875e5e-fd75-41d0-96ee-eb529976ee8f">

- 회원 유형에 따라 회원가입 폼 구분(일반유저/약국유저)
- 비밀번호 암호화: `encryptStringBySHA256` 메서드 사용
- 입력값 유효성 검증, 중복체크 기능

  <img width="431" alt="스크린샷 2024-02-19 10 26 48" src="https://github.com/SSG-MiddleProject/PharmaFinderEclipse/assets/122031650/286e570f-055c-4d4b-80c4-67992e573c8b">

- 우편번호, 도로명주소 불러오기 : daumcdn postcode api 연결
- 위도, 경도 불러오기 : kakao maps api 연결

#### 3. 제품 검색

<img width="443" alt="스크린샷 2024-02-19 10 27 49" src="https://github.com/SSG-MiddleProject/PharmaFinderEclipse/assets/122031650/847fa806-0248-4c86-ac26-326c23877ad4">

- 의약품 개요정보 API 파싱 후 DB 저장
- 효능/이름/제조사 별 검색
- 약 상세 정보 확인
- Geolocation API를 사용해 사용자의 위치 정보를 받아오고 네이버지도 api를 사용해 지도에 주변에 재고가 있는 약국 표시
- 북마크 기능 제공

#### 4. 약국 검색

<img width="452" alt="스크린샷 2024-02-19 10 28 40" src="https://github.com/SSG-MiddleProject/PharmaFinderEclipse/assets/122031650/2e29aa5d-e6e1-4604-b1c6-1093aa7a612b">

- 전국 약국 정보 조회 API 파싱 후 DB 저장
- 약국 검색 및 약국 상세 정보 확인
- 약국 리스트 및 약국 해당 약국에 있는 약 재고와 단가 확인
- 북마크 기능 제공

#### 5. 제품관리

<img width="794" alt="image" src="https://github.com/SSG-MiddleProject/PharmaFinderEclipse/assets/122031650/6253667c-e32f-4ef1-8cc6-e2828210b8b2">

- 판매할 약의 입고단가와 출고단가를 등록
- 등록된 제품의 입출고 가격은 변경

#### 6. 입출고 내역

<img width="786" alt="image" src="https://github.com/SSG-MiddleProject/PharmaFinderEclipse/assets/122031650/4f8d8edc-b01c-4561-9f5d-6ce12bd8e141">

- 해당 날짜에 입고/출고한 약과 수량을 기록
- 기록한 입고/출고 내역을 달력화면으로 조회

#### 7. 재고조회

<img width="751" alt="image" src="https://github.com/SSG-MiddleProject/PharmaFinderEclipse/assets/122031650/4f43f0aa-f419-4f27-af4f-9c9f603c940e">


- 날짜별 약국의 재고현황을 확인
- 제품명/제품코드로 재고정보를 검색


