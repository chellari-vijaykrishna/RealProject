package com.us.cal.gov.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.us.cal.gov.bindings.ApplicationRegistrationsInputs;
import com.us.cal.gov.entity.CitizenAppRegistrationEntity;
import com.us.cal.gov.repo.ICitizenApplicationRegistartion;

@Service
public class ICitizenAppRegistrationEntityServiceImpl implements ICitizenAppRegistrationEntityService {

	@Autowired
	private WebClient client;

	@Autowired
	private ICitizenApplicationRegistartion appRepo;

	@Value("${ssn.app.url}")
	private String endPointUrl;
	@Value("${ssn.app.state}")
	private String targetState;

	@Override
	public Long CitizenRegistration(ApplicationRegistrationsInputs inputs) {

		System.out.println(inputs.getCitizenSsn());

		if (String.valueOf(inputs.getCitizenSsn()).length() == 9) {

			String stateName = client.get().uri(endPointUrl, inputs.getCitizenSsn()).retrieve().bodyToMono(String.class)
					.block();

			if (stateName.equalsIgnoreCase(targetState)) {
				CitizenAppRegistrationEntity entity = new CitizenAppRegistrationEntity();
				BeanUtils.copyProperties(inputs, entity);
				entity.setStateName(stateName);
				Long ssnid = appRepo.save(entity).getAppId();
				return ssnid;
			} else {
				return 0l;
			}
		}
		return 0l;
	}
}
