package com.physical.movement.controller;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import com.physical.movement.entity.Game;
import com.physical.movement.entity.SysUser;
import com.physical.movement.entity.Team;
import com.physical.movement.model.Paginator;
import com.physical.movement.model.ResultJson;
import com.physical.movement.service.GameService;
import com.physical.movement.service.MailService;
import com.physical.movement.service.SysUserService;
import com.physical.movement.service.TeamService;
import com.physical.movement.utils.RandomUtil;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.glassfish.jersey.internal.guava.ThreadFactoryBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

import static com.physical.movement.model.SportsType.STATUS_MAP;

/**
 * 比赛模块
 */
@Controller
@RequestMapping("game")
public class GameController {

    @Autowired
    GameService gameService;

    @Autowired
    SysUserService sysUserService;

    @Autowired
    TeamService teamService;

    @Autowired
    MailService mailService;

    @RequestMapping("/gameAgreement")
    public ModelAndView teamManage(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize, HttpServletRequest request, HttpSession session, ModelAndView mv) {
        String check = request.getParameter("check");
        mv.addObject("check", check);
        String flag = request.getParameter("flag");
        mv.addObject("flag", flag);
        String key = request.getParameter("key");
        mv.addObject("key", key);
        int uid = (int) session.getAttribute("uid");
        Team team = new Team();
        if (check != null && !check.equals("0") && !check.equals("")) {
            team.setTeamtype(Integer.parseInt(check));
        }
        if (flag != null && !flag.equals("0")) {
            team.setTeamleaderid(uid);
        }
        team.setTeamname(key);
        team.setFlag((byte) 1);
        List<Team> teamList = teamService.selectAll(team, pageNum, pageSize);
        PageInfo tlist = new PageInfo(teamList);
        List pagenums = new ArrayList();
        Paginator.page(pagenums, tlist, pageNum, pageSize);
        mv.addObject("pagenums", pagenums);
        mv.addObject("tlist", tlist);
        mv.addObject("SportsType", STATUS_MAP);
        mv.setViewName("/game/gameAgreement");
        return mv;
    }

    @RequestMapping("/gameResult")
    public ModelAndView gameResult(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize, HttpServletRequest request, HttpSession session, ModelAndView mv) {
        String ch = request.getParameter("check");
        Byte check = null;
        if (ch != null && !ch.equals("")) {
            check = Byte.valueOf(ch);
        }
        mv.addObject("check", check);
        String key = request.getParameter("key");
        mv.addObject("key", key);
        int uid = (int) session.getAttribute("uid");
        Game game = new Game();
        if (check != null && !check.equals("0") && !check.equals("")) {
            game.setFlag(check);
        }
        game.setEnemyid(uid);
        game.setChallenger(key);
        game.setEnemy(key);
        List<Game> gameList = gameService.selectAll(game, pageNum, pageSize);
        PageInfo tlist = new PageInfo(gameList);
        List pagenums = new ArrayList();
        Paginator.page(pagenums, tlist, pageNum, pageSize);
        mv.addObject("pagenums", pagenums);
        mv.addObject("glist", tlist);
        mv.addObject("SportsType", STATUS_MAP);
        mv.setViewName("/game/gameResult");
        return mv;
    }

    @RequestMapping("/read-game")
    public ModelAndView readGame(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        Integer gid = Integer.valueOf(request.getParameter("id"));
        Game game = new Game();
        game.setId(gid);
        game = gameService.select(game);
        mv.addObject("game", game);
        mv.setViewName("/game/read-game");
        return mv;
    }

    @RequestMapping("/game-result")
    public ModelAndView gameResult(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        Integer gid = Integer.valueOf(request.getParameter("id"));
        Game game = new Game();
        game.setId(gid);
        game = gameService.select(game);
        mv.addObject("game", game);
        mv.setViewName("/game/game-result");
        return mv;
    }

    @RequestMapping("/apply-game")
    public ModelAndView applyGame(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        Integer gid = Integer.valueOf(request.getParameter("id"));
        Game game = new Game();
        game.setId(gid);
        game = gameService.select(game);
        Team team = new Team();
        team.setId(game.getChallengerid());
        team = teamService.select(team);
        mv.addObject("team", team);
        mv.addObject("game", game);
        mv.setViewName("/game/applycontact");
        return mv;
    }

    @RequestMapping("/challenge-team")
    public ModelAndView challengeTeam(HttpServletRequest request, ModelAndView mv) {
        Integer mid = Integer.valueOf(request.getParameter("id"));
        Team team = new Team();
        team.setId(mid);
        team = teamService.select(team);
        SysUser sysUser = new SysUser();
        sysUser.setId(team.getTeamleaderid());
        sysUser = sysUserService.select(sysUser);
        mv.addObject("sysUser", sysUser);
        mv.addObject("team", team);
        mv.setViewName("/game/contact");
        return mv;
    }

    @RequestMapping("/sendGame")
    @ResponseBody
    public ResultJson sendGame(String message, String name, String email, String subject, Integer id, String time, HttpSession session) {
        Game game = new Game();
        SysUser sysUser = (SysUser) session.getAttribute("sysUser");
        Team challenger = new Team();
        challenger.setTeamleaderid(sysUser.getId());
        challenger = teamService.select(challenger);
        if (challenger == null) {
            return ResultJson.error("您不是队长，不可以约赛");
        }
        Team enemy = new Team();
        enemy.setId(id);
        enemy = teamService.select(enemy);
        if (enemy != null && enemy.getTeamleaderid() == sysUser.getId()) {
            return ResultJson.error("您不能约战自己的队伍");
        }
        message = message + "-------" + "队伍名称：" + challenger.getTeamname() + "-------" + "队长名字：" + challenger.getTeamleader() + "-------" + "比赛时间:" + time;
        boolean flag = mailService.sendWithHtml(email, subject, message);
        if (flag) {
            game.setChallenger(challenger.getTeamname());
            game.setChallengerid(challenger.getId());
            game.setEnemy(enemy.getTeamname());
            game.setEnemyid(enemy.getId());
            game.setType(time);
            game.setFlag((byte) 0);
            game.setStatement(message);
            gameService.insert(game);
            return ResultJson.success("约赛成功");
        } else {
            return ResultJson.error("约赛失败");
        }
    }

    @RequestMapping("/sendGameApply")
    @ResponseBody
    public ResultJson sendGameApply(String message, String email, String subject, Integer id, Byte n_flag, HttpSession session) {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("thread-call-runner-%d").build();
        ExecutorService executorService = new ThreadPoolExecutor(5, 10, 10,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(5), namedThreadFactory);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                mailService.sendWithHtml(email, subject, message);
            }
        });
        Game game = new Game();
        game.setId(id);
        game.setStatement(message);
        game.setFlag(n_flag);
        Integer flag = gameService.updateByPrimaryKeySelective(game);
        if (flag > 0) {
            return ResultJson.success("回复成功");
        } else {
            return ResultJson.error("回复失败");
        }
    }

    @RequestMapping("/submitResult")
    @ResponseBody
    public ResultJson submitApply(String mess, Integer gid, HttpSession session) {
        Game game = new Game();
        game.setId(gid);
        game.setResult(mess);
        int a = gameService.updateByPrimaryKeySelective(game);
        if (a > 0) {
            return ResultJson.success("修改成功");
        } else {
            return ResultJson.error("修改失败");
        }
    }

    @RequestMapping(value = "/upload", produces = {"text/html;charset=UTF-8"}, method = RequestMethod.POST)
    @ResponseBody
    public Object uploadReportFile(@RequestParam(value = "myFileName", required = false) MultipartFile cardFile,
                                   HttpServletRequest request, HttpSession session) {
        String path = Class.class.getClass().getResource("/").getPath();
        path = path + "static" + File.separator + "uploadfiles";


        Map<String, String> map = new HashMap<String, String>();
        if (cardFile != null) {
            String oldFileName = cardFile.getOriginalFilename();

            String prefix = FilenameUtils.getExtension(oldFileName);

            if (cardFile.getSize() > 10000000) {
                return "1";
            } else if (prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png")
                    || prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("pneg")) {
                String fileName = System.currentTimeMillis() + RandomUtil.getRandNum() + "_IDcard.jpg";
                File targetFile = new File(path, fileName);
                // 检测是否存在目录
                if (!targetFile.getParentFile().exists()) {
                    targetFile.getParentFile().mkdirs();
                }

                try {

                    cardFile.transferTo(targetFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                String url = request.getContextPath() + "/uploadfiles/" + fileName;
                map.put("data", url);
                return JSONArray.toJSONString(map);

            } else {
                return "2";
            }
        }

        return null;
    }
}
