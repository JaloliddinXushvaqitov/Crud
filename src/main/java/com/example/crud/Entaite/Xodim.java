package com.example.crud.Entaite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.nio.MappedByteBuffer;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Xodim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String ism;
    @Column(nullable = false)
    private String fam;
    @Column(nullable = false)
    private String maosh;
    @Column(nullable = false)
    private String lavozim;
    @Column(nullable = false,unique = true)
    private String email;

    @OneToMany(mappedBy = "xodim",cascade = CascadeType.ALL)
    private List<Manzil> manzilList;
}
