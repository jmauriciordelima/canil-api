package com.jmauriciordelima.canil_api.repository;

import com.jmauriciordelima.canil_api.model.Cachorro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CachorroRepository extends JpaRepository<Cachorro, UUID> {

}
