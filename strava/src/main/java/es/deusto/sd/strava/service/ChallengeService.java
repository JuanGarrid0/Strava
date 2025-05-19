package es.deusto.sd.strava.service;

import es.deusto.sd.strava.dao.ChallengeDao;
import es.deusto.sd.strava.dto.ChallengeCreateDTO;
import es.deusto.sd.strava.dto.ChallengeDTO;
import es.deusto.sd.strava.dto.UserChallengeDTO;
import es.deusto.sd.strava.entity.Challenge;
import es.deusto.sd.strava.entity.ChallengeAcceptance;
import es.deusto.sd.strava.entity.User;
import es.deusto.sd.strava.mapper.ChallengeMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChallengeService {

    private final ChallengeDao dao;
    private final ChallengeMapper mapper;

    public ChallengeService(ChallengeDao dao, ChallengeMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @Transactional
    public ChallengeDTO create(ChallengeCreateDTO dto) {
        Challenge challenge = mapper.toEntity(dto);
        Challenge saved = dao.save(challenge);
        return mapper.toDto(saved);
    }

    public List<ChallengeDTO> getActive(int limit) {
        return dao.findActive(LocalDate.now(), PageRequest.of(0, limit))
                  .stream()
                  .map(mapper::toDto)
                  .collect(Collectors.toList());
    }

   @Transactional
    public void join(User user, Long challengeId) {
        Challenge ch = dao.findById(challengeId);
        ChallengeAcceptance acc = new ChallengeAcceptance();
        acc.setUser(user);
        acc.setChallenge(ch);
        // ← Aquí inicializamos el campo no-null
        acc.setAcceptedAt(LocalDateTime.now());
        dao.saveAcceptance(acc);
    }
    public List<UserChallengeDTO> getUserChallenges(User user) {
        List<ChallengeAcceptance> accs = dao.findByUser(user);
        return accs.stream()
                   .map(mapper::toUserChallengeDto)
                   .collect(Collectors.toList());
    }
}