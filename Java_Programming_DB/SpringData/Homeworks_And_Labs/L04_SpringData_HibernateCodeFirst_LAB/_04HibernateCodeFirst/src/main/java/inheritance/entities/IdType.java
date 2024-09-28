package inheritance.entities;

import jakarta.persistence.*;

@MappedSuperclass
public class IdType {
    @Id
    // @GeneratedValue(strategy = GenerationType.TABLE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    @Basic
    protected String type;

}
