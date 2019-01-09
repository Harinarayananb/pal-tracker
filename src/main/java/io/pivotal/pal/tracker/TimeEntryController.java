package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class TimeEntryController {

    TimeEntryRepository timeEntryRepository;
    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository=timeEntryRepository;
    }

    @PostMapping("/time-entries")
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry timeEntry = timeEntryRepository.create(timeEntryToCreate);
        return new ResponseEntity(timeEntry, HttpStatus.CREATED);
    }

    @GetMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id) {

        TimeEntry timeEntry = timeEntryRepository.find(id);
        if(timeEntry != null)
        return new ResponseEntity(timeEntry, HttpStatus.OK);
        else
            return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/time-entries/{id}")
    public ResponseEntity update(@PathVariable long id, @RequestBody TimeEntry timeEntry) {
        TimeEntry timeEntryUpdated = timeEntryRepository.update(id, timeEntry);
        if(timeEntryUpdated == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        return new ResponseEntity(timeEntryUpdated, HttpStatus.OK);
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        return new ResponseEntity<>(timeEntryRepository.list(), HttpStatus.OK);
    }

    @DeleteMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable long id) {
        timeEntryRepository.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
