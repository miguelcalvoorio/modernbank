package org.modernbank.services.users.service;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.keycloak.admin.client.Keycloak;
import org.modernbank.services.users.model.User;
import org.modernbank.services.users.utility.UserMapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.InternalServerErrorException;
import jakarta.ws.rs.WebApplicationException;

@ApplicationScoped
public class UserService {
    @ConfigProperty(name = "quarkus.keycloak.admin-client.realm")
    String modernBankRealmName;

    @Inject Keycloak keycloak;
    @Inject UserMapper userMapper;

    public List<User> getAllUsers() throws WebApplicationException {
        try {
            return keycloak.realm(modernBankRealmName).users()
                .list()
                .stream()
                .map(userRepresentation -> userMapper.toModelFromUserRepresentation(userRepresentation))
                .collect(Collectors.toList());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new InternalServerErrorException("Failed to invoke KeyCloak Admin Client UsersResource.list()");
        }
    }
}
