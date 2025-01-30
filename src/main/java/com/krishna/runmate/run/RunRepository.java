package com.krishna.runmate.run;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RunRepository {

    private List<Run> runs = new ArrayList<>();

    List<Run> findAll() {
        return runs;
    }

    Optional<Run> findById(int id) {
        return runs.stream()
                .filter(run -> run.id() == id)
                .findFirst();
    }

    @PostConstruct
    private void init() {
        runs.add(new Run(1,
                "Sunday Morning RUn",
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(1),
                1,
                Location.OUTDOOR));
        runs.add(new Run(2,
                "Monday Evening Run",
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(2),
                2,
                Location.INDOOR));
    }
}
