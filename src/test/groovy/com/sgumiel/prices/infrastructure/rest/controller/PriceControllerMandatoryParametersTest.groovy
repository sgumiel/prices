package com.sgumiel.prices.infrastructure.rest.controller

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.sgumiel.prices.PricesApplication
import com.sgumiel.prices.domain.enums.PriceError
import com.sgumiel.prices.infrastructure.rest.model.ApiErrorResponse
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
class PriceControllerMandatoryParametersTest extends Specification {

    private static final FIND_PRICE = "/price"

    @Autowired
    MockMvc mvc

    @Autowired
    ObjectMapper objectMapper

    def "Test when no mandatory data is set"() {

        given: "No parameters set"

        when:
        def response = mvc.perform(get(FIND_PRICE)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn().response

        then: "the status code must be 400"
        response.status == HttpStatus.BAD_REQUEST.value()

        and:
        def apiErrorList = this.objectMapper.readValue(response.contentAsString, new TypeReference<List<ApiErrorResponse>>() {})
        apiErrorList.size() == 3

        and: "There is a price.001 error"
        def apiErrorPrice001Op = apiErrorList.stream().filter({error -> error.getCode().equals(PriceError.PRICE_MANDATORY_DATE.code)}).findFirst()
        apiErrorPrice001Op.isPresent()

        and: "There is a price.002 error"
        def apiErrorPrice002Op = apiErrorList.stream().filter({error -> error.getCode().equals(PriceError.PRICE_MANDATORY_BRAND_ID.code)}).findFirst()
        apiErrorPrice002Op.isPresent()

        and: "There is a price.003 error"
        def apiErrorPrice003Op = apiErrorList.stream().filter({error -> error.getCode().equals(PriceError.PRICE_MANDATORY_PRODUCT_ID.code)}).findFirst()
        apiErrorPrice003Op.isPresent()


    }
}
