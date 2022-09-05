package in.ashokit.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import in.ashokit.model.EligibilityDtls;
import in.ashokit.repo.EligibilityDtlsRepo;

@Component
public class ReportsAppRunner implements ApplicationRunner {

	@Autowired
	private EligibilityDtlsRepo repo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		EligibilityDtls entity1 = new EligibilityDtls();
		entity1.setEligId(1);
		entity1.setName("Akash");
		entity1.setEmail("akashrhiwale18@gmail.com");
		entity1.setMobile(7774949414L);
		entity1.setGender("Male");
		entity1.setSsn(77749595L);
		entity1.setPlanName("ABC");
		entity1.setPlanStatus("APPROVED");
		repo.save(entity1);
		
		EligibilityDtls entity2 = new EligibilityDtls();
		entity2.setEligId(2);
		entity2.setName("Raghu");
		entity2.setEmail("raghu45@gmail.com");
		entity2.setMobile(9552038213L);
		entity2.setGender("Male");
		entity2.setSsn(9585253L);
		entity2.setPlanName("PQR");
		entity2.setPlanStatus("DECLINED");
		repo.save(entity2);
		
		EligibilityDtls entity3 = new EligibilityDtls();
		entity3.setEligId(3);
		entity3.setName("Ashok");
		entity3.setEmail("ashok@gmail.com");
		entity3.setMobile(8446752317L);
		entity3.setGender("Male");
		entity3.setSsn(95648522L);
		entity3.setPlanName("XYZ");
		entity3.setPlanStatus("PENDING");
		repo.save(entity3);
	}

}
