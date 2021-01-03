package com.food4good.facad;

import com.food4good.database.entities.SupplierRate;
import com.food4good.database.entities.User;
import com.food4good.database.entities.UsersPreference;
import com.food4good.database.repositories.UsersRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.Set;

@Service
public class UsersService {
    UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public User getById(Long userId)  {
    	return usersRepository.findById(userId).orElseThrow(()-> new EntityNotFoundException("user not found"));
    }
    
    public User getByToken(String token) throws Exception{
    	User userEntity=usersRepository.findByToken(token).orElseThrow(()->new EntityNotFoundException("user not found"));
    	return userEntity;
    }

    public Set<UsersPreference> registerNewUser(String token) throws Exception{
        Optional<User> newUser=usersRepository.findByToken(token);
        if (!newUser.isPresent()) {
            User userEntity = new User();
            userEntity.setToken(token);
            usersRepository.save(userEntity);
        }
        return user.getPreferences();
    }
}
