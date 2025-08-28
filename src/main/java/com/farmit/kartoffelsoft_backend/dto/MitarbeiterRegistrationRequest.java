package com.farmit.kartoffelsoft_backend.dto;

import com.farmit.kartoffelsoft_backend.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MitarbeiterRegistrationRequest {

    private String vorname;
    private String nachname;
    private String username;
    private String password;
    private Role role;
    private long abteilungId;
}