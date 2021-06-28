package com.sgumiel.prices.infrastructure.rest.controller

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.sgumiel.prices.PricesApplication
import com.sgumiel.prices.infrastructure.rest.model.PriceResponse
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
class PriceControllerTest extends Specification {

    private static final FIND_PRICE = "/price"

    @Autowired
    MockMvc mvc

    @Autowired
    ObjectMapper objectMapper

    def "Test 01"() {

        given:
        def brandId = 1
        def productId = 35455
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

        then: "the status code must be 200"
        response.status == HttpStatus.OK.value()

        and:
        def price = this.objectMapper.readValue(response.contentAsString, new TypeReference<PriceResponse>() {})
        price.price == 35.5
        price.productId == productId
        price.brandId == brandId
        price.priceList == 1
        price.getStartDate().isBefore(dateTime)
        price.getEndDate().isAfter(dateTime)
    }

    def "Test 02"() {

        given:
        def brandId = 1
        def productId = 35455
        def date = "2020-06-14 16:00:00"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        def dateTime = LocalDateTime.parse(date, formatter);


        when:
        def response = mvc.perform(get(FIND_PRICE)
                .param("brandId", brandId + "")
                .param("productId", productId+ "")
                .param("date", date)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn().response

        then: "the status code must be 200"
        response.status == HttpStatus.OK.value()

        and:
        def price = this.objectMapper.readValue(response.contentAsString, new TypeReference<PriceResponse>() {})
        price.price == 25.45
        price.productId == productId
        price.brandId == brandId
        price.priceList == 2
        price.getStartDate().isBefore(dateTime)
        price.getEndDate().isAfter(dateTime)
    }

    def "Test 03"() {

        given:
        def brandId = 1
        def productId = 35455
        def date = "2020-06-14 21:00:00"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        def dateTime = LocalDateTime.parse(date, formatter);


        when:
        def response = mvc.perform(get(FIND_PRICE)
                .param("brandId", brandId + "")
                .param("productId", productId+ "")
                .param("date", date)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn().response

        then: "the status code must be 200"
        response.status == HttpStatus.OK.value()

        and:
        def price = this.objectMapper.readValue(response.contentAsString, new TypeReference<PriceResponse>() {})
        price.price == 35.5
        price.productId == productId
        price.brandId == brandId
        price.priceList == 1
        price.getStartDate().isBefore(dateTime)
        price.getEndDate().isAfter(dateTime)
    }

    def "Test 04"() {

        given:
        def brandId = 1
        def productId = 35455
        def date = "2020-06-15 10:00:00"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        def dateTime = LocalDateTime.parse(date, formatter);


        when:
        def response = mvc.perform(get(FIND_PRICE)
                .param("brandId", brandId + "")
                .param("productId", productId+ "")
                .param("date", date)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn().response

        then: "the status code must be 200"
        response.status == HttpStatus.OK.value()

        and:
        def price = this.objectMapper.readValue(response.contentAsString, new TypeReference<PriceResponse>() {})
        price.price == 30.5
        price.productId == productId
        price.brandId == brandId
        price.priceList == 3
        price.getStartDate().isBefore(dateTime)
        price.getEndDate().isAfter(dateTime)
    }

    def "Test 05"() {

        given:
        def brandId = 1
        def productId = 35455
        def date = "2020-06-16 21:00:00"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        def dateTime = LocalDateTime.parse(date, formatter);


        when:
        def response = mvc.perform(get(FIND_PRICE)
                .param("brandId", brandId + "")
                .param("productId", productId+ "")
                .param("date", date)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn().response

        then: "the status code must be 200"
        response.status == HttpStatus.OK.value()

        and:
        def price = this.objectMapper.readValue(response.contentAsString, new TypeReference<PriceResponse>() {})
        price.price == 38.95
        price.productId == productId
        price.brandId == brandId
        price.priceList == 4
        price.getStartDate().isBefore(dateTime)
        price.getEndDate().isAfter(dateTime)
    }
}
