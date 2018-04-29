package com.code.house.recruit.web;

import com.code.house.recruit.data.nosql.documents.User;
import com.code.house.recruit.data.nosql.repos.UserRepo;
import com.code.house.recruit.common.testdata.TestUserData.USER_1;
import com.code.house.recruit.common.testdata.TestUserData.USER_2;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class UserResourceTest {
    private static User test_user = USER_1.build();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void initData() {
        userRepo.save(test_user);
    }

    @Test
    public void test_1_ShouldGetUsers() throws Exception {
        mockMvc.perform(get("/users")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)));
    }

    @Test
    public void test_2_ShouldReturnGivenUserById() throws Exception {
        mockMvc.perform(get("/users/" + test_user.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", equalToIgnoringCase(test_user.getEmail())))
                .andExpect(jsonPath("$.firstName", equalToIgnoringCase(test_user.getFirstName())))
                .andExpect(jsonPath("$.lastName", equalToIgnoringCase(test_user.getLastName())))
                .andExpect(jsonPath("$.status", equalToIgnoringCase(test_user.getStatus().name())));
    }

    @Test
    public void test_3_ShouldAddNewUser() throws Exception {
        User newUser = USER_2.build();

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newUser)))
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(get("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(2)));
    }

    @Test
    public void test_4_CustomQueryCanBeUsed() throws Exception {
        mockMvc.perform(get("/users/search/findByEmail")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .param("email", test_user.getEmail()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", equalToIgnoringCase(test_user.getEmail())))
                .andExpect(jsonPath("$.firstName", equalToIgnoringCase(test_user.getFirstName())))
                .andExpect(jsonPath("$.lastName", equalToIgnoringCase(test_user.getLastName())))
                .andExpect(jsonPath("$.status", equalToIgnoringCase(test_user.getStatus().name())));
    }

}
