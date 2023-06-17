package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;


@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void join() {
        Member member = new Member();
        member.setName("spring");

        Long savedId = memberService.join(member);

        Member findMember = memberService.findOne(savedId).get();
        assertThat(findMember.getName()).isEqualTo(member.getName());
    }

    @Test
    void joinDuplicate(){
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        Long savedId1 = memberService.join(member1);

        assertThatIllegalStateException()
                .isThrownBy(() -> memberService.join(member2))
                .withMessage("이미 존재하는 회원입니다.");

    }
}
