package com.kardibus.temp.controller

import com.kardibus.temp.common.CommonSpringTest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(InfoController::class)
open class InfoControllerTest : CommonSpringTest() {
    @Autowired
    lateinit var mvc: MockMvc

//    @MockitoBean
//    lateinit var infoService: InfoService

    @Test
    open fun infoTest() {
        // Mockito.`when`(infoService.handlerMapping()).thenReturn(mapOf<String, List<String>>())

        mvc.perform(get("/info")).andExpect(status().isOk)
    }
}
