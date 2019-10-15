package cn.ytmj.controller;

import cn.ytmj.domain.SysLog;
import cn.ytmj.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author rui
 * @create 2019-10-14 22:44
 */

@RestController
@RequestMapping("sysLog")
public class SysLogController {
    @Autowired
    private ISysLogService sysLogService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<SysLog> logs = sysLogService.findAll();
        mv.addObject("sysLogs", logs);
        mv.setViewName("syslog-list");
        return mv;

    }


}
