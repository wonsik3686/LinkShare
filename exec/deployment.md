****⚙️ Install and Usage****

---

### 환경

- OS (AWS EC2): Ubuntu 20.04.3 LTS (GNU/Linux 5.4.0-1018-aws x86_64)

- DB (AWS RDS)

  - 엔진: MySQL Community
  - 인바운드: IPv4, MYSQL/Aurora, TCP, 3306
  - 아웃바운드: IPv4, 모든 트래픽, 전체, 전체

- DB (AWS S3)

  - 버킷 정책

    ```jsx
    {
        "Version": "2012-10-17",
        "Id": "Policy1644472948164",
        "Statement": [
            {
                "Sid": "Stmt1644472937292",
                "Effect": "Allow",
                "Principal": "*",
                "Action": [
                    "s3:GetObject",
                    "s3:PutObject"
                ],
                "Resource": "arn:aws:s3:::linkshare-01-s3/*"
            }
        ]
    }
    ```

  - ACL

    [제목 없음](https://www.notion.so/137845ca0bbd4440bb9f67151e5b0bea)

### 시스템 구성

- Jenkins

- Docker 20.10.12, build e91ed57

- gradle 7.1.1

- nginx 1.18.0 (Ubuntu)

- Java

  openjdk version "11.0.14.1" 2022-02-08 LTS
  OpenJDK Runtime Environment Zulu11.54+25-CA (build 11.0.14.1+1-LTS)
  OpenJDK 64-Bit Server VM Zulu11.54+25-CA (build 11.0.14.1+1-LTS, mixed mode)

### **Ubuntu 버전 업 및 기본 설치**

`sudo apt-get update
sudo apt-get install nodejs
sudo apt-get install npm`

****docker 설치****

```
# 필수 패키지 설치
sudo apt-get install apt-transport-https ca-certificates curl gnupg-agent software-properties-common
# GPG Key 인증
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
# docker repository 등록
sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"# 도커 설치
sudo apt-get update && sudo apt-get install docker-ce docker-ce-cli containerd.io
# 시스템 부팅시 도커 시작
sudo systemctl enable docker && service docker start
# 도커 확인
sudo service docker status
```

### **도커 명령어**

- 도커 컨테이너 보는 법
  - sudo docker ps -a
- 도커 컨테이너 삭제
  - sudo docker rm <container_id>
- 도커 이미지 보는 법
  - sudo docker images
- 도커 이미지 삭제
  - sudo docker rmi <image_id>

****frontend Dockerfile 이미지 생성 및 빌드 실행****

`docker build -t 계정/linkshare_front:latest .`

sudo docker run -p 80:80 계정/linkshare:latest

backend ****Dockerfile 이미지 생성 및 빌드 실행****

`docker build -t 계정/linkshare:latest .`

sudo docker run -p 8082:8082 계정/linkshare:latest

### nginx

```jsx
server {
        listen 80 default_server;
        listen [::]:80 default_server;

        root /var/lib/jenkins/workspace/linkshare/frontend/dist;

        index index.html index.htm index.nginx-debian.html

        server_name _;

        location / {
                try_files $uri $uri/ /index.html;
        }
}
```

### IDE

- IntelliJ
  - IntelliJ IDEA 2021.3 (Ultimate Edition)
  - Build #IU-213.5744.223, built on November 27, 2021
- Visual Studio Code
  - version: 1.64.2
  - node.js: 14.16.0
