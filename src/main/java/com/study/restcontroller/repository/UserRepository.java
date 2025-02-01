package com.study.restcontroller.repository;

import com.study.restcontroller.domain.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserRepository {
    private final UserJdbcRepository userJdbcRepository;

    public UserRepository(UserJdbcRepository userJdbcRepository) {this.userJdbcRepository = userJdbcRepository;}

    @Transactional
    public User create(String name, String gender) {
        return userJdbcRepository.save(new User(name, gender));
    }

    @Transactional
    public User getById(int id) { // updateName과 delete의 공통 메소드 분리
        return userJdbcRepository.findById(id).orElseThrow(() -> new RuntimeException("사용자가 없습니다."));
    }

    @Transactional
    public List<User> findByGender(String gender) {
        return userJdbcRepository.findAllByGenderAndDeletedYn(gender,false);
    }

    @Transactional
    public User updateName(int id, String name) { // user 객체를 찾아서 id에 맞게 바뀐 이름을 save
        User byId = getById(id);
        byId.updateName(name);
        userJdbcRepository.save(byId);
        return byId;
    }

    @Transactional
    public User delete(int id) { // id를 찾아서 delete
        User byId = getById(id);
        byId.delete();
        userJdbcRepository.delete(byId);
        return byId;
    }
}
