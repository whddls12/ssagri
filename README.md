# 🌊 SSAGRI

- SSAFY 9기 2학기 특화프로젝트
- 2023.08.28 ~ 2023.10.06 (6주)

![Imgur](https://i.imgur.com/B9kthsD.png)   

<img src="https://i.imgur.com/VhzQTy3.jpg" width="500" />

### 🎬 [UCC](https://youtu.be/4Hb1UZvzxzc)

# 📌목차
- [소개](#-소개)
- [주요기능](#-주요기능)
- [사용기술](#-사용기술)
- [프로젝트 파일 구조](#-프로젝트-파일-구조)
- [개발과정](#-개발과정)
- [팀원소개](#-팀원소개)


# ✨ 소개
SSAFY에서 1년 동안 교육받으며 컴퓨터 관련 장비들을 많이 사용합니다.   
교육 이후 장비를 처리하고 싶어하는 선배기수와,    
교육을 시작하며 장비를 구매하고 싶어하는 후배기수 사이에 거래를 연결시켜주고자   
SSAFY 교육생들을 위한 IT 물품 중고거래 서비스를 기획하였습니다.


# 💻 주요기능

## 1. 메인화면
<img src="/exec/scenario/project-scenario/mainScroll.gif" width="800"/>
   
- 첫 접속 시, 화면을 통해 SSAGRI의 간단한 서비스들을 확인할 수 있습니다.
- Matter.Js를 제외한 다른 라이브러리 사용없이 순수 자바스크립트로만 페이지를 구성했습니다. 

## 2. 중고 거래
<img src="/exec/scenario/project-scenario/tradeMain.png" width="800"/>   

- 삼성 SW 아카데미가 위치한 서울, 대전, 구미, 광주, 부울경 5개 지역 별로 중고물품을 확인합니다.
- 방금 등록된 물품과 실시간 인기 물품을 확인할 수 있습니다.

<img src="/exec/scenario/project-scenario/tradeDetail.gif" width="800"/>   

- 하나의 물품을 상세조회했을 때 사진, 판매자, 상세 설명을 볼 수 있습니다.
- "찜하기" 버튼을 통해 찜할 수 있습니다.
- "구매 채팅하기" 버튼을 통해 판매자와 대화를 시작할 수 있습니다.

<img src="/exec/scenario/project-scenario/tradeChatting.gif" width="800"/>   

- 사용자 본인의 채팅 목록을 확인할 수 있으며, 채팅방마다 대화를 이어갈 수 있습니다.
   
## 3. 경매
<img src="/exec/scenario/project-scenario/auctionMain.gif" width="800"/>   

- 모니터, 키보드, 마우스 등 카테고리별로 물품들을 볼 수 있습니다.

<img src="/exec/scenario/project-scenario/auctionBidding.gif" width="800"/>   

- 상품을 클릭하면 상품의 상세정보, 시작가, 정가, 현재 최고가를 볼 수 있습니다.
- 입찰을 누르면 입찰 리스트에 기록됩니다.
- 입찰 시간이 마감되고 최고가로 입찰 한 사람과 채팅으로 이어지는 버튼이 생성됩니다.

<img src="/exec/scenario/project-scenario/auctionAlarm.png" width="800"/>   

- 내가 입찰한 금액보다 높은 금액으로 입찰이 진행될 경우, 상회입찰 알림이 보여집니다.


## 4. 생명주기 커뮤니티 서비스
<img src="/exec/scenario/project-scenario/community.gif" width="800"/>
   
- 각각의 커뮤니티에는 기본적으로 7일의 생명주기가 주어집니다.
- 인기가 가장 많은 게시판의 원 모양이 가장 큽니다.
- 클릭 수, 게시글 수, 댓글 수에 따라 게시판 생명주기가 늘어납니다.


# 🛠 사용기술

<img src="https://i.imgur.com/H328xbg.png" width="800" />

### Backend


<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">&nbsp;<img src="https://img.shields.io/badge/springboot-6DB33F47A248?style=for-the-badge&logo=springboot&logoColor=white">&nbsp;<img src="https://img.shields.io/badge/springsecurity-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white">&nbsp;<img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">&nbsp;<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">&nbsp;<img src="https://img.shields.io/badge/mongoDB-47A248?style=for-the-badge&logo=mongoDB&logoColor=white">&nbsp;
<img src="https://img.shields.io/badge/redis-FE2E2E?style=for-the-badge&logo=redis&logoColor=white">

- Java : Oracle OpenJDK 11
- SpringBoot 2.7.15
- Spring Security 
- Spring Data Jpa 
- queryDSL 5.0.0
- Gradle 7.6.1
- MySQL latest
- MongoDB latest
- Redis latest

### FrontEnd


<img src="https://img.shields.io/badge/React-61DAFB?style=for-the-badge&logo=react&logoColor=black">&nbsp;<img src="https://img.shields.io/badge/Redux-764ABC?style=for-the-badge&logo=redux&logoColor=white">&nbsp;<img src="https://img.shields.io/badge/styled components-DB7093?style=for-the-badge&logo=styledcomponents&logoColor=white">&nbsp;<img src="https://img.shields.io/badge/axios-5A29E4?style=for-the-badge&logo=axios&logoColor=white">&nbsp;<img src="https://img.shields.io/badge/node.js-339933?style=for-the-badge&logo=nodedotjs&logoColor=white">&nbsp;<img src="https://img.shields.io/badge/typescript-3178C6?style=for-the-badge&logo=typescript&logoColor=white">&nbsp;
- React 18.2.0
- Node.js 16.16.0
- TypeScript 5.0.4
- Redux 8.0.5
- Redux-toolkit 1.9.4
- Redux-persist 6.0.0
- Styled-component 5.3.9
- Axios 1.3.5

### CI/CD


<img src="https://img.shields.io/badge/aws ec2-FF9900?style=for-the-badge&logo=amazonec2&logoColor=white">&nbsp;<img src="https://img.shields.io/badge/ubuntu-E95420?style=for-the-badge&logo=ubuntu&logoColor=white">&nbsp;<img src="https://img.shields.io/badge/Jenkins-D24939?style=for-the-badge&logo=Jenkins&logoColor=white">&nbsp;<img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=Docker&logoColor=white">&nbsp;<img src="https://img.shields.io/badge/Nginx-009639?style=for-the-badge&logo=nginx&logoColor=white">&nbsp;<img src="https://img.shields.io/badge/openssl-721412?style=for-the-badge&logo=openssl&logoColor=white">&nbsp;
- AWS EC2
- Ubuntu 20.04 LTS
- Jenkins 2.414.3
- Docker Engine 24.0.5
- Nginx 1.18.0
- SSL

<br>

### 협업 툴


<img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">&nbsp;<img src="https://img.shields.io/badge/jira-0052CC?style=for-the-badge&logo=jirasoftware&logoColor=white">&nbsp;<img src="https://img.shields.io/badge/mattermost-0058CC?style=for-the-badge&logo=mattermost&logoColor=white">&nbsp;<img src="https://img.shields.io/badge/notion-000000?style=for-the-badge&logo=notion&logoColor=white">&nbsp;<img src="https://img.shields.io/badge/figma-EA4335?style=for-the-badge&logo=figma&logoColor=white">&nbsp;
- 형상 관리 : Git
- 이슈 관리 : Jira
- 커뮤니케이션 : Mattermost, Notion
- 디자인 : Figma


# 🗂 프로젝트 파일 구조

### Backend

```
📦main
 ┣ 📂java
 ┃ ┗ 📂com
 ┃ ┃ ┗ 📂ssafy
 ┃ ┃ ┃ ┗ 📂ssagri
 ┃ ┃ ┃ ┃ ┣ 📂config
 ┃ ┃ ┃ ┃ ┣ 📂domain
 ┃ ┃ ┃ ┃ ┃ ┣ 📂auction
 ┃ ┃ ┃ ┃ ┃ ┣ 📂auctionbid
 ┃ ┃ ┃ ┃ ┃ ┣ 📂board
 ┃ ┃ ┃ ┃ ┃ ┣ 📂chatroom
 ┃ ┃ ┃ ┃ ┃ ┣ 📂message
 ┃ ┃ ┃ ┃ ┃ ┣ 📂notification
 ┃ ┃ ┃ ┃ ┃ ┣ 📂redis
 ┃ ┃ ┃ ┃ ┃ ┣ 📂S3
 ┃ ┃ ┃ ┃ ┃ ┣ 📂usedproduct
 ┃ ┃ ┃ ┃ ┃ ┣ 📂usedproductlike
 ┃ ┃ ┃ ┃ ┃ ┣ 📂usedproductphoto
 ┃ ┃ ┃ ┃ ┃ ┗ 📂user
 ┃ ┃ ┃ ┃ ┃ ┣ 📂etc
 ┃ ┃ ┃ ┃ ┃ ┣ 📂mail
 ┃ ┃ ┃ ┃ ┣ 📂entity
 ┃ ┃ ┃ ┃ ┃ ┣ 📂auction
 ┃ ┃ ┃ ┃ ┃ ┣ 📂board
 ┃ ┃ ┃ ┃ ┃ ┣ 📂chat
 ┃ ┃ ┃ ┃ ┃ ┣ 📂comment
 ┃ ┃ ┃ ┃ ┃ ┣ 📂common
 ┃ ┃ ┃ ┃ ┃ ┣ 📂email
 ┃ ┃ ┃ ┃ ┃ ┣ 📂usedproduct
 ┃ ┃ ┃ ┃ ┃ ┗ 📂user
 ┃ ┃ ┃ ┃ ┣ 📂util
 ┃ ┃ ┃ ┃ ┃ ┣ 📂etc
 ┃ ┃ ┃ ┃ ┃ ┣ 📂exception
 ┃ ┃ ┃ ┃ ┃ ┣ 📂jwt
 ┃ ┃ ┃ ┃ ┃ ┣ 📂mail
 ┃ ┃ ┃ ┃ ┃ ┣ 📂oauth
 ┃ ┃ ┃ ┃ ┃ ┣ 📂s3upload
 ┗ 📂resources
 ┃ ┣ 📜application-secret.properties
 ┃ ┗ 📜application.properties

```

### FrontEnd

```
📦src
 ┣ 📂assets
 ┣ 📂components
 ┃ ┣ 📂auctionStyle
 ┃ ┣ 📂communityStyle
 ┃ ┣ 📂mainStyle
 ┃ ┣ 📂tradeStyle
 ┣ 📂pages
 ┃ ┣ 📂auctionPage
 ┃ ┣ 📂communityPage
 ┃ ┣ 📂mainPage
 ┃ ┣ 📂tradePage
 ┣ 📂recoil
 ┃ ┗ 📂atoms
 ┣ 📂states
 ┃ ┗ 📂account
 ┣ 📂utils
 ┣ 📜App.css
 ┣ 📜App.tsx
 ┣ 📜ckeditor.ts
 ┣ 📜index.css
 ┣ 📜main.tsx
 ┣ 📜server.ts
 ┣ 📜svg.d.ts
 ┗ 📜vite-env.d.ts
```

# 📋 개발과정

- [ERD](https://www.erdcloud.com/d/6MNLWHTebijmyPxpE)
- [와이어프레임](https://www.figma.com/file/DL5Qh66SOt80ehDzSqqS35/%ED%8A%B9%ED%99%94-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8?type=design&node-id=0-1&mode=design&t=SlrwvSMX9HAsiX06-0)


# 👩‍💻 팀원소개

| 신창학            | 석정원 | 송병훈   | 유태영  | 유혜빈| 황종인  |
| :--- | :--- | :--- | :--- | :--- | :--- |
| <img src="https://github.com/handaldog/SSAGRI/assets/96431408/e4e4d12e-0d1f-4bf2-9f0c-acc612d7f84a" width="130" height="180">|<img src="https://github.com/Joungwon/Joungwon/assets/122511462/f6f52f64-3993-4c3c-a971-659e32e50804" width="130" height="180">|<img src="https://i.imgur.com/OOQVpo4.jpg" width="130" height="180">|<img src="https://github.com/handaldog/SSAGRI/assets/96431408/6128ba53-9dfc-46a9-9e0a-79007eeee363" width="130" height="180">|<img src="https://github.com/handaldog/C-YES/assets/96431408/efaaad7e-09b2-48d6-a790-88af70e42965" width="130" height="180">|<img src="https://github.com/whddls12/whddls12.github.io/assets/122436491/7b597c00-a1d6-4472-9965-e43d64d17839" width="130" height="180">|
| Leader & Backend | Frontend | Fullstack | Backend | Infra &Backend | Frontend |

- 신창학: 회원가입, 로그인, 보안
- 석정원: 메인페이지 로직, 커뮤니티 설계
- 송병훈: 중고거래/채팅 화면 디자인 및 개발, 채팅 로직 개발
- 유태영: 채팅기능, 알림기능 REST API, DB 설계
- 유혜빈: 서버 호스팅, CI/CD 구축, 커뮤니티 API 개발
- 황종인: 로그인, 회원가입, 경매 화면 제작
