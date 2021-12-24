package com.minlebay.api;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;
import com.minlebay.api.config.CarNumberDBRiderTestExecutionListener;
import com.minlebay.dao.CarNumberDao;
import lombok.NoArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = com.minlebay.api.NumberGeneratorApplication.class)
@AutoConfigureMockMvc
@DBUnit(schema = "public",
        url = "jdbc:postgresql://localhost:5434/number_generator_db_test",
        caseInsensitiveStrategy = Orthography.LOWERCASE)
@TestExecutionListeners(value = CarNumberDBRiderTestExecutionListener.class,
        mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
@NoArgsConstructor
public class CarNumberServiceTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CarNumberDao carNumberDao;

    private final String URI = "/number";

    @Test
    @DataSet(cleanBefore = true, value = "/datasets/carnumbers.yml")
    public void CarNumberControllerGetNextTest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URI + "/next"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        assertThat("А000АА 116 RUS").isEqualTo(content);
    }

    @Test
    @DataSet(cleanBefore = true, value = "/datasets/carnumbers-second.yml")
    public void CarNumberControllerGetNextTest2() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URI + "/next"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        assertThat("А003АА 116 RUS").isEqualTo(content);
    }

    @Test
    @DataSet(cleanBefore = true, value = "/datasets/carnumbers.yml")
    public void CarNumberControllerGetRandomTest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URI + "/random"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        assertThat(carNumberDao.getLastCarNumber().getRepresentation() + " 116 RUS").isEqualTo(content);
    }
}
