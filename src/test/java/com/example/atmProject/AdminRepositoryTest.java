package com.example.atmProject;

import com.example.atmProject.model.Admin;
import com.example.atmProject.repository.AdminRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class AdminRepositoryTest {

    @Autowired
    public AdminRepository adminRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void testSaveAdmin(){

        // Yeni bir admin kaydı oluştur
        Admin admin = new Admin();

        admin.setUsername("john");
        admin.setPassword("1234");

        // Veritabanına kaydet
        Admin savedAdmin = adminRepository.save(admin);

        // ID'nin otomatik oluşturulduğunu doğrula
        assertThat(savedAdmin.getId()).isNotNull();
    }


}



