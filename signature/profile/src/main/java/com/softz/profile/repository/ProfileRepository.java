package com.softz.profile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.softz.profile.entity.Profile;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, String> { }
