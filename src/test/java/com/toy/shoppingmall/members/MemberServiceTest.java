package com.toy.shoppingmall.members;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Java6Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("회원가입")
    @Test
    void join() {
        // given
        Member member = new Member();
        member.setName("chloe");

        // when
        Long result = memberService.join(member);

        // then
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(memberRepository.findOne(result).getId());
    }

    @DisplayName("중복회원 시 예외발생")
    @Test
    void validateName_thenException() {
        Member member1 = new Member();
        member1.setName("chloe1");

        Member member2 = new Member();
        member2.setName("chloe1");

        Assertions.assertThatIllegalStateException()
                .isThrownBy(() ->  {
                    memberService.join(member1);
                    memberService.join(member2);
                });
    }
}