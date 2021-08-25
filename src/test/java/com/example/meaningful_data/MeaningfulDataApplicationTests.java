package com.example.meaningful_data;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.keygen.KeyGenerators;

@SpringBootTest
class MeaningfulDataApplicationTests {

    @Test
    void contextLoads() {

        System.out.println(KeyGenerators.string().generateKey());
    }

}
