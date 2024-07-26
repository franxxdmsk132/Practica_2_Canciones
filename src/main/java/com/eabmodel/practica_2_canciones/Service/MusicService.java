package com.eabmodel.practica_2_canciones.Service;

import com.eabmodel.practica_2_canciones.Model.Music;
import com.eabmodel.practica_2_canciones.Repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MusicService {

    private final MusicRepository musicRepository;

    @Autowired
    public MusicService(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }

    public Music create(Music song) {
        return musicRepository.save(song);
    }
    public Music getById(Integer id) {
        Optional<Music> music = musicRepository.findById(id);
        return musicRepository.getById(id);
    }
    public List<Music> listAll() {
        List<Music> music = musicRepository.findAll();
        return music;
    }
    public Music updateMusic(Integer id, Music newMusic) {
        Optional<Music> musicOptional = musicRepository.findById(id);
        if (musicOptional.isPresent()) {
            Music existingMusic = musicOptional.get();
            existingMusic.setTitle(newMusic.getTitle());
            existingMusic.setArtist(newMusic.getArtist());
            existingMusic.setAlbum(newMusic.getAlbum());
            existingMusic.setYear(newMusic.getYear());
            // Update other fields as necessary
            return musicRepository.save(existingMusic);
        } else {
            throw new RuntimeException("Music not found with id: " + id);
        }
    }
    public void delete(Integer id){
        musicRepository.deleteById(id);
    }

}
