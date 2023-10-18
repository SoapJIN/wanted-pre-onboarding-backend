package wanted.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name= "company")
@Getter
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String country;

    @Column
    private String area;
}
