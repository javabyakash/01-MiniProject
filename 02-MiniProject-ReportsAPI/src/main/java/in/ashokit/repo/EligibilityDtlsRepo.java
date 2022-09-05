package in.ashokit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.ashokit.model.EligibilityDtls;

public interface EligibilityDtlsRepo extends JpaRepository<EligibilityDtls, Integer> {

	@Query("select distinct(planName) from EligibilityDtls")
	public List<String> findPlanNames();

	@Query("select distinct(planStatus) from EligibilityDtls")
	public List<String> findPlanStatus();
}
