package in.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.Plan;

public interface IPlanRepo extends JpaRepository<Plan, Integer> {

}
