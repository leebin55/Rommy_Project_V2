## 개발 기술 및 환경
* Spring boot + React + MySQL +Spring Security + Spring Data JPA

## 주제
- 싸이월드를 모티브로 나만의 미니 홈페이지(Room)를 가지고 소통할 수 있는 SNS 
--- 

# Roomy Project V2
- V1 문제점
1. Entity Join등 관계 설정
- 이전 프로젝트는 DATA JPA 의 기본적인 기능만 이용하여 사용해서 엔티티 관계에 대해서 JOIN 을 하지 않았다.
- 그래서 하나의 요청을 하기위해서 많은 fetch 를 보내거나 SQL 퀴리를 많이 날린다
- 데이터가 별로 없을때에는 크게 상관 없었지만 계속 데이터가 쌓일 때 너무 많은 쿼리와 요청이 들어오면 성능에 문제가 생길 거 같아 연관관계를 설정하고 JOIN을 함
- like 테이블은 게시물(board)와  N : 1 관계로 설정했었는데 like 테이블은 한 User 가 서로다른 Board(N) 를 좋아요 누를수 있고 Board 는 서로다른 User(N) 가 좋아요를 누를 수 있다
- like 에 대해 ser : board 는  N  : N 관계 를 갖는다 
- @ManyToMany 를 사용하면 한계가 존재 > 복잡한 조인 쿼리 발생, 두 테입즐에대해 필요한 추가 칼럼 사용 불가
- @ManyToOne , @OneToMany 1: N , N : 1 관계로 풀어주기 ( user : like : board  를  1 : N : 1 로 풀어주기)

2. Spring Security 없이 로그인 구현 
- HTTP 프로토콜은 stateless 특징이 있어 요청을 할때마다 필요한 요청을 모두 같이 보내야된다. => 즉, 로그인한 유저 정보도 같이 보내야됨
- 요청이 올때마다 로그인 정보를 확인하는 공통적인 로직 발생 > security 사용
- session DB 에 로그인한 모든 회원 정보를 저장한다면 서버의 자원을 계속적으로 사용한다는 것이고 사용자가 많아지면 서버 자원이 부족해서 서버의 확장이 필요 > 토큰이용
- 토큰을 이용하면 서버는 로그인한 유저정보를 저장할 필요없이 토큰이 유효한지만 확인해 주면 된다. 

3. front (client)
- 3명이서 각자 맡은 부분을 front 와 back 을 같이 손대다 보니 코드가 중구난방
- 재사용할 수 있는 component 를 재사용 하지 않고 반복적인 코드가 너무 많음


