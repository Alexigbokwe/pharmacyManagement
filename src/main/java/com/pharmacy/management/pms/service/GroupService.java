package com.pharmacy.management.pms.service;

import java.util.List;

import com.pharmacy.management.pms.dto.GroupDto;
import com.pharmacy.management.pms.dto.UpdateGroupDto;

public interface GroupService {
    GroupDto createGroup(GroupDto groupDto);

    GroupDto updateGroup(UpdateGroupDto groupDto);

    List<GroupDto> getAllGroups();

    GroupDto getGroup(Integer groupId);

    void deleteGroup(Integer groupId);
}
