package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    private final List<String> functionality = Arrays.asList(
            "You can manage your tasks",
            "Provides connection with Trello account",
            "Application allows sending tasks to Trello"
    );
    private final Context context = new Context();

    public String buildTrelloCardEmail(String message) {
        prepareContext(message);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildDailyEmail(String message) {
        prepareContext(message);
        return templateEngine.process("/mail/daily-trello-mail", context);
    }

    public void prepareContext(String message) {
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/tasks_frontend/");
        context.setVariable("button", "Visit website");
        context.setVariable("show_button", true);
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("company_name", adminConfig.getCompanyName());
        context.setVariable("company_goal", adminConfig.getCompanyGoal());
        context.setVariable("app_name", adminConfig.getAppName());
        context.setVariable("is_friend", true);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);

    }


}
