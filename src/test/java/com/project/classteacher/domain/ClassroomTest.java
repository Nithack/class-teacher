package com.project.classteacher.domain;



import com.project.classteacher.domain.entity.Classroom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.text.ParseException;



@DisplayName("Classroom Test")
public class ClassroomTest {


    @Test()
    @DisplayName("should be throw exception when date is invalid")
    public void should_be_throw_exception_when_date_is_invalid() {
        Assertions.assertThrows(ParseException.class, () -> {
            Classroom.dateFormat("20AA-10-10T11:15:00.000Z");
        });
    }

}
