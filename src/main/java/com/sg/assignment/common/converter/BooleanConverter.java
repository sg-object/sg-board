package com.sg.assignment.common.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class BooleanConverter implements AttributeConverter<Boolean, String> {

	@Override
	public String convertToDatabaseColumn(Boolean attribute) {
		// TODO Auto-generated method stub
		return (attribute != null && attribute) ? "Y" : "N";
	}

	@Override
	public Boolean convertToEntityAttribute(String dbData) {
		// TODO Auto-generated method stub
		return "Y".equals(dbData);
	}
}
