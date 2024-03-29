package com.derteuffel.controller;

import com.derteuffel.data.Course;
import com.derteuffel.data.Event;
import com.derteuffel.data.PagerModel;
import com.derteuffel.data.Period;
import com.derteuffel.repository.CourseRepository;
import com.derteuffel.repository.EventRepository;
import com.derteuffel.repository.LessonRepository;
import com.derteuffel.repository.PeriodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by derteuffel on 27/12/2018.
 */
@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private PeriodRepository periodRepository;
    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private EventRepository eventRepository;

    private static final int BUTTONS_TO_SHOW = 3;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 5;
    private static final int[] PAGE_SIZES = { 5,6,7,8};

    @GetMapping("/get/{courseId}")
    public String findOneCourse(Model model, @PathVariable Long courseId, HttpSession session) {
        session.setAttribute("courseId",courseId);
        Optional<Course> optional= courseRepository.findById(courseId);
        int likes=optional.get().getViews();
        likes++;
        System.out.println(likes);
        optional.get().setViews(likes++);
        List<Period> periods= new ArrayList<>();
        List<Period> periods1= periodRepository.findAllByStatusOrderByPeriodIdDesc(true);
        List<Period> periodList= periodRepository.findAllByCourses(optional.get().getCourseId());
        for (Period period : periodList){
            for (int i=0; i< periods1.size();i++){
                if (period.getPeriodId().equals(periods1.get(i).getPeriodId())){
                    periods.add(period);
                }
            }
        }
        model.addAttribute("course", optional.get());
        model.addAttribute("periods", periods);
        return "course/course";
    }

    @GetMapping("/language")
    public String language(@RequestParam("pageSize") Optional<Integer> pageSize,
                           @RequestParam("page") Optional<Integer> page, Model model){
        //
        // Evaluate page size. If requested parameter is null, return initial
        // page size
        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        // print repo
        // evaluate page size
        model.addAttribute("selectedPageSize", evalPageSize);
        // add pages size
        model.addAttribute("pageSizes", PAGE_SIZES);
        //language
        Page<Course> ang_fran= courseRepository.findAllByDomainOrderByCourseIdDesc("anglais et/ou francais", new PageRequest(evalPage,evalPageSize));
        PagerModel pager1 = new PagerModel(ang_fran.getTotalPages(),ang_fran.getNumber(),BUTTONS_TO_SHOW);
        model.addAttribute("pager", pager1);
        model.addAttribute("courses", ang_fran);
        return "course/one/languages";
    }

    @GetMapping("/admin")
    public String admin(@RequestParam("pageSize") Optional<Integer> pageSize,
                        @RequestParam("page") Optional<Integer> page, Model model){
        //
        // Evaluate page size. If requested parameter is null, return initial
        // page size
        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        // print repo
        // evaluate page size
        model.addAttribute("selectedPageSize", evalPageSize);
        // add pages size
        model.addAttribute("pageSizes", PAGE_SIZES);
        //language
        //templates.course admin finance
        Page<Course> admin_fin= courseRepository.findAllByDomainOrderByCourseIdDesc("administration et finance", new PageRequest(evalPage, evalPageSize));
        PagerModel pager = new PagerModel(admin_fin.getTotalPages(),admin_fin.getNumber(),BUTTONS_TO_SHOW);
        model.addAttribute("pager", pager);
        model.addAttribute("courses", admin_fin);
        return "course/one/admin";
    }

    @GetMapping("/it")
    public String it(@RequestParam("pageSize") Optional<Integer> pageSize,
                     @RequestParam("page") Optional<Integer> page, Model model){
        //
        // Evaluate page size. If requested parameter is null, return initial
        // page size
        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        // print repo
        // evaluate page size
        model.addAttribute("selectedPageSize", evalPageSize);
        // add pages size
        model.addAttribute("pageSizes", PAGE_SIZES);
        //language
        //templates.course it
        Page<Course> it= courseRepository.findAllByDomainOrderByCourseIdDesc("it", new PageRequest(evalPage, evalPageSize));
        PagerModel pager2 = new PagerModel(it.getTotalPages(),it.getNumber(),BUTTONS_TO_SHOW);
        model.addAttribute("pager", pager2);
        model.addAttribute("courses", it);
        return "course/one/its";
    }

    @GetMapping("/logistique")
    public String logistique(@RequestParam("pageSize") Optional<Integer> pageSize,
                             @RequestParam("page") Optional<Integer> page, Model model){
        //
        // Evaluate page size. If requested parameter is null, return initial
        // page size
        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        // print repo
        // evaluate page size
        model.addAttribute("selectedPageSize", evalPageSize);
        // add pages size
        model.addAttribute("pageSizes", PAGE_SIZES);
        // templates.course logistique
        Page<Course> logistiques= courseRepository.findAllByDomainOrderByCourseIdDesc("logistiques", new PageRequest(evalPage, evalPageSize));
        PagerModel pager3 = new PagerModel(logistiques.getTotalPages(),logistiques.getNumber(),BUTTONS_TO_SHOW);
        model.addAttribute("courses", logistiques);
        model.addAttribute("pager", pager3);
        return "course/one/logistiques";
    }

    @GetMapping("/protection")
    public String protection(@RequestParam("pageSize") Optional<Integer> pageSize,
                             @RequestParam("page") Optional<Integer> page, Model model){
        //
        // Evaluate page size. If requested parameter is null, return initial
        // page size
        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        // print repo
        // evaluate page size
        model.addAttribute("selectedPageSize", evalPageSize);
        // add pages size
        model.addAttribute("pageSizes", PAGE_SIZES);
        //language
        // courses protection
        Page<Course> protections= courseRepository.findAllByDomainOrderByCourseIdDesc("protection", new PageRequest(evalPage, evalPageSize));
        PagerModel pager4 = new PagerModel(protections.getTotalPages(),protections.getNumber(),BUTTONS_TO_SHOW);
        model.addAttribute("courses", protections);
        model.addAttribute("pager", pager4);
        return "course/one/protections";
    }

    @GetMapping("/resources")
    public String resources(@RequestParam("pageSize") Optional<Integer> pageSize,
                            @RequestParam("page") Optional<Integer> page, Model model){
        //
        // Evaluate page size. If requested parameter is null, return initial
        // page size
        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        // print repo
        // evaluate page size
        model.addAttribute("selectedPageSize", evalPageSize);
        // add pages size
        model.addAttribute("pageSizes", PAGE_SIZES);
        // courses resource humaine
        Page<Course> res_hum= courseRepository.findAllByDomainOrderByCourseIdDesc("resources humaines", new PageRequest(evalPage,evalPageSize));
        PagerModel pager5 = new PagerModel(res_hum.getTotalPages(),res_hum.getNumber(),BUTTONS_TO_SHOW);
        model.addAttribute("courses", res_hum);
        model.addAttribute("pager", pager5);
        return "course/one/resources";
    }

    @GetMapping("/leadership")
    public String leadership(@RequestParam("pageSize") Optional<Integer> pageSize,
                             @RequestParam("page") Optional<Integer> page, Model model){
        //
        // Evaluate page size. If requested parameter is null, return initial
        // page size
        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        // print repo
        // evaluate page size
        model.addAttribute("selectedPageSize", evalPageSize);
        // add pages size
        model.addAttribute("pageSizes", PAGE_SIZES);
        // courses leadership
        Page<Course> leaderships= courseRepository.findAllByDomainOrderByCourseIdDesc("leadership", new PageRequest(evalPage, evalPageSize));
        PagerModel pager6 = new PagerModel(leaderships.getTotalPages(),leaderships.getNumber(),BUTTONS_TO_SHOW);
        model.addAttribute("courses", leaderships);
        model.addAttribute("pager", pager6);
        return "course/one/leaderships";
    }

    @GetMapping("/alphabetisation")
    public String alphabetisation(@RequestParam("pageSize") Optional<Integer> pageSize,
                             @RequestParam("page") Optional<Integer> page, Model model){
        //
        // Evaluate page size. If requested parameter is null, return initial
        // page size
        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        // print repo
        // evaluate page size
        model.addAttribute("selectedPageSize", evalPageSize);
        // add pages size
        model.addAttribute("pageSizes", PAGE_SIZES);
        // courses leadership
        Page<Course> alphabetisation= courseRepository.findAllByDomainOrderByCourseIdDesc("alphabetisation", new PageRequest(evalPage, evalPageSize));
        PagerModel pager6 = new PagerModel(alphabetisation.getTotalPages(),alphabetisation.getNumber(),BUTTONS_TO_SHOW);
        model.addAttribute("courses", alphabetisation);
        model.addAttribute("pager", pager6);
        return "course/one/alphabetisation";
    }

    @GetMapping("/entreprenariat")
    public String entreprenariat(@RequestParam("pageSize") Optional<Integer> pageSize,
                                  @RequestParam("page") Optional<Integer> page, Model model){
        //
        // Evaluate page size. If requested parameter is null, return initial
        // page size
        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        // print repo
        // evaluate page size
        model.addAttribute("selectedPageSize", evalPageSize);
        // add pages size
        model.addAttribute("pageSizes", PAGE_SIZES);
        // courses leadership
        Page<Course> entreprenariat= courseRepository.findAllByDomainOrderByCourseIdDesc("entreprenariat", new PageRequest(evalPage, evalPageSize));
        PagerModel pager6 = new PagerModel(entreprenariat.getTotalPages(),entreprenariat.getNumber(),BUTTONS_TO_SHOW);
        model.addAttribute("courses", entreprenariat);
        model.addAttribute("pager", pager6);
        return "course/one/entreprenariat";
    }

    @GetMapping("/management")
    public String management(@RequestParam("pageSize") Optional<Integer> pageSize,
                             @RequestParam("page") Optional<Integer> page, Model model){
        //
        // Evaluate page size. If requested parameter is null, return initial
        // page size
        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        // print repo
        // evaluate page size
        model.addAttribute("selectedPageSize", evalPageSize);
        // add pages size
        model.addAttribute("pageSizes", PAGE_SIZES);
        // courses managements
        Page<Course> managements= courseRepository.findAllByDomainOrderByCourseIdDesc("management", new PageRequest(evalPage, evalPageSize));
        PagerModel pager7 = new PagerModel(managements.getTotalPages(),managements.getNumber(),BUTTONS_TO_SHOW);
        model.addAttribute("courses", managements);
        model.addAttribute("pager", pager7);
        return "course/one/managements";
    }

    @GetMapping("/wash")
    public String wash(@RequestParam("pageSize") Optional<Integer> pageSize,
                       @RequestParam("page") Optional<Integer> page, Model model){
        //
        // Evaluate page size. If requested parameter is null, return initial
        // page size
        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        // print repo
        // evaluate page size
        model.addAttribute("selectedPageSize", evalPageSize);
        // add pages size
        model.addAttribute("pageSizes", PAGE_SIZES);
        // courses wash
        Page<Course> wash= courseRepository.findAllByDomainOrderByCourseIdDesc("wash", new PageRequest(evalPage, evalPageSize));
        PagerModel pager8 = new PagerModel(wash.getTotalPages(),wash.getNumber(),BUTTONS_TO_SHOW);
        model.addAttribute("courses", wash);
        model.addAttribute("pager", pager8);
        return "course/one/washs";
    }

    //second part for shower
    @GetMapping("/administration")
    public String courseAdmin(Model model, @PageableDefault(size = 6) Pageable pageable, @RequestParam("pageSize") Optional<Integer> pageSize,
                              @RequestParam("page") Optional<Integer> page){

        Page<Course> courses1= courseRepository.findAllByDomainOrderByCourseIdDesc("administration et finance",pageable);
        model.addAttribute("courses", courses1);
        model.addAttribute("coursesSize", courses1.getTotalElements());
        //
        // Evaluate page size. If requested parameter is null, return initial
        // page size
        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        // print repo
        // evaluate page size
        model.addAttribute("selectedPageSize", evalPageSize);
        // add pages size
        model.addAttribute("pageSizes", 3);
        Page<Event> list= eventRepository.findAllByType("administration et finance", new PageRequest(evalPage, evalPageSize));
        PagerModel pager1 = new PagerModel(list.getTotalPages(),list.getNumber(),BUTTONS_TO_SHOW);
        model.addAttribute("lists", list);
        model.addAttribute("pager", pager1);
        model.addAttribute("listSize", list.getTotalElements());
        return "course/administration";

    }

    @GetMapping("/languages")
    public String courseLanguage(Model model, @PageableDefault(size = 6) Pageable pageable, @RequestParam("pageSize") Optional<Integer> pageSize,
                                 @RequestParam("page") Optional<Integer> page){

//language
        Page<Course> courses2= courseRepository.findAllByDomainOrderByCourseIdDesc("anglais et/ou francais",pageable);
        model.addAttribute("courses", courses2);
        model.addAttribute("coursesSize", courses2.getTotalElements());
        //
        // Evaluate page size. If requested parameter is null, return initial
        // page size
        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        // print repo
        // evaluate page size
        model.addAttribute("selectedPageSize", evalPageSize);
        // add pages size
        model.addAttribute("pageSizes", 3);
        Page<Event> list1= eventRepository.findAllByType("anglais et/ou francais", new PageRequest(evalPage, evalPageSize));
        PagerModel pager2 = new PagerModel(list1.getTotalPages(),list1.getNumber(),BUTTONS_TO_SHOW);
        model.addAttribute("lists", list1);
        model.addAttribute("pager", pager2);
        model.addAttribute("listSize", list1.getTotalElements());
        return "course/language";

    }

    @GetMapping("/its")
    public String courseIt(Model model, @PageableDefault(size = 6) Pageable pageable, @RequestParam("pageSize") Optional<Integer> pageSize,
                           @RequestParam("page") Optional<Integer> page){

//templates.course it
        Page<Course> courses3= courseRepository.findAllByDomainOrderByCourseIdDesc("it",pageable);
        model.addAttribute("courses", courses3);
        model.addAttribute("coursesSize", courses3.getTotalElements());
        //
        // Evaluate page size. If requested parameter is null, return initial
        // page size
        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        // print repo
        // evaluate page size
        model.addAttribute("selectedPageSize", evalPageSize);
        // add pages size
        model.addAttribute("pageSizes", 3);
        Page<Event> list2= eventRepository.findAllByType("it", new PageRequest(evalPage, evalPageSize));
        PagerModel pager3 = new PagerModel(list2.getTotalPages(),list2.getNumber(),BUTTONS_TO_SHOW);
        model.addAttribute("lists", list2);
        model.addAttribute("pager", pager3);
        model.addAttribute("listSize", list2.getTotalElements());
        return "course/its";

    }

    @GetMapping("/logistiques")
    public String courseLogistique(Model model, @PageableDefault(size = 6) Pageable pageable, @RequestParam("pageSize") Optional<Integer> pageSize,
                                   @RequestParam("page") Optional<Integer> page){

// templates.course logistique
        Page<Course> courses4= courseRepository.findAllByDomainOrderByCourseIdDesc("logistiques", pageable);
        model.addAttribute("courses", courses4);
        model.addAttribute("coursesSize", courses4.getTotalElements());
        // Evaluate page size. If requested parameter is null, return initial
        // page size
        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        // print repo
        // evaluate page size
        model.addAttribute("selectedPageSize", evalPageSize);
        // add pages size
        model.addAttribute("pageSizes", 3);
        Page<Event> list3= eventRepository.findAllByType("logistiques", new PageRequest(evalPage, evalPageSize));
        PagerModel pager4 = new PagerModel(list3.getTotalPages(),list3.getNumber(),BUTTONS_TO_SHOW);
        model.addAttribute("lists", list3);
        model.addAttribute("pager", pager4);
        model.addAttribute("listSize", list3.getTotalElements());
        return "course/logistique";

    }

    @GetMapping("/protections")
    public String courseProtection(Model model, @PageableDefault(size = 6) Pageable pageable, @RequestParam("pageSize") Optional<Integer> pageSize,
                                   @RequestParam("page") Optional<Integer> page){
// courses protection
        Page<Course> courses5= courseRepository.findAllByDomainOrderByCourseIdDesc("protection", pageable);
        model.addAttribute("courses", courses5);
        model.addAttribute("coursesSize", courses5.getTotalElements());
        // Evaluate page size. If requested parameter is null, return initial
        // page size
        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        // print repo
        // evaluate page size
        model.addAttribute("selectedPageSize", evalPageSize);
        // add pages size
        model.addAttribute("pageSizes", 3);

        Page<Event> list4= eventRepository.findAllByType("protection", new PageRequest(evalPage, evalPageSize));
        PagerModel pager5 = new PagerModel(list4.getTotalPages(),list4.getNumber(),BUTTONS_TO_SHOW);
        model.addAttribute("lists", list4);
        model.addAttribute("pager", pager5);
        model.addAttribute("listSize", list4.getTotalElements());
        return "course/protection";

    }

    @GetMapping("/resource")
    public String courseResource(Model model, @PageableDefault(size = 6) Pageable pageable, @RequestParam("pageSize") Optional<Integer> pageSize,
                                 @RequestParam("page") Optional<Integer> page){

        // courses resource humaine
        Page<Course> courses6= courseRepository.findAllByDomainOrderByCourseIdDesc("resources humaines", pageable);

        model.addAttribute("courses", courses6);
        model.addAttribute("coursesSize", courses6.getTotalElements());
        // Evaluate page size. If requested parameter is null, return initial
        // page size
        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        // print repo
        // evaluate page size
        model.addAttribute("selectedPageSize", evalPageSize);
        // add pages size
        model.addAttribute("pageSizes", 3);

        Page<Event> list5= eventRepository.findAllByType("resources humaines", new PageRequest(evalPage, evalPageSize));
        PagerModel pager6 = new PagerModel(list5.getTotalPages(),list5.getNumber(),BUTTONS_TO_SHOW);
        model.addAttribute("lists", list5);
        model.addAttribute("pager", pager6);
        model.addAttribute("listSize", list5.getTotalElements());
        return "course/resource";

    }

    @GetMapping("/leaderships")
    public String courseLeadership(Model model, @PageableDefault(size = 6) Pageable pageable, @RequestParam("pageSize") Optional<Integer> pageSize,
                                   @RequestParam("page") Optional<Integer> page)
    {

        // courses leadership
        Page<Course> courses7= courseRepository.findAllByDomainOrderByCourseIdDesc("leadership", pageable);

        model.addAttribute("courses", courses7);
        model.addAttribute("coursesSize", courses7.getTotalElements());

        // Evaluate page size. If requested parameter is null, return initial
        // page size
        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        // print repo
        // evaluate page size
        model.addAttribute("selectedPageSize", evalPageSize);
        // add pages size
        model.addAttribute("pageSizes", 3);

        Page<Event> list6= eventRepository.findAllByType("leadership", new PageRequest(evalPage, evalPageSize));
        PagerModel pager7 = new PagerModel(list6.getTotalPages(),list6.getNumber(),BUTTONS_TO_SHOW);
        model.addAttribute("lists", list6);
        model.addAttribute("pager", pager7);
        model.addAttribute("listSize", list6.getTotalElements());
        return "course/leadership";

    }

    @GetMapping("/managements")
    public String courseManagement(Model model, @PageableDefault(size = 6) Pageable pageable, @RequestParam("pageSize") Optional<Integer> pageSize,
                                   @RequestParam("page") Optional<Integer> page){

        // courses managements
        Page<Course> courses8= courseRepository.findAllByDomainOrderByCourseIdDesc("management", pageable);

        model.addAttribute("courses", courses8);
        model.addAttribute("coursesSize", courses8.getTotalElements());

        // Evaluate page size. If requested parameter is null, return initial
        // page size
        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        // print repo
        // evaluate page size
        model.addAttribute("selectedPageSize", evalPageSize);
        // add pages size
        model.addAttribute("pageSizes", 3);

        Page<Event> list7= eventRepository.findAllByType("management", new PageRequest(evalPage, evalPageSize));
        PagerModel pager8 = new PagerModel(list7.getTotalPages(),list7.getNumber(),BUTTONS_TO_SHOW);
        model.addAttribute("lists", list7);
        model.addAttribute("pager", pager8);
        model.addAttribute("listSize", list7.getTotalElements());
        return "course/management";

    }


    @GetMapping("/washs")
    public String findWash( Model model, @PageableDefault(size = 6) Pageable pageable, @RequestParam("pageSize") Optional<Integer> pageSize,
                            @RequestParam("page") Optional<Integer> page) {
        // courses wash
        Page<Course> courses9= courseRepository.findAllByDomainOrderByCourseIdDesc("wash", pageable);

        model.addAttribute("courses", courses9);
        model.addAttribute("coursesSize", courses9.getTotalElements());
        //
        // Evaluate page size. If requested parameter is null, return initial
        // page size
        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        // print repo
        // evaluate page size
        model.addAttribute("selectedPageSize", evalPageSize);
        // add pages size
        model.addAttribute("pageSizes", 3);
        Page<Event> list8= eventRepository.findAllByType("wash", new PageRequest(evalPage, evalPageSize));
        PagerModel pager9 = new PagerModel(list8.getTotalPages(),list8.getNumber(),BUTTONS_TO_SHOW);
        model.addAttribute("lists", list8);
        model.addAttribute("pager", pager9);
        model.addAttribute("listSize", list8.getTotalElements());
        return "course/wash";
    }

    @GetMapping("/alphabetisations")
    public String courseAlphabetisation(Model model, @PageableDefault(size = 6) Pageable pageable, @RequestParam("pageSize") Optional<Integer> pageSize,
                                   @RequestParam("page") Optional<Integer> page){
        // courses managements
        Page<Course> courses8= courseRepository.findAllByDomainOrderByCourseIdDesc("alphabetisation", pageable);

        model.addAttribute("courses", courses8);
        model.addAttribute("coursesSize", courses8.getTotalElements());

        // Evaluate page size. If requested parameter is null, return initial
        // page size
        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        // print repo
        // evaluate page size
        model.addAttribute("selectedPageSize", evalPageSize);
        // add pages size
        model.addAttribute("pageSizes", 3);

        Page<Event> list7= eventRepository.findAllByType("alphabetisation", new PageRequest(evalPage, evalPageSize));
        PagerModel pager8 = new PagerModel(list7.getTotalPages(),list7.getNumber(),BUTTONS_TO_SHOW);
        model.addAttribute("lists", list7);
        model.addAttribute("pager", pager8);
        model.addAttribute("listSize", list7.getTotalElements());
        return "course/alphabetisation";

    }


    @GetMapping("/entreprenariats")
    public String findEntreprenariat( Model model, @PageableDefault(size = 6) Pageable pageable, @RequestParam("pageSize") Optional<Integer> pageSize,
                            @RequestParam("page") Optional<Integer> page) {
        // courses wash
        Page<Course> courses9= courseRepository.findAllByDomainOrderByCourseIdDesc("entreprenariat", pageable);

        model.addAttribute("courses", courses9);
        model.addAttribute("coursesSize", courses9.getTotalElements());
        //
        // Evaluate page size. If requested parameter is null, return initial
        // page size
        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        // Evaluate page. If requested parameter is null or less than 0 (to
        // prevent exception), return initial size. Otherwise, return value of
        // param. decreased by 1.
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;
        // print repo
        // evaluate page size
        model.addAttribute("selectedPageSize", evalPageSize);
        // add pages size
        model.addAttribute("pageSizes", 3);
        Page<Event> list8= eventRepository.findAllByType("entreprenariat", new PageRequest(evalPage, evalPageSize));
        PagerModel pager9 = new PagerModel(list8.getTotalPages(),list8.getNumber(),BUTTONS_TO_SHOW);
        model.addAttribute("lists", list8);
        model.addAttribute("pager", pager9);
        model.addAttribute("listSize", list8.getTotalElements());
        return "course/entreprenariat";
    }


}
