# 원티드 프리온보딩 사전과제 제출 | wanted-pre-onboarding
<br>
스프링과 스프링부트를 통해 개발된 애플리케이션 입니다.
<br><br><br>

### 지원자 이름 : 전윤호 <br>
<br><br>

### 애플리케이션 실행 방법 <br>
1. 해당 레포지토리를 git clone 합니다.
2. clone한 디렉토리로 들어가 터미널을 열고 ./gradlew bootRun 명령어를 입력하여 실행시킵니다.
3. 엔드포인트 <br>
포트번호 : 8080 <br>
유저 기능 : /sign-api/ <br>
게시글 기능 : /board-api/ <br>
ex> (POST) http://localhost:8080/sign-api/sign-in <br>

<br><br><br>

### 데이터베이스 테이블 구조 <br>
![image](https://github.com/yoon6763/wanted-pre-onboarding-backend/assets/74063259/d9c1ecf9-66cc-4a99-8a31-e4ffb4224223)

유저 테이블은 이메일과 비밀번호로 회원가입 및 로그인을 진행하며, 게시글은 유저 테이블과 1:n 구조를 가집니다.
<br><br><br>

### 구현 방법 및 이유에 대한 간략한 설명 <br>
기본적인 구조는 유지보수와 확장성을 위해 MVC 모델을 사용하였습니다.<br>
Entity는 데이터베이스의 테이블과 매핑되는 객체이며, 클라이언트와 서버 간 직접 데이터를 교환하는 것을 피하기 위해 DTO를 사용하였습니다.<br>
사용자에게 요청이 오면, 이를 Controller가 받아 Service를 호출합니다. <br>
Service는 비즈니스 로직을 처리하고, 결과값을 DTO 등에 담아 Controller에 넘겨준 뒤, <br>
Controller는 사용자에게 응답을 보냅니다.<br>
<br>

### API 명세서 <br>
<br>

### AWS 관련 자료 <br>
<br>
