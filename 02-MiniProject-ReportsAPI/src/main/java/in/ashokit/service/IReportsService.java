package in.ashokit.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import in.ashokit.request.FilterValuesRequest;
import in.ashokit.response.ReportsResponse;

public interface IReportsService {
	public List<String> getUniqPlanName();
	public List<String> getUniqPlanStatus();
	public List<ReportsResponse> getAllPlanDtls(FilterValuesRequest request);
	public void generateExcel(HttpServletResponse response) throws Exception;
	public void generatePdf(HttpServletResponse response) throws Exception;
}
