package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class TimeEntryController {

    TimeEntryRepository repo ;
    public TimeEntryController(TimeEntryRepository repo) {
        this.repo=repo;
    }
    @PostMapping("/time-entries")
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry timeEntry= repo.create(timeEntryToCreate);
        ResponseEntity response=new ResponseEntity(timeEntry, HttpStatus.CREATED);

        return response ;
    }

    @GetMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable Long id) {
        TimeEntry timeEntry= repo.find(id);
        ResponseEntity response;
        if(timeEntry!=null)
            return new ResponseEntity(timeEntry, HttpStatus.OK);
        response = new ResponseEntity(HttpStatus.NOT_FOUND);
        return response;
    }
    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        return new ResponseEntity<List<TimeEntry>>(repo.list(),HttpStatus.OK);
    }
    @PutMapping("/time-entries/{id}")
    public ResponseEntity update(@PathVariable long id,@RequestBody TimeEntry expected) {
        TimeEntry entry=repo.update(id,expected);
        if(entry!=null)
        return new ResponseEntity<TimeEntry>(entry,HttpStatus.OK);

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/time-entries/{id}")
    public ResponseEntity delete(@PathVariable long id) {
        repo.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
