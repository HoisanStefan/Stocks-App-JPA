package com.project.stocks.services;

import com.project.stocks.entities.Provider;

import java.util.List;

public interface ProviderService {
    Integer countProviders();
    List<Provider> getAllProviders();
    Provider insertProvider(Provider provider);
    Provider getProviderById(Integer id);
    List<Provider> getProviderByName(String name);
}
