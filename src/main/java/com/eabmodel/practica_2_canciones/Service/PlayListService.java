package com.eabmodel.practica_2_canciones.Service;

import com.eabmodel.practica_2_canciones.Model.PlayList;
import com.eabmodel.practica_2_canciones.Repository.PlayListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayListService{
    @Autowired
    private final PlayListRepository playListRepository;

    public PlayListService(PlayListRepository playListRepository) {
        this.playListRepository = playListRepository;
    }

    public PlayList create(PlayList playList) {
        return playListRepository.save(playList);

    }
    public PlayList getByName(String name) {
        Optional<PlayList> playListOptional = playListRepository.findByName(name);
        return playListOptional.orElse(null);
    }
    public List<PlayList> listAll() {
        List<PlayList> playLists = playListRepository.findAll();
        return playLists;
    }
    public PlayList update(Integer id, PlayList newPlayList) {
        Optional<PlayList> playListOptional = playListRepository.findById(id);
        if (playListOptional.isPresent()) {
            PlayList existingPlayList = playListOptional.get();
            existingPlayList.setName(newPlayList.getName());
            existingPlayList.setDescription(newPlayList.getDescription());
            existingPlayList.setSongs(newPlayList.getSongs());
            return playListRepository.save(existingPlayList);
        } else {
            throw new RuntimeException("PlayList not found with id: " + id);
        }
    }
    public void delete(Integer id){
        playListRepository.deleteById(id);
    }



}
