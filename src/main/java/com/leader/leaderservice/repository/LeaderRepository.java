package com.leader.leaderservice.repository;

import com.leader.leaderservice.entity.Leader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface LeaderRepository extends JpaRepository<Leader, Long> {

    Optional<List<Leader>> findByPartyId(Long partyId);

    Optional<Leader> findByIdAndPartyId(Long partyId, Long leaderId);

    @Transactional
    void deleteAllByIdAndPartyId(Long id, Long leaderId);

}