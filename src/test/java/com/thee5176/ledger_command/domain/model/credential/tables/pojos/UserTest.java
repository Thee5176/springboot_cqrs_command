package com.thee5176.ledger_command.domain.model.credential.tables.pojos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class UserTest {

    @Test
    void noArgsConstructorAndSettersGetters() {
        User u = new User();
        User returned = u
            .setId(42L)
            .setUsername("alice")
            .setPassword("secret")
            .setIsEnabled(true)
            .setIsAccountNonExpired(false)
            .setIsCredentialsNonExpired(true)
            .setIsAccountNonLocked(false);

        // fluent setters return same instance
        assertSame(u, returned);

        assertEquals(42L, u.getId());
        assertEquals("alice", u.getUsername());
        assertEquals("secret", u.getPassword());
        assertTrue(u.getIsEnabled());
        assertFalse(u.getIsAccountNonExpired());
        assertTrue(u.getIsCredentialsNonExpired());
        assertFalse(u.getIsAccountNonLocked());
    }

    @Test
    void allArgsConstructor() {
        User u = new User(7L, "bob", "pwd", Boolean.FALSE, Boolean.TRUE, Boolean.FALSE, Boolean.TRUE);

        assertEquals(7L, u.getId());
        assertEquals("bob", u.getUsername());
        assertEquals("pwd", u.getPassword());
        assertFalse(u.getIsEnabled());
        assertTrue(u.getIsAccountNonExpired());
        assertFalse(u.getIsCredentialsNonExpired());
        assertTrue(u.getIsAccountNonLocked());
    }

    @Test
    void copyConstructorCreatesEqualButDistinctInstance() {
        User original = new User(1L, "carol", "x", true, true, true, true);
        User copy = new User(original);

        assertEquals(original, copy);
        assertNotSame(original, copy);

        // modifying copy does not change original reference equality of fields (since fields are immutable types)
        copy.setUsername("changed");
        assertNotEquals(original.getUsername(), copy.getUsername());
    }

    @Test
    void equalsAndHashCodeBehavior() {
        User a = new User(10L, "u", "p", true, false, true, false);
        User b = new User(10L, "u", "p", true, false, true, false);
        User c = new User(11L, "u", "p", true, false, true, false);

        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());

        assertNotEquals(a, c);
        assertNotEquals(a.hashCode(), c.hashCode());

        assertFalse(a.equals(null));
        assertFalse(a.equals("not a user"));
        assertTrue(a.equals(a));
    }

    @Test
    void toStringContainsFieldValues() {
        User u = new User(99L, "dave", "hidden", Boolean.TRUE, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE);
        String s = u.toString();

        assertTrue(s.contains("99"));
        assertTrue(s.contains("dave"));
        assertTrue(s.contains("hidden"));
        // boolean flags should appear as well
        assertTrue(s.contains("true") || s.contains("false"));
    }

    @Test
    void equalityWithNullFields() {
        User x = new User();
        User y = new User();

        // both have all-null fields -> should be equal
        assertEquals(x, y);
        assertEquals(x.hashCode(), y.hashCode());

        // set one field on one instance -> not equal
        x.setUsername("non-null");
        assertNotEquals(x, y);
    }
}