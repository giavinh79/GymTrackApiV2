package com.gymtrack.api;

import com.gymtrack.api.feature.routine.controller.RoutineController;
import com.gymtrack.api.feature.routine.service.RoutineServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

	@Autowired
	private RoutineController routineController;

	@Autowired
	private RoutineServiceImpl routineServiceImpl;

	@Test
	void contextLoads() {
		assertThat(routineController).isNotNull();
		assertThat(routineServiceImpl).isNotNull();
	}

}
