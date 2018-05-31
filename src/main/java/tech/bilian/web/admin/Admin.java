package tech.bilian.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tech.bilian.dto.Execution;
import tech.bilian.dto.PlayCount;
import tech.bilian.pojo.*;
import tech.bilian.service.*;
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

    @Resource
    ShangjiaService shangjiaService;

    @Resource
    SongService songService;

    @Resource
    OwnerService ownerService;




    @RequestMapping(value = "powers", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> powers(HttpServletRequest request) {
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

            case "search":
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
    @RequestMapping(value = "historys", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> historys(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();

        Long userId = HttpServletRequestUtil.getLong(request, "userId");
        String songName = HttpServletRequestUtil.getString(request, "songName");
        Integer state = HttpServletRequestUtil.getInt(request, "status");

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
            case "playcount":
                Execution<PlayCount> playCountExecution = historyService.selectPlayCount();
                if (playCountExecution.getState()<0){
                    modelMap.put("success", false);
                    modelMap.put("errMsg", playCountExecution.getStateInfo());
                    return modelMap;
                }

                modelMap.put("success", true);
                modelMap.put("playcount", playCountExecution.getModels());
                return modelMap;
            default:
                modelMap.put("success", false);
                modelMap.put("errMsg", "输入信息有误！");
                return modelMap;
        }
    }

    /**
     * 商家操作
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "shangjias", method = RequestMethod.GET)
    @ResponseBody
    public  Map<String, Object> shangjias(HttpServletRequest request) {

        Map<String, Object> modelMap = new HashMap<>();

        String method = HttpServletRequestUtil.getString(request, "method");
        Long master = HttpServletRequestUtil.getLong(request, "master");
        String name = HttpServletRequestUtil.getString(request, "name");
        Long shangjiaId = HttpServletRequestUtil.getLong(request, "shangjiaId");


        if (method == null || method.trim().equals("")) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "输入数据有误");
            return modelMap;
        }
        switch (method){
            case "add":
                if (name == null || name.trim().equals("")){
                    modelMap.put("success", false);
                    modelMap.put("errMsg", "请输入完整数据");
                    return modelMap;
                }
                Shangjia shangjia = new Shangjia();
                shangjia.setShangjiaName(name);

                if (master > 0){
                    shangjia.setMaster(master);
                }

                Execution<Shangjia> shangjiaExecution = shangjiaService.insertShangjia(shangjia);
                if (shangjiaExecution.getState() <= 0){
                    modelMap.put("success", false);
                    modelMap.put("errMsg", shangjiaExecution.getStateInfo());
                    return modelMap;
                }
                modelMap.put("success", true);
                modelMap.put("data", shangjiaExecution.getStateInfo());
                return modelMap;

            case "search":
                Shangjia shangjiaSearch = new Shangjia();
                if (name != null){
                    shangjiaSearch.setShangjiaName(name);
                }
                if (master > 0){
                    shangjiaSearch.setMaster(master);
                }
                if (shangjiaId > 0){
                    shangjiaSearch.setShangjiaId(shangjiaId);
                }
                Execution<Shangjia> shangjiaExecutionSearch = shangjiaService.queryShangjia(shangjiaSearch);
                if (shangjiaExecutionSearch.getState() <= 0){
                    modelMap.put("success", false);
                    modelMap.put("errMsg", shangjiaExecutionSearch.getStateInfo());
                    return modelMap;
                }
                modelMap.put("success", true);
                modelMap.put("data", shangjiaExecutionSearch.getModels());
                return modelMap;



            default:
                modelMap.put("success", false);
                modelMap.put("errMsg", "输入数据有误");

                return modelMap;

        }

    }


    @RequestMapping(value = "songs", method = RequestMethod.GET)
    @ResponseBody
    public  Map<String, Object> songs(HttpServletRequest request) {

        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("user", (tech.bilian.pojo.Admin)request.getSession().getAttribute("user"));

        String method = HttpServletRequestUtil.getString(request, "method");







        if (method == null || method.trim().equals("")) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "输入数据有误");
            return modelMap;
        }

        switch (method){
            case "search":
                Execution<Song> songExecution = songService.querySong(null);
                if (songExecution.getState() <= 0){
                    modelMap.put("success", false);
                    modelMap.put("errMsg", songExecution.getStateInfo());
                    return modelMap;
                }
                modelMap.put("success", true);
                modelMap.put("data", songExecution.getModels());
                return modelMap;



            default:
                modelMap.put("success", false);
                modelMap.put("errMsg", "输入数据有误");

                return modelMap;
        }

    }


    @RequestMapping(value = "owners", method = RequestMethod.GET)
    @ResponseBody
    public  Map<String, Object> owners(HttpServletRequest request) {

        Map<String, Object> modelMap = new HashMap<>();

        String method = HttpServletRequestUtil.getString(request, "method");


        if (method == null || method.trim().equals("")) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "输入数据有误");
            return modelMap;
        }
        Execution<Owner> ownerExecution;

        switch (method) {
            case "search":
                ownerExecution = ownerService.queryOwner(null);
                if (ownerExecution.getState() <= 0){
                    modelMap.put("success", false);
                    modelMap.put("errMsg", ownerExecution.getStateInfo());
                    return modelMap;
                }
                modelMap.put("success", true);
                modelMap.put("data", ownerExecution.getModels());
                return modelMap;



            default:
                modelMap.put("success", false);
                modelMap.put("errMsg", "输入数据有误");

                return modelMap;

        }

    }












        @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(){
        return "admin/index";
    }

    @RequestMapping(value = "music", method = RequestMethod.GET)
    public String music(){
        return "admin/music";
    }

    @RequestMapping(value = "shangjia", method = RequestMethod.GET)
    public String shangjia(){
        return "admin/shangjia";
    }

    @RequestMapping(value = "owner", method = RequestMethod.GET)
    public String owner(){
        return "admin/owner";
    }

    @RequestMapping(value = "playinformation", method = RequestMethod.GET)
    public String playInformation(){
        return "admin/playinformation";
    }

    @RequestMapping(value = "userdetails", method = RequestMethod.GET)
    public String userdetails(){
        return "admin/userdetails";
    }

    @RequestMapping(value = "addbanquanfang", method = RequestMethod.GET)
    public String addBanquanfang(){
        return "admin/addbanquanfang";
    }
}
