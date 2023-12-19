package be.jossart.dao;

import java.util.ArrayList;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.json.JSONObject;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import be.jossart.javabeans.Person;

public class PersonDAO extends DAO<Person> {
	public PersonDAO() {
	}
	@Override
	public boolean create(Person obj) {
		MultivaluedMap<String, String> paramsPost = new MultivaluedMapImpl();
		paramsPost.add("firstname", obj.getFirstname());
		paramsPost.add("lastname", obj.getLastname());
		paramsPost.add("username", obj.getUsername());
		paramsPost.add("password", obj.getPassword());

		try {
			 ClientResponse res = this.resource
	 	                .path("person/create")
	 	                .accept(MediaType.APPLICATION_JSON)
	 	                .post(ClientResponse.class, paramsPost);
			
			if (res.getStatus() == 201) {
				return true;
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return false;
		}
		return false;
	}

	@Override
	public boolean delete(Person obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Person obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Person find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Person> findAll(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}
	public Person login(String username, String password) {
		MultivaluedMap<String, String> paramsPost = new MultivaluedMapImpl();
		paramsPost.add("username", username);
		paramsPost.add("password", password);

		try {
			 ClientResponse res = this.resource
	 	                .path("person/login")
	 	                .accept(MediaType.APPLICATION_JSON)
	 	                .post(ClientResponse.class, paramsPost);
			
			if (res.getStatus() == 200) {
				String response = res.getEntity(String.class);
				JSONObject json = new JSONObject(response);
				int idPerson = json.getInt("idPerson");
				String firstname = json.getString("firstname");
				String lastname = json.getString("lastname");
				Person person = new Person(idPerson, firstname, lastname, username, password);
				
				return person;
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
		return null;
	}
}
