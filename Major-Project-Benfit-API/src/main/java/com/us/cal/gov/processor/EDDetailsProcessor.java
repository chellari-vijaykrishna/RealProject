package com.us.cal.gov.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.us.cal.gov.bindings.ElgibilityDetails;
import com.us.cal.gov.entity.EligiblityDetailsEntity;

@Component
public class EDDetailsProcessor implements ItemProcessor<EligiblityDetailsEntity, ElgibilityDetails> {

	@Override
	public ElgibilityDetails process(EligiblityDetailsEntity item) throws Exception {
		if(item.getPlanStatus().equalsIgnoreCase("approved")) {
			   ElgibilityDetails  details=new  ElgibilityDetails();
			   BeanUtils.copyProperties(item, details);
			   return details;
		}
		return null;
	}

}
