package in.mindcraft.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import in.mindcraft.dto.AcademicResponse;

import org.springframework.web.client.HttpClientErrorException;
@Service
public class ExternalApiService {

    private static final Logger logger =
            LoggerFactory.getLogger(ExternalApiService.class);

    private final RestTemplate restTemplate;

    private static final String ACADEMIC_SERVICE_URL =
            "http://localhost:8081/academic/students/";

    public ExternalApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public AcademicResponse getAcademicDetails(
            Integer studentId) {

        String url = ACADEMIC_SERVICE_URL + studentId;

        logger.info(
                "Calling Academic Service for student ID: {}",
                studentId
        );

        logger.info(
                "External API URL: {}",
                url
        );

        try {

            AcademicResponse response =
                    restTemplate.getForObject(
                            url,
                            AcademicResponse.class
                    );

            logger.info(
                    "Academic Service call successful for student ID: {}",
                    studentId
            );

            logger.info(
                    "Academic response received: {}",
                    response
            );

            return response;

        } catch (RestClientException e) {

            logger.error(
                    "Error while calling Academic Service for student ID {}: {}",
                    studentId,
                    e.getMessage()
            );

            throw e;
        }
    }
    
//    public AcademicResponse getAcademicDetailsWithHeaders(
//            Integer studentId) {
//
//        String url = ACADEMIC_SERVICE_URL + studentId;
//
//        logger.info(
//                "Calling Academic Service with headers for student ID: {}",
//                studentId
//        );
//
//        HttpHeaders headers = new HttpHeaders();
//
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
//
//        logger.info(
//                "Outgoing request headers: {}",
//                headers
//        );
//
//        HttpEntity<Void> entity =
//                new HttpEntity<>(headers);
//
//        try {
//
//            ResponseEntity<AcademicResponse> response =
//                    restTemplate.exchange(
//                            url,
//                            HttpMethod.GET,
//                            entity,
//                            AcademicResponse.class
//                    );
//
//            logger.info(
//                    "Academic Service call with headers successful"
//            );
//
//            logger.info(
//                    "Response status: {}",
//                    response.getStatusCode()
//            );
//
//            return response.getBody();
//
//        } catch (RestClientException e) {
//
//            logger.error(
//                    "Error while calling Academic Service with headers: {}",
//                    e.getMessage()
//            );
//
//            throw e;
//        }
//    }

    public AcademicResponse getAcademicDetailsWithHeaders(
            Integer studentId) {

        String url = ACADEMIC_SERVICE_URL + studentId;

        logger.info(
                "Calling Academic Service with headers for student ID: {}",
                studentId
        );

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        headers.set("X-Client-Id", "STUDENT-MANAGEMENT");
//        headers.set("X-Client-Id", "ABCDXYZ");

        logger.info(
                "Outgoing request headers: {}",
                headers
        );

        HttpEntity<Void> entity =
                new HttpEntity<>(headers);

        try {

            ResponseEntity<AcademicResponse> response =
                    restTemplate.exchange(
                            url,
                            HttpMethod.GET,
                            entity,
                            AcademicResponse.class
                    );

            logger.info(
                    "Academic Service call with headers successful"
            );

            logger.info(
                    "Response status: {}",
                    response.getStatusCode()
            );

            return response.getBody();

        } 
        
        catch (HttpClientErrorException.Forbidden e) {

            logger.error(
                    "Academic Service rejected the client. Status: {}",
                    e.getStatusCode()
            );

            throw e;
        }
    }
    
    public AcademicResponse getAcademicDetailsWithJwt(
            Integer studentId,
            String authorizationHeader) {

        String url = ACADEMIC_SERVICE_URL + studentId;

        logger.info(
                "Calling Academic Service with JWT for student ID: {}",
                studentId
        );

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        headers.set("X-Client-Id", "STUDENT-MANAGEMENT");

        headers.set(
                HttpHeaders.AUTHORIZATION,
                authorizationHeader
        );

        logger.info(
                "Outgoing headers: Content-Type={}, Accept={}, X-Client-Id={}",
                headers.getContentType(),
                headers.getAccept(),
                headers.getFirst("X-Client-Id")
        );

        logger.info(
                "Authorization header: Bearer [TOKEN HIDDEN]"
        );

        HttpEntity<Void> entity =
                new HttpEntity<>(headers);

        try {

            ResponseEntity<AcademicResponse> response =
                    restTemplate.exchange(
                            url,
                            HttpMethod.GET,
                            entity,
                            AcademicResponse.class
                    );

            logger.info(
                    "Authenticated Academic Service call successful"
            );

            logger.info(
                    "Response status: {}",
                    response.getStatusCode()
            );

            return response.getBody();

        } catch (HttpClientErrorException.Unauthorized e) {

            logger.error(
                    "JWT authentication failed at Academic Service"
            );

            throw e;

        } catch (HttpClientErrorException.Forbidden e) {

            logger.error(
                    "Academic Service rejected the client"
            );

            throw e;

        } catch (RestClientException e) {

            logger.error(
                    "Error while calling Academic Service: {}",
                    e.getMessage()
            );

            throw e;
        }
    }
}