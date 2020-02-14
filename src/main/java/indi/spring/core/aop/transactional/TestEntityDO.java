package indi.spring.core.aop.transactional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "test_entity")
public class TestEntityDO {

    @Id
    @GeneratedValue// 自动生成(mysql)
    @Column(name = "id_")
    private Long id;

    @Column
    private String username;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("TestEntityDO [id=");
        builder.append(id);
        builder.append(", username=");
        builder.append(username);
        builder.append("]");
        return builder.toString();
    }

}
