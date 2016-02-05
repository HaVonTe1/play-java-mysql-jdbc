package de.webtrekk.dp.chapter.play.controllers;

import static play.libs.Json.toJson;

import org.slf4j.LoggerFactory;

import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;

import com.fasterxml.jackson.databind.JsonNode;

import de.webtrekk.dp.chapter.play.backend.PersonDao;
import de.webtrekk.dp.chapter.play.models.Person;

public class Application extends Controller {

	org.slf4j.Logger LOGGER = LoggerFactory.getLogger(Application.class);

    @BodyParser.Of(BodyParser.Json.class)
    public Result addPerson() {
    	LOGGER.info("adding new person");
    	JsonNode asJson = request().body().asJson();
    	LOGGER.debug("JSon: {}",asJson);
    	Person person = Json.fromJson(asJson, Person.class);
    	LOGGER.debug("Entity: {}",person);
        person = PersonDao.persists(person);
        return Results.created(Json.toJson(person));
    }
    
	public Result getPersons() {
		return ok(toJson(PersonDao.getAll()));
	}
	
    @BodyParser.Of(BodyParser.Json.class)
	public Result updatePerson()
	{
    	JsonNode asJson = request().body().asJson();
    	Person person = Json.fromJson(asJson, Person.class);
    	Person updatedPerson = PersonDao.update(person);    	
    	return Results.ok(Json.toJson(updatedPerson));
	}
    
    public Result getPerson(int id)
    {
    	return Results.ok(Json.toJson(PersonDao.find(id)));
    }
    
    public Result deletePerson(int id)
    {
    	PersonDao.delete(id);
    	return Results.ok();
    }
}

