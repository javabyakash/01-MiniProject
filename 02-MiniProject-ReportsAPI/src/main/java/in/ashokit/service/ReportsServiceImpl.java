package in.ashokit.service;

import java.awt.Color;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.ashokit.model.EligibilityDtls;
import in.ashokit.repo.EligibilityDtlsRepo;
import in.ashokit.request.FilterValuesRequest;
import in.ashokit.response.ReportsResponse;

@Service
public class ReportsServiceImpl implements IReportsService {

	@Autowired
	private EligibilityDtlsRepo repo;

	@Override
	public List<String> getUniqPlanName() {
		return repo.findPlanNames();
	}

	@Override
	public List<String> getUniqPlanStatus() {
		return repo.findPlanStatus();
	}

	@Override
	public List<ReportsResponse> getAllPlanDtls(FilterValuesRequest request) {

		List<ReportsResponse> response = new ArrayList<>();

		EligibilityDtls queryBuilder = new EligibilityDtls();

		String planName = request.getPlanName();
		if (planName != null && planName != "")
			queryBuilder.setPlanName(planName);

		String planStatus = request.getPlanStatus();
		if (planStatus != null && planStatus != "")
			queryBuilder.setPlanStatus(planStatus);

		LocalDate startDate = request.getStartDate();
		if (startDate != null)
			queryBuilder.setPlanStartDate(startDate);

		LocalDate endDate = request.getEndDate();
		if (endDate != null)
			queryBuilder.setPlanEndDate(endDate);

		Example<EligibilityDtls> example = Example.of(queryBuilder);

		List<EligibilityDtls> entities = repo.findAll(example);

		for (EligibilityDtls entity : entities) {
			ReportsResponse rr = new ReportsResponse();
			BeanUtils.copyProperties(entity, rr);
			response.add(rr);
		}

		return response;
	}

	@Override
	public void generateExcel(HttpServletResponse response) throws Exception {
		// create Workbook
		HSSFWorkbook workbook = new HSSFWorkbook();

		// create sheets
		HSSFSheet sheet = workbook.createSheet("Plan Details");

		// create row
		HSSFRow headerRow = sheet.createRow(0);

		// create cells
		headerRow.createCell(0).setCellValue("S.No");
		headerRow.createCell(1).setCellValue("Name");
		headerRow.createCell(2).setCellValue("Email");
		headerRow.createCell(3).setCellValue("Mobile");
		headerRow.createCell(4).setCellValue("Gender");
		headerRow.createCell(5).setCellValue("SSN");

		// get all records from database
		List<EligibilityDtls> entities = repo.findAll();

		// using normal for loop
		int i = 1;
		for(EligibilityDtls entity : entities) {
			HSSFRow dataRow = sheet.createRow(i);
			dataRow.createCell(0).setCellValue(entity.getEligId());
			dataRow.createCell(1).setCellValue(entity.getPlanName());
			dataRow.createCell(2).setCellValue(entity.getEmail());
			dataRow.createCell(3).setCellValue(entity.getMobile());
			dataRow.createCell(4).setCellValue(entity.getGender());
			dataRow.createCell(5).setCellValue(entity.getSsn());
			i++;
		}

		// using java8 forEach() method
		//NOTE : In this case forEach() method not recommended bcoz i++ not working as expected 
		/*entities.forEach(entity -> {
			int i = 1;
			HSSFRow dataRow = sheet.createRow(i);
			dataRow.createCell(0).setCellValue(entity.getEligId());
			dataRow.createCell(1).setCellValue(entity.getPlanName());
			dataRow.createCell(2).setCellValue(entity.getEmail());
			dataRow.createCell(3).setCellValue(entity.getMobile());
			dataRow.createCell(4).setCellValue(entity.getGender());
			dataRow.createCell(5).setCellValue(entity.getSsn());
			i++;
		});*/

		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}

	@Override
	public void generatePdf(HttpServletResponse response) throws Exception {
		// fetch all records to display on PDF
		List<EligibilityDtls> entities = repo.findAll();

		// create Document with pagesize
		Document document = new Document(PageSize.A4);

		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();

		// define font
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLUE);

		// define Paragraph
		Paragraph p = new Paragraph("SEARCH REPORT", font);
		p.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(p);

		// add a couple of blank lines
		document.add(Chunk.NEWLINE);

		// create Table by defining column size
		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 2.0f, 4.5f, 2.8f, 1.7f, 2.5f });

		// setting values to PdfCell
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(5);

		// setting Font values for table header cell (columns)
		font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);

		cell.setPhrase(new Phrase("Name", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Email", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Mobile", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Gender", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("SSN", font));
		table.addCell(cell);

		// Iterate all records
		for (EligibilityDtls entity : entities) {
			table.addCell(entity.getName());
			table.addCell(entity.getEmail());
			table.addCell(String.valueOf(entity.getMobile()));
			table.addCell(entity.getGender());
			table.addCell(String.valueOf(entity.getSsn()));
		}
		document.add(table);
		document.close();
	}

}
