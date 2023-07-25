package com.lbd.testliquibase.service;


import com.lbd.testliquibase.domain.TutorialDTO;
import com.lbd.testliquibase.entity.TutorialEntity;
import com.lbd.testliquibase.exceptions.MyException;
import com.lbd.testliquibase.repository.TutorialRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
@EnableCaching
public class TutorialService {


    private final TutorialRepository repository;

    @Cacheable(value = "tutorials")
    public List<TutorialDTO> getAllTutorials() {

        log.info("All data");
        return repository.findAll().stream().map(this::mapValues).collect(Collectors.toList());

    }

    @Cacheable(value = "tutorial", key = "#id")
    public TutorialDTO getTutorial(int id) {
        return repository.findById(id).map(this::mapValues).orElseThrow(() -> new MyException("Id not found", 404));
    }

    @CacheEvict(value = {"tutorials", "tutorial"}, allEntries = true)
    //@CacheEvict(value = {"tutorials", "tutorial"})
   //@CacheEvict(value = "tutorial", key = "#id")
    public void deleteTutorial(int id) {

        repository.deleteById(id);
    }

    @CacheEvict(value = "tutorials", allEntries = true)
    public TutorialDTO createTutorial(TutorialDTO item) {
        return mapValues(repository.save(toEntity(item)));
    }

    @CachePut(value = "tutorial", key = "#id")
    @CacheEvict(value = "tutorials" , allEntries = true)
    public TutorialDTO updateTutorial(TutorialDTO item, int id) {
        repository.save(TutorialEntity.builder().id(id)
                .description(item.getDescription())
                .name(item.getName())
                .duration(item.getDuration())
                .build());
        return item;
    }


    private TutorialDTO mapValues(TutorialEntity item) {
        return TutorialDTO.builder()
                .id(item.getId())
                .description(item.getDescription())
                .name(item.getName())
                .duration(item.getDuration())
                .build();
    }

    private TutorialEntity toEntity(TutorialDTO item) {
        return TutorialEntity.builder()

                .description(item.getDescription())
                .name(item.getName())
                .duration(item.getDuration())
                .build();
    }


}
