package com.example;

import com.example.model.User;
import com.example.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static java.util.Arrays.asList;
import static org.springframework.http.HttpMethod.DELETE;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserEndPointTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    //@Autowired
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;


    @Before
    public void setup () {
        User user = new User(1, "Charles", "charlesdccti@gmail.com");
        BDDMockito.when(userRepository.findOne(user.getId())).thenReturn(user);
    }

    @Test
    public void listStudentsWhenUsernameAndPasswordAreCorrectShouldReturnStatusCode200 () {
        List<User> users = asList(new User("Charles", "charlesdccti@gmail.com"),
                new User("Nemuel", "nemuel@gmail.com"));
        BDDMockito.when(userRepository.findAll()).thenReturn(users);
        ResponseEntity<String> response = restTemplate.getForEntity("/users/", String.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
        
        System.out.println(response.getBody());
    }

    
    @Test
    public void findUserByIdShouldReturnStatusCode200 () {
        User user = new User(1 ,"Charles", "charlesdccti@gmail.com");
        BDDMockito.when(userRepository.findOne(user.getId())).thenReturn(user);
        
        ResponseEntity<String> response = restTemplate.getForEntity("/users/{id}", String.class, user.getId());
        
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);

        System.out.println(response.getStatusCodeValue());
        System.out.println(response.getBody());
    }
    
    @Test
    public void getUserByIdWhenUserIdExistShouldReturnStatusCode200 () {
        User user = new User(1 ,"Charles", "charlesdccti@gmail.com");
        BDDMockito.when(userRepository.findOne(user.getId())).thenReturn(user);
        
        ResponseEntity<User> response = restTemplate.getForEntity("/users/{id}/response", User.class, user.getId());
        
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);

        System.out.println(response.getStatusCodeValue());
        System.out.println(response.getBody());
    }


    @Test
    public void deleteWhenUserExistsShouldReturnStatusCode200 () {
        BDDMockito.doNothing().when(userRepository).delete(1);
        ResponseEntity<String> exchange = restTemplate.exchange("/users/{id}", DELETE, null, String.class, 1);
        Assertions.assertThat(exchange.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void delete2WhenUserExistsShouldReturnStatusCode200 () throws Exception {
        //BDDMockito.doNothing().when(userRepository).delete(1);
        mockMvc.perform(MockMvcRequestBuilders.delete("/users/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void createShouldPersistDataAndReturnStatusCode201 () throws Exception {
        User user = new User(1,"Charles", "charlesdccti@gmail.com");
        BDDMockito.when(userRepository.save(user)).thenReturn(user);
        ResponseEntity<User> response = restTemplate.postForEntity("/users/post/", user, User.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(201);
        Assertions.assertThat(response.getBody().getId()).isNotNull();

        System.out.println(response.getBody());

    }


}
