# Follow

최종 구조
userId, followId를 복합키로 갖는 follow table 구조 설계

1. 했던 시도
   기존에는 foreign키로 userId와 followId를 user의 pk를 참조하는 형태로 구성
   발생했던 문제

1) 중복된 데이터가 있는지 확인하지 못함 (insert 할 때마다 계속 해당 데이터 존재하는지 확인해야함)
2) MySQL의 특성상 외래키를 설정하게 되면 무조건 index 생김 -> index의 여부에 따라 api 호출 시간의 차이를 확인하고 싶었음

=> 데이터의 정합성을 체크하는 로직을 별도로 두고 팔로우 팔로워 체크를 먼저 합시다.

2. embeddable annotion 사용시에 주의할 점

네, JPA에서 복합키를 사용할 때 @Embeddable 클래스를 만들고, 이를 엔티티의 복합키로 설정하려면 해당 클래스에 Serializable 인터페이스를 구현하는 것이 일반적으로 권장됩니다.

Serializable 구현이 필요한 이유는 다음과 같습니다:

JPA 요구사항: JPA는 복합키를 엔티티에 임베디드 타입으로 사용하기 위해 Serializable을 구현해야 한다고 명시하고 있습니다. JPA는 복합키 클래스가 직렬화 가능해야 하므로 Serializable을
구현함으로써 JPA가 내부적으로 해당 키를 식별하고 처리하는 데 필요한 일관성을 보장할 수 있습니다.

캐싱 및 네트워크 전송: 복합키가 사용되는 엔티티가 캐시되거나 네트워크를 통해 전송될 때, Serializable이 적용되어야만 직렬화와 역직렬화가 원활히 수행될 수 있습니다.

식별 및 비교: @Embeddable 클래스에서 equals와 hashCode 메서드를 반드시 구현하여 엔티티 비교가 올바르게 이루어지도록 해야 합니다. Serializable은 이러한 비교와 관련된 JPA의 기본
요구 사항을 충족하기 위해 필요한 경우가 많습니다.

따라서, @Embeddable로 복합키를 정의할 때 Serializable을 구현하는 것이 좋습니다.

# 90174건 데이터 기준

index를 활용한 성능 개선

### Index 적용 이전과 이후 성능 비교

<img src="../../../SNS/IMG/indexBefore.png"/>

#### 평균 35.4

<img src="../../../SNS/IMG/indexAfter.png"/>

#### 평균 4.8

### 약 87%의 성능 개선

### 쿼리문 변경을 통한 성능개선

#### 서브쿼리를 통한 값 도출

<b>0.0009225</b>
<div> select user.img_url, user.nickname, user.id,
(select count(*) from follow f where f.user_id = user.id and f.follow_id = 1)
from follow
 </div>


<b>0.00371175</b>
<div>select follow1.follow_id,
u.user_id,
u.id,
u.nickname,
u.img_url,
sum(case when follow1.user_id = follow2.follow_id then 1 else 0 end) as equalCnt
from follow follow1 join follow follow2 on follow1.follow_id = follow2."</div>
