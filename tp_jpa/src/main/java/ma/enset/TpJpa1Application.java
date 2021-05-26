package ma.enset;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ma.enset.dao.PatientRepository;
import ma.enset.entities.Patient;

@SpringBootApplication
public class TpJpa1Application implements CommandLineRunner{
	
	@Autowired
	private PatientRepository patientRepository;

	public static void main(String[] args) {
		SpringApplication.run(TpJpa1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		patientRepository.save(new Patient(null,"hassan",new Date(), false,8));
		patientRepository.save(new Patient(null,"Sofia",new Date(), false,9));
		patientRepository.save(new Patient(null,"radia",new Date(), false,10));
		
		patientRepository.findAll().forEach(p->{
			System.out.println(p.getName());
		});
	}

}