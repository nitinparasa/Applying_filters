package se.bth.softhouse.db;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

import se.bth.softhouse.entities.Users;

public interface UserDAO {
	@SqlUpdate("create table if not exists USERS(id int primary key auto_increment, username varchar(255), password varchar(255), confirm_password varchar(255))")
	public void createUsersTable();

	@SqlUpdate("insert into USERS (id, username,password,confirm_password) values (:id, :username,:password,:confirm_password)")
	void insertUser(@Bind("id") int id, @Bind("username") String username, @Bind("password") String password,
			@Bind("confirm_password") String confirm_password);

	@SqlQuery("SELECT * FROM USERS WHERE id = :id;")
	public Users getBy(@Bind("id") int id);

}