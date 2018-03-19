package com.code.house.recruit.common.web;

import com.code.house.recruit.common.nosql.documents.User;
import com.code.house.recruit.common.nosql.repos.UserRepo;
import com.code.house.recruit.common.testdata.TestUserData.USER_1;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class UserResourceTest {
    private static User test_user = USER_1.build();
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepo userRepo;

    @Before
    public void initData() {
        userRepo.save(test_user);
    }

    @Test
    public void shouldGetUsers() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.users", hasSize(1)));
    }

    @Test
    public void shouldReturnGivenUserById() throws Exception {
        mockMvc.perform(get("/users/" + test_user.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", equalToIgnoringCase(test_user.getEmail())))
                .andExpect(jsonPath("$.firstName", equalToIgnoringCase(test_user.getFirstName())))
                .andExpect(jsonPath("$.lastName", equalToIgnoringCase(test_user.getLastName())))
                .andExpect(jsonPath("$.status", equalToIgnoringCase(test_user.getStatus().name())));
    }

}
