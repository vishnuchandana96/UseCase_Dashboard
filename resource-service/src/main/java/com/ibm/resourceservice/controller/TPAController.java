package com.ibm.resourceservice.controller;

import com.ibm.resourceservice.domain.TPA;
import com.ibm.resourceservice.service.TPAServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1")
@CrossOrigin(value = "*")
public class TPAController
{
    @Autowired
    TPAServiceImpl tpaService;

    public TPAController(TPAServiceImpl tpaService)
    {
        this.tpaService=tpaService;
    }

    @PostMapping("tpa")
    public ResponseEntity<?> saveTPA(@RequestBody TPA tpa)
    {
        TPA tpa1 = tpaService.saveTPA(tpa);
        return new ResponseEntity<TPA>(tpaService.saveTPA(tpa), HttpStatus.CREATED);
    }


    @GetMapping("tpa")
    public ResponseEntity<?> getAllTPAs()
    {
        return new ResponseEntity<List<TPA>>(tpaService.getTPAs(), HttpStatus.OK);
    }

    @PutMapping("tpa/{tpa_id}")
    public ResponseEntity<?> updateTPA(@RequestBody TPA tpa, @PathVariable String tpa_id)
    {
        return new ResponseEntity<TPA>(tpaService.updateTPA(tpa,tpa_id),HttpStatus.OK);

    }

    @DeleteMapping("tpa/{tpa_id}")
    public ResponseEntity<?> deleteTPA(@PathVariable String tpa_id)
    {
        return new ResponseEntity<TPA>(tpaService.deleteTPA(tpa_id),HttpStatus.OK);
    }
}
