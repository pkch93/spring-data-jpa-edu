package edu.pkch.datajpa.postgresql;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LegacyMemberRepository extends JpaRepository<LegacyMember, Long> {
}
