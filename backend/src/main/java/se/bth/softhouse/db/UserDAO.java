package se.bth.softhouse.db;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import se.bth.softhouse.entities.Users;

@RegisterMapperFactory(BeanMapperFactory.class)
public interface UserDAO {
	@SqlUpdate("create table if not exists App_Filter_USERS(id int primary key auto_increment, username varchar(255), emailid varchar(255), password varchar(255), confirm_password varchar(255))")
	public void createUsersTable();

	@SqlUpdate("insert into App_Filter_USERS (username,emailid,password,confirm_password) values (:username,:emailid,:password,:confirm_password);")
	void insertUser(@BindBean Users users);

	@SqlQuery("SELECT * FROM App_Filter_USERS WHERE id = :id;")
	public Users getBy(@Bind("id") int id);

	@SqlQuery("Select * from App_Filter_USERS where username = :username;")
	public Users getBy(@Bind("username") String username);

	@SqlQuery("select id,username,emailid from App_Filter_USERS;")
	public Users getBy();
}
