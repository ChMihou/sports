package com.physical.movement.entity;

import com.physical.movement.entity.common.Id;

/**
 * 比赛实体类
 */
public class Game extends Id {

    private static final long serialVersionUID = -8331637732864573070L;
    private Integer type;

    private String challenger;

    private Integer challengerid;

    private String statement;

    private String enemy;

    private Integer enemyid;

    private Byte flag;

    private String result;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getChallenger() {
        return challenger;
    }

    public void setChallenger(String challenger) {
        this.challenger = challenger == null ? null : challenger.trim();
    }

    public Integer getChallengerid() {
        return challengerid;
    }

    public void setChallengerid(Integer challengerid) {
        this.challengerid = challengerid;
    }

    public String getEnemy() {
        return enemy;
    }

    public void setEnemy(String enemy) {
        this.enemy = enemy == null ? null : enemy.trim();
    }

    public Integer getEnemyid() {
        return enemyid;
    }

    public void setEnemyid(Integer enemyid) {
        this.enemyid = enemyid;
    }

    public Byte getFlag() {
        return flag;
    }

    public void setFlag(Byte flag) {
        this.flag = flag;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }
}