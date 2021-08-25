package com.example.meaningful_data.rest.api.utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.stereotype.Component;

/**
 * @Authot: Albert Akimov
 * @Date: 25.08.2021
 * @Description:
 */

@Component
@Data
@PropertySource(value = {"classpath:application.properties"})
public class EncryptorUtil {

    @Value("${aes.256.salt:}")
    private String salt;

    @Value("${aes.256.password:}")
    private String password;

    public String encrypt(String str) {
        return Encryptors.text(password, salt).encrypt(str);
    }

    public String decrypt(String str) {
        return Encryptors.text(password, salt).decrypt(str);
    }
}
