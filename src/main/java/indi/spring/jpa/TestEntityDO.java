package indi.spring.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "test_entity")
public class TestEntityDO {

    @Id
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
