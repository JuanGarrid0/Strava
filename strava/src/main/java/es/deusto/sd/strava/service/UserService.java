package es.deusto.sd.strava.service;

import es.deusto.sd.strava.dao.GoogleDao;
import es.deusto.sd.strava.dao.MetaDao;
import es.deusto.sd.strava.dao.UserDao;
import es.deusto.sd.strava.dto.*;
import es.deusto.sd.strava.entity.User;
import es.deusto.sd.strava.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {
    private final UserDao userDao;
    private final TokenService tokenService;
    private final UserMapper userMapper;
    private final GoogleDao googleDao;
    private final MetaDao metaDao;

    public UserService(UserDao userDao,
                       TokenService tokenService,
                       UserMapper userMapper,
                       GoogleDao googleDao,
                       MetaDao metaDao) {
        this.userDao = userDao;
        this.tokenService = tokenService;
        this.userMapper = userMapper;
        this.googleDao = googleDao;
        this.metaDao = metaDao;
    }

    @Transactional
    public UserProfileDTO register(UserRegisterDTO dto) {
        // Validate email with external provider
        if (dto.getProvider() == AuthProvider.GOOGLE) {
            if (!googleDao.validateEmail(dto.getEmail())) {
                throw new IllegalArgumentException("Email not registered with Google");
            }
        } else if (dto.getProvider() == AuthProvider.META) {
            boolean success = metaDao.registerUser(
                dto.getEmail(), dto.getName(), dto.getBirthDate().toString());
            if (!success) {
                throw new IllegalArgumentException("Email already registered with Meta");
            }
        }
        if (userDao.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email already registered locally");
        }
        User user = userMapper.toEntity(dto);
        User saved = userDao.save(user);
        return userMapper.toDto(saved);
    }

    @Transactional
    public TokenDTO login(UserLoginDTO dto) {
        // Validate login with external provider
        if (dto.getProvider() == AuthProvider.GOOGLE) {
            if (!googleDao.validateEmail(dto.getEmail())) {
                throw new IllegalArgumentException("Invalid Google credentials");
            }
        } else if (dto.getProvider() == AuthProvider.META) {
            if (!metaDao.validateEmail(dto.getEmail())) {
                throw new IllegalArgumentException("Invalid Meta credentials");
            }
        }
        Optional<User> opt = userDao.findByEmail(dto.getEmail());
        if (opt.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        User user = opt.get();
        String tokenValue = tokenService.generateToken(user);
        TokenDTO tokenDto = new TokenDTO();
        tokenDto.setValue(tokenValue);
        tokenDto.setCreatedAt(LocalDateTime.now());
        return tokenDto;
    }

    @Transactional
    public void logout(String token) {
        tokenService.revokeToken(token);
    }

    public User getUserFromToken(String token) {
        return tokenService.getUser(token)
            .orElseThrow(() -> new SecurityException("Invalid or expired token"));
    }
}
