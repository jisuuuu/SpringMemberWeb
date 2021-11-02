package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();//동시성 문제 고려되지 않음, 실무에서는 HashMap 사용 X
    private static long sequence = 0L;//실무에서는 AtomicLong

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //Null인경우 체크해주는 것
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {//java 실무에서 List 많이 사용
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
