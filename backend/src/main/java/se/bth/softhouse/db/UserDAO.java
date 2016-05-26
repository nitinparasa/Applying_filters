package se.bth.softhouse.db;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

import com.example.helloworld.core.Users;

public interface UserDAO {
@SqlUpdate("create table if not exists users(id int primary key auto_increment, username varchar(255), password varchar(255), confirm_password varchar(255))")
public void createUsersTable();

@SqlUpdate("insert into users (id, username,password,confirm_password) values (:id, :username,:password,:confirm_password)")
void insertUser(@Bind("id") int id, @Bind("username") String username, @Bind("password") String password, @Bind("confirm_password") String confirm_password);

}