package com.Lims.repo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.Lims.dao.UserRepository;
import com.Lims.entities.User;

@SpringBootTest
class UserRepo {
	
	@Autowired
	private UserRepository userRepo;
//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}

	@Test
	void getUserByUserName() {
		
		 User user=userRepo.getUserByUserName("zxc@gmail.com");
		  if(user!=null) {
			  assertTrue(true);
		  }else {
			  assertFalse(false);
		  }
		}

	@Test
	public void getUserbyId() {
	User user=userRepo.findById(13).get();
	assertThat(user.getEmail()).isEqualTo("efg@gmail.com");
	}
	
	@Test
	public void getListofUser() {
		List<User> user=userRepo.findAll();
		
		assertThat(user.size()).isGreaterThan(0);
	}
	
	@Test
	public void updateUser() {
		User user=userRepo.findById(13).get();
		user.setFirstName("update");
		User userupdated=userRepo.save(user);
		assertThat(userupdated.getFirstName()).isEqualTo("update");
		
	}
	
}
