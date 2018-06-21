package com.strazhevich.service.impl;

import com.strazhevich.entity.Application;
import com.strazhevich.repository.ApplicationRepository;
import com.strazhevich.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class ApplicationServiceImpl implements ApplicationService {

    private ApplicationRepository applicationRepository;

    @Autowired
    public void setApplicationRepository(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Override
    public void addApplication(Application application) {
        applicationRepository.save(application);
    }

    @Override
    public void completeById(Integer id) {
        Application application = applicationRepository.findOne(id);
        application.setStatus("completed");
        applicationRepository.save(application);
    }

    @Override
    public void denyById(Integer id) {
        Application application = applicationRepository.findOne(id);
        application.setStatus("denied");
        applicationRepository.save(application);
    }


    @Override
    public List<Application> findAllByStatusDesc() {
        return applicationRepository.findAllByStatusDesc();
    }

    @Override
    public List<Application> findByUsername(String username) {
        return applicationRepository.findByUsername(username);
    }

    @Override
    public List<Application> findByStatus(String status) {
        return applicationRepository.findByStatus(status);
    }

    @Override
    public int getCountByStatus(String status) {
        return applicationRepository.getCountByStatus(status);
    }

    @Override
    public int getAllCount() {
        return applicationRepository.countAllBy();
    }


}
