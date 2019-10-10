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
