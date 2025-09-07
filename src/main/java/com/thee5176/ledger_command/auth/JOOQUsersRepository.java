package com.thee5176.ledger_command.auth;

import org.jooq.DSLContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import com.thee5176.ledger_command.domain.model.credential.Tables;
import com.thee5176.ledger_command.domain.model.credential.tables.pojos.Users;
import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class JOOQUsersRepository {
    private final DSLContext dsl;

    public void createUser(UserDetails user) {
        dsl.insertInto(Tables.USERS)
                .columns(Tables.USERS.USERNAME, Tables.USERS.PASSWORD)
                .values(user.getUsername(), user.getPassword())
                .execute();
    }



    public Users fetchUserByUsername(String username) {
        return dsl.select()
                .from(Tables.USERS)
                .where(Tables.USERS.USERNAME.eq(username))
                .fetchOneInto(Users.class);
    }
}
