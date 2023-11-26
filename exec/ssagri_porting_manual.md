# 💰SSAGRI Porting Manual

1.  **Develop Environment** - 1.1 Front-End - 1.2 Back-End - 1.3 Infra Structure - 1.4 DataBase

        # 1. Develop Environment

    > ### 1.1 Front-end

- npm 9.6.7
- node 18.17.0
- react
  - Dependencies
    - 디펜던시 넣어야 함.
- Tool: [VSCode](https://code.visualstudio.com/)

> ### 1.2 Back-end

- openjdk 11.0.0.1
- [Spring](https://start.spring.io/)

  - Project Build: Gradle - Groovy
  - Language: Java
  - Spring Boot: 2.7.15
  - Packaging: Jar
  - Java: 11
  - Dependencies: - Spring Web - Spring Security - Lombok - Spring Data JPA - MySQL Driver - Spring Reactive Web - Others (build.gradle.kts)
    ![Imgur](https://i.imgur.com/A6NTmVm.png)

```
    buildscript {
    ext {
    queryDslVersion = "5.0.0"
    }
    }
    plugins {
    id 'java'
    id 'org.springframework.boot' version '2.7.15'
    id 'io.spring.dependency-management' version '1.0.15.RELEASE'

        //querydsl 추가
        id "com.ewerk.gradle.plugins.querydsl" version "1.0.10"

    }


group = 'com.ssafy'
version = '0.0.1-SNAPSHOT'

java {
sourceCompatibility = '11'
}

configurations {
compileOnly {
extendsFrom annotationProcessor
}
}

repositories {
mavenCentral()
}

dependencies {
implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
implementation 'org.springframework.boot:spring-boot-starter-security' //security
implementation 'org.springframework.boot:spring-boot-starter-web'
implementation 'org.springframework.boot:spring-boot-starter-webflux'
implementation 'org.springframework:spring-context' // @Value
implementation 'org.springframework.boot:spring-boot-starter-mail' //mail
implementation 'org.springframework.boot:spring-boot-starter-data-redis' //Redis
implementation 'org.springframework.boot:spring-boot-configuration-processor' //annotation-processer

// //aws
// implementation 'org.springframework.cloud:spring-cloud-starter-aws:2.2.6.RELEASE'
// implementation "com.amazonaws:aws-java-sdk-s3:1.12.395"

    // AWS
    implementation 'io.awspring.cloud:spring-cloud-starter-aws:2.3.1'
    implementation 'software.amazon.awssdk:s3:2.17.65'



    compileOnly 'org.projectlombok:lombok'
    runtimeOnly 'com.mysql:mysql-connector-j'
    annotationProcessor 'org.projectlombok:lombok'

    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    testImplementation 'io.projectreactor:reactor-test'
    testImplementation 'org.springframework.security:spring-security-test'




    // (3) queryDSL 추가
    implementation "com.querydsl:querydsl-jpa:${queryDslVersion}"
    annotationProcessor "com.querydsl:querydsl-apt:${queryDslVersion}"


    //jwt setting
    implementation 'io.jsonwebtoken:jjwt-api:0.11.2'
    implementation 'io.jsonwebtoken:jjwt-impl:0.11.2'
    implementation 'io.jsonwebtoken:jjwt-jackson:0.11.2' // (선택 사항) JSON 직렬화를 위한 Jackson 의존성


    //Swagger
    implementation 'io.springfox:springfox-boot-starter:3.0.0'
    implementation 'io.springfox:springfox-swagger-ui:3.0.0'


    //	websocket
    implementation 'org.springframework.boot:spring-boot-starter-websocket'

    //	MongoDB
    implementation 'org.springframework.boot:spring-boot-starter-data-mongodb'

    // S3
    implementation 'org.springframework.cloud:spring-cloud-starter-aws:2.2.6.RELEASE' //AWS Cloud와 통합을 쉽게 해주는 Spring Cloud AWS 모듈의 스타터 패키지. 내부적으로는 AWS SDK를 포함.
    implementation 'net.lingala.zip4j:zip4j:2.6.1' // zip 압축 다운로드
    implementation 'org.apache.commons:commons-text:1.9' // 랜덤 문자열 생성

    // Json
    // https://mvnrepository.com/artifact/org.json/json
    implementation group: 'org.json', name: 'json', version: '20230227'

}

tasks.named('test') {
useJUnitPlatform()
}

// (4) queryDSL 추가 : QueryDSL 빌드 옵션
def querydslDir = "$buildDir/generated/querydsl"

querydsl {
jpa = true
querydslSourcesDir = querydslDir
}
sourceSets {
main.java.srcDir querydslDir
}
configurations {
querydsl.extendsFrom compileClasspath
}
compileQuerydsl {
options.annotationProcessorPath = configurations.querydsl
}

```

- Tool: [IntelliJ Ultimate (2023.1.3)](https://www.jetbrains.com/ko-kr/idea/download/?section=windows)

> ### 1.3 Infrastructure

![Imgur](https://i.imgur.com/btbd5Lm.png)

- Docker version 24.0.6
- docker-compose version 1.24.1
- nginx:1.15
- Jenkins 2.414.1
- certbot 2.7.0

> ### 1.4 DataBase

#### Mysql -> RDS 설정

![Imgur](https://i.imgur.com/aKbySt8.png)
![Imgur](https://i.imgur.com/Z2nzqWx.png)
![Imgur](https://i.imgur.com/ogiRbB4.png)

> https://velog.io/@shawnhansh/AWS-RDSmySql-%ED%94%84%EB%A6%AC%ED%8B%B0%EC%96%B4-%EC%83%9D%EC%84%B1%ED%95%98%EA%B8%B0

위의 문서를 참고하여 Rds 설정하기

#### MongoDB + EC2

- mongoDB 키를 가져온다

```
wget -qO - https://www.mongodb.org/static/pgp/server-4.4.asc | sudo apt-key add -

```

- MongoDB를 위한 List 파일을 만들기

```
echo "deb [ arch=amd64,arm64 ] https://repo.mongodb.org/apt/ubuntu focal/mongodb-org/4.4 multiverse" | sudo tee /etc/apt/sources.list.d/mongodb-org-4.4.list

```

- 업데이트 후 mongoDB 설치

```
apt-get update
apt-get install -y mongodb-org
```

- 실행 후 실행상태 확인

```
systemctl start mongod # 실행
systemctl status mongod # 실행 상태 확인

```

- conf 파일 변경하기
  https://velog.io/@enosoup/MongoDB-EC2-On-Docker

실행 상태 결과

```
ubuntu@ip-172-26-7-160:~$ systemctl status mongod
● mongod.service - MongoDB Database Server
     Loaded: loaded (/lib/systemd/system/mongod.service; enabled; vendor preset: enabled)
     Active: active (running) since Sun 2023-10-01 10:09:35 UTC; 2 days ago
       Docs: https://docs.mongodb.org/manual
   Main PID: 1071117 (mongod)
     Memory: 75.8M
     CGroup: /system.slice/mongod.service
             └─1071117 /usr/bin/mongod --config /etc/mongod.conf

Oct 01 10:09:35 ip-172-26-7-160 systemd[1]: Started MongoDB Database Server.
Oct 01 10:09:35 ip-172-26-7-160 mongod[1071117]: {"t":{"$date":"2023-10-01T10:09:35.782Z"},"s":"I",  "c":"CONTROL",  "id":7484500, "ctx":"main","msg":"Environment var>
```

- 보안 그룹 설정은 이 블로그를 참고하여 설정
  https://be-developer.tistory.com/53

- 외부 접속을 위한 사용자 계정생성
  mongo -> 몽고로 들어가기

use admin -> admin 계정 접속

db.createUser({user: "input", pwd: "input", roles:["root"]}); -> 외부 접속용 계정 생성

exit -> mongoDB 나오기

service mongod restart -> mongoDB 재시작

- MongoDB Compose에 연결하기
  ![Imgur](https://i.imgur.com/o5JM0HH.png)

참고문서
https://velog.io/@hke2/AWSEC2-mongoDB-Compass%EB%A1%9C-%EC%A0%91%EC%86%8D

# 3. Server Setting

> ### 3.1. EC2 CLI 접속

- Windows 창을 통해 Microsoft Store에 접근하고 Termius를 다운받는다.
- Termius를 실행하고 "NEW HOST"를 클릭한다. 우측에 설정 입력란이 나온다.
- Label --> 이름 , Address --> Public IP주소를 입력한다.
- SSH --> "Set a Key" -> "NEW KEY" 를 클릭하여 인스턴스를 생성하며 다운받은 pem key를 삽입한다.
- SSH의 username은 OS가 Amazon이라면 "ec2-user", Ubuntu라면 "ubuntu"를 입력해준다.
- CLI 연결 완료.

![Imgur](https://i.imgur.com/DFgdEsU.png)

> ### 3.2 Domain Name Server Setting

> ### 3.3 기본 설정

- 생성한 인스턴스에 들어가기 위해 방화벽 설정을 해준다.

- ufw 명령어로 방화벽 상태 관리를 할 수 있다.

=====================

- ufw 활성화

- sudo ufw enable

- ufw 비활성화

- sudo ufw disable

- ufw 상태 확인

- sudo ufw status verbose

=====================

1. 우선 포트 상태 확인을 한다.

> $ sudo ufw status

```bash
  Status: active


   To Action From

   ---

   22 ALLOW Anywhere
   22 (v6) ALLOW Anywhere (v6)

```

2. 사용 할 포트들을 열어준다.

-- 80, 443, 젠킨스를 9090 으로 사용 할 것이므로 allow 한다.

> $ sudo ufw allow [사용할 포트 번호]

```bash

    Status: active

    To Action From

    ---

    22 ALLOW Anywhere
    80 ALLOW Anywhere
    9090 ALLOW Anywhere
    22 (v6) ALLOW Anywhere (v6)
    80 (v6) ALLOW Anywhere (v6)
    9090 (v6) ALLOW Anywhere (v6)

```

- 포트가 허용된 것을 알 수 있다.

> ### 3.4 Jenkins

- 자동 배포를 도와줄 jenkins 를 EC2에 깐다.

- Jenkins 를 사용하기 위해서는 jdk가 필요하기 때문에 jdk가 있는 지 확인한다.

  `java --version`

  ```bash

      Command 'java' not found, but can be installed with:

      sudo apt install default-jre              # version 2:1.11-72, or
  sudo apt install openjdk-11-jre-headless  # version 11.0.7+10-3ubuntu1
  sudo apt install openjdk-13-jre-headless  # version 13.0.3+3-1ubuntu2
  sudo apt install openjdk-14-jre-headless  # version 14.0.1+7-1ubuntu1
  sudo apt install openjdk-8-jre-headless   # version 8u252-b09-1ubuntu1

  ```

- java 존재하지 않기 때문에 jdk를 깔아준다.
- java 11, java 17 둘 중 하나를 깔아준다.

> $ sudo apt-get install openjdk-11-jdk

> $ sudo apt-get install openjdk-11-jre // 배포를 위한 jre

다시 `java --version`을 하게 되면 이렇게 나올 것이다.

```bash

    openjdk 11.0.20.1 2023-08-24
    OpenJDK Runtime Environment (build 11.0.20.1+1-post-Ubuntu-0ubuntu120.04)
    OpenJDK 64-Bit Server VM (build 11.0.20.1+1-post-Ubuntu-0ubuntu120.04, mixed mode, sharing)

```

- 이제 jdk를 다 깔았으니 Jenkins를 깔아 볼 것이다.

> jenkins가 있는 지 확인해본다.

`sudo systemctl status jenkins`

    ```bash

        Unit jenkins.service could not be found.

    ```


    - 아직 젠킨스가 없기 때문이다. 젠킨스를 깔아주자.

https://pkg.origin.jenkins.io/debian-stable/ 을 참고하여 Jenkins를 깔아보자.

> Jenkins의 데비안 패키지 저장소를 사용하려면 먼저 시스템에 키를 추가해야 한다.

```bash
curl -fsSL https://pkg.jenkins.io/debian-stable/jenkins.io-2023.key | sudo tee \
    /usr/share/keyrings/jenkins-keyring.asc > /dev/null
```

> 다음으로 Jenkins apt 저장소 항목을 추가한다.

```bash
    echo deb [signed-by=/usr/share/keyrings/jenkins-keyring.asc] \
    https://pkg.jenkins.io/debian-stable binary/ | sudo tee \
    /etc/apt/sources.list.d/jenkins.list > /dev/null
```

> 기본 업데이트를 한 번 해준다.

`sudo apt-get update`

> 젠킨스를 설치한다.

`sudo apt-get install jenkins`

> 젠킨스 포트번호 변경

- 기본 젠킨스 포트번호가 8080인데 우리는 9090으로 쓸 것이기 때문에 변경해줘야 한다.

`sudo vi /etc/default/jenkins`

- 내리다 보면 'HTTP_PORT' 가 나온다.

![Imgur](https://i.imgur.com/mkH2oyB.png)

- 이 부분을 내가 쓸 포트로 변경해준다. ex) HTTP_PORT=9090

> Jenkins 실행

`$ sudo systemctl start jenkins`

> Jenkins 서비스 상태 확인

`$ sudo systemctl status jenkins`

- 확인 결과 아직 바뀌지 않았다.

![Imgur](https://i.imgur.com/AwWwSrZ.png)

> 다른 방식으로 포트 변경하기

`$ sudo vi /usr/lib/systemd/system/jenkins.service`

![Imgur](https://i.imgur.com/HZjkCfP.png)

- 변경된 것을 알 수 있다.

> ### 3.5 Jenkins 와 Webhook 연동

- 우선 내가 만든 젠킨스 url로 이동한다.
  ex) http://[EC2도메인 주소]:[젠킨스 포트번호]/

- 처음에 이 주소로 가게 되면 이러한 화면이 나온다.

![jenkins 초기 화면](https://i.imgur.com/nFEpfvb.png)

> admin password에는 termius에서 `sudo systemctl status jenkins` 했을 시 저장한 비밀번호를 넣어준다.

> 그 다음 계정명, 비밀번호는 자유롭게 프로젝트에 맞게 작성하면 된다.
> Available plugins" - Generic Webhook Trigger - Gitlab - Gitlab API - Gitlab Authentication - Mattermost Notification - Docker pipeline 등을 다운 받아준다.

![Imgur](https://i.imgur.com/uPMeyKA.png)

> 연결할 깃랩의 엑세스토큰을 만들어 준다.

![Imgur](https://i.imgur.com/kBf8R0I.png)

> password에 방금 만든 엑세스토큰을 넣어준다.

- 깃랩에 > 웹훅으로 들어가서 url과 secret token 을 넣어준다.
- url 과 secret token 은 젠킨스에 새로운 Item > 파이프라인 이름을 작성하고 > 파이프라인을 선택하고 > ok를 누른다.
- 만들어진 파이프라인 밑에 Pipeline > 구성 > Build Triggers > Build when a change is pushed to GitLab. GitLab webhook URL: http://[DOMAIN]/project/[ITEM_NAME] 체크 <-- 이게 url이 된다.
- Build를 유발할 Trigger 옵션을 선택하여 적용한다.
- 선택 후 "고급"을 눌러 Webhook 설정을 위한 Secret Token을 발급받는다. (Generate 클릭) <-- 이게 secret token이 된다.

![Imgur](https://i.imgur.com/4kqI5kk.png)

> 이렇게 웹훅에서 테스트를 누르면 200이 뜨면 성공한거다!!

> ### 3.6 Docker credential 설정

> 우선 도커 이미지를 담아놓을 도커허브에서 레포지토리를 만든다.
> ![Imgur](https://i.imgur.com/e2M6nl4.png)

> 도커 허브 Security를 설정해야 한다.
> ![Imgur](https://i.imgur.com/CGS2eJb.png)

- 꼭 저 토큰을 따로 저장해놔야 한다.

> 그럼 이렇게 토큰이 생성이 된다.
> ![Imgur](https://i.imgur.com/SKGvjGQ.png)

> ### 3.7 EC2에 도커와 도커 컴포즈 올리기

1.  필요한 기능들 update

`sudo apt-get update`

2.  도커 설치시 필요한 SW 설치

```bash
$ sudo apt-get install \
apt-transport-https \
ca-certificates \
curl \
gnupg \
lsb-release
```

- result (Do you want to continue? [Y/n] --> y 눌러주면 된다.)

```bash
Reading package lists... Done
Building dependency tree
Reading state information... Done
lsb-release is already the newest version (11.1.0ubuntu2).
lsb-release set to manually installed.
ca-certificates is already the newest version (20230311ubuntu0.20.04.1).
ca-certificates set to manually installed.
curl is already the newest version (7.68.0-1ubuntu2.19).
curl set to manually installed.
gnupg is already the newest version (2.2.19-3ubuntu2.2).
gnupg set to manually installed.
The following NEW packages will be installed:
  apt-transport-https
0 upgraded, 1 newly installed, 0 to remove and 119 not upgraded.
Need to get 1704 B of archives.
After this operation, 162 kB of additional disk space will be used.
Do you want to continue? [Y/n] y
Get:1 http://ap-northeast-2.ec2.archive.ubuntu.com/ubuntu focal-updates/universe amd64 apt-transport-https all 2.0.9 [1704 B]
Fetched 1704 B in 0s (102 kB/s)
Selecting previously unselected package apt-transport-https.
(Reading database ... 61920 files and directories currently installed.)
Preparing to unpack .../apt-transport-https_2.0.9_all.deb ...
Unpacking apt-transport-https (2.0.9) ...
Setting up apt-transport-https (2.0.9) ...
```

3. 서명키로 사용할 GPG 키를 추가해야한다.

- 디렉토리를 만들고, 권한 부여

  `$ sudo mkdir -m 0755 -p /etc/apt/keyrings`

- GPG 키 추가

  `$ curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /etc/apt/keyrings/docker.gpg`

4. 안정화된 Repository 환경 구축한다.

   ```bash
   $ echo \
   "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.gpg] https://download.docker.com/linux/ubuntu \
   $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
   ```

5. 도커 설치

`$ sudo apt-get update`

`$ sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin`

6. 도커 설치 완료 후 버전 확인

```bash
$ docker --version
Docker version 24.0.6, build ed223bc
```

7. 마지막으로 관리자 외에도 Docker를 사용할 수 있도록 설정한다.

`$ sudo usermod -aG docker ubuntu`

8. 도커 컴포즈 설치, 설정

```bash
$ sudo curl -L "https://github.com/docker/compose/releases/download/1.24.1/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose

 % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
  0     0    0     0    0     0      0      0 --:--:-- --:--:-- --:--:--     0
100 15.4M  100 15.4M    0     0  21.4M      0 --:--:-- --:--:-- --:--:-- 21.4M

$ sudo chmod +x /usr/local/bin/docker-compose
$ sudo ln -s /usr/local/bin/docker-compose /usr/bin/docker-compose
$ docker-compose -version
docker-compose version 1.24.1, build 4667896b
```

> ### 3.8 Jenkins Pipeline 작성

- Dashboard -> 구성 -> 파이프라인 에서 작성 할 수 있다.

```
pipeline {
    agent any

// 변수 설정
    environment {
        GIT_URL = "https://lab.ssafy.com/s09-fintech-finance-sub2/S09P22B209.git"
        BE_IMAGE_NAME = "ssagri-backend"
		FE_IMAGE_NAME = "ssagri-frontend"
		NGINX_IMAGE = "ssagri-nginx"
        //DOCKER_ID = "hyebin123"
        //DOCKER_PW = "smy6082^^**"
    }

    // 진행할 Pipeline들
    stages {
        // Pipeline의 요소 하나. Git의 소스코드를 가져온다.
        stage('Git clone BE') {
            steps {
                git branch: 'develop',   // 소스를 가져올 Branch명
                    url: "${GIT_URL}",  // 위에서 설정한 GitLab URL 변수
                    credentialsId: "6b26d11d-e364-4357-94bb-85401a8a8da7" // GitLab Credential ID
            }

            // step 이후 수행
            post {
                // 성공했을 때
                success {
                    sh 'echo "Successfully Cloned backend repository"'
                }
                // 실패했을 때
                failure {
                    sh 'echo "Fail Cloned backend repository"'
                }
            } // post
        } // stage


//         // 컨테이너 만들기 전에 원래 있던 것들 삭제
        stage('container deletion') {
             steps {

                 sh """
                    pwd
                    docker-compose down --rmi all

                 """
                    // docker stop react-container
                    // docker stop spring-container
                    // docker rm -f react-container
                    // docker rm -f spring-container
             }

             post {
                 // 실행 성공시
                 success {
                     echo 'Deletion Success'
                 }
                 // 실행 실패시
                 failure {
                     echo 'Deletion Failed'
                 }
            } // post
         } // stage


// 컨테이너들을 생성하기 전에 전에 실행중인 도커들은 다운시킨다.


        // stage('Docker down') {
        //     steps {
        //         // 현재위치를 확인하고, 이전에 실행되고 있던 컨테이너들을 종료시킨다.
        //         sh '''
        //             pwd
        //             docker-compose down --rmi all
        //         '''
        //     } // step

        //     post {
        //         // 컨테이너 종료 성공시
        //         success {
        //             echo 'Down Success'
        //         } // success
        //         // 컨테이너 종료 실패시
        //         failure {
        //             echo 'Down Failed'
        //         } // failure
        //     } // post
        // } // stage


// // 기존의 이미지 삭제

// stage('Delete Docker Image') {
//             steps {
//                 // sh """
//                 //     sudo usermod -aG docker $USER

//                 // """
//                 script {

//                     def backendImage = 'ssagri-backend:latest'
//                     def frontendImage = 'ssagri-frontend:latest'

//                     // 이미지가 존재하는지 확인
//                     def backendImageExists = sh(script: "docker images --format '{{.Repository}}:{{.Tag}}' | grep '${backendImage}'", returnStatus: true) == 0
// 				def frontendImageExists = sh(script: "docker images --format '{{.Repository}}:{{.Tag}}' | grep '${frontendImage}'", returnStatus: true) == 0

//                     // 백엔드 이미지가 존재하면 삭제
//                   if (backendImageExists) {
//                         echo "Deleting ${backendImage}..."
//                         sh "docker rmi ${backendImage}"
//                     } else {
//                         echo "Image '${backendImage}' not found. Skipping deletion."
//                     }

// 					// 프론트엔드 이미지가 존재하면 삭제
// 					if (frontendImageExists) {
//                         echo "Deleting ${frontendImage}..."
//                         sh "docker rmi ${frontendImage}"
//                     } else {
//                         echo "Image '${frontendImage}' not found. Skipping deletion."
//                     }

//                 }
//             }
//         }



// // 가져온 소스코드를 Build 해본다.
        stage('Build BE') {
            steps {
                // 현재 위치 출력 후
                // var/lib/jenkins에서 gradlew 파일이 있는 곳으로 경로 이동
                // 실행권한 부여 후 build 실행
                sh '''
                    pwd
                    cd backend/ssagri
                    chmod +x ./gradlew
                    ./gradlew clean build --exclude-task test
                '''
            } // step
            post {
                // build 성공시
                success {
                    echo 'gradle backend build success'
                } // success
                // build 실패시
                failure {
                    echo 'gradle backend build failed'
                } // failure
            } // post
        } // build be


// // build 성공시 Docker Hub로 이미지를 만들어 전송한다.
        stage('Image build BE'){
            steps{
                sh 'echo " Image Bulid Start"'

                // var/lib/jenkins에서 dockerfile이 있는 곳으로 경로 이동
                // dockerfile을 수행하여 지정한 이름으로 이미지를 만든다.
                // 도커에 로그인한 후, 생성한 이미지를 push한다.
                sh """
                    cd backend/ssagri

                    docker build --no-cache -t ${BE_IMAGE_NAME} .

                """
            } // step
            post {
                // 이미지 전송 성공시
                success {
                    sh 'echo "PUSH backend Docker Image Success"'
                } // success
                // 이미지 전송 실패시
                failure {
                    sh 'echo "PUSH backendDocker Image Fail"'
                } // failure
            } // post

        } // image build be

// // 가져온 프론트앤드 소스코드를 Build 해본다.
        stage('Build FE') {
            steps {
                // 현재 위치 출력 후
                // var/lib/jenkins에서 gradlew 파일이 있는 곳으로 경로 이동
                // 실행권한 부여 후 build 실행
                sh '''
                    pwd
                    cd frontend
                    npm install --no-fund --no-audit
		            npm run build --no-warnings-flags
                '''
            } // step
            post {
                // build 성공시
                success {
                    echo 'gradle frontend build success'
                } // success
                // build 실패시
                failure {
                    echo 'gradle frontend build failed'
                } // failure
            } // post
        } // build fe


// // build 성공시 Docker Hub로 이미지를 만들어 전송한다.
        stage('Image build FE'){
            steps{
                sh 'echo " Image Bulid Start"'

                // var/lib/jenkins에서 dockerfile이 있는 곳으로 경로 이동
                // dockerfile을 수행하여 지정한 이름으로 이미지를 만든다.
                // 도커에 로그인한 후, 생성한 이미지를 push한다.
                sh """
                    cd frontend
                    docker build --no-cache -t ${FE_IMAGE_NAME} .

                """
            } // step
            post {
                // 이미지 전송 성공시
                success {
                    sh 'echo "PUSH backend Docker Image Success"'
                } // success
                // 이미지 전송 실패시
                failure {
                    sh 'echo "PUSH backendDocker Image Fail"'
                } // failure
            } // post

        } // stage






  stage('Deploy Automation') {
             steps {
                 // 현재 위치를 파악하고, 현재 위치에 있는 docker-compose.yml 파일을 실행시킨다.
                 sh """
                     pwd
                     docker-compose up -d

                 """
             }

             post {
                 // 실행 성공시
                 success {
                     echo 'Deploy Success'
                 }
                 // 실행 실패시
                 failure {
                     echo 'Deploy Failed'
                 }
            } // post
         } // stage


     } // stages


    } // any


```

> ### 4.1 Dockerfile
>
> ++ Frontend Dockerfile

```
# react-dockerizing/Dockerfile

# base image 설정(as build 로 완료된 파일을 밑에서 사용할 수 있다.)
FROM node:18-alpine as build

# 컨테이너 내부 작업 디렉토리 설정
WORKDIR /app


# app dependencies
# 컨테이너 내부로 package.json 파일들을 복사
COPY package*.json ./

# package.json 및 package-lock.json 파일에 명시된 의존성 패키지들을 설치
RUN npm install

# 호스트 머신의 현재 디렉토리 파일들을 컨테이너 내부로 전부 복사
COPY . .

# npm build
RUN npm run build


# Stage 2: Serve app with nginx server
FROM nginx:1.21-alpine

# Copy build output from build stage
COPY --from=build /app/dist/ /usr/share/nginx/html

```

++ Backend Dockerfile

```
# FROM: 이미지 지정
FROM openjdk:11

# ARG: docker build 커맨드를 사용할 때 입력받을 수 있는 인자를 선언
ARG JAR_FILE=build/libs/*.jar

# COPY: 이미지에 파일이나 폴더를 추가
COPY ${JAR_FILE} app.jar

# ENTRYPOINT: 컨테이너를 실행할 때 실행할 명령어 강제 지정
ENTRYPOINT ["java", "-jar", "/app.jar"]

```

- 각각의 Dockerfile들을 각 프로젝트 최상단에 위치시켜야함.
  ++ Backend 도커파일 위치
  ![Imgur](https://i.imgur.com/TdHKIfH.png)
  ++ Frontend 도커파일 위치
  ![Imgur](https://i.imgur.com/UL7Lpbg.png)

> ### 4.2 Docker-compose.yml

```
version: '3'

# 컨테이너들
services:
    # nginx 라는 service
    nginx:
        # 사용되는 이미지
        image: nginx:1.15-alpine
        # 만들어질 컨테이너명
        container_name: nginx-container
        # 도커 네트워크
        networks:
            - special-network
        # 환경
        environment:
            - TZ=Asia/Seoul
        # 의존하고 있는 서비스(컨테이너)
        depends_on:
            - spring
            - react
        # 재시작하는 경우
        restart: unless-stopped
        # 컨테이너가 만들어질 때, 가져올 데이터들.
        # jenkins를 통해 실행되므로 var/lib/jenkins 하위 디렉토리에서 데이터를 가져온다.
        volumes:
            #- /infra/nginx:/etc/nginx/conf.d
            - /data/nginx:/etc/nginx/conf.d
            - /data/certbot/conf:/etc/letsencrypt
            - /data/certbot/www:/var/www/certbot
        # 외부에 공개할 포트
        ports:
            - "80:80"
            - "443:443"
        # 컨테이너가 만들어진 후에 실행할 명령어
        command: "/bin/sh -c 'while :; do sleep 6h & wait $${!}; nginx -s reload; done & nginx -g \"daemon off;\"'"
    # certbot 이라는 service
    certbot:
        # 사용되는 이미지
        image: certbot/certbot
        # 만들어질 컨테이너명
        container_name: certbot-container
        # 도커 네트워크
        networks:
            - special-network
        # 재시작하는경우
        restart: unless-stopped
        # 컨테이너가 만들어질 때, 가져올 데이터들
        # jenkins를 통해 실행되므로 var/lib/jenkins 하위 디렉토리에서 데이터를 가져온다.
        volumes:
            - /data/certbot/conf:/etc/letsencrypt
            - /data/certbot/www:/var/www/certbot
        # 컨테이너가 만들어진 후에 강제로 실행할 명령어
        entrypoint: "/bin/sh -c 'trap exit TERM; while :; do certbot renew; sleep 12h & wait $${!}; done;'"
    # spring 이라는 service
    spring:
        # 사용되는 이미지
        image: ssagri-backend
        # 만들어질 컨테이너명
        container_name: spring-container
        # 도커 네트워크
        networks:
            - special-network
        # 재시작하는경우
        restart: always
        # 컨테이너 내부에서 도커 네트워크에게 열어주는 포트
        expose:
            - "5000"
        #ports: 외부에서 host로 누구나 접근할 수 있는 포트
        # 컨테이너가 만들어질 때, 가져올 데이터들
        # jenkins를 통해 실행되므로 var/lib/jenkins 하위 디렉토리에서 데이터를 가져온다.
        #volumes:
            #- ~/config/spring:/Develop/BackEnd/joinit/src/main/resources:ro

    react:
        # 사용되는 이미지
        image: ssagri-frontend
        # 만들어질 컨테이너명
        container_name: react-container
        # 도커 네트워크
        networks:
            - special-network
        # 재시작하는경우
        restart: always
        # 컨테이너 내부에서 도커 네트워크에게 열어주는 포트
        expose:
            - "5173"
        # 컨테이너가 만들어질 때, 가져올 데이터들
        # jenkins를 통해 실행되므로 var/lib/jenkins 하위 디렉토리에서 데이터를 가져온다.
        volumes:
            - /data/react-nginx:/etc/nginx/conf.d
# 도커 네트워크
networks:
    # 도커 네트워크 이름
    special-network:

```

- ec2에 있는 내용을 nginx 내로 옮겨서 프록시 되게 한다.

> ### 5.1 Nginx

- Nginx를 원하는 곳으로 프록시 시키기 위해서는 app.conf 파일을 만들어 nginx.conf 파일에 include 시켜줘야함.

```
# 프로젝트 특성상 GIF 파일 전송이 가능하도록 용량을 늘렸다.
client_max_body_size 100M;

# upstream을 통해 nginx reverse proxy 기능을 사용한다.
# upstream 우측의 이름은 nginx 내부에서 사용되는 host 이름이고
# 구현부의 server 우측의 이름은 docker-compose에서 선언한 service의 이름이다.
upstream react {
        server react:5173;
}

upstream spring {
        server spring:5000;
}


# 80 포트번호로 들어오면 https로 redirect 시켜준다.
server {
    listen 80;
    server_name j9b209.p.ssafy.io www.j9b209.p.ssafy.io;
    server_tokens off;

    location / {
       return 301 https://$host$request_uri;
        # try_files $uri $uri/ /index.html =404;
        #try_files $uri /index.html;
    }

    #location / {
    #root   /app/dist;
    #index  index.html index.htm;
    #try_files $uri $uri/ /index.html;
    #}

    location /.well-known/acme-challenge/ {
        allow all;
        root /var/www/certbot;
    }
}

# j9b209.p.ssafy.io 도메인으로 https, 443으로 들어왔을 때 reverse proxy 설정을 해준다.
# / 로 들어오면 react로 들여보내고, /api로 들어오면 spring으로 들여보낸다.
# nginx 이후로는 도커 네트워크를 통해 컨테이너간 통신이 가능해지므로 http가 된다.

server {
    listen 443 ssl;
    server_name j9b209.p.ssafy.io www.j9b209.p.ssafy.io;
    server_tokens off;

    # certbot으로 SSL 인증을 받은 파일들의 위치를 명시함으로서 https 기능이 활성화된다.
    ssl_certificate /etc/letsencrypt/live/j9b209.p.ssafy.io/fullchain.pem;
    ssl_certificate_key /etc/letsencrypt/live/j9b209.p.ssafy.io/privkey.pem;
    include /etc/letsencrypt/options-ssl-nginx.conf;
    ssl_dhparam /etc/letsencrypt/ssl-dhparams.pem;

    location / {
        proxy_pass                              http://react;
        proxy_set_header    Host                $http_host;
        proxy_set_header    X-Real-IP           $remote_addr;
        proxy_set_header    X-Forwarded-For     $proxy_add_x_forwarded_for;

        proxy_buffer_size                       128k;
        proxy_buffers                           4 256k;
        proxy_busy_buffers_size                 256k;
    }

    location /api {

        proxy_http_version 1.1;
        proxy_set_header Connection "";

        proxy_pass                              http://spring;
        proxy_set_header   Host                 $host;
        proxy_set_header   X-Real-IP            $remote_addr;
        proxy_set_header   X-Forwarded-For      $proxy_add_x_forwarded_for;
    }
}

```

- 모든 요청을 443으로 redirect 시키기

> Nginx.conf 파일

```
user www-data;
worker_processes auto;
pid /run/nginx.pid;

events {
    worker_connections 768;
    # multi_accept on;
}

http {
    ##
    # Basic Settings
    ##

    sendfile on;
    tcp_nopush on;
    tcp_nodelay on;
    keepalive_timeout 65;
    types_hash_max_size 2048;

    server_tokens off;

    # server_names_hash_bucket_size 64;



   ##
   # Virtual Host Configurations
   ##

   include /etc/nginx/conf.d/*.conf;
   include /etc/nginx/sites-enabled/*;
}

```

- 마지막 줄에 \*.conf에 대한 모든 conf 파일 포함 시킴.
