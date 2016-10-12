package seedu.address.model.task;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.UniqueTagList;
import seedu.address.model.tag.UniqueTagList.DuplicateTagException;

import java.util.Date;
import java.util.Objects;

/**
 * Represents a Task in the task scheduler.
 * Guarantees: details are present and not null, field values are validated.
 */
public class Task implements ReadOnlyTask {

    private Name name;
    private TaskDateTime startDateTime;
    private TaskDateTime endDateTime;
    private Location address;
    private String type;
    
    private UniqueTagList tags;

    /**
     * Every field must be present and not null.
     */
    public Task(Name name, TaskDateTime startDateTime, TaskDateTime endDateTime, Location address) {
        this(name,startDateTime,endDateTime,address,null);
    }
    /**
     * Every field must be present and not null.
     */
    public Task(Name name, TaskDateTime startDateTime, TaskDateTime endDateTime, Location address, String type) {
        assert !CollectionUtil.isAnyNull(name, startDateTime, endDateTime, address);
        this.name = name;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.address = address;
        this.tags = new UniqueTagList();
        this.type = type;
        if (type != null) {
            try {
                this.tags.add(new Tag(type));
            } catch (DuplicateTagException e) {
                assert false : "The tag cannot be duplicate";
            } catch (IllegalValueException e) {
                assert false : "The tag name cannot be illegal";
            } // protect internal tags from changes in the arg list
        }
    }

    /**
     * Copy constructor.
     */
    public Task(ReadOnlyTask source) {
        this(source.getName(), source.getStartDate(), source.getEndDate(), source.getLocation(), source.getType());
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
    
    @Override
    public String getType() {
        return type;
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

}
