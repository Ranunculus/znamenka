package ru.znamenka.controller.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.znamenka.api.domain.ClientApi;
import ru.znamenka.api.domain.TrainingApi;
import ru.znamenka.api.page.schedule.SubscriptionApi;
import ru.znamenka.jpa.model.User;
import ru.znamenka.service.ApiStore;
import ru.znamenka.service.page.schedule.SubscriptionPageService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class ScheduleController {

    private final ApiStore service;

    private final SubscriptionPageService pageService;

    @Autowired
    public ScheduleController(SubscriptionPageService pageService, @Qualifier("dataService") ApiStore service) {
        this.pageService = pageService;
        this.service = service;
    }

    @GetMapping("/schedule")
    public String getSchedulePage(Model model) {
        List<ClientApi> list = service.findAll(ClientApi.class);
        model.addAttribute("clients", list);
        model.addAttribute("training", new TrainingApi());
        return "schedule";
    }
//// TODO: 06.09.2016 getpage???? 
    @GetMapping("/clients")
    public String getClientPage(Model model) {
        List<ClientApi> list = service.findAll(ClientApi.class);
        model.addAttribute("clients", list);
        return "clients";
    }

    @GetMapping("/schedule/subscriptions")
    public ResponseEntity<List<SubscriptionApi>> getSubscriptions(@RequestParam("clientId") Long clientId) {
        if (clientId == null) {
            return badRequest().body(emptyList());
        }
        List<SubscriptionApi> subscriptions = pageService.getSubscriptionByClientId(clientId);
        return ok(subscriptions);
    }

    @RequestMapping(path = "/schedule/", method = POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE + "; charset:utf-8")
    public ModelAndView bookTraining(@Valid TrainingApi training, BindingResult bindingResult) throws IOException {
        if (!bindingResult.hasErrors()) {
            training.setStatusId(1L);
            training.setTrainerId(training.getTrainerId() != null ? training.getTrainerId() : getTrainerIdIfExists());
            service.save(TrainingApi.class, training);
            pageService.postToCalendar(training);
        }
        ModelAndView mv = new ModelAndView("schedule");
        mv.addObject("training", training);
        mv.addObject("clients", service.findAll(ClientApi.class));
        return mv;
    }

    private Long getTrainerIdIfExists() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getTrainerId();
    }

    // TODO: 10.08.2016  сделать мапинг для метода поиска покупок по клиенту task 1.1

    // TODO: 10.08.2016  сделать мапинг для метода поиска платежей по покупке task 2.1

}
