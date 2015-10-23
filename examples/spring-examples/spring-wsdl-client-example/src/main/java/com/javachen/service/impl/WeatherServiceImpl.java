package com.javachen.service.impl;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.javachen.domain.CurrentWeather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javachen.service.WeatherService;
import com.javachen.soap.GlobalWeatherSoap;

@Component("weatherService")
public class WeatherServiceImpl implements WeatherService {

	@Autowired
	private GlobalWeatherSoap soapService;

	public CurrentWeather getCurrentWeather(String country, String city) throws JAXBException {
		String weatherStr = soapService.getWeather(city, country);

		JAXBContext jaxbContext = JAXBContext.newInstance(CurrentWeather.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		StringReader reader = new StringReader(weatherStr);
		return (CurrentWeather) unmarshaller.unmarshal(reader);
	}

}
