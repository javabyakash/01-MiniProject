package in.ashokit.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.entity.Plan;
import in.ashokit.service.IPlanService;

/**
 * @author Akash
 */
@RestController
public class PlanController {

	@Autowired
	private IPlanService planService;

	/**
	 * This method is used to get all categories
	 */
	@GetMapping("/categories")
	public ResponseEntity<Map<Integer, String>> getAllCategories() {
		Map<Integer, String> categories = planService.getCategories();
		return new ResponseEntity<Map<Integer, String>>(categories, HttpStatus.OK);
	}

	/**
	 * This method is used to save Plan
	 * 
	 * @param plan
	 * @return boolean
	 */
	@PostMapping("/save")
	public ResponseEntity<String> savePlan(@RequestBody Plan plan) {
		String msg = "";
		boolean isPlanSaved = planService.savePlan(plan);
		if (isPlanSaved)
			msg = "PLAN SAVED SUCCESSFULLY!";
		else
			msg = "FAILED TO SAVE THE PLAN!";

		return new ResponseEntity<>(msg, HttpStatus.CREATED);
	}

	/**
	 * This method is used to get all Plans
	 */
	@GetMapping("/plans")
	public ResponseEntity<List<Plan>> getAllPlans() {
		List<Plan> allPlans = planService.getAllPlans();
		return new ResponseEntity<List<Plan>>(allPlans, HttpStatus.OK);
	}

	/**
	 * This method is used to get Plan by id
	 * 
	 * @param planId
	 * @return Plan
	 */
	@GetMapping("/plan/{planId}")
	public ResponseEntity<Plan> getOnePlan(@PathVariable Integer planId) {
		Plan plan = planService.getPlanById(planId);
		return new ResponseEntity<>(plan, HttpStatus.OK);
	}

	/**
	 * This method is used to update the Plan
	 * 
	 * @param plan
	 * @return boolean
	 */
	@PutMapping("/update")
	public ResponseEntity<String> updatePlan(@RequestBody Plan plan) {
		boolean isPlanUpdated = planService.updatePlan(plan);
		String msg = "";
		if (isPlanUpdated)
			msg = "PLAN UPDATED SUCCESSFULLY!";
		else
			msg = "FAILED TO UPDATE THE PLAN!";

		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	/**
	 * This method is used to delete Plan by Id
	 * 
	 * @param planId
	 * @return boolean
	 */
	@DeleteMapping("/delete/{planId}")
	public ResponseEntity<String> deletePlan(@PathVariable Integer planId) {
		boolean isPlanDeleted = planService.deletePlanById(planId);
		String msg = "";
		if (isPlanDeleted)
			msg = "PLAN DELETED SUCCUSSFULLY!";
		else
			msg = "FAILED TO DELETE THE PLAN";

		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

	/**
	 * This method is used to change the status (Active/Deactive)
	 * 
	 * @param planId, status
	 * @return boolean
	 */
	@PutMapping("/change-status/{planId}/{status}")
	public ResponseEntity<String> changeStatus(@PathVariable Integer planId, @PathVariable String status) {
		boolean isStatusChanged = planService.planStatusChange(planId, status);
		String msg = "";
		if (isStatusChanged)
			msg = "PLAN STATUS CHANGED SUCCESSULLY!";
		else
			msg = "FAILED TO CHANGE THE PLAN STATUS!";

		return new ResponseEntity<>(msg, HttpStatus.OK);
	}

}
