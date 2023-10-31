package com.pharmacy.management.pms.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharmacy.management.pms.dto.GroupDto;
import com.pharmacy.management.pms.dto.UpdateGroupDto;
import com.pharmacy.management.pms.service.GroupService;
import com.pharmacy.management.pms.utils.SuccessResponse;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/groups")
public class GroupController extends BaseController {
    private GroupService groupService;

    @GetMapping("/all")
    public ResponseEntity<SuccessResponse<List<GroupDto>>> getAllMedicineGroups() {
        List<GroupDto> medicineGroups = this.groupService.getAllGroups();
        return ResponseEntity.ok(this.successResponse("Medicine Groups successfully fetched", medicineGroups));
    }

    @GetMapping("{id}")
    public ResponseEntity<SuccessResponse<GroupDto>> getMedicineGroup(@PathVariable("id") Integer medicineGroupId) {
        GroupDto medicineGroup = this.groupService.getGroup(medicineGroupId);
        return ResponseEntity.ok(this.successResponse("Medicine Group successfully fetched", medicineGroup));
    }

    @PostMapping
    public ResponseEntity<SuccessResponse<GroupDto>> saveMedicineGroup(@Valid @RequestBody GroupDto medicineGroup) {
        GroupDto createdGroup = this.groupService.createGroup(medicineGroup);
        return ResponseEntity.ok(this.successResponse("Medicine Group successfully created", createdGroup));
    }

    @PatchMapping
    public ResponseEntity<SuccessResponse<GroupDto>> updateMedicineGroup(
            @Valid @RequestBody UpdateGroupDto medicineGroup) {
        GroupDto updatedGroup = this.groupService.updateGroup(medicineGroup);
        return ResponseEntity.ok(this.successResponse("Medicine Group successfully updated", updatedGroup));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<SuccessResponse<String>> deleteMedicineGroup(@PathVariable("id") Integer medicineGroupId) {
        this.groupService.deleteGroup(medicineGroupId);
        return ResponseEntity.ok(this.successResponse("Medicine Group successfully deleted", null));
    }
}
