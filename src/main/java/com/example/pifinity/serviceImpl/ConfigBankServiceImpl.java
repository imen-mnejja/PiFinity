package com.example.pifinity.serviceImpl;

import com.example.pifinity.entity.Configbank;
import com.example.pifinity.repository.BankAccountRepository;
import com.example.pifinity.repository.ConfigBankRepository;
import com.example.pifinity.serviceInterface.IConfigBankService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ConfigBankServiceImpl implements IConfigBankService {


    ConfigBankRepository configBankRepository;
    @Override
    public Configbank addConfigBank(Configbank configbank) {
        configbank.setDateCreation(LocalDateTime.now());
        return configBankRepository.save(configbank);
    }

    @Override
    public Configbank updateConfigBank(int id, Configbank configbank) {
    Configbank cb=retrieveConfigbank(id);
    cb.setDateCreation(LocalDateTime.now());
    cb.setInteret(configbank.getInteret());

        return configBankRepository.save(cb);
    }

    @Override
    public Configbank retrieveConfigbank(int id) {
        return configBankRepository.findById(id).orElse(null);
    }

    @Override
    public List<Configbank> retrieveAllConfigBank() {
        return configBankRepository.findAll();
    }

    @Override
    public Configbank getConfigBankForDate(LocalDate date) {

        return configBankRepository.findByDateCreation(date);
    }

}
