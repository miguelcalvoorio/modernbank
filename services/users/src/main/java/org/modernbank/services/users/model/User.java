package org.modernbank.services.users.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class User {
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
