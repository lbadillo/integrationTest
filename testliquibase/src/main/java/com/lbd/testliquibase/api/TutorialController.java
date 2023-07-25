package com.lbd.testliquibase.api;


import com.lbd.testliquibase.domain.TutorialDTO;
import com.lbd.testliquibase.service.TutorialService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/tutorial")
@Slf4j
public class TutorialController {

    private TutorialService service;

    @PostMapping
    public TutorialDTO createTutorial(@RequestBody TutorialDTO item){
        return service.createTutorial(item);
    }

    @PutMapping
    public TutorialDTO updateTutorial(@RequestBody TutorialDTO item){
        return service.updateTutorial(item, item.getId());
    }
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTutorial(@PathVariable int id) {
        service.deleteTutorial(id);
    }
    @GetMapping
    public List<TutorialDTO> getAllTutorials(){
        return service.getAllTutorials();
    }

    @GetMapping(value = "/{id}")
    public TutorialDTO getTutorialById(@PathVariable int id){
        return service.getTutorial(id);

    }
}
