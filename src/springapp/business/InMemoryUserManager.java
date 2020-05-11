package springapp.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springapp.dao.Dao;
import springapp.model.utils.User;

@Service("userManager")
public class InMemoryUserManager implements IUserManager {
	
	@Autowired
	Dao dao;

	@Override
	public User authentificate(User user) {
		return dao.authUser(user);
	}
	
}