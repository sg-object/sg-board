# sg-board

# 주요 기능
* Spring Data Jpa 및 Thymeleaf를 활용한 게시판
* Naver 로그인 연동

# 참고 사항 (Naver)
* Naver 로그인 연동 시 발급받은 access_token을 이용해 로그인한 사용자 정보를 가져옴
* Response로 받은 사용자 정보는 이메일, 이름, 별명이며 아이디는 포함되어 있지 않음 (대신 계정 고유 일련번호를 받음)
* Naver 로그인의 경우 게시물 등록 및 수정 시 아이디 대신 계정 고유 일련번호를 저장함

# 참고 사항 (Application)
* Application 구동 시 200개의 게시판 글이 등록됨
* BoardJpaService Bean 생성 시 init method 호출
* Path : **프로젝트/src/main/java/com/sg/assignment/board/service/impl/BoardJpaService.java**

# Version
* Java : 1.8
* Spring Boot : 2.3.1.RELEASE
* Swagger : 2.9.2

# H2 Database
* 간편한 테스트를 위해 H2 Database를 사용
* Console 접속 : http://localhost:8080/console
* Password는 설정되어 있지 않기 때문에 바로 접속 가능

# 구동
* IDE를 사용하여 프로젝트를 Import 후 구동 하거나 WAR File로 Packaging 후 구동
* Executable Jar로 Packaging 되기 때문에 Java 명령어로 실행 가능 (**Java가 설치되어 있어야 됨**)
* WAR File 이름은 sg-board.war
* 명령어 : java -jar /경로/sg-board.war
