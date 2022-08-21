package in.ashokit.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.entity.Plan;
import in.ashokit.entity.PlanCategory;
import in.ashokit.repo.IPlanCategoryRepo;
import in.ashokit.repo.IPlanRepo;

@Service
public class PlanServiceImpl implements IPlanService {

	@Autowired
	private IPlanCategoryRepo planCategoryRepo;

	@Autowired
	private IPlanRepo planRepo;

	@Override
	public Map<Integer, String> getCategories() {
		List<PlanCategory> categories = planCategoryRepo.findAll();

		Map<Integer, String> categoryMap = new HashMap<Integer, String>();

		for (PlanCategory category : categories) {
			categoryMap.put(category.getCategoryId(), category.getCategoryName());
		}

		return categoryMap;
	}

	@Override
	public boolean savePlan(Plan plan) {
		Plan savedPlan = planRepo.save(plan);
		return savedPlan.getPlanId() != null;
	}

	@Override
	public List<Plan> getAllPlans() {
		return planRepo.findAll();
	}

	@Override
	public Plan getPlanById(Integer planId) {
		Optional<Plan> plan = planRepo.findById(planId);
		if (plan.isPresent()) {
			return plan.get();
		}
		// TODO : Need To Throw Custom Exception - Id Not Found
		return null;
	}

	@Override
	public boolean updatePlan(Plan plan) {
		Plan updatedPlan = planRepo.save(plan);
		return updatedPlan.getPlanId() != null;
	}

	@Override
	public boolean deletePlanById(Integer planId) {
		boolean status = false;
		try {
			planRepo.deleteById(planId);
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean planStatusChange(Integer planId, String status) {

		// 1st we need to fetch the Plan Object by planId
		Optional<Plan> findById = planRepo.findById(planId);
		if (findById.isPresent()) {
			Plan plan = findById.get();
			//set the specific value (status)
			plan.setActiveDeactiveStatus(status);
			//save plan object
			planRepo.save(plan);
			return true;
		}
		return false;
	}

}
