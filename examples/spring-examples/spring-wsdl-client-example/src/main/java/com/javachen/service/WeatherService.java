package com.javachen.service;

import com.javachen.domain.CurrentWeather;

import javax.xml.bind.JAXBException;

public interface WeatherService {

	CurrentWeather getCurrentWeather(String country, String city) throws JAXBException;
}
