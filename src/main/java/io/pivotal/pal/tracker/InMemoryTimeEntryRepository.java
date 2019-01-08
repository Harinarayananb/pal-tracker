package io.pivotal.pal.tracker;


import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.*;

//@Component
public class InMemoryTimeEntryRepository implements TimeEntryRepository{

    private Map<Long, TimeEntry> timeEntryMap = null;
    private Long currentId = null;


    public InMemoryTimeEntryRepository() {
        this.timeEntryMap = new HashMap<Long, TimeEntry>();
        this.currentId=1l;
    }

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        Long id = currentId++;
        timeEntry.setId(id);
        timeEntryMap.put(id, timeEntry);
        return timeEntry;
    }

    @Override
    public TimeEntry find(long id) {
        return timeEntryMap.get(id);
    }

    @Override
    public List<TimeEntry> list() {
        List<TimeEntry> list =  new ArrayList<TimeEntry>();
        list.addAll(timeEntryMap.values());
        return list;
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
        TimeEntry timeEntryToUpdate = timeEntryMap.get(id);
        if(timeEntryToUpdate == null)
            return null;

        timeEntryToUpdate.setDate(timeEntry.getDate());
        timeEntryToUpdate.setHours(timeEntry.getHours());
        timeEntryToUpdate.setProjectId(timeEntry.getProjectId());
        timeEntryToUpdate.setUserId(timeEntry.getUserId());

        return timeEntryToUpdate;
    }

    @Override
    public void delete(long id) {
        timeEntryMap.remove(id);
    }
}
