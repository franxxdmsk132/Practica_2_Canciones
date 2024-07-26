package com.eabmodel.practica_2_canciones.Repository;

import com.eabmodel.practica_2_canciones.Model.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicRepository extends JpaRepository<Music, Integer> {
}
