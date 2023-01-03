package com.miracle.login.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.miracle.login.beans.RefreshToken;
import com.miracle.login.beans.User;

@Repository
public interface RefreshTokenRepository extends MongoRepository<RefreshToken, Long> {
  Optional<RefreshToken> findByToken(String token);

  int deleteByUser(User user);
}
