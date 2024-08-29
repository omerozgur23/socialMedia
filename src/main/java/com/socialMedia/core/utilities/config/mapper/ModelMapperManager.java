package com.socialMedia.core.utilities.config.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelMapperManager implements ModelMapperService {

	@Autowired
	private ModelMapper mapper;

	@Override
	public ModelMapper forResponse() {
		mapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.LOOSE);
		return mapper;
	}

	@Override
	public ModelMapper forRequest() {
		mapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.STANDARD);
		return mapper;
	}

}
