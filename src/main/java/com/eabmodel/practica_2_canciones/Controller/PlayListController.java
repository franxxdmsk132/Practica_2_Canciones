package com.eabmodel.practica_2_canciones.Controller;

import com.eabmodel.practica_2_canciones.Model.PlayList;
import com.eabmodel.practica_2_canciones.Service.PlayListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlists")
public class PlayListController {

    @Autowired
    private final PlayListService playListService;

    public PlayListController(PlayListService playListService) {
        this.playListService = playListService;
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAll() {
        List<PlayList> playLists = playListService.listAll();
        if (playLists.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No playlists found!");
        }
        return ResponseEntity.ok(playLists);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody PlayList playList) {
        try {
            PlayList created = playListService.create(playList);
            return ResponseEntity.status(HttpStatus.CREATED).body("Playlist created successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating playlist: " + e.getMessage());
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getByName(@PathVariable String name) {
        PlayList playList = playListService.getByName(name);
        if (playList == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Playlist not found with name: " + name);
        }
        return ResponseEntity.ok(playList);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody PlayList newPlayList) {
        try {
            PlayList updatedPlayList = playListService.update(id, newPlayList);
            return ResponseEntity.ok("Playlist updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating playlist: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            playListService.delete(id);
            return ResponseEntity.ok("Playlist deleted successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting playlist: " + e.getMessage());
        }
    }
}
