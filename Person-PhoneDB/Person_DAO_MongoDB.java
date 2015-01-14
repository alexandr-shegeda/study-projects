package PersonDB;

import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;


public class Person_DAO_MongoDB implements PersonDAO
{
	MongoClient mongoClient;
	DB database;
	DBCollection collection;
	
	@Override
	public void Connect() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException 
	{
		try 
		{
			mongoClient = new MongoClient();
			database = mongoClient.getDB("test");
//			collection = database.getCollection("person");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public void DisConnect() throws SQLException 
	{
		mongoClient = null;
	}

	@Override
	public void Create(Person p) 
	{
		collection = database.getCollection("person");
		DBObject person = new BasicDBObject("ID", p.getId())
		.append("FirstName", p.getFName())
		.append("LastName", p.getLName())
		.append("Age", p.getAge());
		collection.insert(person);
	}

	@Override
	public ArrayList<Person> Read() throws SQLException 
	{
		collection = database.getCollection("person");
		DBCursor c = collection.find();
		ArrayList<Person> personList = new ArrayList<Person>();
		while(c.hasNext())
		{
			Person p = new Person();
			p.setId((Integer)c.next().get("ID"));
			p.setFName((String)c.next().get("FirstName"));
			p.setLName((String)c.next().get("LastName"));
			p.setAge((Integer)c.next().get("Age"));
			personList.add(p);
		}
		return personList;
	}

	@Override
	public void Update(Person p) 
	{
		collection = database.getCollection("person");
		DBObject query = new BasicDBObject("ID",p.getId());
		DBObject update = new BasicDBObject("ID", p.getId())
		.append("FirstName", p.getFName())
		.append("LastName", p.getLName())
		.append("Age", p.getAge());
		collection.findAndModify(query, update);
	}

	@Override
	public void Delete(Person p) 
	{
		collection = database.getCollection("person");
		DBObject person = new BasicDBObject("ID",p.getId());
		collection.remove(person);
	}

}
