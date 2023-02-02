package lk.ijse.veggieSystem.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.veggieSystem.db.DBConnection;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;


import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;

public class CustomerReportFormController {
    public static JFXTextField txtOrderItemId;
    public AnchorPane CustomrReportContext;
    //private Object JasperViewer;

    public void btnGenerateReportOnAction(ActionEvent actionEvent) {
        InputStream resource = this.getClass().getResourceAsStream("/lk/ijse/veggieSystem/Report/CustomerReports.jrxml");

       HashMap<String,Object> hm=new HashMap<>();
//        hm.put("Order-Item Id:-",txtOrderItemId.getText());


        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hm, DBConnection.getInstance().getConnection());
            //JasperPrintManager.printReport(jasperPrint,true);
            JasperViewer.viewReport(jasperPrint);

        } catch (JRException |SQLException| ClassNotFoundException e) {
            throw new RuntimeException();
        }

    }
    }

