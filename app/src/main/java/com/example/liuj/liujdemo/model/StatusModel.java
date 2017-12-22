package com.example.liuj.liujdemo.model;

/**
 * Created by liuj on 2017/12/21.
 */

public class StatusModel extends BaseModel {

    public static final int STATUS_SCHEDULE_JOB = 0;
    public static final int STATUS_START_TASK = STATUS_SCHEDULE_JOB + 1;
    public static final int STATUS_STOP_STASK = STATUS_SCHEDULE_JOB +2;

    public int id;

    public String msg;

    public int status;

    public StatusModel(int id, String msg, int status) {
        this.id = id;
        this.msg = msg;
        this.status = status;
    }

    public static StatusModel newScheduleJobStatusObj(int id, String msg) {
        return new StatusModel(id, msg, STATUS_SCHEDULE_JOB);
    }

    public static StatusModel newStartTaskObj(int id, String msg) {
        return new StatusModel(id, msg, STATUS_START_TASK);
    }

    public static StatusModel newStopTaskObj(int id, String msg) {
        return new StatusModel(id, msg, STATUS_STOP_STASK);
    }

}