package com.example.case_md4.service.user;

import com.example.case_md4.model.User;
import com.example.case_md4.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends IGeneralService<User>, UserDetailsService {
}
