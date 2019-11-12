# Small shopping mall
> Spring boot & JPA 활용 infrean 강의 실습을 하며, JPA study 합니다.


#### Spec 
- REST API
- Spring boot 2.x
- JPA 
- Gradle 
- Lombok
- H2  
- Thymeleaf

#### DB modeling
- 회원:주문 = 1:N
- 주문:상품 = N:N

- Member  
- Item
- Order
- OrderItem
- OrderStatus


#### 요구사항
- [ ] 회원은 상품을 주문할 수 있다.
- [ ] 주문 시 여러 종류의 상품을 선택할 수 있다.
 

__[회원기능]__
- [ ] 회원 등록
- [ ] 회원 조회
        
__[상품기능]__ 
- [ ] 상품 등록
- [ ] 상품 수정
- [ ] 상품 조회

__[주문기능]__
- [ ] 상품 주문
- [ ] 주문 내역 조회
- [ ] 주문 취소

__[기타요구사항]__
- 상품은 제고 관리 필요 
- 상품의 종류는 도서, 음반,영화
- 상품은 카테고리로 구분한다.
- 상품 주문 시 배송 정보를 입력할 수 있다.

---

## Cascade

ALL : persist 전파



mybatis, jdbctemplate
data change
밖에서 data 변경해서 직접 날려야함 
item 재고 + 또 날려야함

// 변경된 내역 감지 = 더티체킹 


DDD domain model pattern
비즈니스 로직이 대부분 엔티티에 있는 경우
서비스 계층은 단순히 엔티티에 필요한 요청을 위임하는 역할을 한다
엔티티가 비즈니스 로직을 가지고 객체 지향 특성을 적그 활용하는 것을 도메인 모델 패턴이라한다
<-> 트랜잭션 스크립트 패턴 : 서비스 계층에서 대부분의 비즈니스 로직을 처리하는 방식




