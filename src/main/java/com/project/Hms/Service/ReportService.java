package com.project.Hms.Service;

import com.project.Hms.DTO.Response.GenericResponse;
import com.project.Hms.Entity.Hall;
import com.project.Hms.Entity.Report;
import com.project.Hms.Entity.Room;
import com.project.Hms.Entity.User;
import com.project.Hms.Exceptions.BadRequestException;
import com.project.Hms.Repository.ReportRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service

public class ReportService {
    @Autowired
    ReportRepository reportRepository;

    @Autowired
    HallService hallService;

    @Autowired
    UserService userService;

    @Autowired
    RoomService roomService;


    // create report
    public ResponseEntity<?> createReport(Long userId,Long roomId, Report report){


        Room room = roomService.findById(roomId);
        if(room== null) throw new BadRequestException("room doesn't exist");
        Long hallId = room.getHallId();

        LocalDateTime localDateTime = LocalDateTime.now();
        String dateTime = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        report.setUserId(userId);
        report.setRoomId(roomId);
        report.setHallId(hallId);
        report.setHasBeenResolved(Boolean.FALSE);
        report.setCreatedDate(dateTime);
        report.setModifiedDate(dateTime);
        Report rs = reportRepository.save(report);
        return ResponseEntity.ok( new GenericResponse("00",
                        HttpStatus.OK,
                        "new report created",
                     report,
                HttpStatus.CREATED));
    }


    // view report by Id
    public ResponseEntity<?> findReportById(Long reportId){
        Optional<Report> report = reportRepository.findById(reportId);
        return ResponseEntity.ok( new GenericResponse("00",
                HttpStatus.OK,
                "View report by id",
                report,
                HttpStatus.OK));


    }

    // view all reports
    public ResponseEntity<?> viewAllReports(){
        List<Report> reportList = reportRepository.findAll();
        return ResponseEntity.ok( new GenericResponse("00",
                HttpStatus.OK,
                "View all reports",
                reportList,
                HttpStatus.OK));

    }

    // view all reports by User
    public ResponseEntity<?> viewAllReportsByUser(Long userId){
        User user = userService.findUserById(userId);
        String name = user.getUserName();
        List<Report> reportList = reportRepository.findReportsByUserId(userId);
        return ResponseEntity.ok( new GenericResponse("00",
                HttpStatus.OK,
                "View all reports by " + name,
                reportList,
                HttpStatus.OK));

    }

    // view all Reports by Room
    public ResponseEntity<?> viewAllReportsByRoom(Long roomId){
        List<Report> reportList = reportRepository.findReportsByRoomId(roomId);
        return ResponseEntity.ok( new GenericResponse("00",
                HttpStatus.OK,
                "View all reports by room " + roomId,
                reportList,
                HttpStatus.OK));

    }

    // view all Reports by Hall
    public ResponseEntity<?> viewAllReportsByHall(String hallName){
        Hall hall = hallService.findHallByName(hallName);
        if(hall== null) throw new BadRequestException("Hall doesn't exist");
        Long hallId = hall.getHallId();
        List<Report> reportList = reportRepository.findReportsByHallId(hallId);
        return ResponseEntity.ok( new GenericResponse("00",
                HttpStatus.OK,
                "View all reports by hall " + hallId,
                reportList,
                HttpStatus.OK));

    }

    // view all Reports that have been resolved
    public ResponseEntity<?> viewAllReportsResolved(){
        List<Report> reportList = reportRepository.findReportsByResolved();
        return ResponseEntity.ok( new GenericResponse("00",
                HttpStatus.OK,
                "View all reports resolved ",
                reportList,
                HttpStatus.OK));

    }

    // view all Reports that have not  been resolved
    public ResponseEntity<?> viewAllReportsNotResolved(){
        List<Report> reportList = reportRepository.findReportsByNotResolved();
        return ResponseEntity.ok( new GenericResponse("00",
                HttpStatus.OK,
                "View all reports not resolved ",
                reportList,
                HttpStatus.OK));

    }

    // update report status
    @Transactional
    public  ResponseEntity<?> resolveReport(Long reportId, Long userId){

        // check if report exists
        Report report1 = reportRepository.findReportsByReportId(reportId);
        if(report1 == null) throw new BadRequestException("report doesn't exist");


        // Ensure User updating the report is the creator
        //Ensure the report exists
        try{
            Report rs = reportRepository.findReportsByReportId(reportId);
            Long id = rs.getUserId();
            if(id!=userId) throw  new BadRequestException("Only the student that created the report can update it");

        }
        catch (BadRequestException ex){
            throw new BadRequestException("Report doesn't exist");
        }

        // get date time  when the report is resolved
        LocalDateTime localDateTime = LocalDateTime.now();
        String dateTime = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        reportRepository.resolveReport(dateTime,reportId);

        Optional<Report> report = reportRepository.findById(reportId);
        return ResponseEntity.ok( new GenericResponse("00",
                HttpStatus.OK,
                "Report resolved ",
                report,
                HttpStatus.OK));


    }




}
