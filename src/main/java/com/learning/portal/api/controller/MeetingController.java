package com.learning.portal.api.controller;

import com.learning.portal.api.data.ResponseModel;
import com.learning.portal.api.facade.MeetingFacade;
import com.learning.portal.web.controller.ControllerGenerator;
import com.learning.portal.web.meetings.entity.Meetings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/meetings")
public class MeetingController extends ControllerGenerator<Meetings, MeetingFacade> {
    private MeetingFacade meetingFacade;

    public MeetingController(MeetingFacade meetingFacade) {
        super(meetingFacade);
        this.meetingFacade = meetingFacade;
    }

    @CrossOrigin(origins ={ "http://localhost:3000"})
    @GetMapping("/fetch-per-grade/{id}")
    public ResponseEntity<ResponseModel> fetchPerGrade(@PathVariable("id") Long id){
        ResponseModel<Meetings> response = meetingFacade.fetchPerGrade(id);
        boolean isSuccess = response.getStatus().equals("00");

        return ResponseEntity.status(
                (isSuccess ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value()))
                .body(response);
    }

    @CrossOrigin(origins ={ "http://localhost:3000"})
    @GetMapping("/fetch-per-creator/{id}")
    public ResponseEntity<ResponseModel> fetchPerCreator(@PathVariable("id") Long id){
        ResponseModel<Meetings> response = meetingFacade.fetchPerCreator(id);
        boolean isSuccess = response.getStatus().equals("00");

        return ResponseEntity.status(
                (isSuccess ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value()))
                .body(response);
    }
}
