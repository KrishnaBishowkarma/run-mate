package com.krishna.runmate.run;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Repository
public class RunRepository {

    private static final Logger log = LoggerFactory.getLogger(RunRepository.class);
    private final JdbcClient jdbcClient;

    public RunRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Run> findAll() {
        return jdbcClient.sql("select * from run").query(Run.class).list();
    }

    public Optional<Run> findById(Integer requestedId) {
        return jdbcClient.sql("SELECT * FROM run WHERE id = :requestedId")
                .param("requestedId", requestedId)
                .query(Run.class)
                .optional();
    }

    public void create(Run run) {
        var update = jdbcClient.sql("INSERT INTO Run (id, title, startedOn, completedOn, miles, location) values (?, ?, ?, ?, ?, ?)")
                .params(List.of(run.id(), run.title(), run.startedOn(), run.completedOn(), run.miles(), run.location().toString()))
                .update();
        Assert.state(update == 1, "Failed to insert run" + run.title());
    }

    public void update(Run run, Integer requestedId) {
        var update = jdbcClient.sql("UPDATE run SET title = ?, startedOn = ?, completedOn = ?, miles = ?, location = ? WHERE id = ?")
                .params(List.of(run.title(), run.startedOn(), run.completedOn(), run.miles(), run.location().toString(), requestedId))
                .update();
        Assert.state(update == 1, "Failed to update run " + run.title());
    }

    public void delete(Integer requestedId) {
        var update = jdbcClient.sql("DELETE FROM run WHERE id = :requestedId")
                .param("requestedId", requestedId)
                .update();
        Assert.state(update == 1, "Failed to delete run " + requestedId);
    }

    public int count() {
        return jdbcClient.sql("SELECT * FROM run")
                .query().listOfRows().size();
    }

    public void saveAll(List<Run> runs) {
        runs.stream().forEach(this::create);
    }

    public List<Run> findByLocation(String requestedLocation) {
        return jdbcClient.sql("SELECT * FROM run WHERE Location = :requestedLocation")
                .param("requestedLocation", requestedLocation)
                .query(Run.class)
                .list();
    }
}






















