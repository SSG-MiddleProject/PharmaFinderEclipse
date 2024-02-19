# PharmaFinder

### 프로젝트 소개

약국 정보와 복약 정보를 쉽고 편리하게 접근할 수 있도록 하여 약국 서비스를 효과적으로 이용
다양한 지역의 약국 정보를 제공함으로써, 사용자들이 자신에게 필요한 약국을 빠르고 쉽게 검색 가능

### 개발 스택
BE : Spring Framework
FE : JSP, JQuery, Bulma

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
