package hello.hellospring.service;

import hello.hellospring.domain.Member;

import static org.assertj.core.api.Assertions.*;

import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class MemberServiceTest {

    MemberService memberService;

    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

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

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}