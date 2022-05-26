package io.GuardianScore.client.api;

import java.util.concurrent.TimeUnit;

import io.GuardianScore.client.ApiClient;
import io.GuardianScore.client.model.RequestBody;
import io.GuardianScore.client.model.ResponseBody;
import io.GuardianScore.client.model.CreditReport;
import io.GuardianScore.interceptor.SignerInterceptor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import okhttp3.OkHttpClient;

public class GuardIAnScoreApiTest {
    private final GuardIAnScoreApi api = new GuardIAnScoreApi();

    private Logger logger = LoggerFactory.getLogger(GuardIAnScoreApiTest.class.getName());

    private ApiClient apiClient;
    private String xApiKey = "your_api_key";
    private String url = "the_url";
    private String username = "your_username";
    private String password = "your_password";

    @Before()
    public void setUp() {
    	 
		this.apiClient = api.getApiClient();
        this.apiClient.setBasePath(url);
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
               .readTimeout(30, TimeUnit.SECONDS)
               .addInterceptor(new SignerInterceptor())
               .build();
        apiClient.setHttpClient(okHttpClient);
    }
    
    @Test
    public void creditreportTest() throws Exception {
        CreditReport report = new CreditReport();
        RequestBody body = new RequestBody();
        
        report.setIdFolioConsultaReporte("folio_consulta");
        report.setFolioOtorgante("folio_otorgante");
        
        body.setCreditReport(report);

        ResponseBody response = api.creditreport(xApiKey, username, password, body);
        System.out.println(response.toString());
        logger.info("Report: "+response.toString());
        
        Assert.assertTrue(response.getFolioConsulta() != null);
    }
}
