package com.kanban.back;

import com.kanban.back.config.JasyptConfig;
import com.kanban.back.dto.requestDTO.BoardReqDTO;
import com.kanban.back.entity.Board;
import com.kanban.back.repository.BoardRepository;
import com.kanban.back.repository.UserTableRepository;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootTest
@EnableEncryptableProperties
@EnableJpaAuditing
@EnableScheduling
class BackApplicationTests {
	@Test
	void dummyData() {

	}
}
