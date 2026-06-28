package com.zetta.ratevolut;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(TestcontainersConfig.class)
public abstract class BaseIntegrationTest {

}