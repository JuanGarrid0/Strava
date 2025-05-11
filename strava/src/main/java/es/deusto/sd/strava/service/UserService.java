package es.deusto.sd.strava.service;

import es.deusto.sd.strava.dao.UserDao;
import es.deusto.sd.strava.dto.*;
import es.deusto.sd.strava.entity.User;
import es.deusto.sd.strava.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {
    private final UserDao userDao;
    private final TokenService tokenService;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserDao userDao, TokenService tokenService, UserMapper userMapper) {
        this.userDao = userDao;
        this.tokenService = tokenService;
        this.userMapper = userMapper;
    }

    @Transactional
    public UserProfileDTO register(UserRegisterDTO dto) {
        // TODO: validate with external provider
        if (userDao.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("Email already registered");
        }
        User user = userMapper.toEntity(dto);
        User saved = userDao.save(user);
        return userMapper.toDto(saved);
    }

    @Transactional
    public TokenDTO login(UserLoginDTO dto) {
        // TODO: validate credentials with external provider
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