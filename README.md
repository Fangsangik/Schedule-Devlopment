# Schedule-Devlopment

## 🛠️ Tools :  <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> <img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=github&logoColor=Green"> <img alt="Java" src ="https://img.shields.io/badge/Java-007396.svg?&style=for-the-badge&logo=Java&logoColor=white"/>  <img alt="Java" src ="https://img.shields.io/badge/intellijidea-000000.svg?&style=for-the-badge&logo=intellijidea&logoColor=white"/>

## 👨‍💻 Period : 2024/11/12 ~ 2024/11/13

## ERD 
![ERD](https://github.com/user-attachments/assets/1b423880-afe5-4040-a287-3e978a1b3117)
회원과 스케줄을 1 : 1 관계를 성립해 회원의 값이 삭제 되면 스케줄의 값이 삭제 되도록 설정했고, 
스케줄과 댓글을 1 : 1 관계를 성립해 스케줄이 삭제되면 댓글도 삭제되게 했다. 
회원과 댓글 또한 1 : 1 관계를 성립해, 회원 삭제시 댓글 삭제되게 했다. 

## API 명세서 
<a href>https://www.postman.com/gold-robot-131519/myapi/documentation/4xuox4d/scheduleapi?workspaceId=c64232d4-fdd3-46da-b127-41e93826dc0a

## 👨‍💻 About Project
### Schedeule 
- Schedule
  - Schedule에 필요한 필드값 

- ScheduleDto
  - Dto & Valdiation 

- ScheduleMapper
  - Entity와 Dto에 대한 변환

- ScheduleResponseDto
  - member에 대한 정보를 전부 가져오는 것이 아닌 member에 id 값만 가져올 수있도록 설정 했습니다.  
    
- Repository
  - JpaRepository를 상속 받아 사용

 - Service
   - CRUD 구현

### Member 
- Member
  - Member에 필요한 필드 값

- MemberDto
  - Dto & Validaiton 

- Repository
  - JPA를 상속받아 구현

- Service
  - CRUD 구현

- MemberResponseDto
  - 비밀번호 같은 민감한 정보는 보여지면 안된다고 생각해 Response 값을 제어 했습니다.  

- MemberMapper
  - Dto와 Entity 변환

### Comment
- Comment
  - Comment에 필요한 필드 값

- CommentDto
  - Dto & Validaiton

- CommentResponseDto
  - comment에 필요한 값과 스케줄 pk 값만 나타낼 수 있도록 설정  

- Repository
  - JPA를 상속받아 구현

- Service
  - CRUD 구현

- CommentMapper
  - Dto와 Entity 변환
 
### Config
- QueryDslConfig : JPAQueryFactroy를 Bean으로 등록
- WebConfig : LoginCheckFilter가 작동하여 사용자가 로그인했는지 확인하고, 필요에 따라 접근을 제한하거나 특정 로직을 적용
- PasswordEncoder : Password 암호화

### auth 
- LoginCheckFilter : whiteList에 있는 url pattern들만 필터를 거치지 않고 적용
- 나머지는 인증 인가 필터를 통해 인증된 사용자만 다음 일을 진행 할 수 있음 

## 🥵 Trouble Shooting 
1. 연관관계에 대한 고민 때문에 Member와 Comment를 연관관계를 성립하는게 맞는지에 대한 고민 

if) 여러 회원이 여러 댓글을 사용 해야 한다면 회원에 comment를 주는게 맞지만 그게 아니라면, 회원 한명이 댓글을 하나씩 관리하는 것이 맞다. 
설계에 따라 연관관계의 설정이 달라질 것 같아 그 부분에 대한 고민 

2. BaseEntity를 설정 했는데 날짜에 대한 값이 안들어가는 원인 
abstract에 대한 설정을 빼먹었습니다.

3. Page 조회 할때 MemberDto가 같이 조회가 안되어서 member를 Join 해서 가져와서 활용 

## 🚀 Refactor
1. PostMan에 모든 값을 보여줄 필요가 없다고 느꼈고, ResponseDto를 따로 만들어서 Response 값을 제어 했다.
   controller에 Response 값을 받을 수 있도록 설계
2. Schedule에서 CommentCount가 즉각 반영이 안되어서 QueryDsl로 문제를 해결
3. QueryDsl을 사용하기 위해서 생성자 따로 생성
4. Mapstruct 부분에 ResponseDto 값 추가
5. 회원과 댓글 연관관계 setting 

## 👨‍💻 이번 프로젝트때 시도해 본 것과 이유 

- Entity와 Dto에 대한 변환 (MapStruct)
    이전에는 Converter라는 클래스를 Beand으로 직접 등록해서 사용했다. 이전 과제에서의 Comment를 참고하여 Bean으로 관리 할꺼면 Mapstruct를 사용해보라는 가이드를 주셔서 이번에 MapStruct을 적용했다.
    MapStruct를 사용하면 좋은 이유는 일관성, 수동으로 작성한 코드의 실수를 감소 할 수 있지 않을까 생각이 들었다.
    하지만 자동이지만 꼭 impl을 확인해 내가 의도 한 바와 같은지 봐야한다.

 - QueryDSL을 사용해 각 필드값들 조회
     JPA 명명법을 사용해도 되는데 왜 QueryDSL을 사용했는가? 하면
     JPA명명 규칙이 늘어나면, 오타라던지, 정확한 의미를 파악하기 어렵기도 하고,
     자동으로 join 해서 가져온다고 하지만, QueryDSL을 사용함으로 join을 직접 지정해서 사용 할 수 있기 때문
    
- Entity에 메서드 생성
   좋아하는 방법은 아니지만, Entity에 update 기능 메서드를 생성했다.
   명확한 개별적 책임을 부여하고, 비즈니스 로직 반영에 검증이나 수정할때 유지 보수성 면에서 이득이라고 생각이 들었다 .

- ServiceInterface 
  작은 프로젝트에서 interface를 생성해 만드는 것은 비용적인 면에서 소모적이라는 것은 알고 있다.
  IoC 원칙을 생각해 코드를 작성한다면 interface를 적용해보고 싶었다. 
  interface를 적용하면, 우선 service코드를 작성할때 기능을 빼먹는 일은 없다고 생각이 들었고, IoC 원칙을 지켜서 작성해보고 싶었다.

- 지난번 과제떄 Response를 제어하지 못한 부분에 대해 Dto를 따로 만들어 Response 값 제어를 했습니다.
  불필요한 정보들을 Response에 보여지게 되면 위험 노출도 크고, 지금은 table도 많지 않고 그래서 크게 상관 없겠지만, 프로잭트가 커지면, 값을 제어하기도 힘들고 bulk하게 나갈경우 문제가
  생길수 있다고 생각했습니다.

- PasswordEncoder룰 Util로 관리해야 할까 아님 Component로 관리해야 할까?
  1. 유틸리티 클래스 (static 메서드로 사용)
  인스턴스 생성 없이 메서드를 static으로 호출 가능

  2. @Component로 관리
  스프링 컨테스트에서 관리, 의존성 주입을 통해 클래스 내에서 쉽게 사용 가능 
   
  판단 여부, test의 방향성과, 프로잭트 설계 방향에 따라 달라질 것 같다. 
