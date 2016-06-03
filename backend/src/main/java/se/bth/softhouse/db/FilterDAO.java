package se.bth.softhouse.db;

import java.util.List;

import se.bth.softhouse.entities.Filter;

@RegisterMapperFactory(BeanMapperFactory.class)
public interface FilterDAO {

	@SqlUpdate("create table  if not exists filter (id int default not null auto_increment primary key, name varchar(100));")
	public void createFilterTable();

	@SqlQuery("SELECT * FROM filter;")
	public List<Filter> getAll();

	@SqlUpdate("INSERT INTO filter (name) VALUES (:name);")
	public void createFilter(@Bind("name") String name);

	@SqlQuery("SELECT * FROM filter WHERE id = :id;")
	public Filter getBy(@Bind("id") int id);

}
