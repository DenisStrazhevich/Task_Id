package com.strazhevich.repository;

import com.strazhevich.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;


public interface ApplicationRepository extends JpaRepository<Application,Integer> {

    @Query("select a from Application a inner join a.user u where a.user.username =:username")
    List<Application> findByUsername(@Param("username") String username);

    @Query("select a from Application a where a.status =:status order by a.status desc ")
    List<Application> findByStatus(@Param("status") String status);

    @Query("select count(a) from Application a where a.status =:status ")
    int getCountByStatus(@Param("status") String status);

    @Query("select a from Application a order by a.status desc ")
    List<Application> findAllByStatusDesc();
    int countAllBy();


}
