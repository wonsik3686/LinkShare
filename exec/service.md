# 외부 서비스

### Dockerhub

- [https://hub.docker.com/](https://hub.docker.com/)
- 회원 가입 후 repository 생성

### Jenkins

- 젠킨스 세팅 후 회원가입 및 로그인

![Untitled](%E1%84%8B%E1%85%AC%E1%84%87%E1%85%AE%20%E1%84%89%E1%85%A5%E1%84%87%E1%85%B5%E1%84%89%200eded/Untitled.png)

### AWS

1. AWS 세팅

- MobaXterm 설치

  - 모바엑스텀(MobaXterm) 이란?

    SSH 클라이언트 프로그램 이라고 생각하시면 됩니다.

  - SSH(Secure Shell) 이란?

    네트워크 상의 다른 컴퓨터에 로그인하거나 원격 시스템에서 명령을 실행하고 다른 시스템으로 파일을 복사할 수 있도록 해주는 응용 프로그램 또는 프로토콜입니다.

  [[AWS] EC2 Centos MobaXterm (SSH) 접속 방법, login as 값](https://gethlemn.tistory.com/12)

  - 참고해서 AWS 접속

- 우분투 환경

  - nginx 설치

  [[Ubuntu] Ubuntu 20.04에 Nginx 설치 및 이해](https://t-okk.tistory.com/154)

  - mysql 사용

    ![Untitled](%E1%84%8B%E1%85%AC%E1%84%87%E1%85%AE%20%E1%84%89%E1%85%A5%E1%84%87%E1%85%B5%E1%84%89%200eded/Untitled%201.png)

  [Docker, Nginx 활용 AWS EC2에 프로젝트 배포하기 + Docker Compose (1)](https://blog.naver.com/PostView.naver?blogId=jinwoo6612&logNo=222532202235&categoryNo=0&parentCategoryNo=0&viewDate=&currentPage=1&postListTopCurrentPage=1&from=postView)

- 개인 계정으로 AWS S3, RDS 서버 생성

  - 

  [AWS RDS 사용하기](https://velog.io/@noyo0123/AWS-RDS-%ED%94%84%EB%A6%AC%ED%8B%B0%EC%96%B4%EB%A1%9C-%EC%82%AC%EC%9A%A9%ED%95%B4%EB%B3%B4%EA%B8%B0-gek2el89jw)

  [Bastion Host의 이해와 AWS에서의 구성 (Proxy)](https://err-bzz.oopy.io/f5616e26-79ca-4167-b2eb-140de69b9b54)

  - rds 생성시 vpc에 가용영역을 최소 2개 이상 사용하도록 서브넷을 미리 만들어 두어야한다.

    - ap-northeast-2-a
    - ap-northeast-2-b

  - vpc에 DNS resolution, hostname 둘 다 활성화 되어 있어야 한다.

    - vpc - DNS 호스트 이름 편집
    - vpc - DNS 확인 편집

  - 게이트웨이

    - [https://galid1.tistory.com/367](https://galid1.tistory.com/367)

  - 

  - s3 생성

    - 버킷 생성

      [[AWS] S3 버킷 프리티어 생성](https://dev-elena-k.tistory.com/15)

    [AWS Amazon S3 버킷 생성하기](https://zzang9ha.tistory.com/358)

    - IAM 설정 ( 사용자 설정 ) - KEY 저장해놓아야함

  ![Untitled](%E1%84%8B%E1%85%AC%E1%84%87%E1%85%AE%20%E1%84%89%E1%85%A5%E1%84%87%E1%85%B5%E1%84%89%200eded/Untitled%202.png)

  ![Untitled](%E1%84%8B%E1%85%AC%E1%84%87%E1%85%AE%20%E1%84%89%E1%85%A5%E1%84%87%E1%85%B5%E1%84%89%200eded/Untitled%203.png)

  [S3 버킷 CORS 설정 (json)](https://www.enteroa.com/2020/11/05/s3-%EB%B2%84%ED%82%B7-cors-%EC%84%A4%EC%A0%95-json/)

  [[AWS] EC2 X Docker : Spring Boot 프로젝트 올리기](https://velog.io/@haeny01/AWS-EC2-X-Docker-Spring-Boot-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-%EC%98%AC%EB%A6%AC%EA%B8%B0)

- 도커

  - 로컬에서 도커 이미지 빌드 및 허브에 업로드
    - 프로젝트 빌드 → jar
    - jar 파일로 도커 이미지 빌드 ( docker build conbrio1/test-ec2:0.0.1 .  ) ( tag 도 생성 )
    - 도커 로그인
    - 도커 허브에 업로드 ( push )
  - ec2에서 도커 이미지 다운로드
    - 다운로드는 pull 명령어로 함
    - 실행

- NGINX 설정

  - 도커 컨테이너와 연결을 해주어야될 것 같다..

- EC2 HTTPS 설정

  - SSL

    - 

    [[Nginx] Let's Encrypt를 통해 Nginx에서 무료로 https 설정하기](
