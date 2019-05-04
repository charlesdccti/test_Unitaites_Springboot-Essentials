package com.example;

import com.example.controller.UserController;
import com.example.model.User;
import com.example.service.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class UserControllerTest extends DemoApplicationTests {

    private MockMvc mockMvc;

    @Autowired
    private UserController userController;

    @Autowired
    private UserService userService;

    @Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}
    
    @Test
	public void testShouldGetIndex() throws Exception {
        this.mockMvc.perform(get("/users"))
        .andExpect(model().attributeExists("listUser"))
        .andExpect(status().isOk());
	}

    /* 
     * name_method: test + Verbo http + metodo chamado + nome do controle testado 
     */
    @Test
	public void testGETShowUserController() throws Exception {
        User user = userService.save(new User("Charles", "charlesdccti@gmail.com"));

		this.mockMvc.perform(get("/users/"+ user.getId()))
            .andExpect(model().attributeExists("user"))	
            .andExpect(status().isOk());
	}
    
    
    @Test
	public void testGETSaveUserController() throws Exception {
		this.mockMvc.perform(get("/users/new"))
			.andExpect(model().attributeExists("user"))	
			.andExpect(status().isOk());
    }
    
    @Test
	public void testPOSTSaveUserController() throws Exception {
		this.mockMvc.perform(post("/users")
				.param("name", "Charles Ferreira")
				.param("email", "charlesdccti@gmail.com")
				).andExpect(redirectedUrl("/users/1"));
				//.andExpect(model().attributeExists("user"));
				//.andExpect(status().isOk());
				//.andExpect(redirectedUrl("/users/21"));
    }
    

    @Test
	public void testGETUpdateUserController() throws Exception {
        User user = userService.save(new User("Charles", "charlesdccti@gmail.com"));

		this.mockMvc.perform(get("/users/" + user.getId() + "/edit"))
            .andExpect(model().attributeExists("user"))	
            .andExpect(status().isOk());
		
	}
    
    @Test
	public void test_PUT_Update_UserController() throws Exception {
        User user = userService.save(new User("Charles", "charlesdccti@gmail.com"));

		System.out.println(user);
		
		this.mockMvc.perform(post("/users")
				.param("id", "1")
				.param("name", "Charles Souza")
				.param("email", "xxx@gmail.com")
			)
			.andExpect(redirectedUrl("/users/1"))
		 	.andExpect(status().isOk());
		
	}
    
    @Test
	public void test_DELETE_delete_UserController() throws Exception {
        User user = userService.save(new User("Charles", "charlesdccti@gmail.com"));

		this.mockMvc.perform(delete("/users/" + user.getId()))
			.andExpect(model().attribute("message", "success!"))
			.andExpect(model().attributeDoesNotExist("user"))
		 	.andExpect(status().isOk());
		
	}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}
