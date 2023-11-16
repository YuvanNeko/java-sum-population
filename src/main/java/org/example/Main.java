package org.example;

import com.eclipsesource.json.JsonObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) throws IOException {
        String JsonString = Files.readString(Path.of("src/main/java/org/example/world.json"));

        JsonObject world = JsonObject.readFrom(JsonString);

        System.out.println(world.names());

        JsonObject continent = JsonObject.readFrom(String.valueOf(world.get("continent")));

        population_of_world(continent);

//        population_of_continent(continent, "Asia");
//
//        population_of_country(continent, "India");
//
//        population_of_state(continent, "Tamil Nadu");
//
//        population_of_city( continent, "Chennai");

        continent.forEach((continentName)-> {

            population_of_continent(continent ,continentName.getName());
            JsonObject continents = JsonObject.readFrom(String.valueOf(continent.get(continentName.getName())));
            continents.forEach((cntrys)->{
                JsonObject country = JsonObject.readFrom(String.valueOf(continents.get(cntrys.getName())));
                country.forEach((countryName)->{
                    population_of_country(continent, countryName.getName());
                    System.out.println(countryName.getName());
                    JsonObject country1 = JsonObject.readFrom(String.valueOf(countryName.getValue()));
                    country1.forEach((stat)->{
                        JsonObject state = JsonObject.readFrom(String.valueOf(country1.get(stat.getName())));
                        state.forEach((stateName) -> {
                            population_of_state(continent ,stateName.getName());
                            JsonObject states = JsonObject.readFrom(String.valueOf(state.get(stateName.getName())));
                            states.forEach((cty) -> {
                                JsonObject city = JsonObject.readFrom(String.valueOf(states.get("cities")));
                                city.forEach((city_name)->{
                                    population_of_city(continent ,city_name.getName());
                                });
                            });


                        });
                    });


                });
            });

        });


    }

    static void population_of_world(JsonObject continent){

        AtomicInteger PopulationOfAll = new AtomicInteger();

        continent.forEach((continentName)-> {
            JsonObject continents = JsonObject.readFrom(String.valueOf(continent.get(continentName.getName())));
            continents.forEach((cnt)->{
                JsonObject country = JsonObject.readFrom(String.valueOf(continents.get(cnt.getName())));
                country.forEach((countryName)->{
                    JsonObject country1 = JsonObject.readFrom(String.valueOf(countryName.getValue()));
                    country1.forEach((stat) -> {
                        JsonObject state = JsonObject.readFrom(String.valueOf(country1.get(stat.getName())));
                        state.forEach((stateName) -> {
                            JsonObject states = JsonObject.readFrom(String.valueOf(state.get(stateName.getName())));
                            states.forEach((cty)->{
                                JsonObject city = JsonObject.readFrom(String.valueOf(states.get(cty.getName())));
                                city.forEach((city_name)->{
                                    JsonObject cityName = JsonObject.readFrom(String.valueOf(city.get(city_name.getName())));
                                    PopulationOfAll.addAndGet(cityName.get("population").asInt());
                                });
                            });
                        });
                    });

                });
            });

        });


        System.out.println( "Population of the entire world : "  + PopulationOfAll);

    }

    static void population_of_continent(JsonObject continent, String ContinentName){

        AtomicInteger PopulationOfContinent = new AtomicInteger();

        if (continent.get(ContinentName) != null) {

            JsonObject country = JsonObject.readFrom(String.valueOf(continent.get(ContinentName)));
            country.forEach((cntry)->{
                JsonObject countries = JsonObject.readFrom(String.valueOf(country.get(cntry.getName())));
                countries.forEach((countryName) -> {
                    JsonObject country1 = JsonObject.readFrom(String.valueOf(countries.get(countryName.getName())));
                    country1.forEach((stat)->{
                        JsonObject state = JsonObject.readFrom(String.valueOf(country1.get(stat.getName())));
                        state.forEach((stateName) -> {
                            JsonObject states = JsonObject.readFrom(String.valueOf(state.get(stateName.getName())));
                            states.forEach((cty)->{
                                    JsonObject city = JsonObject.readFrom(String.valueOf(states.get(cty.getName())));
                            city.forEach((cityName) -> {
                                JsonObject cities = JsonObject.readFrom(String.valueOf(city.get(cityName.getName())));
                                PopulationOfContinent.addAndGet(cities.get("population").asInt());

                            });
                            });

                        });
                    });

                });
            });


            System.out.println("Population of the continent " + ContinentName + " : " + PopulationOfContinent);

        }


    }

    static void population_of_country(JsonObject continent, String CountryName){

        AtomicInteger PopulationOfCountry = new AtomicInteger();

        continent.forEach((ContinentName) -> {
            JsonObject countries = JsonObject.readFrom(String.valueOf(continent.get(ContinentName.getName())));
            JsonObject country = JsonObject.readFrom(String.valueOf(countries.get("countries")));
            if(country.get(CountryName) != null){JsonObject country1 = JsonObject.readFrom(String.valueOf(country.get(CountryName)));
                JsonObject state = JsonObject.readFrom(String.valueOf(country1.get("states")));
                state.forEach((stateName)-> {
                    JsonObject states = JsonObject.readFrom(String.valueOf(state.get(stateName.getName())));
                    JsonObject city = JsonObject.readFrom(String.valueOf(states.get("cities")));
                    city.forEach((cityName)-> {
                        JsonObject cities = JsonObject.readFrom(String.valueOf(city.get(cityName.getName())));
                        PopulationOfCountry.addAndGet(cities.get("population").asInt());
                    });
                });}

        });

        System.out.println("Population of the country " + CountryName + " : " + PopulationOfCountry);

    }

    static void population_of_state(JsonObject continent, String StateName){

        AtomicInteger PopulationOfState = new AtomicInteger();

        continent.forEach((ContinentName)-> {
            JsonObject continents = JsonObject.readFrom(String.valueOf(continent.get(ContinentName.getName())));
            JsonObject country = JsonObject.readFrom(String.valueOf(continents.get("countries")));
            country.forEach((countryName)->{
                JsonObject country1 = JsonObject.readFrom(String.valueOf(countryName.getValue()));
                JsonObject state = JsonObject.readFrom(String.valueOf(country1.get("states")));
                if(state.get(StateName) != null) {
                    JsonObject states = JsonObject.readFrom(String.valueOf(state.get(StateName)));
                    JsonObject city = JsonObject.readFrom(String.valueOf(states.get("cities")));
                    city.forEach((cityName)-> {
                        JsonObject cities = JsonObject.readFrom(String.valueOf(city.get(cityName.getName())));
                        PopulationOfState.addAndGet(cities.get("population").asInt());
                    });
                }

            });
        });

        System.out.println( "Population of state " + StateName +  " : "  + PopulationOfState);

    }

    static void population_of_city(JsonObject continent, String CityName){

        AtomicInteger PopulationOfCity = new AtomicInteger();

        continent.forEach((continentName)-> {
            JsonObject continents = JsonObject.readFrom(String.valueOf(continent.get(continentName.getName())));
            JsonObject country = JsonObject.readFrom(String.valueOf(continents.get("countries")));
            country.forEach((countryName)->{
                JsonObject country1 = JsonObject.readFrom(String.valueOf(countryName.getValue()));
                JsonObject state = JsonObject.readFrom(String.valueOf(country1.get("states")));
                state.forEach((stateName) -> {
                    JsonObject states = JsonObject.readFrom(String.valueOf(state.get(stateName.getName())));
                    JsonObject city = JsonObject.readFrom(String.valueOf(states.get("cities")));
                    if(city.get(CityName) != null){
                        JsonObject population = JsonObject.readFrom(String.valueOf(city.get(CityName)));
                        PopulationOfCity.addAndGet(population.get("population").asInt());
                    }
                });
            });
        });


        System.out.println( "Population of city " + CityName + " : "  + PopulationOfCity);

    }

}
