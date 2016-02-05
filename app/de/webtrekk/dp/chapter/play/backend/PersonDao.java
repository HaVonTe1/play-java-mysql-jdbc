package de.webtrekk.dp.chapter.play.backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.google.common.collect.Lists;

import de.webtrekk.dp.chapter.play.models.Person;
import play.db.DB;

public class PersonDao {
	
	private static final Connection connection = DB.getConnection();
	
	
	public static Person persists(Person p)
	{
		try {
        	PreparedStatement prepareStatement = connection.prepareStatement("insert into PERSON (firstname, lastname) values(?,?)");
        	prepareStatement.setString(1, p.firstname);
			prepareStatement.setString(2, p.lastname);
			prepareStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//TODO: find new primary Key
		
		return p;
	}

	
	public static List<Person> getAll()
	{
		List<Person> persons = Lists.newLinkedList();
		try {
			PreparedStatement prepareStatement = connection
					.prepareStatement("select id, firstname,lastname from PERSON");
			ResultSet rs = prepareStatement.executeQuery();
			while (rs.next()) {
				Person p = new Person();
				p.id = rs.getInt(1);
				p.firstname = rs.getString(2);
				p.lastname = rs.getString(3);
				persons.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return persons;
	}
	
	public static Person update(Person p)
	{
		if(p.id!=0)
		{
			
			try {
				PreparedStatement prepareStatement = connection
						.prepareStatement("update PERSON set firstname = ? , lastname = ? where id = ?");
				prepareStatement.setString(1, p.firstname);
				prepareStatement.setString(2, p.lastname);
				prepareStatement.setInt(3, p.id);
				
				prepareStatement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return p;
		
	}
	
	public static Person find(final int id)
	{
		if(id!=0)
		{
			
			PreparedStatement prepareStatement =null;
			ResultSet rs =null;
			try {
				prepareStatement = connection
						.prepareStatement("select id, firstname, lastname from PERSON  where id = ?");
				prepareStatement.setInt(1, id);
				
				rs = prepareStatement.executeQuery();
				Person result = new Person();
				if(rs.next())
				{
					result.id = rs.getInt("id");
					result.firstname  =rs.getString("firstname");
					result.lastname = rs.getString("lastname");
					
				}
				return result;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
			finally{
				try {
					rs.close();
					prepareStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	public static void delete(final int id)
	{
		if(id!=0)
		{
			
			PreparedStatement prepareStatement =null;
			try {
				prepareStatement = connection
						.prepareStatement("delete from PERSON  where id = ?");
				prepareStatement.setInt(1, id);
				
				prepareStatement.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally{
				try {
					prepareStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
