package br.com.univirtus.bo;

import java.time.LocalDate;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.stock.manager.bo.ClientBO;
import br.com.stock.manager.model.Client;
import br.com.stock.manager.model.enums.Gender;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestMethodOrder(OrderAnnotation.class)
public class ClientBOTest {

	@Autowired
	private ClientBO bo;
	
	@Test
	@Order(1)
	public void insert() {
		Client client = new Client();
		client.setName("John Doe");
		client.setEmail("doe@email.com");
		client.setCpf("01234567890");
		client.setBirthDate(LocalDate.of(1990, 1, 1));
		client.setGender(Gender.MALE);
		client.setPhone("22220000");
		client.setMobile("987654321");
		client.setActive(true);
		bo.insert(client);
	}
	
	@Test
	@Order(2)
	public void searchById() {
		Client client = bo.searchById(1L);
		System.out.println(client);
	}
	
	@Test
	@Order(3)
	public void update() {
		Client client = bo.searchById(1L);
		client.setCpf("997654322");
		System.out.println(client);
	}
	
}
