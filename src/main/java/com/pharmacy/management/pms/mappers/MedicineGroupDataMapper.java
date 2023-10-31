package com.pharmacy.management.pms.mappers;

import com.pharmacy.management.pms.dto.GroupDto;
import com.pharmacy.management.pms.dto.UpdateGroupDto;
import com.pharmacy.management.pms.entities.MedicineGroup;

public class MedicineGroupDataMapper {
    public static GroupDto mapToGroupDto(MedicineGroup medicineGroup) {
        return new GroupDto(medicineGroup.getId(), medicineGroup.getName(),
                medicineGroup.getCreatedAt(),
                medicineGroup.getUpdatedAt());
    }

    public static GroupDto mapToGroupDto(UpdateGroupDto medicineGroup) {
        return new GroupDto(medicineGroup.getId(), medicineGroup.getName(),
                medicineGroup.getCreatedAt(),
                medicineGroup.getUpdatedAt());
    }

    public static MedicineGroup mapToMedicineGroup(GroupDto groupDto) {
        MedicineGroup medicineGroup = new MedicineGroup();
        medicineGroup.setId(groupDto.getId());
        medicineGroup.setName(groupDto.getName());
        medicineGroup.setCreatedAt(groupDto.getCreatedAt());
        medicineGroup.setUpdatedAt(groupDto.getUpdatedAt());
        return medicineGroup;
    }
}
