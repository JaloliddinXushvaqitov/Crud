package com.example.crud.Payload;

import com.example.crud.Entaite.Manzil;
import lombok.Data;

import java.util.List;

@Data
public class XodimDTO {
    private String ism;
    private  String fam;
    private  String maosh;
    private String lavozim;
    private  String email;
    private List<Manzil> manzilList;
}
