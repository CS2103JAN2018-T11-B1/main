package seedu.taskscheduler.model.task;

import seedu.taskscheduler.commons.exceptions.IllegalValueException;
import seedu.taskscheduler.commons.util.CollectionUtil;
import seedu.taskscheduler.model.tag.Tag;
import seedu.taskscheduler.model.tag.UniqueTagList;
import seedu.taskscheduler.model.tag.UniqueTagList.DuplicateTagException;

import java.util.Objects;

//@@author A0148145E
/**
 * Represents a Task in the task scheduler.
 * Guarantees: details are present and not null, field values are validated.
 */
public class Task implements ReadOnlyTask {

    private Name name;
    private TaskDateTime startDateTime;
    private TaskDateTime endDateTime;
    private Location address;
    
    private UniqueTagList tags;

    /**
     * Every field must be present and not null.
     */
    public Task(Name name, TaskDateTime startDateTime, TaskDateTime endDateTime, Location address, UniqueTagList tags) {
        assert !CollectionUtil.isAnyNull(name, startDateTime, endDateTime, address, tags);
        this.name = name;
        this.startDateTime = new TaskDateTime(startDateTime);
        this.endDateTime = new TaskDateTime(endDateTime);
        this.address = address;
        this.tags = new UniqueTagList(tags);
    }

    /**
     * Copy constructor.
     */
    public Task(ReadOnlyTask source) {
        this(source.getName(), source.getStartDate(), source.getEndDate(), source.getLocation(), source.getTags());
    }

    @Override
    public Name getName() {
        return name;
    }

    @Override
    public TaskDateTime getStartDate() {
        return startDateTime;
    }

    @Override
    public TaskDateTime getEndDate() {
        return endDateTime;
    }

    @Override
    public Location getLocation() {
        return address;
    }
    
    public void setName(Name name) {
        this.name = name;
    }
    
    public void setStartDate(TaskDateTime startDate) {
        this.startDateTime = startDate;
    }
    
    public void setEndDate(TaskDateTime endDate) {
        this.endDateTime = endDate;
    }

    public void setLocation(Location address) {
        this.address = address;
    }
    
    public void addDuration(long duration) {
        if (startDateTime.getDate() != null)
            this.startDateTime.setDate(startDateTime.getDate().getTime() + duration + 1);
        if (endDateTime.getDate() != null)
            this.endDateTime.setDate(endDateTime.getDate().getTime() + duration + 1);
    }
    
    /**
     * Add completed tag to indicate task done.
     */
    public void markComplete() throws DuplicateTagException {
        try {
            this.tags.add(new Tag("Completed"));
        } catch (DuplicateTagException dte) { 
            throw dte;
        } catch (IllegalValueException ive) {
            assert false : "The tag cannot be illegal value";
        } 
    }

    /**
     * Add completed tag to indicate task done.
     */
    public void unMarkComplete() throws NullPointerException {
        try {
            this.tags.remove(new Tag("Completed"));
        }
        catch (NullPointerException npe) { 
            throw npe;
        } catch (IllegalValueException ive) {
            assert false : "The tag cannot be illegal value";
        }
    }
    
    public void copyField(Task task) {
        setName(task.getName());
        setStartDate(task.getStartDate());
        setEndDate(task.getEndDate());
        setLocation(task.getLocation());
        setTags(task.getTags());
     }

    @Override
    public UniqueTagList getTags() {
        return new UniqueTagList(tags);
    }

    /**
     * Replaces this task's tags with the tags in the argument tag list.
     */
    public void setTags(UniqueTagList replacement) {
        tags.setTags(replacement);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReadOnlyTask // instanceof handles nulls
                && this.isSameStateAs((ReadOnlyTask) other));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, startDateTime, endDateTime, address);
    }

    @Override
    public String toString() {
        return getAsText();
    }

    @Override
    public Task copy() {
        return new Task(this);
    }

}