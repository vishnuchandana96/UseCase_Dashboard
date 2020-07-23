package com.ibm.resourceservice.service;

import com.ibm.resourceservice.domain.TPA;
import com.ibm.resourceservice.repository.TPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TPAServiceImpl  implements TPAService
{
    @Autowired
    TPARepository tpaRepository;

    public TPAServiceImpl(TPARepository tpaRepository)
    {
        this.tpaRepository=tpaRepository;
    }

    @Override
    public TPA saveTPA(TPA tpa)
    {
        TPA savedTPA = null;
        savedTPA= tpaRepository.save(tpa);
        return savedTPA;
    }

    @Override
    public List<TPA> getTPAs()
    {
        List<TPA> tpaList = tpaRepository.findAll();
        tpaList = tpaList.stream().filter(x -> !x.isIsdeleted()).collect(Collectors.toList());
        return tpaList;
    }

    @Override
    public TPA updateTPA(TPA tpa, String tpa_id)
    {
        Optional<TPA> tpa1=tpaRepository.findById(tpa_id);
        tpa.setCluster_name((tpa.getCluster_name()));
        tpa.setApplication_name(tpa.getApplication_name());
        tpa.setTpa(tpa.getTpa());
        tpa.setTpa_pd(tpa.getTpa_pd());
        tpa.setTpa_status(tpa.getTpa_status());
        tpa.setDelivery_date(tpa.getDelivery_date());
        tpa.setTpa_id(tpa_id);
        TPA modifiedTPA=tpaRepository.save(tpa);
        return modifiedTPA ;
    }

    @Override
    public TPA deleteTPA(String tpa_id)
    {
        Optional<TPA> tpa1=tpaRepository.findById(tpa_id);
        tpaRepository.delete(tpa1.get());
        return tpa1.get();
    }
}
