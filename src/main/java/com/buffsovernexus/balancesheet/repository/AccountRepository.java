package com.buffsovernexus.balancesheet.repository;


import com.buffsovernexus.balancesheet.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, UUID> {
    Optional<AccountEntity> findAccountByUsername(String username);

    Optional<AccountEntity> findAccountByUsernameAndPasswordOrEmail(String username, String password, String email);

}
