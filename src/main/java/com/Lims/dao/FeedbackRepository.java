package com.Lims.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Lims.entities.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

}
