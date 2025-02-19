package com.krishna.runmate.run;

import com.krishna.runmate.exceptions.RunNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/runs")
public class RunController {

    private final RunRepository runRepository;

    public RunController(RunRepository runRepository) {
        this.runRepository = runRepository;
    }

    // HTTP GET method to retrieve all runs
    @GetMapping("")
    List<Run> findAll() {
        return runRepository.findAll();
    }

    // HTTP GET method to retrieve a run by id
    @GetMapping("/{requestedId}")
    Run findById(@PathVariable Integer requestedId) {
        Optional<Run> run = runRepository.findById(requestedId);
        if (run.isEmpty()) {
            throw new RunNotFoundException();
        }
        return run.get();
    }


    // HTTP POST method to create a new run
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    void create(@Valid @RequestBody Run run) {
        runRepository.create(run);
    }

    // HTTP PUT method to update an existing run
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{requestedId}")
    void update(@Valid @RequestBody Run run, @PathVariable Integer requestedId) {
        runRepository.update(run, requestedId);
    }

    // HTTP DELETE method to delete a run
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{requestedId}")
    void delete(@PathVariable Integer requestedId) {
        runRepository.delete(requestedId);
    }
}
