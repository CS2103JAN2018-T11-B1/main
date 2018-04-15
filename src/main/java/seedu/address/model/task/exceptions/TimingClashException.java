package seedu.address.model.task.exceptions;

import seedu.address.commons.exceptions.DuplicateDataException;

//@@author ChoChihTun
/**
 * Signals that there is a clash of timing in the schedule or there is a duplicate task
 */
public class TimingClashException extends DuplicateDataException {

    public TimingClashException(String message) {
        super(message);
    }
}
