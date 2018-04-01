package seedu.address.model.tutee;

import static org.junit.Assert.assertEquals;
import static seedu.address.testutil.TypicalCalendarEntries.VALID_DESCRIPTION;
import static seedu.address.testutil.TypicalCalendarEntries.VALID_DURATION;
import static seedu.address.testutil.TypicalCalendarEntries.VALID_NAME;
import static seedu.address.testutil.TypicalCalendarEntries.VALID_START_DATE_TIME;
import static seedu.address.testutil.TypicalCalendarEntries.getTuitionEntry;

import org.junit.BeforeClass;
import org.junit.Test;

import seedu.address.testutil.TypicalCalendarEntries;

//@@author ChoChihTun
public class TuitionTaskTest {

    @BeforeClass
    public static void setupBeforeClass() {
        new TypicalCalendarEntries();
    }

    @Test
    public void constructor_validArgs_success() {
        TuitionTask tuitionTask = new TuitionTask(VALID_NAME, VALID_START_DATE_TIME,
                VALID_DURATION, VALID_DESCRIPTION);

        assertEquals(VALID_NAME, tuitionTask.getPerson());
        assertEquals(VALID_START_DATE_TIME, tuitionTask.getTaskDateTime());
        assertEquals(VALID_DURATION, tuitionTask.getDuration());
        assertEquals(VALID_DESCRIPTION, tuitionTask.getDescription());
        assertEquals(getTuitionEntry(), tuitionTask.getEntry());
    }
}
