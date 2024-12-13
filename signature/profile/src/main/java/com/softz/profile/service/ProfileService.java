package com.softz.profile.service;

import com.softz.profile.entity.Profile;
import com.softz.profile.repository.ProfileRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProfileService {
    ProfileRepository profileRepository;

    public Profile create(Profile profile){
        return profileRepository.save(profile);
    }


}
