package com.example.user.config.oauth.service;

import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

@Service
public class IClientService {
    public BaseClientDetails findClientByClientId(String clientId) {
        return null;
    }
}
