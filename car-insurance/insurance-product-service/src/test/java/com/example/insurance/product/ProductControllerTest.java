package com.example.insurance.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void getAllProducts_returnsList() throws Exception {
        // Ensure at least one product exists by creating it
        String body = "{\n" +
                "  \"productName\": \"Accident Cover\",\n" +
                "  \"price\": 199.99,\n" +
                "  \"benefits\": \"Medical|roadside\"\n" +
                "}";
        mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/products").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].productId").exists())
                .andExpect(jsonPath("$[0].productName", not(emptyOrNullString())))
                .andExpect(jsonPath("$[0].price").exists())
                .andExpect(jsonPath("$[0].benefits", not(emptyOrNullString())));
    }

    @Test
    void create_get_update_delete_product_flow() throws Exception {
        // Create
        String createBody = "{\n" +
                "  \"productName\": \"Premium Cover\",\n" +
                "  \"price\": 299.99,\n" +
                "  \"benefits\": \"Extended support | Extended Warranty\"\n" +
                "}";
        MvcResult createResult = mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.productId").exists())
                .andExpect(jsonPath("$.productName").value("Premium Cover"))
                .andReturn();
        String responseJson = createResult.getResponse().getContentAsString();
        JsonNode node = objectMapper.readTree(responseJson);
        long id = node.get("productId").asLong();

        MvcResult getResult = mockMvc.perform(get("/products/" + id))
                .andReturn();
        if (getResult.getResponse().getStatus() != 200) {
            fail("GET /products/" + id + " responded with status " + getResult.getResponse().getStatus() +
                 " and body: " + getResult.getResponse().getContentAsString());
        }
        mockMvc.perform(get("/products/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(id))
                .andExpect(jsonPath("$.productName").value("Premium Cover"))
                .andExpect(jsonPath("$.benefits").value("Extended support | Extended Warranty"));

        // Update
        String updateBody = "{\n" +
                "  \"productName\": \"Premium Cover Plus\",\n" +
                "  \"price\": 399.99,\n" +
                "  \"benefits\": \"Extended support| priority\"\n" +
                "}";
        mockMvc.perform(put("/products/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productName").value("Premium Cover Plus"))
                .andExpect(jsonPath("$.price").value(399.99))
                .andExpect(jsonPath("$.benefits").value("Extended support| priority"));

        // Delete
        mockMvc.perform(delete("/products/" + id))
                .andExpect(status().isNoContent());

        // Get after delete -> 404
        mockMvc.perform(get("/products/" + id))
                .andExpect(status().isNotFound());
    }
}
