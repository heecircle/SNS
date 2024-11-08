# Assignment - Very Simple SNS

- 해당 과제는 총 7개의 Step으로 구성되어 있습니다.
- 모든 과제는 C4-Cometrue 깃허브 레포에서 관리되어야 하며, 반드시 각 Step이 종료될 때 마다 PR 요청을 날려야 합니다.
    - 물론, 다른 사람들의 PR에 대한 리뷰도 가능합니다.
- 서버는 필요할 때 편하게 말씀해 주세요.
- 상당히 고민이 많이 필요한 주제입니다. 일부 Step에 대해서 제공하는 키워드가 힌트가 될 수 있으니, 키워드와 관련한 학습을 진행하는 것을 권장합니다.
- 궁금증이 있으면 주저하지 말고 서로 커뮤니케이션하고, 그럼에도 해결되지 않는다면 질문을 남겨주세요.
- 각 PR 마다 설계 의도를 작성해주세요. 좀 더 효율적인 리뷰가 가능할 겁니다.

## 프로젝트 설명

`기본적인 SNS 만들기`

- 간단한 기능을 제공하는 SNS를 개발해 봅시다.
- 본격적인 프로젝트 경험이 부족하거나, 단순 기능 개발만 중점적으로 진행하셨던 분들에게 추천 드립니다. (**더 나은 프로젝트를 만들기 위해 무엇이 필요한가?** 에 대한 쉬운 예제라고 생각하시면 좋습니다.)

### Step 1. 유저 기능 개발

#### 구현사항

- 유저 기능을 개발해 주세요.
- 기본적인 SNS에서 필요한 기능을 잘 생각해서, 회원가입 시 입력할 수 있도록 해 주세요.

#### 프로그래밍 요구사항

- 금칙어가 있나요? 사용자에게 혼란을 줄 수 있는 키워드는 들어올 수 없도록 원천 차단이 필요합니다.
- 회원가입에 OAuth를 사용해도 되고, 그렇지 않아도 됩니다. 해당 프로젝트의 주 목적은 로그인/인증이 아니니, 편한 방법으로 구현해 주세요.

### Step 2. 피드 관련 기능 개발

#### 구현사항

- 피드를 작성하는 기능을 개발해 주세요.✅
- 일단은 본인의 피드만 보이도록 해야 합니다.✅
- 피드는 255자 제한이 걸려있고, 이미지를 첨부할 수 있습니다.✅
    - 이미지는 이미지 업로드 후, 생성된 이미지의 URL을 첨부하는 방식으로 구현해 주세요.✅
- 피드 조회시에는 페이징을 사용해야 합니다. 페이징 기법을 조회 한 후, 일반적인 SNS에서 활용할 수 있는 적당한 방법을 찾아서 사용해 주세요.✅
- 특정 사용자가 작성, 조회 API를 과도하게 호출하지 못하도록 방어합시다.✅
    - 분당 5회 이상 호출하지 못하게 하도록 방어해 주세요.✅
    - 엄밀한 구현을 위해선 다소 복잡할 수 있으므로, 저희는 **자료구조를 통해 API 횟수를 기록하도록** 설정해 주면 됩니다.✅

#### 프로그래밍 요구사항

- 데이터가 가장 많이 쌓이게 될 테이블은 바로 피드 테이블입니다. 인덱스를 모른다면 인덱스에 대해 학습하시고, 피드와 관련된 모든 쿼리가 최대한 인덱스를 타도록 개발해 주세요.
- 분 단위 task는 `cron`을 통해 처리하도록 해 주세요.✅
- 팁이라면, 일반적인 SNS에서 조회를 여러 번 광클해도 실제로는 요청이 서버로 전달이 안 되는 경우가 많습니다. 그렇기에, 정상적인 사용 케이스에서 API 요청 한도를 넘는 경우는 많이 없습니다.✅

### Step 3. 팔로우 기능 개발

#### 구현사항

- 사용자는 다른 사용자를 팔로우할 수 있습니다.✅
- 특정 사용자를 팔로우한 경우, 타임라인에 해당 사용자의 피드를 조회할 수 있어야 합니다.✅
- 상대방이 나를 팔로우한 경우, "팔로워" 로 취급되며 별도로 카운트 됩니다.✅
- 사용자는 팔로우/팔로워 목록을 갖고 있으며, 단순 카운트 외에도 목록을 조회할 수 있습니다.✅
    - 해당 목록 조회 시, 서로 팔로우 상태인 경우 별도 표시가 필요합니다.✅
- 내가 아닌 다른 사용자의 피드는, "좋아요" 를 남길 수 있습니다.

#### 프로그래밍 요구사항

- 데이터의 양이 본격적으로 많아집니다.✅
- 일반적으로 팔로우 한 사용자의 피드를 조회할 때는 "IN" 쿼리를 사용하겠죠?✅
    - 서브쿼리로 쿼리가 나가지 않도록 유의하면 좋을 것 같습니다.✅
    - 팔로우한 사용자가 너무 많으면 쿼리가 기형적으로 변할 수 있긴 하지만, 이번에는 해당 사항은 고려하지 않습니다.✅
- Step 2에서 페이징을 사용하여 쿼리를 구현한 만큼, Step 3에서도 큰 구조는 변경되어선 안 됩니다.✅
- 관계를 표현하는 별도의 테이블이 필요합니다.✅
    - 힌트를 드리자면, 팔로우 유저/팔로워 유저를 표현하는 각각의 칼럼에 대해 인덱스를 거시는게 좋습니다.✅
    - 가능하면, 인덱스를 안 걸고 쿼리를 수행했을 때의 시간/인덱스를 걸고 쿼리를 수행했을 때의 시간을 기록하시면 많은 도움이 될 거에요.✅
- 화면에 피드가 보여 "좋아요"를 눌렀는데, 그 시간동안 다른 사용자가 해당 피드를 삭제할 수 있습니다. 이 경우, 어떻게 처리해야 할까요?✅ transaction, delete했을 때 확인하고 넣기,
  foreignkey 설정

### Step 4. 검색 기능 개발

#### 구현사항

- 검색 기능을 개발합니다.✅
- 모든 피드에 대해 검색/팔로우한 사용자의 피드만 검색 옵션이 존재합니다.✅
- 해당 검색 결과 또한 페이징을 사용하도록 해 주세요.✅
- 혹시 로그를 사용하고 계신가요? **다른 로그의 로그 수준을 조정하고, 검색 API가 호출될 때 마다 특정 레벨의 로그가 출력되도록 해 주세요.** (다른 로그보다 로그 수준이 높아야 합니다.)✅

#### 프로그래밍 요구사항

- 일반적으로, 검색은 DB 인덱스로 해결하기 어렵습니다.
    - MySQL은 Full Text Index, MongoDB는 MongoDB Atlas에서 제공하는 Text Search 등을 활용할 수 있으나, 데이터의 양이 많을 경우 해당 방식 보다는 별도의 검색 서버를
      구축하는 것이 권장됩니다.
    - 상술한 인덱스를 적용해 보셔도 좋고, 그렇지 않다면 해당 기능에 한 해 인덱스를 붙이지 않고 만들어 보셔도 좋습니다.
- Spring 을 사용하는 경우, Log Level는 TRACE - DEBUG - INFO - WARN - ERROR 로 구성되어 있습니다. ✅
    - 다른 로그의 레벨을 DEBUG 이하로 낮춰주시고, 검색 API 호출만 INFO로 맞춰주세요. ✅
    - 왜 그러냐고요? 이후에 알게 됩니다!✅

### Step 5. 인기 해시태그 기능 개발

#### 구현사항

- 이번에는, 실시간으로 호출되지 않는 기능을 개발할 겁니다.✅
    - 간단하게 말해서, 사용자가 직접 호출하지 않고, 내부적으로 동작하는 기능이라고 생각하면 됩니다. ✅
- 10분에 한 번, 인기 해시태그 목록을 갱신합니다.✅
    - 최근 1시간 동안 작성된 피드에 대해, 피드에 포함된 해시태그 (ex. `#안녕`) 을 가져와, 가장 많이 등장한 해시태그 상위 5개를 저장합니다. ✅
- 사용자가 인기 해시태그 조회를 요청한 경우, 기록한 상위 5개의 해시태그 목록을 반환하도록 해 주세요.

#### 프로그래밍 요구사항

- 인기검색어는 우리가 생각하는 것 처럼 실시간이 아닙니다. 위와 유사한 방식으로 주기적으로 갱신한다고 보시면 좋아요. ✅
    - 왜냐고요? 실시간인 경우, 소수의 어뷰저에 의해 검색어가 실시간으로 바뀔 수 있거든요! ✅
    - 사용되는 자원도 상당히 부담되고요...✅

### Step 6. 통계성 어드민 기능 개발

#### 구현사항

- 일반적으로, 사용자의 정보, 전체적인 서비스와 활성화도를 파악하기 위해 통계성 집계를 주기적으로 진행합니다. ✅
- 일일 피드 작성 양, 누적 피드 작성 양을 매일 계산해서 별도의 테이블에 저장하도록 해 주세요.✅
    - 어드민 API 호출 시, 최근 일주일간의 데이터를 조회해서 반환합니다.✅
- 일일 주요 검색어 키워드를 상위 10개 추출해 주세요.
    - 어드민 API 호출 시, 최근 일주일간의 데이터를 조회해서 반환합니다.

#### 프로그래밍 요구사항

- 피드 통계는 크게 문제가 없을 것 같은데, 검색어는 어떻게 구할 수 있을까요?
    - 힌트는, Step 4에서 우리가 로그를 조정한 것과 연관이 있습니다.
    - 로그의 활용도를 알 수 있는 use-case 라고 보시면 좋을 것 같아요.

### Step 7. 성능 테스트

#### 구현사항

- 실제로 개발한 서버의 성능이 어떨까요?
- 사용자의 사용 시나리오를 설계하고, 이를 활용해 스트레스 테스트 툴을 사용한 성능 테스트를 진행해봅시다.
    - 여기서는 nGrinder를 사용해 봅시다.
