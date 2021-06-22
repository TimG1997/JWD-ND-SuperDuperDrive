package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.dto.DecryptedCredentialsDTO;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CredentialsService {

    private final CredentialsMapper credentialsMapper;
    private final EncryptionService encryptionService;

    public CredentialsService(CredentialsMapper credentialsMapper, EncryptionService encryptionService) {
        this.credentialsMapper = credentialsMapper;
        this.encryptionService = encryptionService;
    }

    public boolean addCredentials(Credentials credentials){
        String password = credentials.getPassword();

        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = this.encryptionService.encryptValue(password, encodedKey);

        credentials.setPassword(encryptedPassword);
        credentials.setKey(encodedKey);

        int affectedRows = this.credentialsMapper.insertCredentials(credentials);
        return affectedRows > 0;
    }

    public boolean deleteCredentials(Integer id, Integer userId){
        int affectedRows = this.credentialsMapper.deleteCredentials(id, userId);

        return affectedRows > 0;
    }

    public Credentials getDecryptedCredentials(Integer id, Integer userId){
        Credentials credentials = this.credentialsMapper.getCredentials(id, userId);

        if(credentials == null){
            return null;
        }

        String encryptedPassword = credentials.getPassword();
        String key = credentials.getKey();
        String decryptedPassword = encryptionService.decryptValue(encryptedPassword, key);

        credentials.setPassword(decryptedPassword);
        return credentials;
    }

    public List<DecryptedCredentialsDTO> getAllCredentials(Integer userId){
        Credentials[] allCredentials = credentialsMapper.getAllCredentials(userId);

        List<DecryptedCredentialsDTO> credentialDTOs = Arrays.stream(allCredentials)
                .map(credentials -> {
                    String decryptedPassword = encryptionService.decryptValue(credentials.getPassword(), credentials.getKey());
                    return new DecryptedCredentialsDTO(credentials.getId(),
                                                       credentials.getUrl(),
                                                       credentials.getUsername(),
                                                       credentials.getKey(),
                                                       credentials.getPassword(),
                                                       credentials.getUserId(),
                                                       decryptedPassword);
                }).collect(Collectors.toList());

        return credentialDTOs;
    }

    public boolean updateCredentials(Credentials credentials){
        String encryptedPassword = this.encryptionService.encryptValue(credentials.getPassword(), credentials.getKey());
        credentials.setPassword(encryptedPassword);
        int rowsAffected = this.credentialsMapper.updateCredentials(credentials);

        return rowsAffected > 0;
    }


}