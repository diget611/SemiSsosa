# SemiSsosa

### 개발목표
- 커뮤니티의 게시판 기능에 대한 DB 설계 및 기능 구현 목표

### 개발기간
- 2023년 02월 09일 ~ 2023년 02월 23일

### 개발환경
- OS : Microsoft Window 10
- Language : Java 11, Servlet/JSP, HTML5, CSS3, JavaScript
- Web Application Server : Apache Tomcat 9
- DataBase : Oracle 21c
- Framework, Open API : Bootstrap5, jQuery, CKeditor
- Development Tool : SQL Developer 17.2.0, VSCODE
- ER Diagram Tool : ERD Cloud
- Version Management System : Git (Github Desktop)

### ERD
![Semi](https://github.com/diget611/SemiSsosa/assets/115057175/322d5c03-0b72-478f-8038-6d9242bb83a0)

### 구현기능
![메인](https://github.com/diget611/SemiSsosa/assets/115057175/9b909c00-2491-4555-87cb-8e6be290101c)
메인페이지
- 좌측상단 TITLE을 클릭함으로써 메인페이지로 이동 가능
- 각 게시판별 게시글 최신순으로 5개씩 출력
- 로그인시 우측상단 버튼 마이페이지 / 로그아웃 버튼으로 변경
- 우측상단 버튼 클릭으로 각 페이지로 이동 가능

![로그인](https://github.com/diget611/SemiSsosa/assets/115057175/d6e0c387-06f4-4008-8461-3bb0f3d8bb37)
로그인 / 회원가입
- DB의 아이디/패스워드와 일치하는 정보 입력 시 로그인 가능
- 로그인 성공 시 메인페이지로 이동하며 좌측 상단 버튼 변경
- 로그인한 회원의 경우만 게시글 작성/수정/삭제, 댓글 작성, 추천/비추천 기능 사용 가능
- 회원가입 버튼 클릭시 회원가입 페이지로 이동
- 각 입력창에 입력한 값에 따라 아이디 / 패스워드 유효성 확인 가능
- 회원가입 완료 시 메인페이지로 이동

![마이페이지](https://github.com/diget611/SemiSsosa/assets/115057175/b386f087-2adc-4b89-8e6a-0bfa276d92c7)
마이페이지
- DB의 패스워드와 일치하는 패스워드 입력 후 수정버튼 클릭시 회원정보 수정 가능
- 일치하지 않는 패스워드 입력시 Alert창 출력

![게시판](https://github.com/diget611/Chazazo/assets/115057175/54a4c5e7-5aba-4108-ad03-d0c64f60e79e)
게시판
- 로그인하지 않았을 경우 각 제목을 클릭함으로써 게시글의 상세 내용을 확인할 수 있음
- 글쓰기 버튼은 로그인 했을 경우에만 사용 가능하며 클릭 시 작성 페이지로 이동하여 게시글 작성 가능
- 게시글 클릭 시 조회수 증가 기능 구현

![게시글](https://github.com/diget611/SemiSsosa/assets/115057175/e727dd8a-ace1-4f7d-bedc-b2df9c6c7521)
게시판
- 로그인하지 않았을 경우 게시글의 내용과 댓글을 조회하기만 가능
- 작성자와 로그인한 회원이 동일한 경우에 게시글 수정 / 삭제 가능
- 추천/비추천은 해당 게시글을 추천/비추천하지 않았을 때 버튼 클릭시 각각 +1 / -1 로 값이 변경되며,
- 추천/비추천을 했던 게시글이라면 값을 0으로 변경함
- 댓글 작성 시 일반적으로 댓글이 작성되며 댓글에 대댓글을 작성할 경우
- '└' 표시를 통해 해당 댓글이 댓글의 대댓글인지 아닌지 확인 가능하도록 작성됨
