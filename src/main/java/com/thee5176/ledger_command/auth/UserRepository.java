package com.thee5176.ledger_command.auth;

import org.jooq.DSLContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import com.thee5176.ledger_command.domain.model.credential.Tables;
import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class UserRepository {
    private final DSLContext dsl;

    public void createUser(UserDetails user) {
        dsl.insertInto(Tables.USER)
                .columns(Tables.USER.USERNAME, Tables.USER.PASSWORD)
                .values(user.getUsername(), user.getPassword())
                .execute();
    }

    public UserDetails fetchUserByUsername(String username) {
        return dsl.selectFrom(Tables.USER)
                .where(Tables.USER.USERNAME.eq(username))
                .fetchOneInto(UserDetails.class);
    }
}
