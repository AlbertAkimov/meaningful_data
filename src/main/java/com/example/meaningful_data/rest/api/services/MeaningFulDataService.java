package com.example.meaningful_data.rest.api.services;

import com.example.meaningful_data.rest.api.models.MeaningFulData;
import com.example.meaningful_data.rest.api.repositories.MeaningFulDataRepository;
import com.example.meaningful_data.rest.api.utils.EncryptorUtil;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Authot: Albert Akimov
 * @Date: 23.08.2021
 * @Description:
 */
@Service
public class MeaningFulDataService extends AbstractCRUDService<MeaningFulData, MeaningFulDataRepository> {

    private final EncryptorUtil encryptorUtil;

    protected MeaningFulDataService(MeaningFulDataRepository repository, EncryptorUtil encryptorUtil) {
        super(repository, MeaningFulData.class);
        this.encryptorUtil = encryptorUtil;
    }

    @Override
    public MeaningFulData getById(Long id) {
        MeaningFulData result = super.getById(id);
        result.setAccess(encryptorUtil.decrypt(result.getAccess()));
        return result;
    }

    @Override
    public MeaningFulData save(MeaningFulData entity) {
        entity.setAccess(encryptorUtil.encrypt(entity.getAccess()));
        return super.save(entity);
    }

    @Override
    public List<MeaningFulData> getAll() {
        List<MeaningFulData> results = super.getAll();
        results.forEach(x -> x.setAccess(encryptorUtil.decrypt(x.getAccess())));
        return results;
    }
}
