package ma.projet.domaine;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import ma.projet.beans.Service1;
import ma.projet.beans.Employe;
import ma.projet.service.EmployeService;
import ma.projet.service.Service1Service;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author Lenovo
 */
@ManagedBean (name = "service1Bean")
@ViewScoped
public class Service1Bean implements Serializable{
    
    private Service1 service1;
    private Employe employe;
    private EmployeService employeService;
    private Service1Service service1Service;
    private List<Service1> services; 
    
    public Service1Bean(){
        service1 = new Service1();
        employeService = new EmployeService();
        service1Service = new Service1Service();
        loadServices();
         services = service1Service.getAll();
        
    }

    public Service1 getService1() {
        return service1;
    }

    public void setService1(Service1 service1) {
        this.service1 = service1;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public EmployeService getEmployeService() {
        return employeService;
    }

    public void setEmployeService(EmployeService employeService) {
        this.employeService = employeService;
    }

    public Service1Service getService1Service() {
        return service1Service;
    }

    public void setService1Service(Service1Service service1Service) {
        this.service1Service = service1Service;
    }

    public List<Service1> getServices() {
        return services;
    }

    public void setServices(List<Service1> services) {
        this.services = services;
    }
    
    
    public String createService() {
        service1Service.create(service1);
        service1 = new Service1(); // Clear the input fields after creation
        loadServices();
        return null;
    }

    public String updateService() {
        service1Service.update(service1);
        service1 = new Service1(); // Clear the input fields after update
        loadServices();
        return null;
    }

    public String deleteService(Service1 service1) {
        service1Service.delete(service1);
        loadServices();
        return null;
    }

    public void loadServices() {
        services = service1Service.getAll();
        
    }
    
    public BarChartModel getEmployeeChartData() {
        BarChartModel model = new BarChartModel();

        List<Service1> services = service1Service.getAllServices();

        for (Service1 service : services) {
            ChartSeries series = new ChartSeries();
            series.setLabel(service.getNom());

            int employeeCount = service.getEmployes().size();
            series.set("Service", employeeCount);

            model.addSeries(series);
        }

        return model;
    }
    
    
    
    
    
}