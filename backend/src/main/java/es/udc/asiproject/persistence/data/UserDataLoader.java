package es.udc.asiproject.persistence.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import es.udc.asiproject.persistence.dao.UserDao;
import es.udc.asiproject.persistence.model.User;
import es.udc.asiproject.persistence.model.User.RoleType;

@Component
@Profile("!test")
public class UserDataLoader implements ApplicationRunner {
    @Autowired
    private UserDao userDao;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
	if (userDao.count() == 0) {
	    User user1 = new User(passwordEncoder.encode("pass"), "Gerente", "Perez", "gerente@gmail.com");
	    user1.setRole(RoleType.GERENTE);
	    userDao.save(user1);

	    User user2 = new User(passwordEncoder.encode("pass"), "Agente1", "Ferrer", "agente1@gmail.com");
	    user2.setRole(RoleType.AGENTE);
	    userDao.save(user2);

	    User user3 = new User(passwordEncoder.encode("pass"), "Agente2", "Gil", "agente2@gmail.com");
	    user3.setRole(RoleType.AGENTE);
	    userDao.save(user3);

	    User user4 = new User(passwordEncoder.encode("pass"), "Agente3", "Pita", "agente3@gmail.com");
	    user4.setRole(RoleType.AGENTE);
	    userDao.save(user4);

	    User user5 = new User(passwordEncoder.encode("pass"), "Informático", "Salgado", "informatico@gmail.com");
	    user5.setRole(RoleType.INFORMATICO);
	    userDao.save(user5);
	}
    }
}
