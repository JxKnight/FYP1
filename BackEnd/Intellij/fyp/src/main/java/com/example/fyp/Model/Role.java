package com.example.fyp.Model;

import javax.persistence.*;

@Entity
@Table(name="role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "roles_name",unique = true)
    private String roleName;
    @Column(name = "roles_description")
    private String roleDescription;
    @Column(name = "roles_rate")
    private String roleRate;
    @Column(name = "roles_warehouse")
    private String warehouse;
    @Column(name = "roles_orders")
    private String orders;
    @Column(name = "roles_customers")
    private String customers;
    @Column(name = "roles_reports")
    private String reports;
    @Column(name = "roles_tasks")
    private String tasks;

    public Role() {
    }

    public Role(String roleName, String roleDescription, String roleRate, String warehouse, String orders, String customers, String reports, String tasks) {
        this.roleName = roleName;
        this.roleDescription = roleDescription;
        this.roleRate = roleRate;
        this.warehouse = warehouse;
        this.orders = orders;
        this.customers = customers;
        this.reports = reports;
        this.tasks = tasks;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public String getRoleRate() {
        return roleRate;
    }

    public void setRoleRate(String roleRate) {
        this.roleRate = roleRate;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }

    public String getCustomers() {
        return customers;
    }

    public void setCustomers(String customers) {
        this.customers = customers;
    }

    public String getReports() {
        return reports;
    }

    public void setReports(String reports) {
        this.reports = reports;
    }

    public String getTasks() {
        return tasks;
    }

    public void setTasks(String tasks) {
        this.tasks = tasks;
    }
}