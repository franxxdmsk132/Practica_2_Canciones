package com.eabmodel.practica_2_canciones.Repository;

import com.eabmodel.practica_2_canciones.Model.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayListRepository extends JpaRepository<PlayList, Integer> {
    Optional<PlayList> findByName(String name);}
