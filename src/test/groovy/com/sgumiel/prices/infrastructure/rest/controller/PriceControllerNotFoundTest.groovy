package com.sgumiel.prices.infrastructure.rest.controller


import com.fasterxml.jackson.databind.ObjectMapper
import com.sgumiel.prices.PricesApplication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

@AutoConfigureMockMvc
@ActiveProfiles("test")
@AutoConfigureTestDatabase
@SpringBootTest(classes = PricesApplication.class)
class PriceControllerNotFoundTest extends Specification {

    private static final FIND_PRICE = "/price"

    @Autowired
    MockMvc mvc

    @Autowired
    ObjectMapper objectMapper

    def "Test when price not found"() {

        given:
        def brandId = 10
        def productId = 35
        def date = "2020-06-14 10:00:00"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        def dateTime = LocalDateTime.parse(date, formatter);


        when:
        def response = mvc.perform(get(FIND_PRICE)
                .param("brandId", brandId + "")
                .param("productId", productId+ "")
                .param("date", date)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn().response

        then: "the status code must be 404"
        response.status == HttpStatus.NOT_FOUND.value()

    }
}
