package com.thee5176.ledger_command.auth;

import java.util.List;
import org.jooq.DSLContext;
import com.thee5176.ledger_command.domain.model.credential.Tables;
import com.thee5176.ledger_command.domain.model.credential.tables.pojos.Authorities;

public class JOOQAuthoritiesRepository {
    private DSLContext dsl;

    public List<Authorities> getAuthoritiesByUsername(String username) {
        return dsl.select(Tables.AUTHORITIES.AUTHORITY)
                .from(Tables.AUTHORITIES)
                .where(Tables.AUTHORITIES.USERNAME.eq(username))
                .fetchInto(Authorities.class);
    }
}