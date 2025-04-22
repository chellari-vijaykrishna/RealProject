package com.us.cal.gov.service;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.us.cal.gov.bindings.COTriggerSumarry;
import com.us.cal.gov.entity.CitizenAppRegistrationEntity;
import com.us.cal.gov.entity.DCCasesEntity;
import com.us.cal.gov.entity.DCTriggerEntity;
import com.us.cal.gov.entity.EligiblityDetailsEntity;
import com.us.cal.gov.repo.ICitizenAppRegisterRepo;
import com.us.cal.gov.repo.IDCCasesEntityRepo;
import com.us.cal.gov.repo.IDCTriggerEntityRepo;
import com.us.cal.gov.repo.IEligiblityDetailsEntityRepo;
import com.us.cal.gov.utilis.EmailUtilis;

@Service
public class ICorrespondingServiceImpl implements ICorrespondingService {

	@Autowired
	private ICitizenAppRegisterRepo citizenRepo;

	@Autowired
	private IDCCasesEntityRepo caseRepo;

	@Autowired
	private IDCTriggerEntityRepo triggerRepo;

	@Autowired
	private IEligiblityDetailsEntityRepo eliERepo;

	@Autowired
	private EmailUtilis emailUtilis;

	@Override
	public COTriggerSumarry processTriggers() {
		
		
		

		EligiblityDetailsEntity eligiblityDetailsEntity = null;
		CitizenAppRegistrationEntity citizenAppRegistrationEntity = null;

		int successTriggers = 0;
		int failureTriggers = 0;

		List<DCTriggerEntity> triggerEntity = triggerRepo.findByTriggerStatus("pending");
		System.out.println(triggerEntity.toString());
		for (DCTriggerEntity entity : triggerEntity) {
			System.out.println(entity.toString());
			eligiblityDetailsEntity = eliERepo.findByCaseNumber(entity.getCaseNumber());
			System.out.println(eligiblityDetailsEntity.toString());
			Optional<DCCasesEntity> caseOptional = caseRepo.findById(entity.getCaseNumber());
			System.out.println(caseOptional.toString());
			Long appId = null;
			if (caseOptional.isPresent()) {
				// Get appId
				appId = caseOptional.get().getAppId();
				Optional<CitizenAppRegistrationEntity> citiOptional = citizenRepo.findById(appId);
				if (citiOptional.isPresent()) {
					citizenAppRegistrationEntity = citiOptional.get();
				}
			}
			try {
				System.out.println(eligiblityDetailsEntity.toString());
		    	generatePdfAndSendMail(citizenAppRegistrationEntity, eligiblityDetailsEntity);
				successTriggers++;
			} catch (Exception e) {
				e.printStackTrace();
				failureTriggers++;
			}
		} // for Each
		COTriggerSumarry coTriggerSumarry = new COTriggerSumarry();
		coTriggerSumarry.setPendingTriggers(failureTriggers);
		coTriggerSumarry.setSuccessTriggers(successTriggers);
		coTriggerSumarry.setTotalTriggers(triggerEntity.size());
		return coTriggerSumarry;
	}
	// private method to generate PDF and Sent Mail
	private void generatePdfAndSendMail(CitizenAppRegistrationEntity citizenAppRegistrationEntity,
			EligiblityDetailsEntity eligiblityDetailsEntity) throws Exception {

		Document doc = new Document(PageSize.A4);

		File file = new File(eligiblityDetailsEntity.getCaseNumber() + ".pdf");
		FileOutputStream fileOutputStream = new FileOutputStream(file);

		PdfWriter.getInstance(doc, fileOutputStream);

		doc.open();

		com.lowagie.text.Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);

		font.setSize(25);

		font.setColor(Color.CYAN);

		Paragraph para = new Paragraph("Plan Approval/Plan Rejection Confirmaion Mail", font);

		para.setAlignment(Paragraph.ALIGN_CENTER);

		doc.add(para);

		PdfPTable table = new PdfPTable(10);

		table.setWidthPercentage(60);

		table.setWidths(new float[] { 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f });

		table.setSpacingBefore(2.0f);

		PdfPCell cell = new PdfPCell();

		cell.setBackgroundColor(Color.GRAY);

		cell.setPadding(4);

		com.lowagie.text.Font cellFont = FontFactory.getFont(FontFactory.COURIER_BOLD);

		cell.setPhrase(new Phrase("TRACE_ID", cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("CASE_NUMBER", cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("HOLDER_NAME", cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("HOLDER_SSN", cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("PLAN_NAME", cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("PLAN_STATUS", cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("PLAN_STATEDATE", cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("PLAN_ENDDATE", cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("BENFIT_AMOUNT", cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("DENIAL REASON", cellFont));
		table.addCell(cell);

		table.addCell(String.valueOf(eligiblityDetailsEntity.getEdTraceId()));
		table.addCell(String.valueOf(eligiblityDetailsEntity.getCaseNumber()));
		table.addCell(String.valueOf(citizenAppRegistrationEntity.getFullName()));
		table.addCell(String.valueOf(citizenAppRegistrationEntity.getCitizenSsn()));
		table.addCell(String.valueOf(eligiblityDetailsEntity.getPlanName()));
		table.addCell(String.valueOf(eligiblityDetailsEntity.getPlanStatus()));
		table.addCell(String.valueOf(eligiblityDetailsEntity.getPlanStartDate()));
		table.addCell(String.valueOf(eligiblityDetailsEntity.getPlanEndDate()));
		table.addCell(String.valueOf(eligiblityDetailsEntity.getBenifitAmt()));
		table.addCell(String.valueOf(eligiblityDetailsEntity.getDenialReason()));

		doc.add(table);
		doc.close();

		// send a mail

		String subject = " Notification Regarding Your Plan Application Plan Approval/Denial Comminication CASE ID :"
				+ eligiblityDetailsEntity.getCaseNumber();

		String body = "Dear :" + citizenAppRegistrationEntity.getFullName() + "\r\n" + "\r\n"
				+ "Thank you for submitting your application"
				+ "For more information, including a detailed breakdown of the benefits, "
				+ "terms, and next steps, please refer to the attached PDF document."
				+ "If you have any questions or need further clarification, feel free to contact us at [Contact Information].\r\n"
				+ "\r\n" + "Thank you for your interest \r\n" + "\r\n" + "Sincerely,\r\n" + "ADMIN\r\n"
				+ "CALIFORINA STATE GOVERNMENT \r\n";
		emailUtilis.sendMail(citizenAppRegistrationEntity.getEmail(), subject, body, file);

		updaterDCTriggerTable(eligiblityDetailsEntity.getCaseNumber(), file);

		return;
	}

	private void updaterDCTriggerTable(Long caseNumber, File file) throws Exception {
		DCTriggerEntity dcTriggerEntity = triggerRepo.findByCaseNumber(caseNumber);
		byte arr[] = new byte[(int) file.length()];

		FileInputStream fileInputStream = new FileInputStream(file);

		fileInputStream.read(arr);

		if (dcTriggerEntity != null) {
			dcTriggerEntity.setCoNoticePdf(arr);
			dcTriggerEntity.setTriggerStatus("Completed");
			triggerRepo.save(dcTriggerEntity);
		}
		fileInputStream.close();
	}

}
