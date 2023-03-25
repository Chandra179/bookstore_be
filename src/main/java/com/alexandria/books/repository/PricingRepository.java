package com.alexandria.books.repository;

import com.alexandria.books.entity.Pricing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PricingRepository extends JpaRepository<Pricing, UUID>, CrudRepository<Pricing, UUID> {

}
