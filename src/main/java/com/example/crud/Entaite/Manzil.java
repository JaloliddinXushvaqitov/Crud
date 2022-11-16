package com.example.crud.Entaite;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Manzil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
   // @Column(nullable = false)
    private String viloyat;
   // @Column(nullable = false)
    private String tuman;
    //@Column(nullable = false)
    private String kocha;
    // bu anatatsiya orqali bitta repozitary orqali javob olig=nganda
    // ishlatiladi bilmasa json ga ogiraolmay qoladi
    @JsonIgnore
    @ManyToOne
    private Xodim xodim;
}
