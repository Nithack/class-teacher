package com.project.classteacher.infra.http.controller.teacher;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.classteacher.config.MyIntegrationConfig;
import com.project.classteacher.domain.entity.Token;
import com.project.classteacher.domain.enums.Roles;
import com.project.classteacher.infra.dataBase.mongoDB.model.ClassroomModel;
import com.project.classteacher.infra.dataBase.mongoDB.model.UserModel;
import com.project.classteacher.util.builder.TestBuilderUtil;
import com.project.classteacher.util.mock.MockGenerate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class TeacherControllerTest extends MyIntegrationConfig {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockGenerate mockGenerate;


    @Test()
    @DisplayName("Should be return 403 when not have token")
    public void should_be_return_401_when_not_have_token() throws Exception {

        mockMvc.perform(get("/teacher/classroom"))
                .andExpect(status().is4xxClientError());

        mockMvc.perform(post("/secretary/classroom" + TestBuilderUtil.generateId())
        ).andExpect(status().is4xxClientError());

        mockMvc.perform(post("/secretary/classroom")
        ).andExpect(status().is4xxClientError());

    }

    @Test
    @DisplayName("Should return list classroom by teacher id")
    public void should_return_list_classroom_by_teacher_id() throws Exception {

        var QUANTITY = 10;

        UserModel secretary = mockGenerate.createUser("secretary", Roles.SECRETARY, true);

        UserModel teacher = mockGenerate.createUser("teacher", Roles.TEACHER, false);

        List<ClassroomModel> teacherClassroomModelList = mockGenerate.generateMultiplesClassroom(QUANTITY, teacher.getId());

        mockGenerate.generateMultiplesClassroom(QUANTITY, secretary.getId());

        Token teacherToken = Token.encode(teacher.toDomain());

        var mvcResult = mockMvc.perform(get("/teacher/classroom")
                        .header("Authorization", teacherToken.getToken())
                ).andExpect(status().isOk())
                .andReturn();

        verifyResponseContent(teacherClassroomModelList, mvcResult.getResponse().getContentAsString(), teacherClassroomModelList.size());
    }

    private void verifyResponseContent(List<ClassroomModel> classroomList, String responseContent, Number quantity) throws Exception {
        List<ClassroomModel> actualClassroom = objectMapper.readValue(responseContent, new TypeReference<>() {
        });

        assertEquals(quantity.intValue(), actualClassroom.size());
        for (int i = 0; i < classroomList.size(); i++) {
            ClassroomModel expectedClassroom = classroomList.get(i);
            ClassroomModel actualClassroomItem = actualClassroom.get(i);

            assertAll(
                    () -> assertEquals(expectedClassroom.getTitle(), actualClassroomItem.getTitle()),
                    () -> assertEquals(expectedClassroom.getDescription(), actualClassroomItem.getDescription()),
                    () -> assertEquals(expectedClassroom.getTeacherId(), actualClassroomItem.getTeacherId())
            );
        }
    }
}
