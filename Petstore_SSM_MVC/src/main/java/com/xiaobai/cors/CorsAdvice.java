package com.xiaobai.cors;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;

@Component
@ControllerAdvice
@CrossOrigin
public class CorsAdvice {
}
