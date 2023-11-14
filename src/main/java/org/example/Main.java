package org.example;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicInteger;


public class Main {



    public static void main(String[] args) throws IOException {

//        String jsonString = Files.readString(Path.of("src/main/java/org/example/world.json"));
//
//
//
//        JsonObject jsonObject = JsonObject.readFrom(jsonString);
//
//        JsonArray continent = JsonArray.readFrom(String.valueOf(jsonObject.get("continent")));
////         for(int i = 0; i < continent.size(); i++){
////             JsonObject country = JsonObject.readFrom(String.valueOf(continent.get(i)));
////             JsonObject country2 = JsonObject.readFrom(String.valueOf(country.get("countries")));
//////             JsonArray countries = JsonArray.readFrom(String.valueOf(country.get("countries")));
////         }
//        JsonObject country = JsonObject.readFrom(String.valueOf(continent.get(0)));
//        JsonArray countries = JsonArray.readFrom(String.valueOf(country.get("Asia")));
//        JsonObject country2 = JsonObject.readFrom(String.valueOf(countries.get(0)));
////        System.out.println(country2.names());
//        JsonArray States = JsonArray.readFrom(String.valueOf(country2.get("countries")));
////        System.out.println(States.get(0));
//        JsonObject countries2 = JsonObject.readFrom(String.valueOf(States.get(0)));
////        System.out.println(countries2.names());
//        JsonObject country3 = JsonObject.readFrom(String.valueOf(countries2.get("Afghanistan")));
////        System.out.println(country3.get(0));
//        JsonArray State = JsonArray.readFrom(String.valueOf(country3.get("states")));
////        System.out.println(State.get(0));
//        JsonObject states = JsonObject.readFrom(String.valueOf(State.get(0)));
////        System.out.println(states.names());
//
////        System.out.println(continent.get(0));
//
//        population_of_world();

//        population_of_continent("Asia");
//
//        population_of_country("India");
//
//        population_of_state("Tamil Nadu");
//
//        population_of_city("Chennai");

//        System.out.println(countries.get(0));

        String jsonString = Files.readString(Path.of("src/main/java/org/example/world.json"));

        JsonObject jsonObject = JsonObject.readFrom(jsonString);


        JsonObject continent = JsonObject.readFrom(String.valueOf(jsonObject.get("continent")));

//        System.out.println(continent.names());

        continent.forEach((continentName)-> {

            try {
                population_of_continent(continentName.getName());
                JsonObject continents = JsonObject.readFrom(String.valueOf(continent.get(continentName.getName())));
//            System.out.println(continents.names());
                JsonObject country = JsonObject.readFrom(String.valueOf(continents.get("countries")));
//            System.out.println(country.names());

                country.forEach((countryName)->{
                    try {
                        population_of_country(countryName.getName());
                        System.out.println(countryName.getName());
                        JsonObject country1 = JsonObject.readFrom(String.valueOf(countryName.getValue()));
//                System.out.println(country1.names());
                        JsonObject state = JsonObject.readFrom(String.valueOf(country1.get("states")));
//                System.out.println(state.names());
                        state.forEach((stateName) -> {
                            try {
                                population_of_state(stateName.getName());
                                JsonObject states = JsonObject.readFrom(String.valueOf(state.get(stateName.getName())));
//                    System.out.println(states.names());
                                JsonObject city = JsonObject.readFrom(String.valueOf(states.get("cities")));
//                    System.out.println(city.get("Chennai"));
                                city.forEach((city_name)->{
                                    try {
                                        population_of_city(city_name.getName());
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                    JsonObject cityName = JsonObject.readFrom(String.valueOf(city.get(city_name.getName())));
                                });
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }

                        });
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        });




    }

    static void population_of_world() throws IOException {

        AtomicInteger PopulationOfAll = new AtomicInteger();

        String jsonString = Files.readString(Path.of("src/main/java/org/example/world.json"));

        JsonObject jsonObject = JsonObject.readFrom(jsonString);


        JsonObject continent = JsonObject.readFrom(String.valueOf(jsonObject.get("continent")));

//        System.out.println(continent.names());

        continent.forEach((continentName)-> {
            JsonObject continents = JsonObject.readFrom(String.valueOf(continent.get(continentName.getName())));
//            System.out.println(continents.names());
            JsonObject country = JsonObject.readFrom(String.valueOf(continents.get("countries")));
//            System.out.println(country.names());
            country.forEach((countryName)->{
                JsonObject country1 = JsonObject.readFrom(String.valueOf(countryName.getValue()));
//                System.out.println(country1.names());
                JsonObject state = JsonObject.readFrom(String.valueOf(country1.get("states")));
//                System.out.println(state.names());
                state.forEach((stateName) -> {
                    JsonObject states = JsonObject.readFrom(String.valueOf(state.get(stateName.getName())));
//                    System.out.println(states.names());
                    JsonObject city = JsonObject.readFrom(String.valueOf(states.get("cities")));
//                    System.out.println(city.get("Chennai"));
                    city.forEach((city_name)->{
                        JsonObject cityName = JsonObject.readFrom(String.valueOf(city.get(city_name.getName())));
//                        System.out.println(cityName.get("population"));
                        PopulationOfAll.addAndGet(cityName.get("population").asInt());
                    });
                });
            });
        });

//        System.out.println(country.names());

        System.out.println( "Population of the entire world : "  + PopulationOfAll);
////            JsonArray country = JsonArray.readFrom(String.valueOf(continents.get("countries")));
//            System.out.println(.get(0));

        }


    static void population_of_continent(String continentName) throws IOException {

        AtomicInteger populationOfContinent = new AtomicInteger();

        String jsonString = Files.readString(Path.of("src/main/java/org/example/world.json"));

        JsonObject jsonObject = JsonObject.readFrom(jsonString);

        JsonObject continent = JsonObject.readFrom(String.valueOf(jsonObject.get("continent")));

//        System.out.println(continent.get(continentName));
        if (continent.get(continentName) != null) {

        JsonObject country = JsonObject.readFrom(String.valueOf(continent.get(continentName)));
//        System.out.println(country.names());
        JsonObject countries = JsonObject.readFrom(String.valueOf(country.get("countries")));
//        System.out.println(countries.names());
        countries.forEach((countryName) -> {
            JsonObject country1 = JsonObject.readFrom(String.valueOf(countries.get(countryName.getName())));
            JsonObject state = JsonObject.readFrom(String.valueOf(country1.get("states")));
//            System.out.println(state.names());
            state.forEach((stateName) -> {
                JsonObject states = JsonObject.readFrom(String.valueOf(state.get(stateName.getName())));
//                System.out.println(states.names());
                JsonObject city = JsonObject.readFrom(String.valueOf(states.get("cities")));
                city.forEach((cityName) -> {
                    JsonObject cities = JsonObject.readFrom(String.valueOf(city.get(cityName.getName())));
//                    System.out.println(cities.get("population"));
                    populationOfContinent.addAndGet(cities.get("population").asInt());

                });
            });
        });
    }

        System.out.println("Population of the continent " + continentName + " : " + populationOfContinent);


    }

    static void population_of_country(String countryName) throws IOException {

        AtomicInteger populationOfCountry = new AtomicInteger();

        String jsonString = Files.readString(Path.of("src/main/java/org/example/world.json"));

        JsonObject jsonObject = JsonObject.readFrom(jsonString);

        JsonObject continent = JsonObject.readFrom(String.valueOf(jsonObject.get("continent")));

//        System.out.println(continent.get(continentName));

//        JsonObject country = JsonObject.readFrom(String.valueOf(continent.get()));
        continent.forEach((continent1) -> {
            JsonObject countries = JsonObject.readFrom(String.valueOf(continent.get(continent1.getName())));
//        System.out.println(countries.names());
        JsonObject country = JsonObject.readFrom(String.valueOf(countries.get("countries")));
//            System.out.println(country.names());
            if(country.get(countryName) != null){JsonObject country1 = JsonObject.readFrom(String.valueOf(country.get(countryName)));
//            System.out.println(country.get(countryName));
                JsonObject state = JsonObject.readFrom(String.valueOf(country1.get("states")));
//            System.out.println(state.names());
                state.forEach((stateName)-> {
                    JsonObject states = JsonObject.readFrom(String.valueOf(state.get(stateName.getName())));
//                System.out.println(states.names());
                    JsonObject city = JsonObject.readFrom(String.valueOf(states.get("cities")));
                    city.forEach((cityName)-> {
                        JsonObject cities = JsonObject.readFrom(String.valueOf(city.get(cityName.getName())));
//                    System.out.println(cities.get("population"));
                        populationOfCountry.addAndGet(cities.get("population").asInt());
//
                    });
                });}

        });
//        System.out.println(country.names());

        System.out.println("Population of the country " + countryName + " : " + populationOfCountry);


    }

    static void population_of_state(String stateName) throws IOException {

        AtomicInteger PopulationOfAll = new AtomicInteger();

        String jsonString = Files.readString(Path.of("src/main/java/org/example/world.json"));

        JsonObject jsonObject = JsonObject.readFrom(jsonString);


        JsonObject continent = JsonObject.readFrom(String.valueOf(jsonObject.get("continent")));

//        System.out.println(continent.names());

        continent.forEach((continentName)-> {
            JsonObject continents = JsonObject.readFrom(String.valueOf(continent.get(continentName.getName())));
//            System.out.println(continents.names());
            JsonObject country = JsonObject.readFrom(String.valueOf(continents.get("countries")));
//            System.out.println(country.names());
            country.forEach((countryName)->{
                JsonObject country1 = JsonObject.readFrom(String.valueOf(countryName.getValue()));
//                System.out.println(country1.names());
                JsonObject state = JsonObject.readFrom(String.valueOf(country1.get("states")));
//                System.out.println(state.names());
//                state.forEach((stateName1) -> {

                    if(state.get(stateName) != null) {
                        JsonObject states = JsonObject.readFrom(String.valueOf(state.get(stateName)));
//                        System.out.println(states.names());
                        JsonObject city = JsonObject.readFrom(String.valueOf(states.get("cities")));
                        city.forEach((cityName)-> {
                            JsonObject cities = JsonObject.readFrom(String.valueOf(city.get(cityName.getName())));
                            PopulationOfAll.addAndGet(cities.get("population").asInt());
                        });
                    }

//                    JsonObject states = JsonObject.readFrom(String.valueOf(state.get(stateName1)));
//                    System.out.println(states.names());
//                    JsonObject city = JsonObject.readFrom(String.valueOf(states.get("cities")));
//                city.forEach((cityName)-> {
//                    JsonObject cities = JsonObject.readFrom(String.valueOf(city.get(cityName.getName())));
////                    System.out.println(cities.get("population"));
//                    PopulationOfAll.addAndGet(cities.get("population").asInt());
////
//                });
//                    System.out.println(city.get(cityName));

                });
            });
//        });

//        System.out.println(country.names());

        System.out.println( "Population of state " + stateName +  " : "  + PopulationOfAll);
////            JsonArray country = JsonArray.readFrom(String.valueOf(continents.get("countries")));
//            System.out.println(.get(0));

    }
    static void population_of_city(String cityName) throws IOException {

        AtomicInteger PopulationOfCity = new AtomicInteger();

        String jsonString = Files.readString(Path.of("src/main/java/org/example/world.json"));

        JsonObject jsonObject = JsonObject.readFrom(jsonString);


        JsonObject continent = JsonObject.readFrom(String.valueOf(jsonObject.get("continent")));

//        System.out.println(continent.names());

        continent.forEach((continentName)-> {
            JsonObject continents = JsonObject.readFrom(String.valueOf(continent.get(continentName.getName())));
//            System.out.println(continents.names());
            JsonObject country = JsonObject.readFrom(String.valueOf(continents.get("countries")));
//            System.out.println(country.names());
            country.forEach((countryName)->{
                JsonObject country1 = JsonObject.readFrom(String.valueOf(countryName.getValue()));
//                System.out.println(country1.names());
                JsonObject state = JsonObject.readFrom(String.valueOf(country1.get("states")));
//                System.out.println(state.names());
                state.forEach((stateName) -> {
                    JsonObject states = JsonObject.readFrom(String.valueOf(state.get(stateName.getName())));
//                    System.out.println(states.names());
                    JsonObject city = JsonObject.readFrom(String.valueOf(states.get("cities")));
                    if(city.get(cityName) != null){
//                        System.out.println(city.get(cityName));
                        JsonObject population = JsonObject.readFrom(String.valueOf(city.get(cityName)));
                        PopulationOfCity.addAndGet(population.get("population").asInt());
//                        System.out.println(population.get("population"));
                    }


                });
            });
        });

//        System.out.println(country.names());

        System.out.println( "Population of city " + cityName + " : "  + PopulationOfCity);
////            JsonArray country = JsonArray.readFrom(String.valueOf(continents.get("countries")));
//            System.out.println(.get(0));

    }


}


