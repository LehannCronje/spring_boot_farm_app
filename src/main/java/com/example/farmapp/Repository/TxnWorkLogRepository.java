package com.example.farmapp.Repository;

import com.example.farmapp.Entity.TxnWorkLog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TxnWorkLogRepository extends JpaRepository<TxnWorkLog, Long> {

}