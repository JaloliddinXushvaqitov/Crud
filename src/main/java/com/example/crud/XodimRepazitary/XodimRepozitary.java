package com.example.crud.XodimRepazitary;

import com.example.crud.Entaite.Xodim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface XodimRepozitary extends JpaRepository<Xodim,Integer> {
    // Optional bilan faqat findBy bilan ishlaydi;
    Optional<Xodim> findByEmail(String email);
}
