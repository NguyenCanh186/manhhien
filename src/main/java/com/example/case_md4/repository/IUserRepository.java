package com.example.case_md4.repository;

import com.example.case_md4.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;
@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
}
