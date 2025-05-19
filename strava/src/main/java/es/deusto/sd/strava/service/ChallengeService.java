package es.deusto.sd.strava.service;

import es.deusto.sd.strava.dao.*;
import es.deusto.sd.strava.dto.*;
import es.deusto.sd.strava.entity.*;
import es.deusto.sd.strava.mapper.ChallengeMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChallengeService {
    private final ChallengeDao challengeDao;
    private final ChallengeAcceptanceDao acceptanceDao;
    private final ChallengeMapper challengeMapper;

    public ChallengeService(ChallengeDao challengeDao,
                             ChallengeAcceptanceDao acceptanceDao,
                             ChallengeMapper challengeMapper) {
        this.challengeDao = challengeDao;
        this.acceptanceDao = acceptanceDao;
        this.challengeMapper = challengeMapper;
    }

    public ChallengeDTO create(ChallengeCreateDTO dto) {
        Challenge challenge = challengeMapper.toEntity(dto);
        Challenge saved = challengeDao.save(challenge);
        return challengeMapper.toDto(saved);
    }

    public List<ChallengeDTO> getActive(int limit) {
        return challengeDao.findActive(LocalDate.now(), PageRequest.of(0, limit))
                .stream()
                .map(challengeMapper::toDto)
                .collect(Collectors.toList());
    }

    public void join(User user, Long challengeId) {
        Challenge challenge = challengeDao.findById(challengeId)
                .orElseThrow(() -> new IllegalArgumentException("Challenge not found"));
        if (!acceptanceDao.existsByUserAndChallenge(user, challenge)) {
            ChallengeAcceptance ca = new ChallengeAcceptance();
            ca.setUser(user);
            ca.setChallenge(challenge);
            ca.setAcceptedAt(LocalDateTime.now());
            acceptanceDao.save(ca);
        }
    }

    public List<UserChallengeDTO> getUserChallenges(User user) {
        return acceptanceDao.findByUser(user)
                .stream()
                .map(challengeMapper::toUserChallengeDto)
                .collect(Collectors.toList());
    }
}
