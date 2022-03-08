package hello.hellospring.domain;

import javax.persistence.*;

@Entity //jpa가 관리하는 Entity 라는 뜻
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB가 알아서 생성될 수 있도록하는 것
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
