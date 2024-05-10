package com.example.restapi.restintegration.dto;

public class CountryPopulationDto {
		private NameDto name;
		private long population;
		
		
		public NameDto getName() {
			return name;
		}
		public long getPopulation() {
			return population;
		}
		public void setName(NameDto name) {
			this.name = name;
		}
		public void setPopulation(long population) {
			this.population = population;
		}
		
}
