/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wimaxapp;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;
import wimax.BTSManager;
import static wimax.BTSManager.DataTable;
import wimax.BestEffort;
import wimax.CDMAManager;
import static wimax.CDMAManager.AllUsers;
import static wimax.CDMAManager.SurrendedUsers;
import wimax.Data;
import wimax.NoRealTime;
import wimax.PDFGenerator;
import wimax.RealTime;
import wimax.ResourceManager;
import wimax.Statistics;
import wimax.User;
import static wimax.BTSManager.UsersCollision;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class WimaxController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label label;
    @FXML
    private JFXTextField users;
    @FXML
    private JFXTextField NRRT;
    @FXML
    private JFXTextField NRNRT;
    @FXML
    private JFXTextField NRBE;
    @FXML
    private JFXTextField RTPOOL1;
    @FXML
    private JFXTextField RTPOOL2;
    @FXML
    private JFXTextField NRTPOOL1;
    @FXML
    private JFXTextField NRTPOOL2;
    @FXML
    private JFXTextField BEPOOL1;
    @FXML
    private JFXTextField BEPOOL2;
    @FXML
    private JFXTextField cycle;
    @FXML
    public Button DashboardButton;
    @FXML
    public Button StatisticsButton;
    @FXML
    public Button DataTableButton;
    @FXML
    public Button HelpButton;
    @FXML
    private AnchorPane DashboardPane;
    @FXML
    private AnchorPane StatisticsPane;
    @FXML
    private AnchorPane DataTablePane;
    @FXML
    private AnchorPane HelpPane;
    @FXML
    private JFXButton Run;
    @FXML
    private ProgressIndicator RealTimeProgress;
    @FXML
    private ProgressIndicator NoRealTimeProgress;
    @FXML
    private JFXTextField BandWidth;
    @FXML
    private ProgressIndicator BestEffortProgress;
    private int cycleArg,usersArg,NRRTArg,NRNRTArg,NRBEArg,RTPOOL1Arg,RTPOOL2Arg,BANDWIDTHArg,NRTPOOL1Arg,NRTPOOL2Arg,BEPOOL1Arg,BEPOOL2Arg;
    
    //it's helpful for barchart
    private final static String RealTimeCB = "Real Time";
    private final static String NoRealTimeCB = "No Real Time";
    private final static String BestEffortCB = "Best Effort";
    @FXML
    final CategoryAxis xAxis = new CategoryAxis();
    @FXML
    final NumberAxis yAxis = new NumberAxis();
    @FXML
    private BarChart<String,Number> StatisticsBC = new BarChart<String,Number>(xAxis,yAxis);
    @FXML
    private ScrollPane scrolling;
    @FXML
    private TableView DataTableView = new TableView();
    @FXML
    private TableColumn IDUserColumn;
    @FXML 
    private TableColumn UserClasseColumn;
    @FXML 
    private TableColumn UserResColumn;
    @FXML
    private TableColumn UserStateColumn ;
    @FXML 
    private TitledPane ProgressPane;
    private PDFGenerator PDFG ;
    //this is for saving data list and use it in Datatable
    private final ObservableList<Data> data =
            FXCollections.observableArrayList();
    private Scene homePageScene;
    
    //for parsing the input values 
    public void inputToArg(){
        cycleArg = Integer.parseInt(cycle.getText());
        usersArg = Integer.parseInt(users.getText());
        NRRTArg = Integer.parseInt(NRRT.getText());
        NRNRTArg = Integer.parseInt(NRNRT.getText());
        NRBEArg = Integer.parseInt(NRBE.getText());
        RTPOOL1Arg = Integer.parseInt(RTPOOL1.getText());
        RTPOOL2Arg = Integer.parseInt(RTPOOL2.getText());
        NRTPOOL1Arg = Integer.parseInt(NRTPOOL1.getText());
        NRTPOOL2Arg = Integer.parseInt(NRTPOOL2.getText());
        BEPOOL1Arg = Integer.parseInt(BEPOOL1.getText());
        BEPOOL2Arg = Integer.parseInt(BEPOOL2.getText());
        BANDWIDTHArg = Integer.parseInt(BandWidth.getText());
    }
    
    @FXML
    public void handleButtonAction(ActionEvent event) throws IOException{
      //From the first page to the second
        try{
            Parent homePage = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
            homePageScene = new Scene(homePage);
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.hide();
            appStage.setScene(homePageScene);
            appStage.show();
          }catch(Exception e){
            e.printStackTrace();
          }
        
    }
    //for navigating between pages(Menu)
    public void changePages(ActionEvent event){
       if(event.getSource() == DashboardButton){
                 DashboardPane.toFront();
              /*----------------- Menu Style ------------------------*/
                 DashboardButton.setStyle("-fx-background-color: #B8BBBC;");
                 StatisticsButton.setStyle("-fx-background-color: transparent;");
                 DataTableButton.setStyle("-fx-background-color: transparent;");
                 HelpButton.setStyle("-fx-background-color: transparent;");
        }
        if(event.getSource() == StatisticsButton){
                 StatisticsPane.toFront();
                 scrolling.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
            /*----------------- Menu Style ------------------------*/
                 DashboardButton.setStyle("-fx-background-color: transparent;");
                 StatisticsButton.setStyle("-fx-background-color: #B8BBBC;");
                 DataTableButton.setStyle("-fx-background-color: transparent;");
                 HelpButton.setStyle("-fx-background-color: transparent;");
        }
        if(event.getSource() == DataTableButton){
                 DataTablePane.toFront();
            /*----------------- Menu Style ------------------------*/
                 DashboardButton.setStyle("-fx-background-color: transparent;");
                 StatisticsButton.setStyle("-fx-background-color: transparent;");
                 DataTableButton.setStyle("-fx-background-color: #B8BBBC;");
                 HelpButton.setStyle("-fx-background-color: transparent;");                 
        }
        if(event.getSource() == HelpButton){
                 HelpPane.toFront();
            /*----------------- Menu Style ------------------------*/
                 DashboardButton.setStyle("-fx-background-color: transparent;");
                 StatisticsButton.setStyle("-fx-background-color: transparent;");
                 DataTableButton.setStyle("-fx-background-color: transparent;");
                 HelpButton.setStyle("-fx-background-color: #B8BBBC;");       
        }
    }
    
    @FXML 
    private void handleClose(ActionEvent event){
        System.exit(0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // For initializing the table columns
        IDUserColumn = new TableColumn("User ID");
        UserClasseColumn = new TableColumn("User Classe");
        UserResColumn = new TableColumn("Resource Needed");
        UserStateColumn = new TableColumn("User State");
        
     /*--------------------------------- STYLE IMPROVING -----------------------------------------*/
        IDUserColumn.prefWidthProperty().bind(DataTableView.widthProperty().multiply(0.15));
        IDUserColumn.setResizable(false);
        UserClasseColumn.prefWidthProperty().bind(DataTableView.widthProperty().multiply(0.25));
        UserClasseColumn.setResizable(false);
        UserResColumn.prefWidthProperty().bind(DataTableView.widthProperty().multiply(0.3));
        UserClasseColumn.setResizable(false);
        UserStateColumn.prefWidthProperty().bind(DataTableView.widthProperty().multiply(0.3));
        UserStateColumn.setResizable(false);
        DataTableView.getColumns().addAll(IDUserColumn,UserClasseColumn,UserResColumn,UserStateColumn);
    /*************************************** PDF INSTANTIATION --------------------------------------*/
        PDFG = new PDFGenerator();
    }    
    
 /*--------------------------------This function for running the Wimax algorithme------------------------------*/
    public void runExcusion() throws IOException{
            StatisticsBC.getData().clear();
            inputToArg();
            BTSManager BTS = new BTSManager();
            CDMAManager C = new CDMAManager();
            ResourceManager Res = new ResourceManager();
            RealTime.setNRbe(NRRTArg);
            NoRealTime.setNRbe(NRNRTArg);
            BestEffort.setNRbe(NRBEArg);
            CDMAManager.History = 0;
            CDMAManager.BEUsersNumber=0;
            CDMAManager.RTUsersNumber=0;
            CDMAManager.NRTUsersNumber=0;
            Statistics s = new Statistics();
            int i=0;
    /*---------------- LOOP CYCLE --------------------------------*/
          while(i<cycleArg){
                 C.AllUsersClasse(usersArg,i);
                 C.UsersCDMA(RTPOOL1Arg, RTPOOL2Arg, NRTPOOL1Arg, NRTPOOL2Arg, BEPOOL1Arg, BEPOOL2Arg);
                 BTS.checkCDMA(i,cycleArg);
                  Res.setBandWidth(BANDWIDTHArg);
                 Res.ResourceSpreading();
                 AllUsers.clear();
                 i++;
                  }
    /*------------------------- PROGRESS CERCLE STATISTICS ------------------------------------*/
           RealTimeProgress.setProgress(Statistics.getRealTimePourcentage()*0.01);
           NoRealTimeProgress.setProgress(Statistics.getNoRealTimePourcentage()*0.01);
           BestEffortProgress.setProgress(Statistics.getBestEffortPourcentage()*0.01);
           
    /*--------------------------BAR CHART STATISTICS -------------------------------------------*/
           xAxis.setLabel("Classes");       
           yAxis.setLabel("Pourcentage");
           StatisticsBC.setTitle("Percentage of each class"); 
           XYChart.Series series1 = new XYChart.Series();
           series1.setName("Success");       
           series1.getData().add(new XYChart.Data(RealTimeCB, Statistics.getRealTimeSuccess()));
           series1.getData().add(new XYChart.Data(NoRealTimeCB, Statistics.getNoRealTimeSuccess()));
           series1.getData().add(new XYChart.Data(BestEffortCB, Statistics.getBestEffortSuccess()));
           XYChart.Series series2 = new XYChart.Series();
           series2.setName("Collision");       
           series2.getData().add(new XYChart.Data(RealTimeCB, Statistics.getRealTimeCollusion()));
           series2.getData().add(new XYChart.Data(NoRealTimeCB, Statistics.getNoRealTimeCollusion()));
           series2.getData().add(new XYChart.Data(BestEffortCB, Statistics.getBestEffortCollusion()));
           XYChart.Series series3 = new XYChart.Series();
           series3.setName("Abandoned");       
           series3.getData().add(new XYChart.Data(RealTimeCB, Statistics.getRealTimeSurrended()));
           series3.getData().add(new XYChart.Data(NoRealTimeCB, Statistics.getNoRealTimeSurrended()));
           series3.getData().add(new XYChart.Data(BestEffortCB, Statistics.getBestEffortSurrended()));
           StatisticsBC.getData().addAll(series3,series2,series1);
           StatisticsBC.setAnimated(false);
           StatisticsBC.applyCss();
           StatisticsBC.layout();
    /*------------------------ DATATABLE INSERTIONS ------------------------------------*/
           data.addAll(DataTable);
           IDUserColumn.setCellValueFactory(new PropertyValueFactory<Data,Integer>("IDUser"));
           UserClasseColumn.setCellValueFactory(new PropertyValueFactory<Data,String>("ClasseUser"));
           UserResColumn.setCellValueFactory(new PropertyValueFactory<Data,Integer>("UsedRes"));
           UserStateColumn.setCellValueFactory(new PropertyValueFactory<Data,String>("UserState"));
           DataTableView.setItems(data);
    }
    
    
    public void printingData(){
        try{
    /*--------------------------STATISTIQUE BAR CHART IMAGE--------------------------------------*/
           String statisticsBCImg = "StatisticsBC.png";
           WritableImage img = new WritableImage(800, 400); 
           File file = new File(statisticsBCImg); 
           StatisticsBC.snapshot(new SnapshotParameters(), img);
           RenderedImage renderedImage = SwingFXUtils.fromFXImage(img, null);        
           ImageIO.write(renderedImage,"png", file);
           
    /*--------------------------PROGRESS IMAGE--------------------------------------*/
           String ProgressImg = "ProgressImg.png";
           WritableImage img2 = new WritableImage(970, 400); 
           File file2 = new File(ProgressImg); 
           ProgressPane.snapshot(new SnapshotParameters(), img2);
           RenderedImage renderedImage2 = SwingFXUtils.fromFXImage(img2, null);        
           ImageIO.write(renderedImage2,"png", file2);
    /*------------------------------------------------------------------------------*/
           PDFG.PDFGenerating(DataTable,statisticsBCImg,ProgressImg);
        
        }catch(Exception e){
        
        }
        
    }
   
    public void resetFields(){
        users.clear();
        NRRT.clear();
        NRNRT.clear();
        NRBE.clear();
        RTPOOL1.clear();
        RTPOOL2.clear();
        NRTPOOL1.clear();
        NRTPOOL2.clear();
        BEPOOL1.clear();
        BEPOOL2.clear();
        cycle.clear();
        BandWidth.clear();
        StatisticsBC.getData().clear();
        DataTable.clear();
        DataTableView.getItems().clear();
    }
    
}
