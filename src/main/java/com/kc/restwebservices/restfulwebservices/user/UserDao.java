package com.kc.restwebservices.restfulwebservices.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDao {

	private static List<User> users = new ArrayList<>();
	private static int usercount = 0;

	static {

		users.add(new User(++usercount, "Mahesh", LocalDate.now().minusYears(30)));
		users.add(new User(++usercount, "suresh", LocalDate.now().minusYears(25)));
		users.add(new User(++usercount, "Rajesh", LocalDate.now().minusYears(20)));

	}

	public List<User> findall() {

		return users;
	}

	public User save(User user) {
		user.setId(++usercount);
		users.add(user);
		return user;
	}

	public User findUserById(Integer id) {

		return users.stream().filter(users -> users.getId() == id).findAny().orElse(null);
	}
	
	public boolean delete(Integer id) {

		return users.removeIf(user->user.getId() == id);
	}
}
