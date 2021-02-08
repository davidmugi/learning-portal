package com.learning.portal.api.controller;

import com.learning.portal.api.FacadeInterface;
import com.learning.portal.api.facade.ContentFacade;
import com.learning.portal.web.content.entity.Content;
import com.learning.portal.web.controller.ControllerGenerator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/content")
public class ContentController extends ControllerGenerator<Content, ContentFacade> {

    public ContentController(ContentFacade contentFacade) {
        super(contentFacade);
    }
}
