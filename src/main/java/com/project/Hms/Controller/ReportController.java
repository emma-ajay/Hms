package com.project.Hms.Controller;

import com.project.Hms.DTO.Requests.CreateReport;
import com.project.Hms.Entity.Report;
import com.project.Hms.Security.CurrentUser;
import com.project.Hms.Security.UserPrincipal;
import com.project.Hms.Service.ReportService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    @Autowired
    ReportService reportService;

    @Autowired
    ModelMapper modelMapper;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping()
    public ResponseEntity<?> viewAllReports(){
        return reportService.viewAllReports();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> findReportById(@PathVariable Long id){
        return reportService.findReportById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "/user/{id}")
    public ResponseEntity<?> findReportByUserId(@PathVariable Long id){
        return reportService.viewAllReportsByUser(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "/room/{id}")
    public ResponseEntity<?> findReportsByRoomId(@PathVariable Long id){
        return reportService.viewAllReportsByRoom(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "/hall/{hallName}")
    public ResponseEntity<?> viewAllReportsByHall(String hallName){
        return reportService.viewAllReportsByHall(hallName);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "/resolved")
    public ResponseEntity<?> viewAllReportsResolved(){
        return reportService.viewAllReportsResolved();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "/unresolved")
    public ResponseEntity<?> viewAllReportsNotResolved(){
        return reportService.viewAllReportsNotResolved();
    }

    @PreAuthorize("hasRole('ROLE_STUDENT')")
    @PostMapping(path = "/new")
    public ResponseEntity<?> createReport(@CurrentUser UserPrincipal userPrincipal,@RequestBody CreateReport createReport){
        Long userId = userPrincipal.getId();
        Long roomId = userPrincipal.getRoomId();
        Report report = modelMapper.map(createReport,Report.class);
        return reportService.createReport(userId,roomId,report);

    }
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    @PutMapping(path = "/resolve/{id}")
    public  ResponseEntity<?> resolveReport(@PathVariable  Long id, @CurrentUser UserPrincipal currentUser){
        Long userId = currentUser.getId();
        return reportService.resolveReport(id,userId);
    }







}
