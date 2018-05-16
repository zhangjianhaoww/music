package tech.bilian.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tech.bilian.dto.Execution;
import tech.bilian.pojo.History;
import tech.bilian.pojo.Power;
import tech.bilian.service.HistoryService;
import tech.bilian.service.PowerService;
import tech.bilian.utils.HttpServletRequestUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("admin")
public class Admin {
    @Resource
    HistoryService historyService;

    @Resource
    PowerService powerService;

    @RequestMapping(value = "searchhistory", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> searchHistory(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();

        Long userId = HttpServletRequestUtil.getLong(request, "userId");
        String songName = HttpServletRequestUtil.getString(request, "songName");
        Integer state = HttpServletRequestUtil.getInt(request, "state");

        History history = new History();

        if (userId > 0){
            history.setUserId(userId);
        }
        if (songName!= null && !songName.equals("")){
            history.setSongName(songName);
        }
        if (state == 0 || state == 1){
            history.setState(state);
        }

        Execution<History> historyExecution = historyService.queryHistory(history);
        if (historyExecution.getState()<0){
            modelMap.put("success", false);
            modelMap.put("errMsg", historyExecution.getStateInfo());
            return modelMap;
        }

        modelMap.put("success", true);
        modelMap.put("histories", historyExecution.getModels());
        return modelMap;
    }

    /**
     * 授权
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "addpower", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> addPower(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();

        Long userId = HttpServletRequestUtil.getLong(request, "userId");
        String songName = HttpServletRequestUtil.getString(request, "songName");

        if (userId<=0 || songName==null || songName.equals("")){
            modelMap.put("success", false);
            modelMap.put("errMsg", "输入格式不正确！");
            return modelMap;
        }

        Power power = new Power();
        power.setUserId(userId);
        power.setSongName(songName);
        Execution<Power> powerExecution = powerService.insertPower(power);
        if (powerExecution.getState() <= 0){
            modelMap.put("success", false);
            modelMap.put("errMsg", powerExecution.getStateInfo());
            return modelMap;
        }
        modelMap.put("success", true);
        modelMap.put("data", powerExecution.getStateInfo());
        return modelMap;
    }


    @RequestMapping(value = "querypower", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> queryPower(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();

        Long userId = HttpServletRequestUtil.getLong(request, "userId");
        String songName = HttpServletRequestUtil.getString(request, "songName");

        Power power = new Power();


        if (userId>0){
            power.setUserId(userId);
        }
        if (songName!=null && !songName.equals("")){
            power.setSongName(songName);
        }

        Execution<Power> powerExecution = powerService.queryPower(power);
        if (powerExecution.getState() <= 0){
            modelMap.put("success", false);
            modelMap.put("errMsg", powerExecution.getStateInfo());
            return modelMap;
        }
        modelMap.put("success", true);
        modelMap.put("data", powerExecution.getModels());
        return modelMap;
    }


    @RequestMapping(value = "power", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> power(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        Long userId = HttpServletRequestUtil.getLong(request, "userId");
        String songName = HttpServletRequestUtil.getString(request, "songName");
        String method = HttpServletRequestUtil.getString(request, "method");
        Power power = new Power();
        Execution<Power> powerExecution;
        if (method==null || method.equals("")){
            modelMap.put("success", false);
            modelMap.put("errMsg", "输入数据有误");
            return modelMap;
        }
        switch(method){
            case "add":
                if (userId<=0 || songName==null || songName.equals("")){
                    modelMap.put("success", false);
                    modelMap.put("errMsg", "输入格式不正确！");
                    return modelMap;
                }


                power.setUserId(userId);
                power.setSongName(songName);
                powerExecution = powerService.insertPower(power);
                if (powerExecution.getState() <= 0){
                    modelMap.put("success", false);
                    modelMap.put("errMsg", powerExecution.getStateInfo());
                    return modelMap;
                }
                modelMap.put("success", true);
                modelMap.put("data", powerExecution.getStateInfo());
                return modelMap;
                //break;

            case "query":
               // Power power = new Power();


                if (userId>0){
                    power.setUserId(userId);
                }
                if (songName!=null && !songName.equals("")){
                    power.setSongName(songName);
                }

               powerExecution = powerService.queryPower(power);
                if (powerExecution.getState() <= 0){
                    modelMap.put("success", false);
                    modelMap.put("errMsg", powerExecution.getStateInfo());
                    return modelMap;
                }
                modelMap.put("success", true);
                modelMap.put("data", powerExecution.getModels());
                return modelMap;
               // break;

            case "delete":
                if (userId<=0 || songName==null || songName.equals("")){
                    modelMap.put("success", false);
                    modelMap.put("errMsg", "输入格式不正确！");
                    return modelMap;
                }
                power.setUserId(userId);
                power.setSongName(songName);
                powerExecution = powerService.deletePower(power);
                if (powerExecution.getState() <= 0){
                    modelMap.put("success", false);
                    modelMap.put("errMsg", powerExecution.getStateInfo());
                    return modelMap;
                }
                modelMap.put("success", true);
                modelMap.put("data", powerExecution.getStateInfo());
                return modelMap;
                //break;

            default:
                modelMap.put("success", false);
                modelMap.put("errMsg", "输入信息有误！");
                return modelMap;
        }

    }

    /**
     * 播放历史
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "history", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> history(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();

        Long userId = HttpServletRequestUtil.getLong(request, "userId");
        String songName = HttpServletRequestUtil.getString(request, "songName");
        Integer state = HttpServletRequestUtil.getInt(request, "state");

        Execution<History> historyExecution;
        History history = new History();
        String method = HttpServletRequestUtil.getString(request, "method");
        System.out.println("method:" + method);

        if (method == null || method.equals("")) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "输入数据有误");
            return modelMap;
        }
        switch (method) {
            //添加
            case "add":
                if (userId <= 0 || songName == null || songName.equals("")) {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", "输入格式不正确！");
                    return modelMap;
                }
                history.setUserId(userId);
                history.setSongName(songName);
                historyExecution = historyService.insertHistory(history);
                if (historyExecution.getState() <= 0) {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", historyExecution.getStateInfo());
                    return modelMap;
                }
                modelMap.put("success", true);
                modelMap.put("data", historyExecution.getStateInfo());
                return modelMap;

            //查询
            case "search":
                if (userId > 0){
                    history.setUserId(userId);
                }
                if (songName!= null && !songName.equals("")){
                    history.setSongName(songName);
                }
                if (state == 0 || state == 1 || state == 2){
                    history.setState(state);
                }

                historyExecution = historyService.queryHistory(history);
                if (historyExecution.getState()<0){
                    modelMap.put("success", false);
                    modelMap.put("errMsg", historyExecution.getStateInfo());
                    return modelMap;
                }

                modelMap.put("success", true);
                modelMap.put("histories", historyExecution.getModels());
                return modelMap;

            default:
                modelMap.put("success", false);
                modelMap.put("errMsg", "输入信息有误！");
                return modelMap;
        }
    }

}
