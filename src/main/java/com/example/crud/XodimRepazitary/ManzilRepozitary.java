package com.example.crud.XodimRepazitary;

import com.example.crud.Entaite.Manzil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManzilRepozitary  extends JpaRepository<Manzil, Integer> {
    List<Manzil> findByXodimId(Integer xodim_id);
}
