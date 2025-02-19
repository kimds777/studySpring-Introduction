package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    //ȸ������
    public Long join(Member member) {

        validateDuplicateMember(member); //�ߺ� ȸ�� ����
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // ���� �̸��� �ִ� �ߺ� ȸ��X
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m -> {
//            throw new IllegalArgumentException("�̹� �����ϴ� ȸ���Դϴ�.");
//        });
//      �� �ּ��� �ڵ带 �Ʒ��� ���� �ٿ��� �� �ִ�
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
            throw new IllegalArgumentException("�̹� �����ϴ� ȸ���Դϴ�.");
        });
    }

    // ��ü ȸ�� ��ȸ
    public List<Member> findMembers() {

        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
