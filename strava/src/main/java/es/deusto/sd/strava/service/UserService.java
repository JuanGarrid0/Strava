package es.deusto.sd.strava.service;

import es.deusto.sd.strava.dao.*;
import es.deusto.sd.strava.dto.*;
import es.deusto.sd.strava.entity.*;
import es.deusto.sd.strava.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserDao userDao;
    private final TokenDao tokenDao;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserDao userDao, TokenDao tokenDao, UserMapper userMapper) {
        this.userDao = userDao;
        this.tokenDao = tokenDao;
        this.userMapper = userMapper;
    }

    @Transactional
    public UserProfileDTO register(UserRegisterDTO dto) {
        // TODO: validate with external provider based on dto.getProvider()
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
        Token token = new Token();
        token.setValue(String.valueOf(System.currentTimeMillis()));
        token.setCreatedAt(LocalDateTime.now());
        token.setUser(user);
        Token saved = tokenDao.save(token);
        return userMapper.toDto(saved);
    }

    @Transactional
    public void logout(String tokenValue) {
        tokenDao.deleteByValue(tokenValue);
    }
}