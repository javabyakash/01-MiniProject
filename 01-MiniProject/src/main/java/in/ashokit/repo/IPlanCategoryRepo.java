package in.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.PlanCategory;

public interface IPlanCategoryRepo extends JpaRepository<PlanCategory, Integer> {

}
