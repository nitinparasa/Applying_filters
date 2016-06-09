package se.bth.softhouse.db;

import org.skife.jdbi.v2.sqlobject.Bind;
<<<<<<< HEAD
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;
=======
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
>>>>>>> b97f475cbabbe84eeaab4b9f1aea40ba40561672

import se.bth.softhouse.entities.Users;

@RegisterMapperFactory(BeanMapperFactory.class)
public interface UserDAO {
	@SqlUpdate("create table if not exists USERS(id int primary key auto_increment, username varchar(255), password varchar(255), confirm_password varchar(255))")
	public void createUsersTable();

	@SqlUpdate("insert into USERS (username,password,confirm_password) values (:username,:password,:confirm_password);")
	void insertUser(@BindBean Users users);

	@SqlQuery("SELECT * FROM USERS WHERE id = :id;")
	public Users getBy(@Bind("id") int id);

	@SqlQuery("Select * from USERS where username = :username;")
	public Users getBy(@Bind("username") String username);
	
	@SqlQuery("select * from USERS;")
	public Users getBy();
}
