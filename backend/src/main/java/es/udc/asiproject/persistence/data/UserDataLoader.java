package es.udc.asiproject.persistence.data;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import es.udc.asiproject.persistence.dao.UserDao;
import es.udc.asiproject.persistence.model.User;
import es.udc.asiproject.persistence.model.User.RoleType;

@Component
@Profile("!test")
public class UserDataLoader implements ApplicationRunner {
	@Autowired
	private UserDao userDao;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		if (userDao.count() == 0) {
			User user1 = new User("pepe123", "Jose", "Perez", "jper1994@gmail.com");
			user1.setRole(RoleType.GERENTE);
			userDao.save(user1);

			User user2 = new User("ana123", "Ana", "Ferrer", "anaferrer@gmail.com");
			user2.setRole(RoleType.AGENTE);
			userDao.save(user2);

			User user3 = new User("marcos123", "Marcos", "Gil", "marcosgil@hotmail.com");
			user3.setRole(RoleType.AGENTE);
			userDao.save(user3);

			User user4 = new User("felipe123", "Felipe", "Pita", "fepita@gmail.com");
			user4.setRole(RoleType.AGENTE);
			userDao.save(user4);

			User user5 = new User("manuel123", "Manuel", "Salgado", "manusalg@gmail.com");
			user5.setRole(RoleType.INFORMATICO);
			userDao.save(user5);

			User user6 = new User("rosa123", "Rosa", "Ruiz", "rosaruiz@hotmail.com");
			user6.setRole(RoleType.INFORMATICO);
			userDao.save(user6);
		}
	}
}
