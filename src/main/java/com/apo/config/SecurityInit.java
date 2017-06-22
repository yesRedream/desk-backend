package com.apo.config;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Created by Andrii Pohrebniak andrii.pohrebniak@gmail.com on 22/06/2017.
 */
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity
public class SecurityInit extends AbstractSecurityWebApplicationInitializer {
}
