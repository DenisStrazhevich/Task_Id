package com.strazhevich.service;

import com.strazhevich.entity.Application;

import java.util.List;

public interface ApplicationService {

    void addApplication(Application application);
    void completeById(Integer id);
    void denyById(Integer id);
    List<Application> findAllByStatusDesc();
    List<Application> findByUsername(String username);
    List<Application> findByStatus(String status);
    int getCountByStatus(String status);
    int getAllCount();
}
