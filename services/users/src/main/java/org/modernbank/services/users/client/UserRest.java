package org.modernbank.services.users.client;

import java.util.List;
import java.util.Map;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Schema(
    name = "User",
    description = "User schema")
@JsonInclude(Include.NON_NULL)
@Getter
@Setter
public class UserRest {
    private String id;
    private Long createdTimestamp;
    private String username;
    private String email;
    private boolean emailVerified;
    private String firstName;
    private String lastName;
    List<String> requiredActions;
    Map<String, List<String>> clientRoles;
    protected List<String> groups;
}