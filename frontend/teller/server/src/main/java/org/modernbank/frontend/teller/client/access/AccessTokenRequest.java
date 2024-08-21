package org.modernbank.frontend.teller.client.access;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccessTokenRequest {
    private String username;
    private String password;
}
