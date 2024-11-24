//package web.api;
//
//import web.api.model.BinResponseDTO;
//import web.api.model.CardNumberDTO;
//import web.api.model.CountryDTO;
//import web.api.utils.CountryDataEnum;
//import web.api.utils.Validator;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectWriter;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.math.BigInteger;
//
//import static web.api.utils.StaticContent.*;
//import static web.api.utils.V1.URI_BASE;
//import static web.api.utils.V1.PAYMENT_CARDS_COST_END_POINT;
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class WebApiApplicationTests {
//
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    public void validPanScenario() throws Exception {
//        CardNumberDTO dto = new CardNumberDTO();
//        dto.setCardNumber(new BigInteger(String.valueOf(45715367)));
//        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//        String json = ow.writeValueAsString(dto);
//
//        this.mockMvc.perform(post(URI_BASE+ PAYMENT_CARDS_COST_END_POINT)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(json))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void validPanLengthScenario2() throws Exception {
//        CardNumberDTO dto = new CardNumberDTO();
//        dto.setCardNumber(new BigInteger("76895474167242341"));
//        assertThat(Validator.hasPanValidLength(dto.getCardNumber())).isTrue();
//    }
//
//    @Test
//    public void invalidPanLengthScenario() throws Exception {
//        CardNumberDTO dto = new CardNumberDTO();
//        dto.setCardNumber(new BigInteger("45717"));
//        assertThat(Validator.hasPanValidLength(dto.getCardNumber())).isFalse();
//    }
//
//    @Test
//    public void invalidPanLengthScenario2() throws Exception {
//        CardNumberDTO dto = new CardNumberDTO();
//        dto.setCardNumber(new BigInteger("768954741672423414582"));
//        assertThat(Validator.hasPanValidLength(dto.getCardNumber())).isFalse();
//    }
//
//    @Test
//    public void iinServiceGreeceResponseValidation() throws Exception {
//        CountryDTO countryDTO = new CountryDTO();
//        countryDTO.setNumeric(GREECE_ISO2_CODE);
//        BinResponseDTO binResponseDTO = new BinResponseDTO();
//        binResponseDTO.setCountry(countryDTO);
//        assertThat(getCardInfoResponseDTO(binResponseDTO).getCost().compareTo(CountryDataEnum.GREECE.getCost()) == 0).isTrue();
//    }
//
//    @Test
//    public void iinServiceUSAResponseValidation() throws Exception {
//        CountryDTO countryDTO = new CountryDTO();
//        countryDTO.setNumeric(USA_ISO2_CODE);
//        BinResponseDTO binResponseDTO = new BinResponseDTO();
//        binResponseDTO.setCountry(countryDTO);
//        assertThat(getCardInfoResponseDTO(binResponseDTO).getCost().compareTo(CountryDataEnum.USA.getCost()) == 0).isTrue();
//    }
//
//    @Test
//    public void iinServiceOtherResponseValidation() throws Exception {
//        CountryDTO countryDTO = new CountryDTO();
//        countryDTO.setNumeric("10");
//        BinResponseDTO binResponseDTO = new BinResponseDTO();
//        binResponseDTO.setCountry(countryDTO);
//        assertThat(getCardInfoResponseDTO(binResponseDTO).getCost().compareTo(CountryDataEnum.OTHER.getCost()) == 0).isTrue();
//    }
//
//}
