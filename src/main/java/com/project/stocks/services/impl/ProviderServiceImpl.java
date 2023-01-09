package com.project.stocks.services.impl;

import com.project.stocks.entities.Provider;
import com.project.stocks.repositories.ProviderRepository;
import com.project.stocks.repositories.impl.ProviderRepositoryImpl;
import com.project.stocks.services.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProviderServiceImpl implements ProviderService {
    @Autowired
    private ProviderRepositoryImpl providerRepositoryImpl;

    @Autowired
    private ProviderRepository providerRepository;

    @Override
    public Integer countProviders() {
        //access DAO layer
        Integer count = this.providerRepositoryImpl.count();
        return count;
    }

    @Override
    public List<Provider> getAllProviders() {
        return this.providerRepository.findAll();
    }

    @Override
    public Provider getProviderById(Integer id) {
        Optional<Provider> provider = this.providerRepository.findById(id);
        return provider.orElse(new Provider());
    }

    @Override
    public Provider insertProvider(Provider provider) {
        try {
            return this.providerRepository.save(new Provider(provider));
        } catch (Exception e) {
            System.out.println("A intervenit o eroare:");
            System.out.println(e.getCause());
            return new Provider();
        }
    }

    @Override
    public List<Provider> getProviderByName(String name) {
        return this.providerRepository.findByName(name);
    }
}
