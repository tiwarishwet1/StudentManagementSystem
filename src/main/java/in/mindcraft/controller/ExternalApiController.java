package in.mindcraft.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.mindcraft.dto.AcademicResponse;
import in.mindcraft.service.ExternalApiService;

@RestController
@RequestMapping("/students")
public class ExternalApiController {

    private final ExternalApiService externalApiService;

    public ExternalApiController(
            ExternalApiService externalApiService) {

        this.externalApiService = externalApiService;
    }

    @GetMapping("/{studentId}/academic")
    public ResponseEntity<AcademicResponse>
            getAcademicDetails(
                    @PathVariable Integer studentId) {

        AcademicResponse response =
                externalApiService.getAcademicDetails(
                        studentId
                );

        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/{studentId}/academic/headers")
    public ResponseEntity<AcademicResponse>
            getAcademicDetailsWithHeaders(
                    @PathVariable Integer studentId) {

        AcademicResponse response =
                externalApiService
                        .getAcademicDetailsWithHeaders(studentId);

        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/{studentId}/academic/jwt")
    public ResponseEntity<AcademicResponse>
            getAcademicDetailsWithJwt(
                    @PathVariable Integer studentId,
                    @RequestHeader("Authorization")
                    String authorizationHeader) {

        AcademicResponse response =
                externalApiService
                        .getAcademicDetailsWithJwt(
                                studentId,
                                authorizationHeader
                        );

        return ResponseEntity.ok(response);
    }
}