package com.eabmodel.practica_2_canciones.Controller;

import com.eabmodel.practica_2_canciones.Model.Music;
import com.eabmodel.practica_2_canciones.Service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/music")

public class MusicController {

    @Autowired
    private final MusicService musicService;

    public MusicController(MusicService musicService) {
        this.musicService = musicService;
    }
    @GetMapping("/list")
    public ResponseEntity<?> getAll() {
        List<Music> musicList = musicService.listAll();
        if (musicList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No music found!");
        }
        return ResponseEntity.ok(musicList);
    }
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Music music) {
        try {
            Music created= musicService.create(music);
            return ResponseEntity.status(HttpStatus.CREATED).body("Music created successful!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating music: " + e.getMessage());
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Music newMusic) {
        try {
            Music updatedPerson = musicService.updateMusic(id, newMusic);
            return ResponseEntity.ok("Music updated successful" );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating music: " + e.getMessage());
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable Integer id) {
        try {
            musicService.delete(id);
            return ResponseEntity.ok("Music deleted successful!" );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting music" + e.getMessage());
        }
    }
}
