package in.ashokit.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.request.FilterValuesRequest;
import in.ashokit.response.ReportsResponse;
import in.ashokit.service.IReportsService;

@RestController
public class ReportingApiController {

	@Autowired
	private IReportsService service;
	
	@GetMapping("/plans")
	public ResponseEntity<List<String>> getPlans(){
		List<String> plan = service.getUniqPlanName();
		return new ResponseEntity<>(plan,HttpStatus.OK);
	}
	
	@GetMapping("/statuses")
	public ResponseEntity<List<String>> getStatuses(){
		List<String> statuses = service.getUniqPlanStatus();
		return new ResponseEntity<>(statuses,HttpStatus.OK);
	}
	
	@PostMapping("/reports")
	public ResponseEntity<List<ReportsResponse>> getReports(@RequestBody FilterValuesRequest request){
		List<ReportsResponse> planDtls = service.getAllPlanDtls(request);
		return new ResponseEntity<>(planDtls,HttpStatus.OK);
	}
	
	@GetMapping("/excel")
	public void getExcel(HttpServletResponse response) throws Exception {
		//set Content Type
		response.setContentType("application/octet-stream");
		
		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename=report.xls";
		response.setHeader(headerKey, headerValue);
		service.generateExcel(response);
	}
	
	@GetMapping("/pdf")
	public void getPdf(HttpServletResponse response) throws Exception {
		response.setContentType("application/pdf");
		
		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename=report.pdf";
		
		response.setHeader(headerKey, headerValue);
		
		service.generatePdf(response);
	}
}
