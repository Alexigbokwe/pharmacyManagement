package com.pharmacy.management.pms.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pharmacy.management.pms.dto.GroupDto;
import com.pharmacy.management.pms.dto.UpdateGroupDto;
import com.pharmacy.management.pms.entities.MedicineGroup;
import com.pharmacy.management.pms.exception.ResourceAlreadyExistException;
import com.pharmacy.management.pms.exception.ResourceNotFoundException;
import com.pharmacy.management.pms.mappers.MedicineGroupDataMapper;
import com.pharmacy.management.pms.repository.GroupRepository;
import com.pharmacy.management.pms.service.GroupService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GroupServiceImpl implements GroupService {
    private GroupRepository groupRepository;

    @Override
    public GroupDto createGroup(GroupDto groupDto) {
        if (this.doesGroupExistByName(groupDto.getName())) {
            throw new ResourceAlreadyExistException(
                    "Group with the name " + groupDto.getName() + " already exists.");
        }
        MedicineGroup medicineGroup = MedicineGroupDataMapper.mapToMedicineGroup(groupDto);
        return MedicineGroupDataMapper.mapToGroupDto(this.groupRepository.save(medicineGroup));
    }

    private Boolean doesGroupExistByName(String groupName) {
        return this.groupRepository.findByName(groupName).isPresent();
    }

    private Boolean doesMedicineGroupeExistById(Integer id) {
        return this.groupRepository.findById(id).isPresent();
    }

    @Override
    public GroupDto updateGroup(UpdateGroupDto reqGroup) {
        GroupDto groupDto = MedicineGroupDataMapper.mapToGroupDto(reqGroup);
        if (!this.doesMedicineGroupeExistById(groupDto.getId())) {
            throw new ResourceNotFoundException("Medicine group not found");
        }
        MedicineGroup medicineGroup = MedicineGroupDataMapper.mapToMedicineGroup(groupDto);
        return MedicineGroupDataMapper.mapToGroupDto(this.groupRepository.save(medicineGroup));
    }

    @Override
    public List<GroupDto> getAllGroups() {
        List<MedicineGroup> groups = this.groupRepository.findAll();
        if (groups.isEmpty())
            throw new ResourceNotFoundException("No group found in the system");
        return groups.stream().map(medicineGroup -> MedicineGroupDataMapper.mapToGroupDto(medicineGroup))
                .collect(Collectors.toList());
    }

    @Override
    public GroupDto getGroup(Integer groupId) {
        MedicineGroup medicineGroup = this.groupRepository.findById(groupId)
                .orElseThrow(() -> new ResourceNotFoundException("No record found"));
        return MedicineGroupDataMapper.mapToGroupDto(medicineGroup);
    }

    @Override
    public void deleteGroup(Integer groupId) {
        this.groupRepository.deleteById(groupId);
    }

}
