package telran.ashkelon2020;

import java.time.LocalDateTime;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import telran.ashkelon2020.dao.UserRepositoryMongoDB;
import telran.ashkelon2020.model.user.UserAccount;

@SpringBootApplication
public class ForumWebServiceFilterApplication implements CommandLineRunner {
	
	@Autowired
	UserRepositoryMongoDB userRepository;

	public static void main(String[] args) {
		SpringApplication.run(ForumWebServiceFilterApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (!userRepository.existsById("admin")) {
			String hashPassword = BCrypt.hashpw("admin", BCrypt.gensalt());
			UserAccount admin = new UserAccount();
			admin.setLogin("admin");
			admin.setPassword(hashPassword);
			admin.addRole("USER");
			admin.addRole("Moderator");
			admin.addRole("ADMINISTRATOR");
			admin.setExpDate(LocalDateTime.now().plusYears(25));
			userRepository.save(admin);
		}		
	}

}
