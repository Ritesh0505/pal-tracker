package io.pivotal.pal.tracker;

import java.time.LocalDate;
import java.util.*;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {
    public static Map<Long,TimeEntry> timeEntries= new HashMap<Long,TimeEntry>() ;
    static long id=1L;
    public TimeEntry create(TimeEntry timeEntry) {
        timeEntry.setId(id);
        //TimeEntry entry = new TimeEntry(id, timeEntry.getProjectId(), timeEntry.getUserId(), LocalDate.parse("2017-01-08"), 8);
         timeEntries.put(id,timeEntry);
        id++;
        return  timeEntry;
    }
    public TimeEntry find(long id) {
        TimeEntry timeEntry= timeEntries.getOrDefault(id,null);
        return  timeEntry;
    }

    public List<TimeEntry> list() {

        List<TimeEntry> entryList= new ArrayList<TimeEntry>(timeEntries.values());
       return  entryList;
    }

    public TimeEntry update(long id, TimeEntry timeEntry) {
        timeEntry.setId(id);
        timeEntries.replace(id,timeEntry);
        return timeEntries.getOrDefault(id,null);
    }

    public void delete(long id) {
        timeEntries.remove(id);

    }
}
