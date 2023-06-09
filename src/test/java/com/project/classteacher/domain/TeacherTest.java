package com.project.classteacher.domain;


import com.project.classteacher.util.builder.TestBuilderUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


@DisplayName("Teacher Test")
public class TeacherTest {
    @Test()
    @DisplayName("should be return teacher with approved status false")
    public void should_be_return_teacher_with_approved_status_false() {

        var teacher = TestBuilderUtil.generateTeacher();
        Assertions.assertFalse(teacher.isApproved());
    }


    @Test()
    @DisplayName("should be return teacher with approved status true")
    public void should_be_return_teacher_with_approved_status_true() {

        var teacher = TestBuilderUtil.generateTeacher();
        teacher.approve();
        Assertions.assertTrue(teacher.isApproved());
    }
}
