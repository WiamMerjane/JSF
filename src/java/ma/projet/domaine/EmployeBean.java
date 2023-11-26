package ma.projet.domaine;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import ma.projet.beans.Employe;
import ma.projet.beans.Service1;
import ma.projet.service.EmployeService;
import ma.projet.service.Service1Service;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.TreeNode;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

@ManagedBean(name = "employeBean")
@ViewScoped
public class EmployeBean implements Serializable{
    
    private TreeNode root;
    
    private Employe employe;
    private Service1 service;
    private List<Employe> employes;
    private EmployeService employeService;
    private Service1Service service1Service;
    private List<Employe> chefList;
    private List<Service1> service1List;
    private UploadedFile uploadedPhoto;

    



    public EmployeBean() {
        employe = new Employe();
        employe.setService1(new Service1());
        employe.setChef(new Employe()); // Initialize chef
         // Initialize service1



        employeService = new EmployeService();
        service1Service = new Service1Service();
        root = new DefaultTreeNode("root", null);
        loadTree();
        loadEmployes();
         chefList = employeService.getAll(); // Assuming you have a service to get all employees
         service1List = service1Service.getAll(); // Assuming you have a service to get all services


        
    }
    
    public List<Employe> getChefList() {
    return chefList;
}
    public List<Service1> getService1List() {
    return service1List;
}

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public Service1 getService() {
        return service;
    }

    public void setService(Service1 service) {
        this.service = service;
    }

    public List<Employe> getEmployes() {
        return employes;
    }

    public void setEmployes(List<Employe> employes) {
        this.employes = employes;
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
    
public String createEmploye() {
    if (employe.getService1() != null) {
        try {
            employeService.create(employe);
            employe = new Employe(); // Clear the input fields after creation
            System.out.println("Employe created successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error creating employe: " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error creating employe", e.getMessage()));
        }
    } else {
        System.out.println("Service is null. Cannot create employe.");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Service is null. Cannot create employe", null));
        // Gérer le cas où 'service' est null, par exemple afficher un message d'erreur.
    }
    return null;
}




//public void updateEmploye() {
//    employe.setService1(service); // Set the selected service
//    employeService.update(employe);
//    employe = new Employe(); // Clear the input fields after update
//    loadEmployes();
//}

    
    

    public void deleteEmploye() {
    if (employe != null) {
        employeService.delete(employe);
        loadEmployes();
    }
}

    
    
    
    public List<Employe> serviceLoad() {
        for (Employe e : employeService.getAll()) {
            if (e.getService1().equals(service)) {
                employes.add(e);
            }
        }
        return employes;

    }

   public void loadEmployes() {
    employes = employeService.getAll();
    for (Employe e : employes) {
        System.out.println("Employe " + e.getId() + " has photo: " + (e.getPhoto() != null));
    }
}

    
   public void loadTree() {
        root.getChildren().clear(); // Clear old nodes
        List<Service1> services = service1Service.getAll();

        for (Service1 service : services) {
            // Create a node for the service
            TreeNode serviceNode = new DefaultTreeNode(service, root);

            // Display chief under service
            Employe chief = service.getChef();
            if (chief != null) {
                // Create a node for the chief under the service node
                TreeNode chiefNode = new DefaultTreeNode(chief, serviceNode);

                // Display employees under chief
                for (Employe employe : service.getEmployes()) {
                    // Create a node for each employee under the chief node
                    TreeNode employeNode = new DefaultTreeNode(employe, chiefNode);
                }
            }
        }
    }


    
    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }
    
    public void load() {
        System.out.println(service.getNom());
        service = service1Service.getById(service.getId());
        getEmployes();
    }

    public void onEdit(RowEditEvent event) {

        employe = (Employe) event.getObject();
        Service1 service1 = service1Service.getById(this.employe.getService1().getId());
        employe.setService1(service1);
        employe.getService1().setNom(service1.getNom());
        employeService.update(employe);
            loadEmployes();  // Refresh the data after editing
            
           


        
        
    }
     public void onCancel(RowEditEvent event) {
    }
     
     
      public UploadedFile getUploadedPhoto() {
        return uploadedPhoto;
    }

    public void setUploadedPhoto(UploadedFile uploadedPhoto) {
        this.uploadedPhoto = uploadedPhoto;
    }

   public void uploadPhoto() {
    if (employe != null && uploadedPhoto != null) {
        byte[] photoContent = uploadedPhoto.getContents();
        
        if (photoContent != null && photoContent.length > 0) {
            employe.setPhoto(photoContent);
            System.out.println("Photo uploaded successfully.");
        } else {
            System.out.println("Uploaded photo content is null or empty.");
        }
    } else {
        System.out.println("Employe or uploadedPhoto is null.");
    }
}



   

    
    
    
 
public String getPhoto(Employe employe) {
    if (employe == null || employe.getPhoto() == null) {
        return "D:\\S3\\J2E\\jsf7\\web\\resources\\image.png"; // ou retourner une URL d'image par défaut
    }

    byte[] photo = employe.getPhoto();
    String base64Image = Base64.getEncoder().encodeToString(photo);
    return "data:image/png;base64," + base64Image;
}





  
//  
// public StreamedContent getPhoto(Employe employe) {
//    if (employe != null && employe.getPhoto() != null) {
//        byte[] photo = employe.getPhoto();
//        
//        if (photo != null && photo.length > 0) {
//            return new DefaultStreamedContent(new ByteArrayInputStream(photo));
//        }
//    }
//    
//    // Return a placeholder image or null based on your requirement
//    return null;
//}



 

 
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
 
  public void handleFileUpload(FileUploadEvent event) {
        try {
            InputStream inputStream = event.getFile().getInputstream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            byte[] fileContent = outputStream.toByteArray();
            System.out.println("fileContent: " + Arrays.toString(fileContent));
            employe.setPhoto(fileContent);
            outputStream.close();
            inputStream.close();

            FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (IOException e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error uploading file");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
  
    public ByteArrayInputStream getImageStream(byte[] imageBytes) {
        if (null != imageBytes) {
            ByteArrayInputStream b = new ByteArrayInputStream(imageBytes);
            int byteRead;
            while ((byteRead = b.read()) != -1) {
                System.out.print((char) byteRead);
            }
            return b;
        } else {
            return null;
        }

    }
    
    public String getImageBase64(byte[] imageBytes) {
    if (imageBytes != null && imageBytes.length > 0) {
        return Base64.getEncoder().encodeToString(imageBytes);
    }
    return "";
}

    
}