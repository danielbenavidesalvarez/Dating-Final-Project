package interface_adapter.report_account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReportAccountStateTest {
    private ReportAccountState state;

    @BeforeEach
    void setUp() {
        state = new ReportAccountState();
    }

    @Test
    void testSetAndGetSuccess() {
        // Act
        state.setSuccess(true);

        // Assert
        assertTrue(state.isSuccess());

        // Act
        state.setSuccess(false);

        // Assert
        assertFalse(state.isSuccess());
    }

    @Test
    void testSetAndGetMessage() {
        // Act
        state.setMessage("Report submitted successfully.");

        // Assert
        assertEquals("Report submitted successfully.", state.getMessage());

        // Act
        state.setMessage("Reported user does not exist.");

        // Assert
        assertEquals("Reported user does not exist.", state.getMessage());
    }
}
