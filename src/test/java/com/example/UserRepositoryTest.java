package com.example;

import com.example.model.User;
import com.example.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;	
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void create_Should_PersistData() {
		User user = new User("Charles", "charlesdccti@gmail.com");
		this.userRepository.save(user);
		Assertions.assertThat(user.getId()).isNotNull();
		Assertions.assertThat(user.getName()).isEqualTo("Charles");
		Assertions.assertThat(user.getEmail()).isEqualTo("charlesdccti@gmail.com");
	}
	
	@Test
	public void delete_Should_RemoveData() {
		User user = new User("Charles", "charlesdccti@gmail.com");
		this.userRepository.save(user);
		this.userRepository.delete(user);
		Assertions.assertThat(userRepository.findOne(user.getId())).isNull();
	}
	
	@Test
	public void update_Should_ChangeAndPersistData() {
		User user = new User("Charles", "charlesdccti@gmail.com");
		this.userRepository.save(user);
		user.setName("Charles222");
		user.setEmail("charlesdccti222@gmail.com");
		this.userRepository.save(user);
		user = this.userRepository.findOne(user.getId());
		Assertions.assertThat(user.getName()).isEqualTo("Charles222");
		Assertions.assertThat(user.getEmail()).isEqualTo("charlesdccti222@gmail.com");
	}
	
	@Test
	public void create_WhenNameIsNull_ShouldThrow_ConstraintViolationException() {
		thrown.expect(ConstraintViolationException.class);
		thrown.expectMessage("O campo nome do usuário é obrigatório");
		this.userRepository.save(new User());
	}
	
	@Test
	public void create_WhenEmailIsNull_ShouldThrow_ConstraintViolationException() {
		thrown.expect(ConstraintViolationException.class);
		this.userRepository.save(new User());
	}
	
	
	@Test
	public void create_WhenEmailIsNotValidate_ShouldThrow_ConstraintViolationException() {
		thrown.expect(ConstraintViolationException.class);
		thrown.expectMessage("Digite um email válido");
		User user = new User();
		user.setName("Charles");
		user.setEmail("Charles");
		this.userRepository.save(user);
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
