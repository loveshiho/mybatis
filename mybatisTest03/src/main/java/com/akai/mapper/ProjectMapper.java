package com.akai.mapper;

import com.akai.pojo.Project;

public interface ProjectMapper {
    Project findProjectJoinEmpsByPid(int pid);
}
