package com.example.fyp.Controller;

import com.example.fyp.Model.Role;
import com.example.fyp.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class RoleController {
    @Autowired
    RoleRepository RoleRepo;


    @GetMapping("roles")
    public List<Role> getAllRoles() {
        return RoleRepo.findAll();
    }

    @PostMapping("createRoles")
    public void createRoles(@RequestBody Map<String, String> payload) {
        RoleRepo.save(new Role(payload.get("roleName"),payload.get("roleDescription"),payload.get("roleRate"),payload.get("warehouse"),payload.get("orders"),payload.get("customers"),payload.get("reports"),payload.get("tasks")));
    }

    @PostMapping("currentRole")
    public Role searchRoles(@RequestBody Map<String, String> payload) {
        Optional<Role> userOptional = RoleRepo.findByRoleName(payload.get("roleName"));
        return userOptional.orElseThrow(() ->  new NullPointerException("No record : " +payload.get("roleName")));
    }

    @PostMapping("updateRole")
    public Role updateRole(@RequestBody Map<String, String> payload) {
        return RoleRepo.findByRoleName(payload.get("roleName")).map(role -> {
            role.setRoleDescription(payload.get("roleDescription"));
            role.setRoleRate(payload.get("roleRate"));
            RoleRepo.save(role);
            return role;
        }).orElseThrow(() -> new NullPointerException("Unable to update empty record"));
    }

    @PostMapping("deleteRole")
    public Role deleteRole(@RequestBody Map<String, String> payload) {
        return RoleRepo.findByRoleName(payload.get("roleName")).map(role -> {
            RoleRepo.delete(role);
            return role;
        }).orElseThrow(() -> new NullPointerException("Unable to update empty record"));
    }

}
