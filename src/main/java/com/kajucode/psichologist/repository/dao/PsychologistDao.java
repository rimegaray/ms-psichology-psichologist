package com.kajucode.psichologist.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kajucode.psichologist.repository.entity.PsychologistEntity;

public interface PsychologistDao extends JpaRepository<PsychologistEntity, Integer>{

}
