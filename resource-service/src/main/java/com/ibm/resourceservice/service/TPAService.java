package com.ibm.resourceservice.service;

import com.ibm.resourceservice.domain.TPA;

import java.util.List;

public interface TPAService
{
    public TPA saveTPA(TPA tpa);
    public List<TPA> getTPAs();
    public TPA updateTPA(TPA tpa,String tpa_id);
    public TPA deleteTPA(String tpa_id);
}
