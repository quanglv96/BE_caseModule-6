package ndtq.controller;


import ndtq.model.Singer;
import ndtq.service.Singer.ISingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/singers")
public class SingerController {
    @Autowired
    ISingerService singerService;
    @GetMapping
    public ResponseEntity<Iterable<Singer>> getAllSinger() {
        return new ResponseEntity<>(singerService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Singer> getSinger(@PathVariable("id") Long id) {
        Optional<Singer> singer = singerService.findById(id);
        return new ResponseEntity<>(singer.get(), HttpStatus.OK);
    }

    @PostMapping("/newSinger")
    public ResponseEntity<Iterable<Singer>> newSinger(@RequestBody Singer singer) {
        singerService.save(singer);
        return new ResponseEntity<>(singerService.findAll(), HttpStatus.OK);
    }
    @PostMapping ("/update/{id}")
    public ResponseEntity<Iterable<Singer>> update(@RequestBody Singer singer,
                                                   @PathVariable("id") Long id) {
        Optional<Singer> singerOptional = singerService.findById(id);
        if(singerOptional.isPresent()) {
            singerService.save(singer);
            return new ResponseEntity<>(singerService.findAll(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Iterable<Singer>> delete(@PathVariable Long id) {
        Optional<Singer> singerOptional = singerService.findById(id);
        if(singerOptional.isPresent()) {
            singerService.remove(id);
            return new ResponseEntity<>(singerService.findAll(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/search")
    public ResponseEntity<Iterable<Singer>> search(String name) {
        return new ResponseEntity<>(singerService.findAllByName(name), HttpStatus.OK);
    }
    @GetMapping("/addSong")
    public ResponseEntity<?> addSong(@RequestParam("idSong") Long idSong,@RequestParam("idSinger") Long idSinger) {
        singerService.addSingerSong(idSong,idSinger);
        return ResponseEntity.ok( HttpStatus.OK);
    }
}
