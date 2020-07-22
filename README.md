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

# 개발 환경
* OS : Windows 10 Pro
* IDE : Eclipse 2020-03
* DB : H2 Database

# Version
* Java : 1.8
* Spring Boot : 2.3.1.RELEASE
* Swagger : 2.9.2

# H2 Database
* 간편한 테스트를 위해 H2 Database를 사용
* Console 접속 : http://localhost:8080/console
* Password는 설정되어 있지 않기 때문에 바로 접속 가능
* console 접속 시 오류가 발생하면 이미지의 값과 비교
* JDBC URL : **jdbc:h2:mem:testdb**

![K-001](https://user-images.githubusercontent.com/49360550/88150925-fcd2d100-cc3c-11ea-8613-b06fcdffff61.jpg)

![K-002](https://user-images.githubusercontent.com/49360550/88150928-fe03fe00-cc3c-11ea-94e8-6643663e39a0.jpg)

# 구동
* IDE를 사용하여 프로젝트를 Import 후 구동 하거나 WAR File로 Packaging 후 구동
* Executable Jar로 Packaging 되기 때문에 Java 명령어로 실행 가능 (**Java가 설치되어 있어야 됨**)
* WAR File 이름은 sg-board.war
* 명령어 : java -jar /경로/sg-board.war

![K-003](https://user-images.githubusercontent.com/49360550/88150929-fe9c9480-cc3c-11ea-8c15-83c9fab26268.jpg)

![K-004](https://user-images.githubusercontent.com/49360550/88150931-ff352b00-cc3c-11ea-9f3c-b11507e5a676.jpg)

![K-005](https://user-images.githubusercontent.com/49360550/88150932-ff352b00-cc3c-11ea-85e0-945516361a57.jpg)
