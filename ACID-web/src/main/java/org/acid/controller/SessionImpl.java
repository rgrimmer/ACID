package org.acid.controller;

import java.util.UUID;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionImpl {

    // @TODO Use dependy of database entities
    // @EJB
    // private User;
    private final UUID uuid = UUID.randomUUID();
    private Integer id = null;
    private String name = null;
    private String email = null;

    public boolean isConnect() {
        return (getId() != null);
    }

    public UUID getUuid() {
        return uuid;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
