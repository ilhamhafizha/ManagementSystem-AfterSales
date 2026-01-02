package org.msas.managementsystemaftersales.dto;

import org.msas.managementsystemaftersales.entity.ServiceType;

import java.time.LocalDate;

public class ServiceScheduleResponse {

    private Long id;
    private LocalDate jadwalService;
    private ServiceType serviceType;
    private Boolean statusService;

    public ServiceScheduleResponse(
            Long id,
            LocalDate jadwalService,
            ServiceType serviceType,
            Boolean statusService
    ) {
        this.id = id;
        this.jadwalService = jadwalService;
        this.serviceType = serviceType;
        this.statusService = statusService;
    }

    public Long getId() { return id; }
    public LocalDate getJadwalService() { return jadwalService; }
    public ServiceType getServiceType() { return serviceType; }
    public Boolean getStatusService() { return statusService; }
}