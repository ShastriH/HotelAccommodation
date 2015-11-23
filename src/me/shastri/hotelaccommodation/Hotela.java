/**
 * 
 * Author:              Shastri Harrinanan
 * Version:             1.0.0.1
 * Project Revision:    #014
 * Date Created:        22/10/2015
 * Date Modified:       23/11/2015
 * 
 * Commit Log:
 * #001 22/10/2015 0.0.0.0 - 0.1.0.0: Add initial files
 * #002 23/10/2015 0.1.0.0 - 0.1.1.0: Reorganise the main menu and add missing constructors
 * #003 23/10/2015 0.1.1.0 - 0.2.0.0: Add partial create functionality and refactor the menus
 * #004 23/10/2015 0.2.0.0 - 0.2.1.0: Add partial update functionality
 * #005 23/10/2015 0.2.1.0 - 0.2.2.0: Add partial delete functionality
 * #006 23/10/2015 0.2.2.0 - 0.3.0.0: Change to a persistent database connection
 * #007 24/10/2015 0.3.0.0 - 0.3.1.0: Add complete functionality for more tables
 * #008 25/10/2015 0.3.1.0 - 0.4.0.0: Add complete functionality for the remaining tables
 * #009 26/10/2015 0.4.0.0 - 0.5.0.0: Convert project to GUI
 * #010 26/10/2015 0.5.0.0 - 0.5.0.1: Conduct minor cleaning
 * #011 27/10/2015 0.5.0.1 - 0.5.1.0: Add remaining functionality for one table
 * #012 21/11/2015 0.5.1.0 - 0.6.0.0: Convert the application from a CLI to a GUI version
 * #013 21/11/2015 0.6.0.0 - 1.0.0.0: Complete GUI conversion
 * #014 23/11/2015 1.0.0.0 - 1.0.0.1: Conduct minor cleaning
 * 
 * Current Status: Released.
 * 
 */

package me.shastri.hotelaccommodation;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import me.shastri.hotelaccommodation.beans.*;
import me.shastri.hotelaccommodation.controllers.*;
import me.shastri.hotelaccommodation.util.*;

/**
 *
 * @author Shastri Harrinanan Harrinanan
 */
public class Hotela extends javax.swing.JFrame {
    private static final Connection conn = DBManager.getInstance().getConnection();
    /**
     * Creates new form Hotela
    */
    public Hotela() {
        initComponents();
        initCustomComponents();
    }

    // Method to initialise, where necessary, components created by the student
    private void initCustomComponents(){
        appNameLabel.requestFocusInWindow();
        fillGuestRecordsComboBoxes();
        fillBookingRecordsComboBoxes();
        fillHotelRecordsComboBoxes();
        fillManagerRecordsComboBoxes();
        fillRoomRecordsComboBoxes();
        fillDateComboBoxes();
        fillCreateRoomCapacityComboBox();
    }
    // Method to populate all of the date combo boxes used by the application
    private void fillDateComboBoxes(){
        createBookingArrivalDateDayComboBox.removeAllItems();
        createBookingArrivalDateMonthComboBox.removeAllItems();
        createBookingArrivalDateYearComboBox.removeAllItems();
        createBookingDepartureDateDayComboBox.removeAllItems();
        createBookingDepartureDateMonthComboBox.removeAllItems();
        createBookingDepartureDateYearComboBox.removeAllItems();
        updateBookingArrivalDateDayComboBox.removeAllItems();
        updateBookingArrivalDateMonthComboBox.removeAllItems();
        updateBookingArrivalDateYearComboBox.removeAllItems();
        updateBookingDepartureDateDayComboBox.removeAllItems();
        updateBookingDepartureDateMonthComboBox.removeAllItems();
        updateBookingDepartureDateYearComboBox.removeAllItems();
        for(int i = 1; i <= 31; i++){
            if(i < 10){
                createBookingArrivalDateDayComboBox.addItem("0" + i);
                createBookingDepartureDateDayComboBox.addItem("0" + i);
                updateBookingArrivalDateDayComboBox.addItem("0" + i);
                updateBookingDepartureDateDayComboBox.addItem("0" + i);
            } else {
                createBookingArrivalDateDayComboBox.addItem("" + i);
                createBookingDepartureDateDayComboBox.addItem("" + i);
                updateBookingArrivalDateDayComboBox.addItem("" + i);
                updateBookingDepartureDateDayComboBox.addItem("" + i);
            }
        }
        for(int i = 1; i <= 12; i++){
            if(i < 10){
                createBookingArrivalDateMonthComboBox.addItem("0" + i);
                createBookingDepartureDateMonthComboBox.addItem("0" + i);
                updateBookingArrivalDateMonthComboBox.addItem("0" + i);
                updateBookingDepartureDateMonthComboBox.addItem("0" + i);
            } else {
                createBookingArrivalDateMonthComboBox.addItem("" + i);
                createBookingDepartureDateMonthComboBox.addItem("" + i);
                updateBookingArrivalDateMonthComboBox.addItem("" + i);
                updateBookingDepartureDateMonthComboBox.addItem("" + i);
            }
        }
        for(int i = 2015; i <= 2020; i++){
            createBookingArrivalDateYearComboBox.addItem("" + i);
            createBookingDepartureDateYearComboBox.addItem("" + i);
            updateBookingArrivalDateYearComboBox.addItem("" + i);
            updateBookingDepartureDateYearComboBox.addItem("" + i);
        }
    }
    // Method to populate all of the combo boxes created to display the IDs of all the booking records used by the application
    private void fillBookingRecordsComboBoxes(){
        // Create a Model which contains the IDs of all of the booking records in the database
        DefaultComboBoxModel dcbm = FormFiller.getBookingRecordsComboBox();
        // Set the appropriate combo boxes to that Model
        viewBookingRecordsComboBox.setModel(dcbm);
        updateBookingRecordsComboBox.setModel(dcbm);
        deleteBookingRecordsComboBox.setModel(dcbm);
        // Set the selected index of each combo box to the first item as long as there are booking records in the database
        if(dcbm.getSize() > 0) {
            updateBookingRecordsComboBox.setSelectedIndex(0);
            deleteBookingRecordsComboBox.setSelectedIndex(0);
            viewBookingRecordsComboBox.setSelectedIndex(0);
        }
    }
    // Method to populate all of the combo boxes created to display the IDs of all the guest records used by the application
    private void fillGuestRecordsComboBoxes(){
        // Create a Model which contains the IDs of all of the guest records in the database
        DefaultComboBoxModel dcbm = FormFiller.getGuestRecordsComboBox();
        // Set the appropriate combo boxes to that Model
        createBookingGuestIDComboBox.setModel(dcbm);
        updateBookingGuestIDComboBox.setModel(dcbm);
        viewGuestRecordsComboBox.setModel(dcbm);
        updateGuestRecordsComboBox.setModel(dcbm);
        deleteGuestRecordsComboBox.setModel(dcbm);
        // Set the selected index of each combo box to the first item as long as there are guest records in the database
        if(dcbm.getSize() > 0) {
            createBookingGuestIDComboBox.setSelectedIndex(0);
            updateBookingGuestIDComboBox.setSelectedIndex(0);
            viewGuestRecordsComboBox.setSelectedIndex(0);
            updateGuestRecordsComboBox.setSelectedIndex(0);
            deleteGuestRecordsComboBox.setSelectedIndex(0);
        }  
    }
    // Method to populate all of the combo boxes created to display the IDs of all the hotel records used by the application
    private void fillHotelRecordsComboBoxes(){
        // Create a Model which contains the IDs of all of the hotel records in the database
        DefaultComboBoxModel dcbm = FormFiller.getHotelRecordsComboBox();
        // Set the appropriate combo boxes to that Model
        createManagerHotelIDComboBox.setModel(dcbm);
        viewHotelRecordsComboBox.setModel(dcbm);
        updateHotelRecordsComboBox.setModel(dcbm);
        deleteHotelRecordsComboBox.setModel(dcbm);
        createRoomHotelIDComboxBox.setModel(dcbm);
        // Set the selected index of each combo box to the first item as long as there are hotel records in the database
        if(dcbm.getSize() > 0) {
            createManagerHotelIDComboBox.setSelectedIndex(0);
            viewHotelRecordsComboBox.setSelectedIndex(0);
            updateHotelRecordsComboBox.setSelectedIndex(0);
            deleteHotelRecordsComboBox.setSelectedIndex(0);
            createRoomHotelIDComboxBox.setSelectedIndex(0);
        } 
    }
    // Method to populate all of the combo boxes created to display the IDs of all the manager records used by the application
    private void fillManagerRecordsComboBoxes(){
        // Create a Model which contains the IDs of all of the manager records in the database
        DefaultComboBoxModel dcbm = FormFiller.getManagerRecordsComboBox();
        // Set the appropriate combo boxes to that Model
        viewManagerRecordsComboBox.setModel(dcbm);
        updateManagerRecordsComboBox.setModel(dcbm);
        deleteManagerRecordsComboBox.setModel(dcbm);
        // Set the selected index of each combo box to the first item as long as there are manager records in the database
        if(dcbm.getSize() > 0) {
            viewManagerRecordsComboBox.setSelectedIndex(0);
            updateManagerRecordsComboBox.setSelectedIndex(0);
            deleteManagerRecordsComboBox.setSelectedIndex(0);
        } 
    }
    // Method to populate all of the combo boxes created to display the IDs of all the room records used by the application
    private void fillRoomRecordsComboBoxes(){
        // Create a Model which contains the IDs of all of the room records in the database
        DefaultComboBoxModel dcbm = FormFiller.getRoomRecordsComboBox();
        // Set the appropriate combo boxes to that Model
        viewRoomRecordsComboBox.setModel(dcbm);
        updateRoomRecordsComboBox.setModel(dcbm);
        deleteRoomRecordsComboBox.setModel(dcbm);
        // Create a DefaultListModel which contains the IDs of all of the room records in the database
        DefaultListModel dlm = FormFiller.getRoomRecordsList();
        // Set the appropriate lists to that DefaultListModel
        createBookingRoomList.setModel(dlm);
        updateBookingRoomList.setModel(dlm);
        // Set the lists created to contain selected rooms to empty
        createBookingSelectedRoomList.setModel(new DefaultListModel());
        updateBookingSelectedRoomList.setModel(new DefaultListModel());
        // Set the selected index of each combo box to the first item as long as there are room records in the database
        if(dcbm.getSize() > 0) {
            viewRoomRecordsComboBox.setSelectedIndex(0);
            updateRoomRecordsComboBox.setSelectedIndex(0);
            deleteRoomRecordsComboBox.setSelectedIndex(0);
        }
    }
    // Method to populatethe combo boxes created to display the available capacity options for a room
    private void fillCreateRoomCapacityComboBox(){
        // Clear the defsult set of items
        createRoomCapacityComboBox.removeAllItems();
        // Add the options
        createRoomCapacityComboBox.addItem(1);
        createRoomCapacityComboBox.addItem(2);
        createRoomCapacityComboBox.addItem(4);
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        homeContainer = new javax.swing.JPanel();
        appNameLabel = new javax.swing.JLabel();
        bylineLabel = new javax.swing.JLabel();
        viewRecordButton = new javax.swing.JButton();
        addRecordButton = new javax.swing.JButton();
        updateRecordButton = new javax.swing.JButton();
        DeleteRecordButton = new javax.swing.JButton();
        homeContainerLabel = new javax.swing.JLabel();
        createContainer = new javax.swing.JPanel();
        createContainerLabel = new javax.swing.JLabel();
        createBookingsButton = new javax.swing.JButton();
        createGuestsButton = new javax.swing.JButton();
        createHotelsButton = new javax.swing.JButton();
        createManagersButton = new javax.swing.JButton();
        createRoomsButton = new javax.swing.JButton();
        createContainerMainMenuButton = new javax.swing.JButton();
        createBookingContainer = new javax.swing.JPanel();
        createBookingContainerLabel = new javax.swing.JLabel();
        createBookingContainerInstructionsLabel = new javax.swing.JLabel();
        createBookingGuestIDLabel = new javax.swing.JLabel();
        createBookingGuestIDComboBox = new javax.swing.JComboBox();
        createBookingRoomIDLabel = new javax.swing.JLabel();
        createBookingButton = new javax.swing.JButton();
        createBookingRoomScrollPane = new javax.swing.JScrollPane();
        createBookingRoomList = new javax.swing.JList();
        createBookingRoomAddButton = new javax.swing.JButton();
        createBookingRoomRemoveButton = new javax.swing.JButton();
        createBookingSelectedRoomScrollPane = new javax.swing.JScrollPane();
        createBookingSelectedRoomList = new javax.swing.JList();
        createBookingArrivalTimeLabel = new javax.swing.JLabel();
        createBookingArrivalDateDayComboBox = new javax.swing.JComboBox();
        createBookingArrivalDateMonthComboBox = new javax.swing.JComboBox();
        createBookingArrivalDateYearComboBox = new javax.swing.JComboBox();
        createBookingDepartureTimeLabel = new javax.swing.JLabel();
        createBookingDepartureDateDayComboBox = new javax.swing.JComboBox();
        createBookingDepartureDateMonthComboBox = new javax.swing.JComboBox();
        createBookingDepartureDateYearComboBox = new javax.swing.JComboBox();
        createBookingContainerMainMenuButton = new javax.swing.JButton();
        createBookingContainerCreateMenuButton = new javax.swing.JButton();
        createGuestContainer = new javax.swing.JPanel();
        createGuestContainerLabel = new javax.swing.JLabel();
        createGuestContainerInstructionsLabel = new javax.swing.JLabel();
        createGuestTitleLabel = new javax.swing.JLabel();
        createGuestTitleField = new javax.swing.JTextField();
        createGuestLastNameLabel = new javax.swing.JLabel();
        createGuestLastNameField = new javax.swing.JTextField();
        createGuestFirstNameLabel = new javax.swing.JLabel();
        createGuestFirstNameField = new javax.swing.JTextField();
        createGuestAddressLabel = new javax.swing.JLabel();
        createGuestAddressField = new javax.swing.JTextField();
        createGuestEmailLabel = new javax.swing.JLabel();
        createGuestEmailField = new javax.swing.JTextField();
        createGuestPhoneLabel = new javax.swing.JLabel();
        createGuestPhoneField = new javax.swing.JTextField();
        createGuestButton = new javax.swing.JButton();
        createGuestContainerMainMenuButton = new javax.swing.JButton();
        createGuestContainerCreateMenuButton = new javax.swing.JButton();
        createHotelContainer = new javax.swing.JPanel();
        createHotelContainerLabel = new javax.swing.JLabel();
        createHotelContainerInstructionsLabel = new javax.swing.JLabel();
        createHotelAddressLabel = new javax.swing.JLabel();
        createHotelAddressField = new javax.swing.JTextField();
        createHotelButton = new javax.swing.JButton();
        createHotelContainerMainMenuButton = new javax.swing.JButton();
        createHotelContainerCreateMenuButton = new javax.swing.JButton();
        createManagerContainer = new javax.swing.JPanel();
        createManagerContainerLabel = new javax.swing.JLabel();
        createManagerContainerInstructionsLabel = new javax.swing.JLabel();
        createManagerLastNameField = new javax.swing.JTextField();
        createManagerFirstNameLabel = new javax.swing.JLabel();
        createManagerFirstNameField = new javax.swing.JTextField();
        createManagerEmailLabel = new javax.swing.JLabel();
        createManagerEmailField = new javax.swing.JTextField();
        createManagerPhoneLabel = new javax.swing.JLabel();
        createManagerPhoneField = new javax.swing.JTextField();
        createManagerHotelIDLabel = new javax.swing.JLabel();
        createManagerHotelIDComboBox = new javax.swing.JComboBox();
        createManagerButton = new javax.swing.JButton();
        createManagerContainerMainMenuButton = new javax.swing.JButton();
        createManagerContainerCreateMenuButton = new javax.swing.JButton();
        createGuestLastNameLabel1 = new javax.swing.JLabel();
        createRoomContainer = new javax.swing.JPanel();
        createRoomContainerLabel = new javax.swing.JLabel();
        createRoomContainerInstructionsLabel = new javax.swing.JLabel();
        createRoomPriceLabel = new javax.swing.JLabel();
        createRoomPriceField = new javax.swing.JTextField();
        createRoomCapacityLabel = new javax.swing.JLabel();
        createRoomCapacityComboBox = new javax.swing.JComboBox();
        createRoomSeaViewLabel = new javax.swing.JLabel();
        createRoomSeaViewCheckBox = new javax.swing.JCheckBox();
        createRoomHotelIDLabel = new javax.swing.JLabel();
        createRoomHotelIDComboxBox = new javax.swing.JComboBox();
        createRoomButton = new javax.swing.JButton();
        createRoomContainerMainMenuButton = new javax.swing.JButton();
        createRoomContainerCreateMenuButton = new javax.swing.JButton();
        viewContainer = new javax.swing.JPanel();
        viewContainerLabel = new javax.swing.JLabel();
        viewBookingsButton = new javax.swing.JButton();
        viewGuestsButton = new javax.swing.JButton();
        viewHotelsButton = new javax.swing.JButton();
        viewManagersButton = new javax.swing.JButton();
        viewRoomsButton = new javax.swing.JButton();
        viewContainerMainMenuButton = new javax.swing.JButton();
        viewBookingContainer = new javax.swing.JPanel();
        viewBookingContainerLabel = new javax.swing.JLabel();
        viewBookingContainerInstructionsLabel = new javax.swing.JLabel();
        viewBookingRecordsComboBox = new javax.swing.JComboBox();
        viewBookingGuestLabel = new javax.swing.JLabel();
        viewBookingGuestValueLabel = new javax.swing.JLabel();
        viewBookingRoomsLabel = new javax.swing.JLabel();
        viewBookingRoomsValueLabel = new javax.swing.JLabel();
        viewBookingArrivalDateLabel = new javax.swing.JLabel();
        viewBookingArrivalDateValueLabel = new javax.swing.JLabel();
        viewBookingDepartureDateLabel = new javax.swing.JLabel();
        viewBookingDepartureDateValueLabel = new javax.swing.JLabel();
        viewBookingContainerMainMenuButton = new javax.swing.JButton();
        viewBookingContainerViewMenuButton = new javax.swing.JButton();
        viewGuestContainer = new javax.swing.JPanel();
        viewGuestContainerLabel = new javax.swing.JLabel();
        viewGuestRecordsComboBox = new javax.swing.JComboBox();
        viewGuestTitleLabel = new javax.swing.JLabel();
        viewGuestNameLabel = new javax.swing.JLabel();
        viewGuestAddressLabel = new javax.swing.JLabel();
        viewGuestEmailLabel = new javax.swing.JLabel();
        viewGuestPhoneLabel = new javax.swing.JLabel();
        viewGuestContainerInstructionsLabel = new javax.swing.JLabel();
        viewGuestContainerMainMenuButton = new javax.swing.JButton();
        viewGuestContainerViewMenuButton = new javax.swing.JButton();
        viewHotelContainer = new javax.swing.JPanel();
        viewHotelContainerLabel = new javax.swing.JLabel();
        viewHotelRecordsComboBox = new javax.swing.JComboBox();
        viewHotelAddressLabel = new javax.swing.JLabel();
        viewHotelContainerInstructionsLabel = new javax.swing.JLabel();
        viewHotelContainerMainMenuButton = new javax.swing.JButton();
        viewHotelContainerViewMenuButton = new javax.swing.JButton();
        viewManagerContainer = new javax.swing.JPanel();
        viewManagerContainerLabel = new javax.swing.JLabel();
        viewManagerRecordsComboBox = new javax.swing.JComboBox();
        viewManagerNameLabel = new javax.swing.JLabel();
        viewManagerEmailLabel = new javax.swing.JLabel();
        viewManagerPhoneLabel = new javax.swing.JLabel();
        viewManagerHotelIDLabel = new javax.swing.JLabel();
        viewManagerContainerInstructionsLabel = new javax.swing.JLabel();
        viewManagerContainerMainMenuButton = new javax.swing.JButton();
        viewManagerContainerViewMenuButton = new javax.swing.JButton();
        viewRoomContainer = new javax.swing.JPanel();
        viewRoomContainerLabel = new javax.swing.JLabel();
        viewRoomContainerInstructionsLabel = new javax.swing.JLabel();
        viewRoomRecordsComboBox = new javax.swing.JComboBox();
        viewRoomPriceLabel = new javax.swing.JLabel();
        viewRoomCapacityLabel = new javax.swing.JLabel();
        viewRoomSeaViewLabel = new javax.swing.JLabel();
        viewRoomHotelIDLabel = new javax.swing.JLabel();
        viewRoomContainerMainMenuButton = new javax.swing.JButton();
        viewRoomContainerViewMenuButton = new javax.swing.JButton();
        updateContainer = new javax.swing.JPanel();
        updateContainerLabel = new javax.swing.JLabel();
        updateBookingsButton = new javax.swing.JButton();
        updateGuestsButton = new javax.swing.JButton();
        updateHotelsButton = new javax.swing.JButton();
        updateManagersButton = new javax.swing.JButton();
        updateRoomsButton = new javax.swing.JButton();
        updateContainerMainMenuButton = new javax.swing.JButton();
        updateBookingContainer = new javax.swing.JPanel();
        updateBookingContainerLabel = new javax.swing.JLabel();
        updateBookingContainerInstructionsLabel = new javax.swing.JLabel();
        updateBookingRecordsComboBox = new javax.swing.JComboBox();
        updateBookingGuestLabel = new javax.swing.JLabel();
        updateBookingGuestIDComboBox = new javax.swing.JComboBox();
        updateBookingRoomIDLabel = new javax.swing.JLabel();
        updateBookingRoomListLabel = new javax.swing.JLabel();
        updateBookingRoomScrollPane = new javax.swing.JScrollPane();
        updateBookingRoomList = new javax.swing.JList();
        updateBookingRoomRemoveButton = new javax.swing.JButton();
        updateBookingRoomAddButton = new javax.swing.JButton();
        updateBookingSelectedRoomListLabel = new javax.swing.JLabel();
        updateBookingSelectedRoomScrollPane = new javax.swing.JScrollPane();
        updateBookingSelectedRoomList = new javax.swing.JList();
        updateBookingArrivalTimeLabel = new javax.swing.JLabel();
        updateBookingArrivalDateDayComboBox = new javax.swing.JComboBox();
        updateBookingArrivalDateMonthComboBox = new javax.swing.JComboBox();
        updateBookingArrivalDateYearComboBox = new javax.swing.JComboBox();
        updateBookingDepartureTimeLabel = new javax.swing.JLabel();
        updateBookingDepartureDateYearComboBox = new javax.swing.JComboBox();
        updateBookingDepartureDateMonthComboBox = new javax.swing.JComboBox();
        updateBookingDepartureDateDayComboBox = new javax.swing.JComboBox();
        updateBookingButton = new javax.swing.JButton();
        updateBookingContainerMainMenuButton = new javax.swing.JButton();
        updateBookingContainerUpdateMenuButton = new javax.swing.JButton();
        updateBookingGuestNameLabel = new javax.swing.JLabel();
        updateGuestContainer = new javax.swing.JPanel();
        updateGuestContainerLabel = new javax.swing.JLabel();
        updateGuestRecordsComboBox = new javax.swing.JComboBox();
        updateGuestContainerInstructionsLabel = new javax.swing.JLabel();
        updateGuestTitleField = new javax.swing.JTextField();
        updateGuestLastNameField = new javax.swing.JTextField();
        updateGuestFirstNameField = new javax.swing.JTextField();
        updateGuestAddressField = new javax.swing.JTextField();
        updateGuestEmailField = new javax.swing.JTextField();
        updateGuestPhoneField = new javax.swing.JTextField();
        updateGuestButton = new javax.swing.JButton();
        updateGuestContainerMainMenuButton = new javax.swing.JButton();
        updateGuestContainerUpdateMenuButton = new javax.swing.JButton();
        updateHotelContainer = new javax.swing.JPanel();
        updateHotelContainerLabel = new javax.swing.JLabel();
        updateHotelRecordsComboBox = new javax.swing.JComboBox();
        updateHotelContainerInstructionsLabel = new javax.swing.JLabel();
        updateHotelContainerMainMenuButton = new javax.swing.JButton();
        updateHotelContainerUpdateMenuButton = new javax.swing.JButton();
        updateHotelAddressField = new javax.swing.JTextField();
        updateHotelButton = new javax.swing.JButton();
        updateManagerContainer = new javax.swing.JPanel();
        updateManagerContainerLabel = new javax.swing.JLabel();
        updateManagerRecordsComboBox = new javax.swing.JComboBox();
        updateManagerContainerInstructionsLabel = new javax.swing.JLabel();
        updateManagerLastNameLabel = new javax.swing.JLabel();
        updateManagerLastNameField = new javax.swing.JTextField();
        updateManagerFirstNameLabel = new javax.swing.JLabel();
        updateManagerFirstNameField = new javax.swing.JTextField();
        updateManagerEmailLabel = new javax.swing.JLabel();
        updateManagerEmailField = new javax.swing.JTextField();
        updateManagerPhoneLabel = new javax.swing.JLabel();
        updateManagerPhoneField = new javax.swing.JTextField();
        updateManagerHotelIDLabel = new javax.swing.JLabel();
        updateManagerHotelIDField = new javax.swing.JTextField();
        updateManagerButton = new javax.swing.JButton();
        updateManagerContainerMainMenuButton = new javax.swing.JButton();
        updateManagerContainerUpdateMenuButton = new javax.swing.JButton();
        updateRoomContainer = new javax.swing.JPanel();
        updateRoomContainerLabel = new javax.swing.JLabel();
        updateRoomRecordsComboBox = new javax.swing.JComboBox();
        updateRoomContainerInstructionsLabel = new javax.swing.JLabel();
        updateRoomContainerMainMenuButton = new javax.swing.JButton();
        updateRoomContainerUpdateMenuButton = new javax.swing.JButton();
        updateRoomEuroLabel = new javax.swing.JLabel();
        updateRoomPriceField = new javax.swing.JTextField();
        updateRoomCapacityField = new javax.swing.JTextField();
        updateRoomSeaViewField = new javax.swing.JTextField();
        updateRoomHotelIDField = new javax.swing.JTextField();
        updateRoomButton = new javax.swing.JButton();
        deleteContainer = new javax.swing.JPanel();
        deleteContainerLabel = new javax.swing.JLabel();
        deleteBookingsButton = new javax.swing.JButton();
        deleteGuestsButton = new javax.swing.JButton();
        deleteHotelsButton = new javax.swing.JButton();
        deleteManagersButton = new javax.swing.JButton();
        deleteRoomsButton = new javax.swing.JButton();
        deleteContainerMainMenuButton = new javax.swing.JButton();
        deleteBookingContainer = new javax.swing.JPanel();
        deleteBookingContainerLabel = new javax.swing.JLabel();
        deleteBookingContainerInstructionsLabel = new javax.swing.JLabel();
        deleteBookingRecordsComboBox = new javax.swing.JComboBox();
        deleteBookingGuestLabel = new javax.swing.JLabel();
        deleteBookingGuestValueLabel = new javax.swing.JLabel();
        deleteBookingRoomsLabel = new javax.swing.JLabel();
        deleteBookingRoomsValueLabel = new javax.swing.JLabel();
        deleteBookingArrivalDateLabel = new javax.swing.JLabel();
        deleteBookingArrivalDateValueLabel = new javax.swing.JLabel();
        deleteBookingDepartureDateLabel = new javax.swing.JLabel();
        deleteBookingDepartureDateValueLabel = new javax.swing.JLabel();
        deleteBookingButton = new javax.swing.JButton();
        deleteBookingContainerMainMenuButton = new javax.swing.JButton();
        deleteBookingContainerDeleteMenuButton = new javax.swing.JButton();
        deleteGuestContainer = new javax.swing.JPanel();
        deleteGuestContainerLabel = new javax.swing.JLabel();
        deleteGuestRecordsComboBox = new javax.swing.JComboBox();
        deleteGuestTitleLabel = new javax.swing.JLabel();
        deleteGuestNameLabel = new javax.swing.JLabel();
        deleteGuestAddressLabel = new javax.swing.JLabel();
        deleteGuestEmailLabel = new javax.swing.JLabel();
        deleteGuestPhoneLabel = new javax.swing.JLabel();
        deleteGuestContainerInstructionsLabel = new javax.swing.JLabel();
        deleteGuestButton = new javax.swing.JButton();
        deleteGuestContainerMainMenuButton = new javax.swing.JButton();
        deleteGuestContainerDeleteMenuButton = new javax.swing.JButton();
        deleteHotelContainer = new javax.swing.JPanel();
        deleteHotelContainerLabel = new javax.swing.JLabel();
        deleteHotelContainerInstructionsLabel = new javax.swing.JLabel();
        deleteHotelRecordsComboBox = new javax.swing.JComboBox();
        deleteHotelAddressLabel = new javax.swing.JLabel();
        deleteHotelButton = new javax.swing.JButton();
        deleteHotelContainerMainMenuButton = new javax.swing.JButton();
        deleteHotelContainerDeleteMenuButton = new javax.swing.JButton();
        deleteManagerContainer = new javax.swing.JPanel();
        deleteManagerContainerLabel = new javax.swing.JLabel();
        deleteManagerRecordsComboBox = new javax.swing.JComboBox();
        deleteManagerNameLabel = new javax.swing.JLabel();
        deleteManagerEmailLabel = new javax.swing.JLabel();
        deleteManagerPhoneLabel = new javax.swing.JLabel();
        deleteManagerHotelIDLabel = new javax.swing.JLabel();
        deleteManagerContainerInstructionsLabel = new javax.swing.JLabel();
        deleteManagerButton = new javax.swing.JButton();
        deleteManagerContainerMainMenuButton = new javax.swing.JButton();
        deleteManagerContainerDeleteMenuButton = new javax.swing.JButton();
        deleteRoomContainer = new javax.swing.JPanel();
        deleteRoomContainerLabel = new javax.swing.JLabel();
        deleteRoomContainerInstructionsLabel = new javax.swing.JLabel();
        deleteRoomRecordsComboBox = new javax.swing.JComboBox();
        deleteRoomPriceLabel = new javax.swing.JLabel();
        deleteRoomCapacityLabel = new javax.swing.JLabel();
        deleteRoomSeaViewLabel = new javax.swing.JLabel();
        deleteRoomHotelIDLabel = new javax.swing.JLabel();
        deleteRoomButton = new javax.swing.JButton();
        deleteRoomContainerMainMenuButton = new javax.swing.JButton();
        deleteRoomContainerDeleteMenuButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hotela");
        getContentPane().setLayout(new java.awt.CardLayout());

        homeContainer.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        homeContainer.setMinimumSize(new java.awt.Dimension(1280, 720));
        homeContainer.setPreferredSize(new java.awt.Dimension(1280, 720));

        appNameLabel.setFont(new java.awt.Font("Open Sans", 1, 36)); // NOI18N
        appNameLabel.setText("Hotela");

        bylineLabel.setText(" by Shastri Harrinanan - N00147655");

        viewRecordButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        viewRecordButton.setText("View");
        viewRecordButton.setPreferredSize(new java.awt.Dimension(150, 40));
        viewRecordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewRecordButtonActionPerformed(evt);
            }
        });

        addRecordButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        addRecordButton.setText("Add");
        addRecordButton.setPreferredSize(new java.awt.Dimension(150, 40));
        addRecordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRecordButtonActionPerformed(evt);
            }
        });

        updateRecordButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        updateRecordButton.setText("Modify");
        updateRecordButton.setPreferredSize(new java.awt.Dimension(150, 40));
        updateRecordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateRecordButtonActionPerformed(evt);
            }
        });

        DeleteRecordButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        DeleteRecordButton.setText("Delete");
        DeleteRecordButton.setPreferredSize(new java.awt.Dimension(150, 40));
        DeleteRecordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteRecordButtonActionPerformed(evt);
            }
        });

        homeContainerLabel.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        homeContainerLabel.setText("Select one of the following operations:");

        javax.swing.GroupLayout homeContainerLayout = new javax.swing.GroupLayout(homeContainer);
        homeContainer.setLayout(homeContainerLayout);
        homeContainerLayout.setHorizontalGroup(
            homeContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homeContainerLayout.createSequentialGroup()
                .addGroup(homeContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(homeContainerLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(homeContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(appNameLabel)
                            .addComponent(bylineLabel)))
                    .addGroup(homeContainerLayout.createSequentialGroup()
                        .addGap(443, 443, 443)
                        .addGroup(homeContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(homeContainerLayout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addGroup(homeContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(updateRecordButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(DeleteRecordButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(viewRecordButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(homeContainerLabel)
                            .addGroup(homeContainerLayout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(addRecordButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(603, Short.MAX_VALUE))
        );
        homeContainerLayout.setVerticalGroup(
            homeContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homeContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(appNameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bylineLabel)
                .addGap(37, 37, 37)
                .addComponent(homeContainerLabel)
                .addGap(72, 72, 72)
                .addComponent(addRecordButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(viewRecordButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(updateRecordButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(DeleteRecordButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(327, Short.MAX_VALUE))
        );

        getContentPane().add(homeContainer, "card2");

        createContainerLabel.setFont(new java.awt.Font("Open Sans", 1, 36)); // NOI18N
        createContainerLabel.setText("Create Menu");

        createBookingsButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        createBookingsButton.setText("Bookings");
        createBookingsButton.setPreferredSize(new java.awt.Dimension(150, 40));
        createBookingsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createBookingsButtonActionPerformed(evt);
            }
        });

        createGuestsButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        createGuestsButton.setText("Guests");
        createGuestsButton.setPreferredSize(new java.awt.Dimension(150, 40));
        createGuestsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createGuestsButtonActionPerformed(evt);
            }
        });

        createHotelsButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        createHotelsButton.setText("Hotels");
        createHotelsButton.setPreferredSize(new java.awt.Dimension(150, 40));
        createHotelsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createHotelsButtonActionPerformed(evt);
            }
        });

        createManagersButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        createManagersButton.setText("Managers");
        createManagersButton.setPreferredSize(new java.awt.Dimension(150, 40));
        createManagersButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createManagersButtonActionPerformed(evt);
            }
        });

        createRoomsButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        createRoomsButton.setText("Rooms");
        createRoomsButton.setPreferredSize(new java.awt.Dimension(150, 40));
        createRoomsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createRoomsButtonActionPerformed(evt);
            }
        });

        createContainerMainMenuButton.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        createContainerMainMenuButton.setText("Return to Main Menu");
        createContainerMainMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createContainerMainMenuButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout createContainerLayout = new javax.swing.GroupLayout(createContainer);
        createContainer.setLayout(createContainerLayout);
        createContainerLayout.setHorizontalGroup(
            createContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createContainerLayout.createSequentialGroup()
                .addGap(451, 451, 451)
                .addGroup(createContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(createRoomsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createHotelsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createGuestsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createBookingsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createContainerLabel)
                    .addComponent(createManagersButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createContainerMainMenuButton))
                .addContainerGap(613, Short.MAX_VALUE))
        );
        createContainerLayout.setVerticalGroup(
            createContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createContainerLayout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addComponent(createContainerLabel)
                .addGap(61, 61, 61)
                .addComponent(createBookingsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(createGuestsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(createHotelsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(createManagersButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(createRoomsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(createContainerMainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(190, Short.MAX_VALUE))
        );

        getContentPane().add(createContainer, "card3");

        createBookingContainerLabel.setFont(new java.awt.Font("Open Sans", 1, 36)); // NOI18N
        createBookingContainerLabel.setText("Add Bookings");

        createBookingContainerInstructionsLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        createBookingContainerInstructionsLabel.setText("Add a booking by entering its details below:");

        createBookingGuestIDLabel.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        createBookingGuestIDLabel.setText("Guest");

        createBookingGuestIDComboBox.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        createBookingGuestIDComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        createBookingRoomIDLabel.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        createBookingRoomIDLabel.setText("Rooms");

        createBookingButton.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        createBookingButton.setText("Create Booking");
        createBookingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createBookingButtonActionPerformed(evt);
            }
        });

        createBookingRoomList.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        createBookingRoomList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        createBookingRoomScrollPane.setViewportView(createBookingRoomList);

        createBookingRoomAddButton.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        createBookingRoomAddButton.setText("Add");
        createBookingRoomAddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createBookingRoomAddButtonActionPerformed(evt);
            }
        });

        createBookingRoomRemoveButton.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        createBookingRoomRemoveButton.setText("Remove");
        createBookingRoomRemoveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createBookingRoomRemoveButtonActionPerformed(evt);
            }
        });

        createBookingSelectedRoomList.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        createBookingSelectedRoomList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        createBookingSelectedRoomScrollPane.setViewportView(createBookingSelectedRoomList);

        createBookingArrivalTimeLabel.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        createBookingArrivalTimeLabel.setText("Arrival Date");

        createBookingArrivalDateDayComboBox.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        createBookingArrivalDateDayComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        createBookingArrivalDateMonthComboBox.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        createBookingArrivalDateMonthComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        createBookingArrivalDateYearComboBox.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        createBookingArrivalDateYearComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        createBookingDepartureTimeLabel.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        createBookingDepartureTimeLabel.setText("Departure Date");

        createBookingDepartureDateDayComboBox.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        createBookingDepartureDateDayComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        createBookingDepartureDateMonthComboBox.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        createBookingDepartureDateMonthComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        createBookingDepartureDateYearComboBox.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        createBookingDepartureDateYearComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        createBookingContainerMainMenuButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        createBookingContainerMainMenuButton.setText("Return to Main Menu");
        createBookingContainerMainMenuButton.setPreferredSize(new java.awt.Dimension(150, 40));
        createBookingContainerMainMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createBookingContainerMainMenuButtonActionPerformed(evt);
            }
        });

        createBookingContainerCreateMenuButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        createBookingContainerCreateMenuButton.setText("Return to Create Menu");
        createBookingContainerCreateMenuButton.setPreferredSize(new java.awt.Dimension(150, 40));
        createBookingContainerCreateMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createBookingContainerCreateMenuButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout createBookingContainerLayout = new javax.swing.GroupLayout(createBookingContainer);
        createBookingContainer.setLayout(createBookingContainerLayout);
        createBookingContainerLayout.setHorizontalGroup(
            createBookingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createBookingContainerLayout.createSequentialGroup()
                .addGap(178, 178, 178)
                .addGroup(createBookingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(createBookingContainerLayout.createSequentialGroup()
                        .addGroup(createBookingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(createBookingArrivalTimeLabel)
                            .addComponent(createBookingDepartureTimeLabel)
                            .addComponent(createBookingGuestIDLabel)
                            .addComponent(createBookingRoomIDLabel))
                        .addGap(25, 25, 25)
                        .addGroup(createBookingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(createBookingGuestIDComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(createBookingButton)
                            .addGroup(createBookingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(createBookingContainerLayout.createSequentialGroup()
                                    .addComponent(createBookingDepartureDateDayComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(createBookingDepartureDateMonthComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(createBookingDepartureDateYearComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(createBookingContainerLayout.createSequentialGroup()
                                    .addComponent(createBookingArrivalDateDayComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(createBookingArrivalDateMonthComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(createBookingArrivalDateYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(createBookingContainerLayout.createSequentialGroup()
                                .addComponent(createBookingRoomScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(createBookingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(createBookingRoomAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(createBookingRoomRemoveButton))
                                .addGap(18, 18, 18)
                                .addComponent(createBookingSelectedRoomScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(createBookingContainerInstructionsLabel)
                    .addComponent(createBookingContainerLabel)
                    .addGroup(createBookingContainerLayout.createSequentialGroup()
                        .addComponent(createBookingContainerMainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(createBookingContainerCreateMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(705, 705, 705))
        );
        createBookingContainerLayout.setVerticalGroup(
            createBookingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createBookingContainerLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(createBookingContainerLabel)
                .addGap(43, 43, 43)
                .addComponent(createBookingContainerInstructionsLabel)
                .addGap(36, 36, 36)
                .addGroup(createBookingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createBookingGuestIDLabel)
                    .addComponent(createBookingGuestIDComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(createBookingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(createBookingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(createBookingRoomIDLabel)
                        .addComponent(createBookingRoomScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(createBookingContainerLayout.createSequentialGroup()
                            .addComponent(createBookingRoomAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(createBookingRoomRemoveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(createBookingSelectedRoomScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(createBookingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(createBookingContainerLayout.createSequentialGroup()
                        .addComponent(createBookingArrivalTimeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(createBookingDepartureTimeLabel))
                    .addGroup(createBookingContainerLayout.createSequentialGroup()
                        .addGroup(createBookingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(createBookingArrivalDateDayComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(createBookingArrivalDateMonthComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(createBookingArrivalDateYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(createBookingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(createBookingDepartureDateDayComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(createBookingDepartureDateMonthComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(createBookingDepartureDateYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(32, 32, 32)
                .addComponent(createBookingButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                .addGroup(createBookingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createBookingContainerCreateMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createBookingContainerMainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(132, 132, 132))
        );

        getContentPane().add(createBookingContainer, "card4");

        createGuestContainerLabel.setFont(new java.awt.Font("Open Sans", 1, 36)); // NOI18N
        createGuestContainerLabel.setText("Add Guests");

        createGuestContainerInstructionsLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        createGuestContainerInstructionsLabel.setText("Add a guest by entering its details below:");

        createGuestTitleLabel.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        createGuestTitleLabel.setText("Title");

        createGuestTitleField.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        createGuestLastNameLabel.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        createGuestLastNameLabel.setText("Last name");

        createGuestLastNameField.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        createGuestFirstNameLabel.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        createGuestFirstNameLabel.setText("First name");

        createGuestFirstNameField.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        createGuestAddressLabel.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        createGuestAddressLabel.setText("Address");

        createGuestAddressField.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        createGuestEmailLabel.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        createGuestEmailLabel.setText("Email");

        createGuestEmailField.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        createGuestPhoneLabel.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        createGuestPhoneLabel.setText("Phone");

        createGuestPhoneField.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        createGuestButton.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        createGuestButton.setText("Add Guest");
        createGuestButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createGuestButtonActionPerformed(evt);
            }
        });

        createGuestContainerMainMenuButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        createGuestContainerMainMenuButton.setText("Return to Main Menu");
        createGuestContainerMainMenuButton.setPreferredSize(new java.awt.Dimension(150, 40));
        createGuestContainerMainMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createGuestContainerMainMenuButtonActionPerformed(evt);
            }
        });

        createGuestContainerCreateMenuButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        createGuestContainerCreateMenuButton.setText("Return to Create Menu");
        createGuestContainerCreateMenuButton.setPreferredSize(new java.awt.Dimension(150, 40));
        createGuestContainerCreateMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createGuestContainerCreateMenuButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout createGuestContainerLayout = new javax.swing.GroupLayout(createGuestContainer);
        createGuestContainer.setLayout(createGuestContainerLayout);
        createGuestContainerLayout.setHorizontalGroup(
            createGuestContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createGuestContainerLayout.createSequentialGroup()
                .addGap(178, 178, 178)
                .addGroup(createGuestContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(createGuestContainerLayout.createSequentialGroup()
                        .addGroup(createGuestContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(createGuestLastNameLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(createGuestTitleLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(createGuestFirstNameLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(createGuestEmailLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(createGuestAddressLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(createGuestPhoneLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(createGuestContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(createGuestContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, createGuestContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(createGuestAddressField, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(createGuestFirstNameField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, createGuestContainerLayout.createSequentialGroup()
                                    .addComponent(createGuestPhoneField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(250, 250, 250)))
                            .addGroup(createGuestContainerLayout.createSequentialGroup()
                                .addGroup(createGuestContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(createGuestButton)
                                    .addComponent(createGuestLastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(createGuestTitleField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(createGuestEmailField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(250, 250, 250))))
                    .addComponent(createGuestContainerInstructionsLabel)
                    .addComponent(createGuestContainerLabel)
                    .addGroup(createGuestContainerLayout.createSequentialGroup()
                        .addComponent(createGuestContainerMainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(createGuestContainerCreateMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(536, Short.MAX_VALUE))
        );
        createGuestContainerLayout.setVerticalGroup(
            createGuestContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createGuestContainerLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(createGuestContainerLabel)
                .addGap(43, 43, 43)
                .addComponent(createGuestContainerInstructionsLabel)
                .addGap(58, 58, 58)
                .addGroup(createGuestContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createGuestTitleField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createGuestTitleLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(createGuestContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createGuestLastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createGuestLastNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(createGuestContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createGuestFirstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createGuestFirstNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(createGuestContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createGuestAddressField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createGuestAddressLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(createGuestContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createGuestEmailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createGuestEmailLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(createGuestContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createGuestPhoneField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createGuestPhoneLabel))
                .addGap(24, 24, 24)
                .addComponent(createGuestButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addGroup(createGuestContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createGuestContainerCreateMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createGuestContainerMainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(164, Short.MAX_VALUE))
        );

        getContentPane().add(createGuestContainer, "card4");

        createHotelContainer.setMinimumSize(new java.awt.Dimension(1280, 720));

        createHotelContainerLabel.setFont(new java.awt.Font("Open Sans", 1, 36)); // NOI18N
        createHotelContainerLabel.setText("Add Hotels");

        createHotelContainerInstructionsLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        createHotelContainerInstructionsLabel.setText("Add a hotel by entering its details below:");

        createHotelAddressLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        createHotelAddressLabel.setText("Hotel Address:");

        createHotelAddressField.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        createHotelButton.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        createHotelButton.setText("Add Hotel");
        createHotelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createHotelButtonActionPerformed(evt);
            }
        });

        createHotelContainerMainMenuButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        createHotelContainerMainMenuButton.setText("Return to Main Menu");
        createHotelContainerMainMenuButton.setPreferredSize(new java.awt.Dimension(150, 40));
        createHotelContainerMainMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createHotelContainerMainMenuButtonActionPerformed(evt);
            }
        });

        createHotelContainerCreateMenuButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        createHotelContainerCreateMenuButton.setText("Return to Create Menu");
        createHotelContainerCreateMenuButton.setPreferredSize(new java.awt.Dimension(150, 40));
        createHotelContainerCreateMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createHotelContainerCreateMenuButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout createHotelContainerLayout = new javax.swing.GroupLayout(createHotelContainer);
        createHotelContainer.setLayout(createHotelContainerLayout);
        createHotelContainerLayout.setHorizontalGroup(
            createHotelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createHotelContainerLayout.createSequentialGroup()
                .addGap(178, 178, 178)
                .addGroup(createHotelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(createHotelContainerLayout.createSequentialGroup()
                        .addComponent(createHotelAddressLabel)
                        .addGap(18, 18, 18)
                        .addGroup(createHotelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(createHotelButton)
                            .addComponent(createHotelAddressField, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(createHotelContainerInstructionsLabel)
                    .addComponent(createHotelContainerLabel)
                    .addGroup(createHotelContainerLayout.createSequentialGroup()
                        .addComponent(createHotelContainerMainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(createHotelContainerCreateMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(484, Short.MAX_VALUE))
        );
        createHotelContainerLayout.setVerticalGroup(
            createHotelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createHotelContainerLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(createHotelContainerLabel)
                .addGap(43, 43, 43)
                .addComponent(createHotelContainerInstructionsLabel)
                .addGap(55, 55, 55)
                .addGroup(createHotelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createHotelAddressLabel)
                    .addComponent(createHotelAddressField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(createHotelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(129, 129, 129)
                .addGroup(createHotelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createHotelContainerCreateMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createHotelContainerMainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(268, Short.MAX_VALUE))
        );

        getContentPane().add(createHotelContainer, "card4");

        createManagerContainerLabel.setFont(new java.awt.Font("Open Sans", 1, 36)); // NOI18N
        createManagerContainerLabel.setText("Add Managers");

        createManagerContainerInstructionsLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        createManagerContainerInstructionsLabel.setText("Add a manager by entering its details below:");

        createManagerLastNameField.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        createManagerFirstNameLabel.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        createManagerFirstNameLabel.setText("First name:");

        createManagerFirstNameField.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        createManagerEmailLabel.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        createManagerEmailLabel.setText("Email:");

        createManagerEmailField.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        createManagerPhoneLabel.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        createManagerPhoneLabel.setText("Phone:");

        createManagerPhoneField.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        createManagerHotelIDLabel.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        createManagerHotelIDLabel.setText("Hotel ID:");

        createManagerHotelIDComboBox.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        createManagerHotelIDComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        createManagerHotelIDComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createManagerHotelIDComboBoxActionPerformed(evt);
            }
        });

        createManagerButton.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        createManagerButton.setText("Add Manager");
        createManagerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createManagerButtonActionPerformed(evt);
            }
        });

        createManagerContainerMainMenuButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        createManagerContainerMainMenuButton.setText("Return to Main Menu");
        createManagerContainerMainMenuButton.setPreferredSize(new java.awt.Dimension(150, 40));
        createManagerContainerMainMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createManagerContainerMainMenuButtonActionPerformed(evt);
            }
        });

        createManagerContainerCreateMenuButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        createManagerContainerCreateMenuButton.setText("Return to Create Menu");
        createManagerContainerCreateMenuButton.setPreferredSize(new java.awt.Dimension(150, 40));
        createManagerContainerCreateMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createManagerContainerCreateMenuButtonActionPerformed(evt);
            }
        });

        createGuestLastNameLabel1.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        createGuestLastNameLabel1.setText("Last name:");

        javax.swing.GroupLayout createManagerContainerLayout = new javax.swing.GroupLayout(createManagerContainer);
        createManagerContainer.setLayout(createManagerContainerLayout);
        createManagerContainerLayout.setHorizontalGroup(
            createManagerContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createManagerContainerLayout.createSequentialGroup()
                .addGroup(createManagerContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(createManagerContainerLayout.createSequentialGroup()
                        .addGap(178, 178, 178)
                        .addComponent(createManagerContainerLabel))
                    .addGroup(createManagerContainerLayout.createSequentialGroup()
                        .addGap(178, 178, 178)
                        .addComponent(createManagerContainerInstructionsLabel))
                    .addGroup(createManagerContainerLayout.createSequentialGroup()
                        .addGap(180, 180, 180)
                        .addComponent(createGuestLastNameLabel1)
                        .addGap(41, 41, 41)
                        .addComponent(createManagerLastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(createManagerContainerLayout.createSequentialGroup()
                        .addGap(178, 178, 178)
                        .addComponent(createManagerFirstNameLabel)
                        .addGap(41, 41, 41)
                        .addComponent(createManagerFirstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(createManagerContainerLayout.createSequentialGroup()
                        .addGap(212, 212, 212)
                        .addComponent(createManagerEmailLabel)
                        .addGap(41, 41, 41)
                        .addComponent(createManagerEmailField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(createManagerContainerLayout.createSequentialGroup()
                        .addGap(207, 207, 207)
                        .addComponent(createManagerPhoneLabel)
                        .addGap(41, 41, 41)
                        .addComponent(createManagerPhoneField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(createManagerContainerLayout.createSequentialGroup()
                        .addGap(178, 178, 178)
                        .addComponent(createManagerContainerMainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(createManagerContainerCreateMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(createManagerContainerLayout.createSequentialGroup()
                        .addGap(196, 196, 196)
                        .addComponent(createManagerHotelIDLabel)
                        .addGap(41, 41, 41)
                        .addGroup(createManagerContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(createManagerButton)
                            .addComponent(createManagerHotelIDComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(727, 727, 727))
        );
        createManagerContainerLayout.setVerticalGroup(
            createManagerContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createManagerContainerLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(createManagerContainerLabel)
                .addGap(43, 43, 43)
                .addComponent(createManagerContainerInstructionsLabel)
                .addGap(60, 60, 60)
                .addGroup(createManagerContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(createManagerContainerLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(createGuestLastNameLabel1))
                    .addComponent(createManagerLastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(createManagerContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(createManagerContainerLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(createManagerFirstNameLabel))
                    .addComponent(createManagerFirstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(createManagerContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(createManagerContainerLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(createManagerEmailLabel))
                    .addComponent(createManagerEmailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(createManagerContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(createManagerContainerLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(createManagerPhoneLabel))
                    .addComponent(createManagerPhoneField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(createManagerContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(createManagerContainerLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(createManagerHotelIDLabel))
                    .addComponent(createManagerHotelIDComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(createManagerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(109, 109, 109)
                .addGroup(createManagerContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(createManagerContainerMainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createManagerContainerCreateMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        getContentPane().add(createManagerContainer, "card4");

        createRoomContainerLabel.setFont(new java.awt.Font("Open Sans", 1, 36)); // NOI18N
        createRoomContainerLabel.setText("Add Rooms");

        createRoomContainerInstructionsLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        createRoomContainerInstructionsLabel.setText("Select a room via its ID from the dropdown list below:");

        createRoomPriceLabel.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        createRoomPriceLabel.setText("Price:");

        createRoomPriceField.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        createRoomCapacityLabel.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        createRoomCapacityLabel.setText("Capacity:");

        createRoomCapacityComboBox.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        createRoomCapacityComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        createRoomSeaViewLabel.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        createRoomSeaViewLabel.setText("Sea View:");

        createRoomSeaViewCheckBox.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N

        createRoomHotelIDLabel.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        createRoomHotelIDLabel.setText("Hotel:");

        createRoomHotelIDComboxBox.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        createRoomHotelIDComboxBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        createRoomButton.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        createRoomButton.setText("Add Room");
        createRoomButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createRoomButtonActionPerformed(evt);
            }
        });

        createRoomContainerMainMenuButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        createRoomContainerMainMenuButton.setText("Return to Main Menu");
        createRoomContainerMainMenuButton.setPreferredSize(new java.awt.Dimension(150, 40));
        createRoomContainerMainMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createRoomContainerMainMenuButtonActionPerformed(evt);
            }
        });

        createRoomContainerCreateMenuButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        createRoomContainerCreateMenuButton.setText("Return to Create Menu");
        createRoomContainerCreateMenuButton.setPreferredSize(new java.awt.Dimension(150, 40));
        createRoomContainerCreateMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createRoomContainerCreateMenuButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout createRoomContainerLayout = new javax.swing.GroupLayout(createRoomContainer);
        createRoomContainer.setLayout(createRoomContainerLayout);
        createRoomContainerLayout.setHorizontalGroup(
            createRoomContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, createRoomContainerLayout.createSequentialGroup()
                .addGroup(createRoomContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(createRoomContainerLayout.createSequentialGroup()
                        .addGap(178, 178, 178)
                        .addGroup(createRoomContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(createRoomContainerInstructionsLabel)
                            .addComponent(createRoomContainerLabel)
                            .addGroup(createRoomContainerLayout.createSequentialGroup()
                                .addComponent(createRoomContainerMainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(createRoomContainerCreateMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(createRoomContainerLayout.createSequentialGroup()
                        .addGap(258, 258, 258)
                        .addGroup(createRoomContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(createRoomCapacityLabel)
                            .addComponent(createRoomPriceLabel)
                            .addComponent(createRoomSeaViewLabel)
                            .addComponent(createRoomHotelIDLabel))
                        .addGap(21, 21, 21)
                        .addGroup(createRoomContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(createRoomPriceField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(createRoomSeaViewCheckBox)
                            .addComponent(createRoomHotelIDComboxBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(createRoomButton)
                            .addComponent(createRoomCapacityComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(671, Short.MAX_VALUE))
        );
        createRoomContainerLayout.setVerticalGroup(
            createRoomContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createRoomContainerLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(createRoomContainerLabel)
                .addGap(43, 43, 43)
                .addComponent(createRoomContainerInstructionsLabel)
                .addGap(67, 67, 67)
                .addGroup(createRoomContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createRoomPriceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createRoomPriceLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(createRoomContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createRoomCapacityLabel)
                    .addComponent(createRoomCapacityComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(createRoomContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(createRoomSeaViewLabel)
                    .addComponent(createRoomSeaViewCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(createRoomContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createRoomHotelIDLabel)
                    .addComponent(createRoomHotelIDComboxBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(createRoomButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(117, 117, 117)
                .addGroup(createRoomContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createRoomContainerCreateMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createRoomContainerMainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(174, Short.MAX_VALUE))
        );

        getContentPane().add(createRoomContainer, "card4");

        viewContainerLabel.setFont(new java.awt.Font("Open Sans", 1, 36)); // NOI18N
        viewContainerLabel.setText("View Menu");

        viewBookingsButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        viewBookingsButton.setText("Bookings");
        viewBookingsButton.setPreferredSize(new java.awt.Dimension(150, 40));
        viewBookingsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewBookingsButtonActionPerformed(evt);
            }
        });

        viewGuestsButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        viewGuestsButton.setText("Guests");
        viewGuestsButton.setPreferredSize(new java.awt.Dimension(150, 40));
        viewGuestsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewGuestsButtonActionPerformed(evt);
            }
        });

        viewHotelsButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        viewHotelsButton.setText("Hotels");
        viewHotelsButton.setPreferredSize(new java.awt.Dimension(150, 40));
        viewHotelsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewHotelsButtonActionPerformed(evt);
            }
        });

        viewManagersButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        viewManagersButton.setText("Managers");
        viewManagersButton.setPreferredSize(new java.awt.Dimension(150, 40));
        viewManagersButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewManagersButtonActionPerformed(evt);
            }
        });

        viewRoomsButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        viewRoomsButton.setText("Rooms");
        viewRoomsButton.setPreferredSize(new java.awt.Dimension(150, 40));
        viewRoomsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewRoomsButtonActionPerformed(evt);
            }
        });

        viewContainerMainMenuButton.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        viewContainerMainMenuButton.setText("Return to Main Menu");
        viewContainerMainMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewContainerMainMenuButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout viewContainerLayout = new javax.swing.GroupLayout(viewContainer);
        viewContainer.setLayout(viewContainerLayout);
        viewContainerLayout.setHorizontalGroup(
            viewContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewContainerLayout.createSequentialGroup()
                .addGap(476, 476, 476)
                .addGroup(viewContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(viewRoomsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewHotelsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewGuestsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewBookingsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewContainerLabel)
                    .addComponent(viewManagersButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewContainerMainMenuButton))
                .addContainerGap(625, Short.MAX_VALUE))
        );

        viewContainerLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {viewBookingsButton, viewGuestsButton, viewHotelsButton, viewRoomsButton});

        viewContainerLayout.setVerticalGroup(
            viewContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewContainerLayout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addComponent(viewContainerLabel)
                .addGap(61, 61, 61)
                .addComponent(viewBookingsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(viewGuestsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(viewHotelsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(viewManagersButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(viewRoomsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(viewContainerMainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(174, Short.MAX_VALUE))
        );

        getContentPane().add(viewContainer, "card3");

        viewBookingContainerLabel.setFont(new java.awt.Font("Open Sans", 1, 36)); // NOI18N
        viewBookingContainerLabel.setText("View Bookings");

        viewBookingContainerInstructionsLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        viewBookingContainerInstructionsLabel.setText("Select a booking via its ID from the dropdown list below:");

        viewBookingRecordsComboBox.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        viewBookingRecordsComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        viewBookingRecordsComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewBookingRecordsComboBoxActionPerformed(evt);
            }
        });

        viewBookingGuestLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        viewBookingGuestLabel.setText("Guest");

        viewBookingGuestValueLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        viewBookingGuestValueLabel.setText(" ");

        viewBookingRoomsLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        viewBookingRoomsLabel.setText("Rooms");

        viewBookingRoomsValueLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        viewBookingRoomsValueLabel.setText(" ");

        viewBookingArrivalDateLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        viewBookingArrivalDateLabel.setText("Arrival Date");

        viewBookingArrivalDateValueLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        viewBookingArrivalDateValueLabel.setText(" ");

        viewBookingDepartureDateLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        viewBookingDepartureDateLabel.setText("Departure Date");

        viewBookingDepartureDateValueLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        viewBookingDepartureDateValueLabel.setText(" ");

        viewBookingContainerMainMenuButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        viewBookingContainerMainMenuButton.setText("Return to Main Menu");
        viewBookingContainerMainMenuButton.setPreferredSize(new java.awt.Dimension(150, 40));
        viewBookingContainerMainMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewBookingContainerMainMenuButtonActionPerformed(evt);
            }
        });

        viewBookingContainerViewMenuButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        viewBookingContainerViewMenuButton.setText("Return to View Menu");
        viewBookingContainerViewMenuButton.setPreferredSize(new java.awt.Dimension(150, 40));
        viewBookingContainerViewMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewBookingContainerViewMenuButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout viewBookingContainerLayout = new javax.swing.GroupLayout(viewBookingContainer);
        viewBookingContainer.setLayout(viewBookingContainerLayout);
        viewBookingContainerLayout.setHorizontalGroup(
            viewBookingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewBookingContainerLayout.createSequentialGroup()
                .addGap(178, 178, 178)
                .addGroup(viewBookingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(viewBookingContainerLayout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(viewBookingRecordsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(127, 127, 127)
                        .addGroup(viewBookingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(viewBookingArrivalDateLabel)
                            .addComponent(viewBookingDepartureDateLabel)
                            .addComponent(viewBookingRoomsLabel)
                            .addComponent(viewBookingGuestLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(viewBookingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(viewBookingDepartureDateValueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(viewBookingArrivalDateValueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(viewBookingRoomsValueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(viewBookingGuestValueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(viewBookingContainerLayout.createSequentialGroup()
                        .addGroup(viewBookingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(viewBookingContainerLabel)
                            .addComponent(viewBookingContainerInstructionsLabel))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewBookingContainerLayout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(viewBookingContainerMainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(viewBookingContainerViewMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(310, 310, 310)))
                .addGap(385, 385, 385))
        );
        viewBookingContainerLayout.setVerticalGroup(
            viewBookingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewBookingContainerLayout.createSequentialGroup()
                .addGroup(viewBookingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(viewBookingContainerLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(viewBookingContainerLabel)
                        .addGap(43, 43, 43)
                        .addComponent(viewBookingContainerInstructionsLabel))
                    .addGroup(viewBookingContainerLayout.createSequentialGroup()
                        .addGap(220, 220, 220)
                        .addGroup(viewBookingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(viewBookingGuestLabel)
                            .addComponent(viewBookingRecordsComboBox)
                            .addComponent(viewBookingGuestValueLabel))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(viewBookingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(viewBookingContainerLayout.createSequentialGroup()
                        .addComponent(viewBookingRoomsValueLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(viewBookingArrivalDateValueLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(viewBookingDepartureDateValueLabel))
                    .addGroup(viewBookingContainerLayout.createSequentialGroup()
                        .addComponent(viewBookingRoomsLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(viewBookingArrivalDateLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(viewBookingDepartureDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 237, Short.MAX_VALUE)
                .addGroup(viewBookingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(viewBookingContainerViewMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewBookingContainerMainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(129, 129, 129))
        );

        viewBookingContainerLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {viewBookingArrivalDateLabel, viewBookingDepartureDateLabel, viewBookingGuestLabel});

        getContentPane().add(viewBookingContainer, "card4");

        viewGuestContainerLabel.setFont(new java.awt.Font("Open Sans", 1, 36)); // NOI18N
        viewGuestContainerLabel.setText("View Guests");

        viewGuestRecordsComboBox.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        viewGuestRecordsComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        viewGuestRecordsComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewGuestRecordsComboBoxActionPerformed(evt);
            }
        });

        viewGuestTitleLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        viewGuestTitleLabel.setText("Guest Title");

        viewGuestNameLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        viewGuestNameLabel.setText("Guest Name");

        viewGuestAddressLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        viewGuestAddressLabel.setText("Guest Address");

        viewGuestEmailLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        viewGuestEmailLabel.setText("Guest Email");

        viewGuestPhoneLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        viewGuestPhoneLabel.setText("Guest Phone");

        viewGuestContainerInstructionsLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        viewGuestContainerInstructionsLabel.setText("Select a guest via their ID from the dropdown list below:");

        viewGuestContainerMainMenuButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        viewGuestContainerMainMenuButton.setText("Return to Main Menu");
        viewGuestContainerMainMenuButton.setPreferredSize(new java.awt.Dimension(150, 40));
        viewGuestContainerMainMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewGuestContainerMainMenuButtonActionPerformed(evt);
            }
        });

        viewGuestContainerViewMenuButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        viewGuestContainerViewMenuButton.setText("Return to View Menu");
        viewGuestContainerViewMenuButton.setPreferredSize(new java.awt.Dimension(150, 40));
        viewGuestContainerViewMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewGuestContainerViewMenuButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout viewGuestContainerLayout = new javax.swing.GroupLayout(viewGuestContainer);
        viewGuestContainer.setLayout(viewGuestContainerLayout);
        viewGuestContainerLayout.setHorizontalGroup(
            viewGuestContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewGuestContainerLayout.createSequentialGroup()
                .addGap(178, 178, 178)
                .addGroup(viewGuestContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(viewGuestContainerInstructionsLabel)
                    .addComponent(viewGuestContainerLabel)
                    .addGroup(viewGuestContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(viewGuestContainerLayout.createSequentialGroup()
                            .addGap(68, 68, 68)
                            .addComponent(viewGuestContainerMainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(viewGuestContainerViewMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(viewGuestContainerLayout.createSequentialGroup()
                            .addGap(78, 78, 78)
                            .addComponent(viewGuestRecordsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(127, 127, 127)
                            .addGroup(viewGuestContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(viewGuestNameLabel)
                                .addComponent(viewGuestPhoneLabel)
                                .addComponent(viewGuestEmailLabel)
                                .addComponent(viewGuestAddressLabel)
                                .addComponent(viewGuestTitleLabel)))))
                .addContainerGap(652, Short.MAX_VALUE))
        );
        viewGuestContainerLayout.setVerticalGroup(
            viewGuestContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewGuestContainerLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(viewGuestContainerLabel)
                .addGap(43, 43, 43)
                .addComponent(viewGuestContainerInstructionsLabel)
                .addGap(57, 418, Short.MAX_VALUE)
                .addGroup(viewGuestContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(viewGuestContainerViewMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewGuestContainerMainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(129, 129, 129))
            .addGroup(viewGuestContainerLayout.createSequentialGroup()
                .addGap(220, 220, 220)
                .addGroup(viewGuestContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(viewGuestTitleLabel)
                    .addComponent(viewGuestRecordsComboBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(viewGuestNameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(viewGuestAddressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(viewGuestEmailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(viewGuestPhoneLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        viewGuestContainerLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {viewGuestAddressLabel, viewGuestEmailLabel, viewGuestNameLabel, viewGuestPhoneLabel, viewGuestTitleLabel});

        getContentPane().add(viewGuestContainer, "card4");

        viewHotelContainer.setMinimumSize(new java.awt.Dimension(1280, 720));
        viewHotelContainer.setPreferredSize(new java.awt.Dimension(1280, 720));

        viewHotelContainerLabel.setFont(new java.awt.Font("Open Sans", 1, 36)); // NOI18N
        viewHotelContainerLabel.setText("View Hotels");

        viewHotelRecordsComboBox.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        viewHotelRecordsComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        viewHotelRecordsComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewHotelRecordsComboBoxActionPerformed(evt);
            }
        });

        viewHotelAddressLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        viewHotelAddressLabel.setText("Hotel Address");

        viewHotelContainerInstructionsLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        viewHotelContainerInstructionsLabel.setText("Select a hotel via its ID from the dropdown list below:");

        viewHotelContainerMainMenuButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        viewHotelContainerMainMenuButton.setText("Return to Main Menu");
        viewHotelContainerMainMenuButton.setPreferredSize(new java.awt.Dimension(150, 40));
        viewHotelContainerMainMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewHotelContainerMainMenuButtonActionPerformed(evt);
            }
        });

        viewHotelContainerViewMenuButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        viewHotelContainerViewMenuButton.setText("Return to View Menu");
        viewHotelContainerViewMenuButton.setPreferredSize(new java.awt.Dimension(150, 40));
        viewHotelContainerViewMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewHotelContainerViewMenuButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout viewHotelContainerLayout = new javax.swing.GroupLayout(viewHotelContainer);
        viewHotelContainer.setLayout(viewHotelContainerLayout);
        viewHotelContainerLayout.setHorizontalGroup(
            viewHotelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewHotelContainerLayout.createSequentialGroup()
                .addGroup(viewHotelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(viewHotelContainerLayout.createSequentialGroup()
                        .addGap(256, 256, 256)
                        .addComponent(viewHotelRecordsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(127, 127, 127)
                        .addComponent(viewHotelAddressLabel))
                    .addGroup(viewHotelContainerLayout.createSequentialGroup()
                        .addGap(178, 178, 178)
                        .addGroup(viewHotelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(viewHotelContainerInstructionsLabel)
                            .addComponent(viewHotelContainerLabel)
                            .addGroup(viewHotelContainerLayout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(viewHotelContainerMainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(viewHotelContainerViewMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(667, Short.MAX_VALUE))
        );
        viewHotelContainerLayout.setVerticalGroup(
            viewHotelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewHotelContainerLayout.createSequentialGroup()
                .addGroup(viewHotelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(viewHotelContainerLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(viewHotelContainerLabel)
                        .addGap(43, 43, 43)
                        .addComponent(viewHotelContainerInstructionsLabel))
                    .addGroup(viewHotelContainerLayout.createSequentialGroup()
                        .addGap(220, 220, 220)
                        .addGroup(viewHotelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(viewHotelAddressLabel)
                            .addComponent(viewHotelRecordsComboBox))))
                .addGap(92, 92, 92)
                .addGroup(viewHotelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(viewHotelContainerViewMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewHotelContainerMainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(370, Short.MAX_VALUE))
        );

        getContentPane().add(viewHotelContainer, "card4");

        viewManagerContainerLabel.setFont(new java.awt.Font("Open Sans", 1, 36)); // NOI18N
        viewManagerContainerLabel.setText("View Managers");

        viewManagerRecordsComboBox.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        viewManagerRecordsComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        viewManagerRecordsComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewManagerRecordsComboBoxActionPerformed(evt);
            }
        });

        viewManagerNameLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        viewManagerNameLabel.setText("Manager Name");

        viewManagerEmailLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        viewManagerEmailLabel.setText("Manager Email");

        viewManagerPhoneLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        viewManagerPhoneLabel.setText("Manager Phone");

        viewManagerHotelIDLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        viewManagerHotelIDLabel.setText("Hotel ID");

        viewManagerContainerInstructionsLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        viewManagerContainerInstructionsLabel.setText("Select a manager via their ID from the dropdown list below:");

        viewManagerContainerMainMenuButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        viewManagerContainerMainMenuButton.setText("Return to Main Menu");
        viewManagerContainerMainMenuButton.setPreferredSize(new java.awt.Dimension(150, 40));
        viewManagerContainerMainMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewManagerContainerMainMenuButtonActionPerformed(evt);
            }
        });

        viewManagerContainerViewMenuButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        viewManagerContainerViewMenuButton.setText("Return to View Menu");
        viewManagerContainerViewMenuButton.setPreferredSize(new java.awt.Dimension(150, 40));
        viewManagerContainerViewMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewManagerContainerViewMenuButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout viewManagerContainerLayout = new javax.swing.GroupLayout(viewManagerContainer);
        viewManagerContainer.setLayout(viewManagerContainerLayout);
        viewManagerContainerLayout.setHorizontalGroup(
            viewManagerContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewManagerContainerLayout.createSequentialGroup()
                .addGap(178, 178, 178)
                .addGroup(viewManagerContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(viewManagerContainerInstructionsLabel)
                    .addComponent(viewManagerContainerLabel)
                    .addGroup(viewManagerContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, viewManagerContainerLayout.createSequentialGroup()
                            .addGap(68, 68, 68)
                            .addComponent(viewManagerContainerMainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(viewManagerContainerViewMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(viewManagerContainerLayout.createSequentialGroup()
                            .addGap(78, 78, 78)
                            .addComponent(viewManagerRecordsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(124, 124, 124)
                            .addGroup(viewManagerContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(viewManagerPhoneLabel)
                                .addComponent(viewManagerEmailLabel)
                                .addComponent(viewManagerHotelIDLabel)
                                .addComponent(viewManagerNameLabel))
                            .addGap(3, 3, 3))))
                .addContainerGap(623, Short.MAX_VALUE))
        );
        viewManagerContainerLayout.setVerticalGroup(
            viewManagerContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewManagerContainerLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(viewManagerContainerLabel)
                .addGap(43, 43, 43)
                .addComponent(viewManagerContainerInstructionsLabel)
                .addGap(57, 57, 57)
                .addGroup(viewManagerContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(viewManagerRecordsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(viewManagerContainerLayout.createSequentialGroup()
                        .addComponent(viewManagerNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(viewManagerEmailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(viewManagerPhoneLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(viewManagerHotelIDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 239, Short.MAX_VALUE)
                .addGroup(viewManagerContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(viewManagerContainerViewMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewManagerContainerMainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(129, 129, 129))
        );

        viewManagerContainerLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {viewManagerEmailLabel, viewManagerHotelIDLabel, viewManagerNameLabel, viewManagerPhoneLabel});

        getContentPane().add(viewManagerContainer, "card4");

        viewRoomContainerLabel.setFont(new java.awt.Font("Open Sans", 1, 36)); // NOI18N
        viewRoomContainerLabel.setText("View Rooms");

        viewRoomContainerInstructionsLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        viewRoomContainerInstructionsLabel.setText("Select a room via its ID from the dropdown list below:");

        viewRoomRecordsComboBox.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        viewRoomRecordsComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        viewRoomRecordsComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewRoomRecordsComboBoxActionPerformed(evt);
            }
        });

        viewRoomPriceLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        viewRoomPriceLabel.setText("Price");

        viewRoomCapacityLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        viewRoomCapacityLabel.setText("Capacity");

        viewRoomSeaViewLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        viewRoomSeaViewLabel.setText("Sea View");

        viewRoomHotelIDLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        viewRoomHotelIDLabel.setText("Hotel ID");

        viewRoomContainerMainMenuButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        viewRoomContainerMainMenuButton.setText("Return to Main Menu");
        viewRoomContainerMainMenuButton.setPreferredSize(new java.awt.Dimension(150, 40));
        viewRoomContainerMainMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewRoomContainerMainMenuButtonActionPerformed(evt);
            }
        });

        viewRoomContainerViewMenuButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        viewRoomContainerViewMenuButton.setText("Return to View Menu");
        viewRoomContainerViewMenuButton.setPreferredSize(new java.awt.Dimension(150, 40));
        viewRoomContainerViewMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewRoomContainerViewMenuButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout viewRoomContainerLayout = new javax.swing.GroupLayout(viewRoomContainer);
        viewRoomContainer.setLayout(viewRoomContainerLayout);
        viewRoomContainerLayout.setHorizontalGroup(
            viewRoomContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewRoomContainerLayout.createSequentialGroup()
                .addGap(178, 178, 178)
                .addGroup(viewRoomContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(viewRoomContainerInstructionsLabel)
                    .addComponent(viewRoomContainerLabel)
                    .addGroup(viewRoomContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(viewRoomContainerLayout.createSequentialGroup()
                            .addGap(68, 68, 68)
                            .addComponent(viewRoomContainerMainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(viewRoomContainerViewMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(viewRoomContainerLayout.createSequentialGroup()
                            .addGap(78, 78, 78)
                            .addComponent(viewRoomRecordsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(127, 127, 127)
                            .addGroup(viewRoomContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(viewRoomCapacityLabel)
                                .addComponent(viewRoomHotelIDLabel)
                                .addComponent(viewRoomSeaViewLabel)
                                .addComponent(viewRoomPriceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(671, Short.MAX_VALUE))
        );

        viewRoomContainerLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {viewRoomCapacityLabel, viewRoomHotelIDLabel, viewRoomPriceLabel, viewRoomSeaViewLabel});

        viewRoomContainerLayout.setVerticalGroup(
            viewRoomContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewRoomContainerLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(viewRoomContainerLabel)
                .addGap(43, 43, 43)
                .addComponent(viewRoomContainerInstructionsLabel)
                .addGap(57, 418, Short.MAX_VALUE)
                .addGroup(viewRoomContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(viewRoomContainerViewMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewRoomContainerMainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(129, 129, 129))
            .addGroup(viewRoomContainerLayout.createSequentialGroup()
                .addGap(220, 220, 220)
                .addGroup(viewRoomContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(viewRoomPriceLabel)
                    .addComponent(viewRoomRecordsComboBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(viewRoomCapacityLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(viewRoomSeaViewLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(viewRoomHotelIDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        viewRoomContainerLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {viewRoomCapacityLabel, viewRoomHotelIDLabel, viewRoomPriceLabel, viewRoomSeaViewLabel});

        getContentPane().add(viewRoomContainer, "card4");

        updateContainerLabel.setFont(new java.awt.Font("Open Sans", 1, 36)); // NOI18N
        updateContainerLabel.setText("Update Menu");

        updateBookingsButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        updateBookingsButton.setText("Bookings");
        updateBookingsButton.setPreferredSize(new java.awt.Dimension(150, 40));
        updateBookingsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBookingsButtonActionPerformed(evt);
            }
        });

        updateGuestsButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        updateGuestsButton.setText("Guests");
        updateGuestsButton.setPreferredSize(new java.awt.Dimension(150, 40));
        updateGuestsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateGuestsButtonActionPerformed(evt);
            }
        });

        updateHotelsButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        updateHotelsButton.setText("Hotels");
        updateHotelsButton.setPreferredSize(new java.awt.Dimension(150, 40));
        updateHotelsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateHotelsButtonActionPerformed(evt);
            }
        });

        updateManagersButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        updateManagersButton.setText("Managers");
        updateManagersButton.setPreferredSize(new java.awt.Dimension(150, 40));
        updateManagersButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateManagersButtonActionPerformed(evt);
            }
        });

        updateRoomsButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        updateRoomsButton.setText("Rooms");
        updateRoomsButton.setPreferredSize(new java.awt.Dimension(150, 40));
        updateRoomsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateRoomsButtonActionPerformed(evt);
            }
        });

        updateContainerMainMenuButton.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        updateContainerMainMenuButton.setText("Return to Main Menu");
        updateContainerMainMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateContainerMainMenuButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout updateContainerLayout = new javax.swing.GroupLayout(updateContainer);
        updateContainer.setLayout(updateContainerLayout);
        updateContainerLayout.setHorizontalGroup(
            updateContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateContainerLayout.createSequentialGroup()
                .addGap(451, 451, 451)
                .addGroup(updateContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(updateRoomsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateHotelsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateGuestsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateBookingsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateContainerLabel)
                    .addComponent(updateManagersButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateContainerMainMenuButton))
                .addContainerGap(601, Short.MAX_VALUE))
        );
        updateContainerLayout.setVerticalGroup(
            updateContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateContainerLayout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addComponent(updateContainerLabel)
                .addGap(61, 61, 61)
                .addComponent(updateBookingsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(updateGuestsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(updateHotelsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(updateManagersButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(updateRoomsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(updateContainerMainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(180, Short.MAX_VALUE))
        );

        getContentPane().add(updateContainer, "card3");

        updateBookingContainerLabel.setFont(new java.awt.Font("Open Sans", 1, 36)); // NOI18N
        updateBookingContainerLabel.setText("Update Bookings");

        updateBookingContainerInstructionsLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        updateBookingContainerInstructionsLabel.setText("Select a booking via its ID from the dropdown list below:");

        updateBookingRecordsComboBox.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        updateBookingRecordsComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        updateBookingRecordsComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBookingRecordsComboBoxActionPerformed(evt);
            }
        });

        updateBookingGuestLabel.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        updateBookingGuestLabel.setText("Guest");

        updateBookingGuestIDComboBox.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        updateBookingGuestIDComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        updateBookingGuestIDComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBookingGuestIDComboBoxActionPerformed(evt);
            }
        });

        updateBookingRoomIDLabel.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        updateBookingRoomIDLabel.setText("Rooms");

        updateBookingRoomListLabel.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        updateBookingRoomListLabel.setText("Available Rooms");

        updateBookingRoomList.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        updateBookingRoomScrollPane.setViewportView(updateBookingRoomList);

        updateBookingRoomRemoveButton.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        updateBookingRoomRemoveButton.setText("Remove");
        updateBookingRoomRemoveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBookingRoomRemoveButtonActionPerformed(evt);
            }
        });

        updateBookingRoomAddButton.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        updateBookingRoomAddButton.setText("Add");
        updateBookingRoomAddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBookingRoomAddButtonActionPerformed(evt);
            }
        });

        updateBookingSelectedRoomListLabel.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        updateBookingSelectedRoomListLabel.setText("Selected Rooms");

        updateBookingSelectedRoomList.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        updateBookingSelectedRoomList.setToolTipText("");
        updateBookingSelectedRoomScrollPane.setViewportView(updateBookingSelectedRoomList);

        updateBookingArrivalTimeLabel.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        updateBookingArrivalTimeLabel.setText("Arrival Time");

        updateBookingArrivalDateDayComboBox.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        updateBookingArrivalDateDayComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        updateBookingArrivalDateMonthComboBox.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        updateBookingArrivalDateMonthComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        updateBookingArrivalDateYearComboBox.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        updateBookingArrivalDateYearComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        updateBookingDepartureTimeLabel.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        updateBookingDepartureTimeLabel.setText("Departure Time");

        updateBookingDepartureDateYearComboBox.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        updateBookingDepartureDateYearComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        updateBookingDepartureDateMonthComboBox.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        updateBookingDepartureDateMonthComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        updateBookingDepartureDateDayComboBox.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        updateBookingDepartureDateDayComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        updateBookingButton.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        updateBookingButton.setText("Update Booking");
        updateBookingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBookingButtonActionPerformed(evt);
            }
        });

        updateBookingContainerMainMenuButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        updateBookingContainerMainMenuButton.setText("Return to Main Menu");
        updateBookingContainerMainMenuButton.setPreferredSize(new java.awt.Dimension(150, 40));
        updateBookingContainerMainMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBookingContainerMainMenuButtonActionPerformed(evt);
            }
        });

        updateBookingContainerUpdateMenuButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        updateBookingContainerUpdateMenuButton.setText("Return to Update Menu");
        updateBookingContainerUpdateMenuButton.setPreferredSize(new java.awt.Dimension(150, 40));
        updateBookingContainerUpdateMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBookingContainerUpdateMenuButtonActionPerformed(evt);
            }
        });

        updateBookingGuestNameLabel.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        updateBookingGuestNameLabel.setText(" ");

        javax.swing.GroupLayout updateBookingContainerLayout = new javax.swing.GroupLayout(updateBookingContainer);
        updateBookingContainer.setLayout(updateBookingContainerLayout);
        updateBookingContainerLayout.setHorizontalGroup(
            updateBookingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, updateBookingContainerLayout.createSequentialGroup()
                .addGap(178, 178, 178)
                .addGroup(updateBookingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(updateBookingContainerLayout.createSequentialGroup()
                        .addGroup(updateBookingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(updateBookingContainerMainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(updateBookingRecordsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(updateBookingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(updateBookingContainerUpdateMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(updateBookingButton)
                            .addGroup(updateBookingContainerLayout.createSequentialGroup()
                                .addGroup(updateBookingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(updateBookingArrivalTimeLabel)
                                    .addComponent(updateBookingDepartureTimeLabel)
                                    .addComponent(updateBookingRoomIDLabel)
                                    .addComponent(updateBookingGuestLabel))
                                .addGroup(updateBookingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(updateBookingContainerLayout.createSequentialGroup()
                                        .addGap(25, 25, 25)
                                        .addGroup(updateBookingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(updateBookingRoomListLabel)
                                            .addGroup(updateBookingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(updateBookingContainerLayout.createSequentialGroup()
                                                    .addComponent(updateBookingDepartureDateDayComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(updateBookingDepartureDateMonthComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(updateBookingDepartureDateYearComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGroup(updateBookingContainerLayout.createSequentialGroup()
                                                    .addComponent(updateBookingArrivalDateDayComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(updateBookingArrivalDateMonthComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(updateBookingArrivalDateYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(updateBookingContainerLayout.createSequentialGroup()
                                                .addComponent(updateBookingRoomScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(49, 49, 49)
                                                .addGroup(updateBookingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(updateBookingRoomAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(updateBookingRoomRemoveButton))
                                                .addGap(48, 48, 48)
                                                .addGroup(updateBookingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(updateBookingSelectedRoomListLabel)
                                                    .addComponent(updateBookingSelectedRoomScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                    .addGroup(updateBookingContainerLayout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addComponent(updateBookingGuestIDComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)
                                        .addComponent(updateBookingGuestNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 475, Short.MAX_VALUE))
                    .addGroup(updateBookingContainerLayout.createSequentialGroup()
                        .addGroup(updateBookingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(updateBookingContainerInstructionsLabel)
                            .addComponent(updateBookingContainerLabel))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        updateBookingContainerLayout.setVerticalGroup(
            updateBookingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateBookingContainerLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(updateBookingContainerLabel)
                .addGap(43, 43, 43)
                .addComponent(updateBookingContainerInstructionsLabel)
                .addGap(53, 53, 53)
                .addGroup(updateBookingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateBookingRecordsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateBookingGuestLabel)
                    .addComponent(updateBookingGuestNameLabel)
                    .addComponent(updateBookingGuestIDComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(updateBookingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(updateBookingContainerLayout.createSequentialGroup()
                        .addGroup(updateBookingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(updateBookingRoomListLabel)
                            .addComponent(updateBookingRoomIDLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(updateBookingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(updateBookingRoomScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(updateBookingContainerLayout.createSequentialGroup()
                                .addComponent(updateBookingRoomAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(updateBookingRoomRemoveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, updateBookingContainerLayout.createSequentialGroup()
                        .addComponent(updateBookingSelectedRoomListLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(updateBookingSelectedRoomScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(updateBookingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(updateBookingContainerLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(updateBookingArrivalTimeLabel))
                    .addGroup(updateBookingContainerLayout.createSequentialGroup()
                        .addGroup(updateBookingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(updateBookingArrivalDateDayComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(updateBookingArrivalDateMonthComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(updateBookingArrivalDateYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(updateBookingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(updateBookingDepartureDateDayComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(updateBookingDepartureDateMonthComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(updateBookingDepartureDateYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(updateBookingDepartureTimeLabel))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(updateBookingButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addGroup(updateBookingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateBookingContainerUpdateMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateBookingContainerMainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57))
        );

        getContentPane().add(updateBookingContainer, "card4");

        updateGuestContainerLabel.setFont(new java.awt.Font("Open Sans", 1, 36)); // NOI18N
        updateGuestContainerLabel.setText("Update Guests");

        updateGuestRecordsComboBox.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        updateGuestRecordsComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        updateGuestRecordsComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateGuestRecordsComboBoxActionPerformed(evt);
            }
        });

        updateGuestContainerInstructionsLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        updateGuestContainerInstructionsLabel.setText("Select a guest via their ID from the dropdown list below:");

        updateGuestTitleField.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        updateGuestTitleField.setText("Title");

        updateGuestLastNameField.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        updateGuestLastNameField.setText("Last Name");

        updateGuestFirstNameField.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        updateGuestFirstNameField.setText("First Name");

        updateGuestAddressField.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        updateGuestAddressField.setText("Address");

        updateGuestEmailField.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        updateGuestEmailField.setText("Email");

        updateGuestPhoneField.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        updateGuestPhoneField.setText("Phone");

        updateGuestButton.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        updateGuestButton.setText("Update Guest");
        updateGuestButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateGuestButtonActionPerformed(evt);
            }
        });

        updateGuestContainerMainMenuButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        updateGuestContainerMainMenuButton.setText("Return to Main Menu");
        updateGuestContainerMainMenuButton.setPreferredSize(new java.awt.Dimension(150, 40));
        updateGuestContainerMainMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateGuestContainerMainMenuButtonActionPerformed(evt);
            }
        });

        updateGuestContainerUpdateMenuButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        updateGuestContainerUpdateMenuButton.setText("Return to Update Menu");
        updateGuestContainerUpdateMenuButton.setPreferredSize(new java.awt.Dimension(150, 40));
        updateGuestContainerUpdateMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateGuestContainerUpdateMenuButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout updateGuestContainerLayout = new javax.swing.GroupLayout(updateGuestContainer);
        updateGuestContainer.setLayout(updateGuestContainerLayout);
        updateGuestContainerLayout.setHorizontalGroup(
            updateGuestContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateGuestContainerLayout.createSequentialGroup()
                .addGap(178, 178, 178)
                .addGroup(updateGuestContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(updateGuestContainerLayout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(updateGuestRecordsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(updateGuestContainerLayout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(updateGuestContainerMainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(updateGuestContainerUpdateMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(updateGuestContainerInstructionsLabel)
                    .addComponent(updateGuestContainerLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, updateGuestContainerLayout.createSequentialGroup()
                .addContainerGap(442, Short.MAX_VALUE)
                .addGroup(updateGuestContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(updateGuestContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, updateGuestContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(updateGuestEmailField)
                            .addComponent(updateGuestAddressField, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(updateGuestFirstNameField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, updateGuestContainerLayout.createSequentialGroup()
                            .addComponent(updateGuestPhoneField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(250, 250, 250)))
                    .addGroup(updateGuestContainerLayout.createSequentialGroup()
                        .addGroup(updateGuestContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(updateGuestButton)
                            .addComponent(updateGuestLastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(updateGuestTitleField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(611, 611, 611))))
        );
        updateGuestContainerLayout.setVerticalGroup(
            updateGuestContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateGuestContainerLayout.createSequentialGroup()
                .addGroup(updateGuestContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(updateGuestContainerLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(updateGuestContainerLabel)
                        .addGap(43, 43, 43)
                        .addComponent(updateGuestContainerInstructionsLabel))
                    .addGroup(updateGuestContainerLayout.createSequentialGroup()
                        .addGap(222, 222, 222)
                        .addGroup(updateGuestContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(updateGuestRecordsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(updateGuestTitleField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(updateGuestLastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(updateGuestFirstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(updateGuestAddressField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(updateGuestEmailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(updateGuestPhoneField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(updateGuestButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addGroup(updateGuestContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateGuestContainerUpdateMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateGuestContainerMainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(129, 129, 129))
        );

        getContentPane().add(updateGuestContainer, "card4");

        updateHotelContainer.setMinimumSize(new java.awt.Dimension(1280, 720));

        updateHotelContainerLabel.setFont(new java.awt.Font("Open Sans", 1, 36)); // NOI18N
        updateHotelContainerLabel.setText("Update Hotels");

        updateHotelRecordsComboBox.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        updateHotelRecordsComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        updateHotelRecordsComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateHotelRecordsComboBoxActionPerformed(evt);
            }
        });

        updateHotelContainerInstructionsLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        updateHotelContainerInstructionsLabel.setText("Select a hotel via its ID from the dropdown list below:");

        updateHotelContainerMainMenuButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        updateHotelContainerMainMenuButton.setText("Return to Main Menu");
        updateHotelContainerMainMenuButton.setPreferredSize(new java.awt.Dimension(150, 40));
        updateHotelContainerMainMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateHotelContainerMainMenuButtonActionPerformed(evt);
            }
        });

        updateHotelContainerUpdateMenuButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        updateHotelContainerUpdateMenuButton.setText("Return to Update Menu");
        updateHotelContainerUpdateMenuButton.setPreferredSize(new java.awt.Dimension(150, 40));
        updateHotelContainerUpdateMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateHotelContainerUpdateMenuButtonActionPerformed(evt);
            }
        });

        updateHotelAddressField.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        updateHotelAddressField.setText("Address");

        updateHotelButton.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        updateHotelButton.setText("Update Hotel");
        updateHotelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateHotelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout updateHotelContainerLayout = new javax.swing.GroupLayout(updateHotelContainer);
        updateHotelContainer.setLayout(updateHotelContainerLayout);
        updateHotelContainerLayout.setHorizontalGroup(
            updateHotelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateHotelContainerLayout.createSequentialGroup()
                .addGap(178, 178, 178)
                .addGroup(updateHotelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(updateHotelContainerLayout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(updateHotelContainerMainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(updateHotelContainerUpdateMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(updateHotelContainerInstructionsLabel)
                    .addComponent(updateHotelContainerLabel)
                    .addGroup(updateHotelContainerLayout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(updateHotelRecordsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addGroup(updateHotelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(updateHotelButton)
                            .addComponent(updateHotelAddressField, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(384, Short.MAX_VALUE))
        );
        updateHotelContainerLayout.setVerticalGroup(
            updateHotelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateHotelContainerLayout.createSequentialGroup()
                .addGroup(updateHotelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(updateHotelContainerLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(updateHotelContainerLabel)
                        .addGap(43, 43, 43)
                        .addComponent(updateHotelContainerInstructionsLabel))
                    .addGroup(updateHotelContainerLayout.createSequentialGroup()
                        .addGap(225, 225, 225)
                        .addGroup(updateHotelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(updateHotelRecordsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(updateHotelAddressField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(updateHotelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 263, Short.MAX_VALUE)
                .addGroup(updateHotelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateHotelContainerUpdateMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateHotelContainerMainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(129, 129, 129))
        );

        getContentPane().add(updateHotelContainer, "card4");

        updateManagerContainerLabel.setFont(new java.awt.Font("Open Sans", 1, 36)); // NOI18N
        updateManagerContainerLabel.setText("Update Managers");

        updateManagerRecordsComboBox.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        updateManagerRecordsComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        updateManagerRecordsComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateManagerRecordsComboBoxActionPerformed(evt);
            }
        });

        updateManagerContainerInstructionsLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        updateManagerContainerInstructionsLabel.setText("Select a manager via its ID from the dropdown list below:");

        updateManagerLastNameLabel.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        updateManagerLastNameLabel.setText("Last Name");

        updateManagerLastNameField.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        updateManagerLastNameField.setText("Last Name");

        updateManagerFirstNameLabel.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        updateManagerFirstNameLabel.setText("First Name");

        updateManagerFirstNameField.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        updateManagerFirstNameField.setText("FirstName");

        updateManagerEmailLabel.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        updateManagerEmailLabel.setText("Email");

        updateManagerEmailField.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        updateManagerEmailField.setText("Email");

        updateManagerPhoneLabel.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        updateManagerPhoneLabel.setText("Phone");

        updateManagerPhoneField.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        updateManagerPhoneField.setText("Phone");

        updateManagerHotelIDLabel.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        updateManagerHotelIDLabel.setText("Hotel ID");

        updateManagerHotelIDField.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        updateManagerHotelIDField.setText("Hotel ID");

        updateManagerButton.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        updateManagerButton.setText("Update Manager");
        updateManagerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateManagerButtonActionPerformed(evt);
            }
        });

        updateManagerContainerMainMenuButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        updateManagerContainerMainMenuButton.setText("Return to Main Menu");
        updateManagerContainerMainMenuButton.setPreferredSize(new java.awt.Dimension(150, 40));
        updateManagerContainerMainMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateManagerContainerMainMenuButtonActionPerformed(evt);
            }
        });

        updateManagerContainerUpdateMenuButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        updateManagerContainerUpdateMenuButton.setText("Return to Update Menu");
        updateManagerContainerUpdateMenuButton.setPreferredSize(new java.awt.Dimension(150, 40));
        updateManagerContainerUpdateMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateManagerContainerUpdateMenuButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout updateManagerContainerLayout = new javax.swing.GroupLayout(updateManagerContainer);
        updateManagerContainer.setLayout(updateManagerContainerLayout);
        updateManagerContainerLayout.setHorizontalGroup(
            updateManagerContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateManagerContainerLayout.createSequentialGroup()
                .addGap(178, 178, 178)
                .addGroup(updateManagerContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(updateManagerContainerInstructionsLabel)
                    .addComponent(updateManagerContainerLabel)
                    .addGroup(updateManagerContainerLayout.createSequentialGroup()
                        .addGroup(updateManagerContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(updateManagerContainerLayout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addComponent(updateManagerContainerMainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(updateManagerRecordsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(updateManagerContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(updateManagerContainerUpdateMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, updateManagerContainerLayout.createSequentialGroup()
                                .addGroup(updateManagerContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(updateManagerLastNameLabel)
                                    .addComponent(updateManagerFirstNameLabel)
                                    .addComponent(updateManagerEmailLabel)
                                    .addComponent(updateManagerPhoneLabel)
                                    .addComponent(updateManagerHotelIDLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                                .addGroup(updateManagerContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(updateManagerButton)
                                    .addGroup(updateManagerContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(updateManagerLastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(updateManagerFirstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(updateManagerEmailField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(updateManagerPhoneField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(updateManagerHotelIDField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(29, 29, 29)))))
                .addContainerGap(641, Short.MAX_VALUE))
        );
        updateManagerContainerLayout.setVerticalGroup(
            updateManagerContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateManagerContainerLayout.createSequentialGroup()
                .addGroup(updateManagerContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(updateManagerContainerLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(updateManagerContainerLabel)
                        .addGap(43, 43, 43)
                        .addComponent(updateManagerContainerInstructionsLabel))
                    .addGroup(updateManagerContainerLayout.createSequentialGroup()
                        .addGap(222, 222, 222)
                        .addGroup(updateManagerContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(updateManagerRecordsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(updateManagerLastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(updateManagerLastNameLabel))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(updateManagerContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateManagerFirstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateManagerFirstNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(updateManagerContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateManagerEmailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateManagerEmailLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(updateManagerContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateManagerPhoneField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateManagerPhoneLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(updateManagerContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateManagerHotelIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateManagerHotelIDLabel))
                .addGap(26, 26, 26)
                .addComponent(updateManagerButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
                .addGroup(updateManagerContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateManagerContainerUpdateMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateManagerContainerMainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(129, 129, 129))
        );

        getContentPane().add(updateManagerContainer, "card4");

        updateRoomContainerLabel.setFont(new java.awt.Font("Open Sans", 1, 36)); // NOI18N
        updateRoomContainerLabel.setText("Update Rooms");

        updateRoomRecordsComboBox.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        updateRoomRecordsComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        updateRoomRecordsComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateRoomRecordsComboBoxActionPerformed(evt);
            }
        });

        updateRoomContainerInstructionsLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        updateRoomContainerInstructionsLabel.setText("Select a room via its ID from the dropdown list below:");

        updateRoomContainerMainMenuButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        updateRoomContainerMainMenuButton.setText("Return to Main Menu");
        updateRoomContainerMainMenuButton.setPreferredSize(new java.awt.Dimension(150, 40));
        updateRoomContainerMainMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateRoomContainerMainMenuButtonActionPerformed(evt);
            }
        });

        updateRoomContainerUpdateMenuButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        updateRoomContainerUpdateMenuButton.setText("Return to Update Menu");
        updateRoomContainerUpdateMenuButton.setPreferredSize(new java.awt.Dimension(150, 40));
        updateRoomContainerUpdateMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateRoomContainerUpdateMenuButtonActionPerformed(evt);
            }
        });

        updateRoomEuroLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        updateRoomEuroLabel.setText(" ");

        updateRoomPriceField.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        updateRoomPriceField.setText("Price");

        updateRoomCapacityField.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        updateRoomCapacityField.setText("Capacity");

        updateRoomSeaViewField.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        updateRoomSeaViewField.setText("Sea View");

        updateRoomHotelIDField.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        updateRoomHotelIDField.setText("Hotel ID");

        updateRoomButton.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        updateRoomButton.setText("Update Room");
        updateRoomButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateRoomButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout updateRoomContainerLayout = new javax.swing.GroupLayout(updateRoomContainer);
        updateRoomContainer.setLayout(updateRoomContainerLayout);
        updateRoomContainerLayout.setHorizontalGroup(
            updateRoomContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateRoomContainerLayout.createSequentialGroup()
                .addGap(178, 178, 178)
                .addGroup(updateRoomContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(updateRoomContainerInstructionsLabel)
                    .addComponent(updateRoomContainerLabel)
                    .addGroup(updateRoomContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(updateRoomContainerLayout.createSequentialGroup()
                            .addGap(78, 78, 78)
                            .addGroup(updateRoomContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(updateRoomContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(updateRoomContainerLayout.createSequentialGroup()
                                        .addComponent(updateRoomRecordsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(46, 46, 46)
                                        .addComponent(updateRoomEuroLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(updateRoomPriceField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(updateRoomCapacityField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(updateRoomSeaViewField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(updateRoomHotelIDField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(updateRoomContainerLayout.createSequentialGroup()
                                    .addGap(163, 163, 163)
                                    .addComponent(updateRoomButton)))
                            .addGap(29, 29, 29))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, updateRoomContainerLayout.createSequentialGroup()
                            .addGap(68, 68, 68)
                            .addComponent(updateRoomContainerMainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(updateRoomContainerUpdateMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(671, Short.MAX_VALUE))
        );
        updateRoomContainerLayout.setVerticalGroup(
            updateRoomContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateRoomContainerLayout.createSequentialGroup()
                .addGroup(updateRoomContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(updateRoomContainerLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(updateRoomContainerLabel)
                        .addGap(43, 43, 43)
                        .addComponent(updateRoomContainerInstructionsLabel))
                    .addGroup(updateRoomContainerLayout.createSequentialGroup()
                        .addGap(222, 222, 222)
                        .addGroup(updateRoomContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(updateRoomRecordsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(updateRoomPriceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(updateRoomEuroLabel))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(updateRoomCapacityField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(updateRoomSeaViewField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(updateRoomHotelIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(updateRoomButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 162, Short.MAX_VALUE)
                .addGroup(updateRoomContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateRoomContainerUpdateMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateRoomContainerMainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(129, 129, 129))
        );

        getContentPane().add(updateRoomContainer, "card4");

        deleteContainerLabel.setFont(new java.awt.Font("Open Sans", 1, 36)); // NOI18N
        deleteContainerLabel.setText("Delete Menu");

        deleteBookingsButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        deleteBookingsButton.setText("Bookings");
        deleteBookingsButton.setPreferredSize(new java.awt.Dimension(150, 40));
        deleteBookingsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBookingsButtonActionPerformed(evt);
            }
        });

        deleteGuestsButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        deleteGuestsButton.setText("Guests");
        deleteGuestsButton.setPreferredSize(new java.awt.Dimension(150, 40));
        deleteGuestsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteGuestsButtonActionPerformed(evt);
            }
        });

        deleteHotelsButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        deleteHotelsButton.setText("Hotels");
        deleteHotelsButton.setPreferredSize(new java.awt.Dimension(150, 40));
        deleteHotelsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteHotelsButtonActionPerformed(evt);
            }
        });

        deleteManagersButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        deleteManagersButton.setText("Managers");
        deleteManagersButton.setPreferredSize(new java.awt.Dimension(150, 40));
        deleteManagersButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteManagersButtonActionPerformed(evt);
            }
        });

        deleteRoomsButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        deleteRoomsButton.setText("Rooms");
        deleteRoomsButton.setPreferredSize(new java.awt.Dimension(150, 40));
        deleteRoomsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteRoomsButtonActionPerformed(evt);
            }
        });

        deleteContainerMainMenuButton.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        deleteContainerMainMenuButton.setText("Return to Main Menu");
        deleteContainerMainMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteContainerMainMenuButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout deleteContainerLayout = new javax.swing.GroupLayout(deleteContainer);
        deleteContainer.setLayout(deleteContainerLayout);
        deleteContainerLayout.setHorizontalGroup(
            deleteContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(deleteContainerLayout.createSequentialGroup()
                .addGap(451, 451, 451)
                .addGroup(deleteContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(deleteRoomsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteHotelsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteGuestsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteBookingsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteContainerLabel)
                    .addComponent(deleteManagersButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteContainerMainMenuButton))
                .addContainerGap(616, Short.MAX_VALUE))
        );
        deleteContainerLayout.setVerticalGroup(
            deleteContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(deleteContainerLayout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addComponent(deleteContainerLabel)
                .addGap(61, 61, 61)
                .addComponent(deleteBookingsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(deleteGuestsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(deleteHotelsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(deleteManagersButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(deleteRoomsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(deleteContainerMainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(169, Short.MAX_VALUE))
        );

        getContentPane().add(deleteContainer, "card3");

        deleteBookingContainerLabel.setFont(new java.awt.Font("Open Sans", 1, 36)); // NOI18N
        deleteBookingContainerLabel.setText("Delete Bookings");

        deleteBookingContainerInstructionsLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        deleteBookingContainerInstructionsLabel.setText("Select a booking via its ID from the dropdown list below:");

        deleteBookingRecordsComboBox.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        deleteBookingRecordsComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        deleteBookingRecordsComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBookingRecordsComboBoxActionPerformed(evt);
            }
        });

        deleteBookingGuestLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        deleteBookingGuestLabel.setText("Guest");

        deleteBookingGuestValueLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        deleteBookingGuestValueLabel.setText(" ");

        deleteBookingRoomsLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        deleteBookingRoomsLabel.setText("Rooms");

        deleteBookingRoomsValueLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        deleteBookingRoomsValueLabel.setText(" ");

        deleteBookingArrivalDateLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        deleteBookingArrivalDateLabel.setText("Arrival Date");

        deleteBookingArrivalDateValueLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        deleteBookingArrivalDateValueLabel.setText(" ");

        deleteBookingDepartureDateLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        deleteBookingDepartureDateLabel.setText("Departure Date");

        deleteBookingDepartureDateValueLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        deleteBookingDepartureDateValueLabel.setText(" ");

        deleteBookingButton.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        deleteBookingButton.setText("Delete Booking");
        deleteBookingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBookingButtonActionPerformed(evt);
            }
        });

        deleteBookingContainerMainMenuButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        deleteBookingContainerMainMenuButton.setText("Return to Main Menu");
        deleteBookingContainerMainMenuButton.setPreferredSize(new java.awt.Dimension(150, 40));
        deleteBookingContainerMainMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBookingContainerMainMenuButtonActionPerformed(evt);
            }
        });

        deleteBookingContainerDeleteMenuButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        deleteBookingContainerDeleteMenuButton.setText("Return to Delete Menu");
        deleteBookingContainerDeleteMenuButton.setPreferredSize(new java.awt.Dimension(150, 40));
        deleteBookingContainerDeleteMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBookingContainerDeleteMenuButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout deleteBookingContainerLayout = new javax.swing.GroupLayout(deleteBookingContainer);
        deleteBookingContainer.setLayout(deleteBookingContainerLayout);
        deleteBookingContainerLayout.setHorizontalGroup(
            deleteBookingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(deleteBookingContainerLayout.createSequentialGroup()
                .addGap(178, 178, 178)
                .addGroup(deleteBookingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(deleteBookingContainerLayout.createSequentialGroup()
                        .addGroup(deleteBookingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(deleteBookingContainerLayout.createSequentialGroup()
                                .addComponent(deleteBookingContainerMainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(deleteBookingContainerDeleteMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(deleteBookingContainerLayout.createSequentialGroup()
                                .addGap(228, 228, 228)
                                .addGroup(deleteBookingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(deleteBookingButton)
                                    .addGroup(deleteBookingContainerLayout.createSequentialGroup()
                                        .addGroup(deleteBookingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(deleteBookingGuestLabel)
                                            .addComponent(deleteBookingRoomsLabel)
                                            .addComponent(deleteBookingArrivalDateLabel)
                                            .addComponent(deleteBookingDepartureDateLabel))
                                        .addGap(40, 40, 40)
                                        .addGroup(deleteBookingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(deleteBookingDepartureDateValueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(deleteBookingArrivalDateValueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(deleteBookingRoomsValueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(deleteBookingGuestValueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(0, 428, Short.MAX_VALUE))
                    .addGroup(deleteBookingContainerLayout.createSequentialGroup()
                        .addGroup(deleteBookingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(deleteBookingContainerInstructionsLabel)
                            .addComponent(deleteBookingContainerLabel)
                            .addComponent(deleteBookingRecordsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        deleteBookingContainerLayout.setVerticalGroup(
            deleteBookingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(deleteBookingContainerLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(deleteBookingContainerLabel)
                .addGap(43, 43, 43)
                .addComponent(deleteBookingContainerInstructionsLabel)
                .addGap(59, 59, 59)
                .addGroup(deleteBookingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteBookingRecordsComboBox)
                    .addComponent(deleteBookingGuestLabel)
                    .addComponent(deleteBookingGuestValueLabel))
                .addGap(18, 18, 18)
                .addGroup(deleteBookingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteBookingRoomsLabel)
                    .addComponent(deleteBookingRoomsValueLabel))
                .addGap(18, 18, 18)
                .addGroup(deleteBookingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteBookingArrivalDateLabel)
                    .addComponent(deleteBookingArrivalDateValueLabel))
                .addGap(18, 18, 18)
                .addGroup(deleteBookingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteBookingDepartureDateLabel)
                    .addComponent(deleteBookingDepartureDateValueLabel))
                .addGap(22, 22, 22)
                .addComponent(deleteBookingButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(110, 110, 110)
                .addGroup(deleteBookingContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteBookingContainerDeleteMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteBookingContainerMainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(132, 132, 132))
        );

        getContentPane().add(deleteBookingContainer, "card4");

        deleteGuestContainerLabel.setFont(new java.awt.Font("Open Sans", 1, 36)); // NOI18N
        deleteGuestContainerLabel.setText("Delete Guests");

        deleteGuestRecordsComboBox.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        deleteGuestRecordsComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        deleteGuestRecordsComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteGuestRecordsComboBoxActionPerformed(evt);
            }
        });

        deleteGuestTitleLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        deleteGuestTitleLabel.setText("Guest Title");

        deleteGuestNameLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        deleteGuestNameLabel.setText("Guest Name");

        deleteGuestAddressLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        deleteGuestAddressLabel.setText("Guest Address");

        deleteGuestEmailLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        deleteGuestEmailLabel.setText("Guest Email");

        deleteGuestPhoneLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        deleteGuestPhoneLabel.setText("Guest Phone");

        deleteGuestContainerInstructionsLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        deleteGuestContainerInstructionsLabel.setText("Select a guest via their ID from the dropdown list below:");

        deleteGuestButton.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        deleteGuestButton.setText("Delete Guest");
        deleteGuestButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteGuestButtonActionPerformed(evt);
            }
        });

        deleteGuestContainerMainMenuButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        deleteGuestContainerMainMenuButton.setText("Return to Main Menu");
        deleteGuestContainerMainMenuButton.setPreferredSize(new java.awt.Dimension(150, 40));
        deleteGuestContainerMainMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteGuestContainerMainMenuButtonActionPerformed(evt);
            }
        });

        deleteGuestContainerDeleteMenuButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        deleteGuestContainerDeleteMenuButton.setText("Return to Delete Menu");
        deleteGuestContainerDeleteMenuButton.setPreferredSize(new java.awt.Dimension(150, 40));
        deleteGuestContainerDeleteMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteGuestContainerDeleteMenuButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout deleteGuestContainerLayout = new javax.swing.GroupLayout(deleteGuestContainer);
        deleteGuestContainer.setLayout(deleteGuestContainerLayout);
        deleteGuestContainerLayout.setHorizontalGroup(
            deleteGuestContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(deleteGuestContainerLayout.createSequentialGroup()
                .addGap(178, 178, 178)
                .addGroup(deleteGuestContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(deleteGuestContainerLayout.createSequentialGroup()
                        .addComponent(deleteGuestRecordsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(131, 131, 131)
                        .addGroup(deleteGuestContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(deleteGuestNameLabel)
                            .addComponent(deleteGuestPhoneLabel)
                            .addComponent(deleteGuestEmailLabel)
                            .addComponent(deleteGuestAddressLabel)
                            .addComponent(deleteGuestTitleLabel)
                            .addComponent(deleteGuestButton)))
                    .addComponent(deleteGuestContainerInstructionsLabel)
                    .addComponent(deleteGuestContainerLabel)
                    .addGroup(deleteGuestContainerLayout.createSequentialGroup()
                        .addComponent(deleteGuestContainerMainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(deleteGuestContainerDeleteMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(652, Short.MAX_VALUE))
        );
        deleteGuestContainerLayout.setVerticalGroup(
            deleteGuestContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(deleteGuestContainerLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(deleteGuestContainerLabel)
                .addGap(43, 43, 43)
                .addComponent(deleteGuestContainerInstructionsLabel)
                .addGap(49, 49, 49)
                .addGroup(deleteGuestContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(deleteGuestRecordsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(deleteGuestContainerLayout.createSequentialGroup()
                        .addComponent(deleteGuestTitleLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteGuestNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteGuestAddressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteGuestEmailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteGuestPhoneLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(55, 55, 55)
                .addComponent(deleteGuestButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
                .addGroup(deleteGuestContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteGuestContainerDeleteMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteGuestContainerMainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(130, 130, 130))
        );

        deleteGuestContainerLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {deleteGuestAddressLabel, deleteGuestEmailLabel, deleteGuestNameLabel, deleteGuestPhoneLabel, deleteGuestTitleLabel});

        getContentPane().add(deleteGuestContainer, "card4");

        deleteHotelContainer.setMinimumSize(new java.awt.Dimension(1280, 720));

        deleteHotelContainerLabel.setFont(new java.awt.Font("Open Sans", 1, 36)); // NOI18N
        deleteHotelContainerLabel.setText("Delete Hotels");

        deleteHotelContainerInstructionsLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        deleteHotelContainerInstructionsLabel.setText("Select a hotel via its ID from the dropdown list below:");

        deleteHotelRecordsComboBox.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        deleteHotelRecordsComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        deleteHotelRecordsComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteHotelRecordsComboBoxActionPerformed(evt);
            }
        });

        deleteHotelAddressLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        deleteHotelAddressLabel.setText("Hotel Address");

        deleteHotelButton.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        deleteHotelButton.setText("Delete Hotel");
        deleteHotelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteHotelButtonActionPerformed(evt);
            }
        });

        deleteHotelContainerMainMenuButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        deleteHotelContainerMainMenuButton.setText("Return to Main Menu");
        deleteHotelContainerMainMenuButton.setPreferredSize(new java.awt.Dimension(150, 40));
        deleteHotelContainerMainMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteHotelContainerMainMenuButtonActionPerformed(evt);
            }
        });

        deleteHotelContainerDeleteMenuButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        deleteHotelContainerDeleteMenuButton.setText("Return to Delete Menu");
        deleteHotelContainerDeleteMenuButton.setPreferredSize(new java.awt.Dimension(150, 40));
        deleteHotelContainerDeleteMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteHotelContainerDeleteMenuButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout deleteHotelContainerLayout = new javax.swing.GroupLayout(deleteHotelContainer);
        deleteHotelContainer.setLayout(deleteHotelContainerLayout);
        deleteHotelContainerLayout.setHorizontalGroup(
            deleteHotelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(deleteHotelContainerLayout.createSequentialGroup()
                .addGap(178, 178, 178)
                .addGroup(deleteHotelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(deleteHotelContainerInstructionsLabel)
                    .addComponent(deleteHotelContainerLabel)
                    .addGroup(deleteHotelContainerLayout.createSequentialGroup()
                        .addComponent(deleteHotelContainerMainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(deleteHotelContainerDeleteMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(deleteHotelContainerLayout.createSequentialGroup()
                        .addComponent(deleteHotelRecordsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78)
                        .addGroup(deleteHotelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(deleteHotelButton)
                            .addComponent(deleteHotelAddressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(447, Short.MAX_VALUE))
        );
        deleteHotelContainerLayout.setVerticalGroup(
            deleteHotelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(deleteHotelContainerLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(deleteHotelContainerLabel)
                .addGap(43, 43, 43)
                .addComponent(deleteHotelContainerInstructionsLabel)
                .addGap(59, 59, 59)
                .addGroup(deleteHotelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteHotelRecordsComboBox)
                    .addComponent(deleteHotelAddressLabel))
                .addGap(46, 46, 46)
                .addComponent(deleteHotelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90)
                .addGroup(deleteHotelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteHotelContainerDeleteMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteHotelContainerMainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(284, Short.MAX_VALUE))
        );

        getContentPane().add(deleteHotelContainer, "card4");

        deleteManagerContainerLabel.setFont(new java.awt.Font("Open Sans", 1, 36)); // NOI18N
        deleteManagerContainerLabel.setText("Delete Managers");

        deleteManagerRecordsComboBox.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        deleteManagerRecordsComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        deleteManagerRecordsComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteManagerRecordsComboBoxActionPerformed(evt);
            }
        });

        deleteManagerNameLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        deleteManagerNameLabel.setText("Manager Name");

        deleteManagerEmailLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        deleteManagerEmailLabel.setText("Manager Email");

        deleteManagerPhoneLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        deleteManagerPhoneLabel.setText("Manager Phone");

        deleteManagerHotelIDLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        deleteManagerHotelIDLabel.setText("Hotel ID");

        deleteManagerContainerInstructionsLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        deleteManagerContainerInstructionsLabel.setText("Select a manager via their ID from the dropdown list below:");

        deleteManagerButton.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        deleteManagerButton.setText("Delete Manager");
        deleteManagerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteManagerButtonActionPerformed(evt);
            }
        });

        deleteManagerContainerMainMenuButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        deleteManagerContainerMainMenuButton.setText("Return to Main Menu");
        deleteManagerContainerMainMenuButton.setPreferredSize(new java.awt.Dimension(150, 40));
        deleteManagerContainerMainMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteManagerContainerMainMenuButtonActionPerformed(evt);
            }
        });

        deleteManagerContainerDeleteMenuButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        deleteManagerContainerDeleteMenuButton.setText("Return to Delete Menu");
        deleteManagerContainerDeleteMenuButton.setPreferredSize(new java.awt.Dimension(150, 40));
        deleteManagerContainerDeleteMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteManagerContainerDeleteMenuButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout deleteManagerContainerLayout = new javax.swing.GroupLayout(deleteManagerContainer);
        deleteManagerContainer.setLayout(deleteManagerContainerLayout);
        deleteManagerContainerLayout.setHorizontalGroup(
            deleteManagerContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(deleteManagerContainerLayout.createSequentialGroup()
                .addGap(178, 178, 178)
                .addGroup(deleteManagerContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(deleteManagerContainerLayout.createSequentialGroup()
                        .addGroup(deleteManagerContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(deleteManagerContainerInstructionsLabel)
                            .addComponent(deleteManagerContainerLabel))
                        .addContainerGap(623, Short.MAX_VALUE))
                    .addGroup(deleteManagerContainerLayout.createSequentialGroup()
                        .addGroup(deleteManagerContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(deleteManagerContainerLayout.createSequentialGroup()
                                .addComponent(deleteManagerRecordsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(124, 124, 124)
                                .addGroup(deleteManagerContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(deleteManagerPhoneLabel)
                                    .addComponent(deleteManagerEmailLabel)
                                    .addComponent(deleteManagerHotelIDLabel)
                                    .addComponent(deleteManagerNameLabel)
                                    .addComponent(deleteManagerButton)))
                            .addGroup(deleteManagerContainerLayout.createSequentialGroup()
                                .addComponent(deleteManagerContainerMainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(deleteManagerContainerDeleteMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        deleteManagerContainerLayout.setVerticalGroup(
            deleteManagerContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(deleteManagerContainerLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(deleteManagerContainerLabel)
                .addGap(43, 43, 43)
                .addComponent(deleteManagerContainerInstructionsLabel)
                .addGap(48, 48, 48)
                .addGroup(deleteManagerContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(deleteManagerRecordsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(deleteManagerContainerLayout.createSequentialGroup()
                        .addComponent(deleteManagerNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteManagerEmailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteManagerPhoneLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteManagerHotelIDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(deleteManagerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 171, Short.MAX_VALUE)
                .addGroup(deleteManagerContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteManagerContainerDeleteMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteManagerContainerMainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(131, 131, 131))
        );

        deleteManagerContainerLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {deleteManagerEmailLabel, deleteManagerHotelIDLabel, deleteManagerNameLabel, deleteManagerPhoneLabel});

        getContentPane().add(deleteManagerContainer, "card4");

        deleteRoomContainerLabel.setFont(new java.awt.Font("Open Sans", 1, 36)); // NOI18N
        deleteRoomContainerLabel.setText("Delete Rooms");

        deleteRoomContainerInstructionsLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        deleteRoomContainerInstructionsLabel.setText("Select a room via its ID from the dropdown list below:");

        deleteRoomRecordsComboBox.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        deleteRoomRecordsComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        deleteRoomRecordsComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteRoomRecordsComboBoxActionPerformed(evt);
            }
        });

        deleteRoomPriceLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        deleteRoomPriceLabel.setText("Price");

        deleteRoomCapacityLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        deleteRoomCapacityLabel.setText("Capacity");

        deleteRoomSeaViewLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        deleteRoomSeaViewLabel.setText("Sea View");

        deleteRoomHotelIDLabel.setFont(new java.awt.Font("Open Sans", 0, 18)); // NOI18N
        deleteRoomHotelIDLabel.setText("Hotel ID");

        deleteRoomButton.setFont(new java.awt.Font("Open Sans", 0, 14)); // NOI18N
        deleteRoomButton.setText("Delete Room");
        deleteRoomButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteRoomButtonActionPerformed(evt);
            }
        });

        deleteRoomContainerMainMenuButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        deleteRoomContainerMainMenuButton.setText("Return to Main Menu");
        deleteRoomContainerMainMenuButton.setPreferredSize(new java.awt.Dimension(150, 40));
        deleteRoomContainerMainMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteRoomContainerMainMenuButtonActionPerformed(evt);
            }
        });

        deleteRoomContainerDeleteMenuButton.setFont(new java.awt.Font("Open Sans", 0, 12)); // NOI18N
        deleteRoomContainerDeleteMenuButton.setText("Return to Delete Menu");
        deleteRoomContainerDeleteMenuButton.setPreferredSize(new java.awt.Dimension(150, 40));
        deleteRoomContainerDeleteMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteRoomContainerDeleteMenuButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout deleteRoomContainerLayout = new javax.swing.GroupLayout(deleteRoomContainer);
        deleteRoomContainer.setLayout(deleteRoomContainerLayout);
        deleteRoomContainerLayout.setHorizontalGroup(
            deleteRoomContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(deleteRoomContainerLayout.createSequentialGroup()
                .addGap(178, 178, 178)
                .addGroup(deleteRoomContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(deleteRoomContainerLayout.createSequentialGroup()
                        .addComponent(deleteRoomRecordsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(127, 127, 127)
                        .addGroup(deleteRoomContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(deleteRoomCapacityLabel)
                            .addComponent(deleteRoomHotelIDLabel)
                            .addComponent(deleteRoomSeaViewLabel)
                            .addComponent(deleteRoomButton)
                            .addComponent(deleteRoomPriceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(deleteRoomContainerLayout.createSequentialGroup()
                        .addComponent(deleteRoomContainerMainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(deleteRoomContainerDeleteMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(deleteRoomContainerInstructionsLabel)
                    .addComponent(deleteRoomContainerLabel))
                .addContainerGap(398, Short.MAX_VALUE))
        );
        deleteRoomContainerLayout.setVerticalGroup(
            deleteRoomContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(deleteRoomContainerLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(deleteRoomContainerLabel)
                .addGap(43, 43, 43)
                .addComponent(deleteRoomContainerInstructionsLabel)
                .addGap(57, 57, 57)
                .addGroup(deleteRoomContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteRoomPriceLabel)
                    .addComponent(deleteRoomRecordsComboBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteRoomCapacityLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteRoomSeaViewLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteRoomHotelIDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(deleteRoomButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(149, 149, 149)
                .addGroup(deleteRoomContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteRoomContainerDeleteMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteRoomContainerMainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(136, 136, 136))
        );

        deleteRoomContainerLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {deleteRoomCapacityLabel, deleteRoomHotelIDLabel, deleteRoomPriceLabel, deleteRoomSeaViewLabel});

        getContentPane().add(deleteRoomContainer, "card4");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void createManagerHotelIDComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createManagerHotelIDComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_createManagerHotelIDComboBoxActionPerformed


    /*--------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

    /*

    Student code

     */

    // Main menu

    // Method to move the user from the home menu to the create menu
    private void addRecordButtonActionPerformed(java.awt.event.ActionEvent evt) {
        createContainer.setVisible(true);
        createContainerLabel.requestFocusInWindow();
        homeContainer.setVisible(false);
    }
    // Method to move the user from the home menu to the read menu
    private void viewRecordButtonActionPerformed(java.awt.event.ActionEvent evt) {
        viewContainer.setVisible(true);
        viewContainerLabel.requestFocusInWindow();
        homeContainer.setVisible(false);
    }
    // Method to move the user from the home menu to the update menu
    private void updateRecordButtonActionPerformed(java.awt.event.ActionEvent evt) {
        updateContainer.setVisible(true);
        updateContainerLabel.requestFocusInWindow();
        homeContainer.setVisible(false);
    }
    // Method to move the user from the home menu to the delete menu
    private void DeleteRecordButtonActionPerformed(java.awt.event.ActionEvent evt) {
        deleteContainer.setVisible(true);
        deleteContainerLabel.requestFocusInWindow();
        homeContainer.setVisible(false);
    }

    /*

    Create Functionality

     */

    // Method to move the user from the create menu to the home menu
    private void createContainerMainMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
        homeContainer.setVisible(true);
        createContainer.setVisible(false);
    }

    // Create Booking Functionality

    // Method to move the user from the create menu to the create booking form
    private void createBookingsButtonActionPerformed(java.awt.event.ActionEvent evt) {
        createBookingContainer.setVisible(true);
        createBookingContainerLabel.requestFocusInWindow();
        createContainer.setVisible(false);
    }
    // Method to return the user to the main menu
    private void createBookingContainerMainMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
        homeContainer.setVisible(true);
        createBookingContainer.setVisible(false);
    }
    // Method to return the user to the create menu
    private void createBookingContainerCreateMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
        createContainer.setVisible(true);
        createBookingContainer.setVisible(false);
    }
    // Method to add a room ID to a list of selected rooms in the create booking form
    private void createBookingRoomAddButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Temporarily store the object used to hold the list of selected rooms every time the button is triggered
        DefaultListModel selectedRooms = (DefaultListModel) createBookingSelectedRoomList.getModel();
        // Only add the selected value in the available rooms list to the list of selected rooms if it isn't already in the list
        if(!selectedRooms.contains(createBookingRoomList.getSelectedValue())){
            selectedRooms.addElement(createBookingRoomList.getSelectedValue());
        } else {
            // Follow the "visibility of system status" heuristic
            // If it is, inform the user
            JOptionPane.showMessageDialog(null, "The room you selected was already added to the list.");
        }
        // Reset the list of selected rooms to the temporary list to include any successfully added rooms
        createBookingSelectedRoomList.setModel(selectedRooms);
    }
    // Method to remove a room ID from a list of selected rooms in the create booking form
    private void createBookingRoomRemoveButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Temporarily store the object used to hold the list of selected rooms every time the button is triggered
        DefaultListModel selectedRooms = (DefaultListModel) createBookingSelectedRoomList.getModel();
        // First check if there is anything to remove in the list
        // If there is, then remove whatever is at the index of the selected item
        if(createBookingSelectedRoomList.getSelectedIndex() >= 0){
            selectedRooms.removeElementAt(createBookingSelectedRoomList.getSelectedIndex());
        } else {
            // Follow the "visibility of system status" heuristic
            // If the user did not select anything, inform them
            JOptionPane.showMessageDialog(null, "You did not select a room so nothing was removed.");
        }
        // Reset the list of selected rooms to the temporary list to reflect any successfully removed rooms
        createBookingSelectedRoomList.setModel(selectedRooms);
    }
    // Method to add a booking record to the database using data acquired from the user via the create booking form
    private void createBookingButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Create a temporary list to store the rooms the user selected when they were filling out the form
        ListModel model = createBookingSelectedRoomList.getModel();
        // First ensure the user selected at least one room
        if(model.getSize() > 0) {
            // Construct the strings for the dates
            String arrival = createBookingArrivalDateYearComboBox.getSelectedItem().toString()
                    + "-" +
                    createBookingArrivalDateMonthComboBox.getSelectedItem().toString()
                    + "-" +
                    createBookingArrivalDateDayComboBox.getSelectedItem().toString();
            String departure = createBookingDepartureDateYearComboBox.getSelectedItem().toString()
                    + "-" +
                    createBookingDepartureDateMonthComboBox.getSelectedItem().toString()
                    + "-" +
                    createBookingDepartureDateDayComboBox.getSelectedItem().toString();
            // Convert the strings for the dates to the appropriate Date format (java.sql.Date)
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.sql.Date arrivalDate = null;
            java.sql.Date departureDate = null;
            try {
                arrivalDate = new java.sql.Date((format.parse(arrival)).getTime());
                departureDate = new java.sql.Date((format.parse(departure)).getTime());
            } catch(ParseException  e) {
                // Follow the "visibility of system status" heuristic
                JOptionPane.showMessageDialog(null, e);
            }
            // Create a Booking object to be passed to the booking controller
            Booking booking = new Booking(Integer.parseInt(createBookingGuestIDComboBox.getSelectedItem().toString()), arrivalDate, departureDate);
            try{
                // Attempt to insert a new booking record into the booking table and store whether it was successful
                boolean result = BookingController.insertBooking(booking);
                // If it was successful, continue with the database modification process for adding a booking record
                if(result){
                    // Update all the lists of bookings used in the application
                    fillBookingRecordsComboBoxes();
                    // Follow the "visibility of system status" heuristic
                    JOptionPane.showMessageDialog(null, "The booking was successfully added to the database.");
                    // Now attempt to insert the room-booking records which are necessary because of the M:M relationship between bookings and rooms
                    try {
                        // Go through each selected room
                        for(int i = 0; i < model.getSize(); i++){
                            // Create a RoomBooking object to be passed to the room-booking controller
                            RoomBooking roomBooking = new RoomBooking(Integer.valueOf(model.getElementAt(i).toString()), booking.getBookingID());
                            // Attempt to insert a new room-booking record into the room_booking table and store whether it was successful
                            result = RoomBookingController.insertRoomBooking(roomBooking);
                            if(result){
                                // Follow the "visibility of system status" heuristic
                                // Confirm the successful insertion by providing the new ID of the room-booking record
                                // Note: This is not necessary for any of the hotel staff to know.
                                //       This message is only intended for the purposes of unit testing and verification of the application's correct operation, i.e.,
                                //       it is only seen when run via a Java IDE such as NetBeans.
                                System.out.println("The room-booking with an ID of " + roomBooking.getRoomBookingID() + " was successfully added to the database.");
                            } else {
                                // If any of attempt to add a room-booking record failed, then the booking must be removed so manually rollback the changes to the database
                                // First delete any room-booking records which may have been successfully added to the database
                                RoomBookingController.deleteRoomBookings(booking.getBookingID());
                                // Then delete the booking itself
                                BookingController.deleteBooking(booking.getBookingID());
                                // Follow the "visibility of system status" heuristic
                                JOptionPane.showMessageDialog(null, "There was an error. The booking with an ID of " + booking.getBookingID() + " was only partially added to the database and was therefore removed.");
                                // Break out of the for loop which goes through the list of selected rooms
                                break;
                            }
                        }
                    } catch(ArrayIndexOutOfBoundsException e){
                        // Follow the "visibility of system status" heuristic
                        JOptionPane.showMessageDialog(null, e);
                    }
                } else {
                    // Follow the "visibility of system status" heuristic
                    JOptionPane.showMessageDialog(null, "There was an error. Nothing was added to the database");
                }
            } catch (SQLException e) {
                // Follow the "visibility of system status" heuristic
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    // Create Guest Functionality

    // Method to move the user from the create menu to the create guest form
    private void createGuestsButtonActionPerformed(java.awt.event.ActionEvent evt) {
        createGuestContainer.setVisible(true);
        createGuestContainerLabel.requestFocusInWindow();
        createContainer.setVisible(false);
    }
    // Method to return the user to the main menu
    private void createGuestContainerMainMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
        homeContainer.setVisible(true);
        createGuestContainer.setVisible(false);
    }
    // Method to return the user to the create menu
    private void createGuestContainerCreateMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
        createContainer.setVisible(true);
        createGuestContainer.setVisible(false);
    }
    // Method to add a guest record to the database using data acquired from the user via the create guest form
    private void createGuestButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Create a Guest object to be passed to the guest controller using the form fields' values entered by the user
        Guest g = new Guest(createGuestTitleField.getText(),
                createGuestFirstNameField.getText(),
                createGuestLastNameField.getText(),
                createGuestAddressField.getText(),
                createGuestEmailField.getText(),
                createGuestPhoneField.getText()
        );
        try {
            // Attempt to insert a new guest record into the guest table and store whether it was successful
            boolean result = GuestController.insertGuest(g);
            if(result) {
                // Update all the lists of guests used in the application
                fillGuestRecordsComboBoxes();
                // Follow the "visibility of system status" heuristic
                JOptionPane.showMessageDialog(null, "The guest was successfully added to the database.");
            }
        } catch (SQLException e) {
            // Follow the "visibility of system status" heuristic
            JOptionPane.showMessageDialog(null, e);
        }
    }

    // Create Hotel Functionality

    // Method to move the user from the create menu to the create hotel form
    private void createHotelsButtonActionPerformed(java.awt.event.ActionEvent evt) {
        createHotelContainer.setVisible(true);
        createHotelContainerLabel.requestFocusInWindow();
        createContainer.setVisible(false);
    }
    // Method to return the user to the main menu
    private void createHotelContainerMainMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
        homeContainer.setVisible(true);
        createHotelContainer.setVisible(false);
    }
    // Method to return the user to the create menu
    private void createHotelContainerCreateMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
        createContainer.setVisible(true);
        createHotelContainer.setVisible(false);
    }
    // Method to add a hotel record to the database using data acquired from the user via the create hotel form
    private void createHotelButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Create a Hotel object to be passed to the hotel controller using the form field's values entered by the user
        Hotel hotel = new Hotel(createHotelAddressField.getText());
        try{
            // Attempt to insert a new hotel record into the hotel table and store whether it was successful
            boolean result = HotelController.insertHotel(hotel);
            if(result) {
                // Update all the lists of hotels used in the application
                fillHotelRecordsComboBoxes();
                // Follow the "visibility of system status" heuristic
                JOptionPane.showMessageDialog(null, "The hotel was successfully added to the database.");
            }
        } catch (SQLException e) {
            // Follow the "visibility of system status" heuristic
            JOptionPane.showMessageDialog(null, e);
        }
    }

    // Create Manager Functionality

    // Method to move the user from the create menu to the create manager form
    private void createManagersButtonActionPerformed(java.awt.event.ActionEvent evt) {
        createManagerContainer.setVisible(true);
        createManagerContainerLabel.requestFocusInWindow();
        createContainer.setVisible(false);
    }
    // Method to return the user to the main menu
    private void createManagerContainerMainMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
        homeContainer.setVisible(true);
        createManagerContainer.setVisible(false);
    }
    // Method to return the user to the create menu
    private void createManagerContainerCreateMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
        createContainer.setVisible(true);
        createManagerContainer.setVisible(false);
    }
    // Method to add a manager record to the database using data acquired from the user via the create manager form
    private void createManagerButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Create a Manager object to be passed to the manager controller using the form fields' values entered by the user
        Manager m = new Manager(createManagerFirstNameField.getText(),
                createManagerLastNameField.getText(),
                createManagerEmailField.getText(),
                createManagerPhoneField.getText(),
                Integer.parseInt(createManagerHotelIDComboBox.getSelectedItem().toString())
        );
        try {
            // Attempt to insert a new manager record into the manager table and store whether it was successful
            boolean result = ManagerController.insertManager(m);
            if(result){
                // Update all the lists of managers used in the application
                fillManagerRecordsComboBoxes();
                // Follow the "visibility of system status" heuristic
                JOptionPane.showMessageDialog(null, "The Manager was successfully added to the database.");
            }
        } catch (SQLException e) {
            // Follow the "visibility of system status" heuristic
            JOptionPane.showMessageDialog(null, e);
        }
    }

    // Create Room Functionality

    // Method to move the user from the create menu to the create room form
    private void createRoomsButtonActionPerformed(java.awt.event.ActionEvent evt) {
        createRoomContainer.setVisible(true);
        createRoomContainerLabel.requestFocusInWindow();
        createContainer.setVisible(false);
    }
    // Method to return the user to the main menu
    private void createRoomContainerMainMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
        homeContainer.setVisible(true);
        createRoomContainer.setVisible(false);
    }
    // Method to return the user to the create menu
    private void createRoomContainerCreateMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
        createContainer.setVisible(true);
        createRoomContainer.setVisible(false);
    }
    // Method to add a room record to the database using data acquired from the user via the create room form
    private void createRoomButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Create a Room object to be passed to the room controller using the form fields' values entered by the user
        Room r = new Room(Double.parseDouble(createRoomPriceField.getText()),
                Integer.parseInt(createRoomCapacityComboBox.getSelectedItem().toString()),
                createRoomSeaViewCheckBox.isSelected(),
                Integer.parseInt(createRoomHotelIDComboxBox.getSelectedItem().toString())
        );
        try {
            // Attempt to insert a new room record into the room table and store whether it was successful
            boolean result = RoomController.insertRoom(r);
            if(result) {
                // Update all the lists of rooms used in the application
                fillRoomRecordsComboBoxes();
                // Follow the "visibility of system status" heuristic
                JOptionPane.showMessageDialog(null, "A room was successfully added to the database.");
            }
        } catch (SQLException e) {
            // Follow the "visibility of system status" heuristic
            JOptionPane.showMessageDialog(null, e);
        }
    }


    /*

    Read Functionality

     */

    // Method to move the user from the read menu to the home menu
    private void viewContainerMainMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
        homeContainer.setVisible(true);
        viewContainer.setVisible(false);
    }

    // Read Booking Functionality

    // Method to move the user from the read menu to the read booking window
    private void viewBookingsButtonActionPerformed(java.awt.event.ActionEvent evt) {
        viewBookingContainer.setVisible(true);
        viewBookingContainerLabel.requestFocusInWindow();
        viewContainer.setVisible(false);
    }
    // Method to return the user to the main menu
    private void viewBookingContainerMainMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
        homeContainer.setVisible(true);
        viewBookingContainer.setVisible(false);
    }
    // Method to return the user to the read menu
    private void viewBookingContainerViewMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
        viewContainer.setVisible(true);
        viewBookingContainer.setVisible(false);
    }
    // Method to view the details of a room record in the database
    private void viewBookingRecordsComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
        // The value of the selected item of the combo box must first be checked
        // because this is initially null and would therefore cause a null pointer exception
        if(viewBookingRecordsComboBox.getSelectedItem() != null) {
            try {
                // Create a Booking object using the booking ID from the combo box showing the IDs of all the booking records in the database
                Booking booking = BookingController.getBookingRow(Integer.parseInt(viewBookingRecordsComboBox.getSelectedItem().toString()));
                // Get the list of rooms for the booking via the room-booking controller
                String rooms = RoomBookingController.getRooms(booking.getBookingID());
                // Create a Guest object using the guest ID taken from the Booking object above
                Guest guest = GuestController.getGuestRow(booking.getGuestID());
                // Populate the labels with the appropriate data
                viewBookingGuestValueLabel.setText(guest.getGuestID() + ":    " + guest.getTitle() + " " + guest.getFirstName() + " " + guest.getLastName());
                viewBookingRoomsValueLabel.setText(rooms);
                viewBookingArrivalDateValueLabel.setText(String.valueOf(booking.getArrivalDate()));
                viewBookingDepartureDateValueLabel.setText(String.valueOf(booking.getDepartureDate()));
            } catch (SQLException e) {
                // Follow the "visibility of system status" heuristic
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    // Read Guest Functionality

    // Method to move the user from the read menu to the read booking window
    private void viewGuestsButtonActionPerformed(java.awt.event.ActionEvent evt) {
        viewGuestContainer.setVisible(true);
        viewGuestContainerLabel.requestFocusInWindow();
        viewContainer.setVisible(false);
    }
    // Method to return the user to the main menu
    private void viewGuestContainerMainMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
        homeContainer.setVisible(true);
        viewGuestContainer.setVisible(false);
    }
    // Method to return the user to the read menu
    private void viewGuestContainerViewMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
        viewContainer.setVisible(true);
        viewGuestContainer.setVisible(false);
    }
    // Method to view the details of a guest record in the database
    private void viewGuestRecordsComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
        // The value of the selected item of the combo box must first be checked
        // because this is initially null and would therefore cause a null pointer exception
        if(viewGuestRecordsComboBox.getSelectedItem() != null) {
            try{
                // Create a Guest object using the guest ID from the combo box showing the IDs of all the guest records in the database
                Guest guest = GuestController.getGuestRow(Integer.parseInt(viewGuestRecordsComboBox.getSelectedItem().toString()));
                // Populate the labels with the appropriate data
                viewGuestTitleLabel.setText(guest.getTitle());
                viewGuestNameLabel.setText(guest.getLastName() + ", " + guest.getFirstName());
                viewGuestAddressLabel.setText(guest.getAddress());
                viewGuestEmailLabel.setText(guest.getEmail());
                viewGuestPhoneLabel.setText(guest.getPhone());
            } catch(SQLException e){
                // Follow the "visibility of system status" heuristic
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    // Read Hotel Functionality

    // Method to move the user from the read menu to the read hotel window
    private void viewHotelsButtonActionPerformed(java.awt.event.ActionEvent evt) {
        viewHotelContainer.setVisible(true);
        viewHotelContainerLabel.requestFocusInWindow();
        viewContainer.setVisible(false);
    }
    // Method to return the user to the main menu
    private void viewHotelContainerMainMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
        homeContainer.setVisible(true);
        viewHotelContainer.setVisible(false);
    }
    // Method to return the user to the read menu
    private void viewHotelContainerViewMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
        viewContainer.setVisible(true);
        viewHotelContainer.setVisible(false);
    }
    // Method to view the details of a hotel record in the database
    private void viewHotelRecordsComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
        // The value of the selected item of the combo box must first be checked
        // because this is initially null and would therefore cause a null pointer exception
        if(viewHotelRecordsComboBox.getSelectedItem() != null) {
            try{
                // Create a Hotel object using the hotel ID from the combo box showing the IDs of all the hotel records in the database
                Hotel hotel = HotelController.getHotelRow(Integer.parseInt(viewHotelRecordsComboBox.getSelectedItem().toString()));
                // Populate the label with the appropriate data
                viewHotelAddressLabel.setText(String.valueOf(hotel.getAddress()));
            } catch(SQLException e){
                // Follow the "visibility of system status" heuristic
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    // Read Manager Functionality

    // Method to move the user from the read menu to the read manager window
    private void viewManagersButtonActionPerformed(java.awt.event.ActionEvent evt) {
        viewManagerContainer.setVisible(true);
        viewManagerContainerLabel.requestFocusInWindow();
        viewContainer.setVisible(false);
    }
    // Method to return the user to the main menu
    private void viewManagerContainerMainMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
        homeContainer.setVisible(true);
        viewManagerContainer.setVisible(false);
    }
    // Method to return the user to the read menu
    private void viewManagerContainerViewMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
        viewContainer.setVisible(true);
        viewManagerContainer.setVisible(false);
    }
    // Method to view the details of a manager record in the database
    private void viewManagerRecordsComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
        // The value of the selected item of the combo box must first be checked
        // because this is initially null and would therefore cause a null pointer exception
        if(viewManagerRecordsComboBox.getSelectedItem() != null) {
            try{
                // Create a Manager object using the manager ID from the combo box showing the IDs of all the manager records in the database
                Manager manager = ManagerController.getManagerRow(Integer.parseInt(viewManagerRecordsComboBox.getSelectedItem().toString()));
                // Populate the labels with the appropriate data
                viewManagerNameLabel.setText(manager.getLastName() + ", " + manager.getFirstName());
                viewManagerEmailLabel.setText(manager.getEmail());
                viewManagerPhoneLabel.setText(manager.getPhone());
                viewManagerHotelIDLabel.setText(String.valueOf(manager.getHotelID()));
            } catch(SQLException e){
                // Follow the "visibility of system status" heuristic
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    // Read Room Functionality

    // Method to move the user from the read menu to the read room window
    private void viewRoomsButtonActionPerformed(java.awt.event.ActionEvent evt) {
        viewRoomContainer.setVisible(true);
        viewRoomContainerLabel.requestFocusInWindow();
        viewContainer.setVisible(false);
    }
    // Method to return the user to the main menu
    private void viewRoomContainerMainMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
        homeContainer.setVisible(true);
        viewRoomContainer.setVisible(false);
    }
    // Method to return the user to the read menu
    private void viewRoomContainerViewMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
        viewContainer.setVisible(true);
        viewRoomContainer.setVisible(false);
    }
    // Method to view the details of a room record in the database
    private void viewRoomRecordsComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
        // The value of the selected item of the combo box must first be checked
        // because this is initially null and would therefore cause a null pointer exception
        if(viewRoomRecordsComboBox.getSelectedItem() != null) {
            try{
                // Create a Room object using the room ID from the combo box showing the IDs of all the room records in the database
                Room room = RoomController.getRoomRow(Integer.parseInt(viewRoomRecordsComboBox.getSelectedItem().toString()));
                // Populate the labels with the appropriate data
                viewRoomPriceLabel.setText("\u20ac" + String.valueOf(room.getPrice()));
                viewRoomCapacityLabel.setText(String.valueOf(room.getCapacity()));
                viewRoomSeaViewLabel.setText(String.valueOf(room.getSeaView()));
                viewRoomHotelIDLabel.setText(String.valueOf(room.getHotelID()));
            } catch(SQLException e){
                // Follow the "visibility of system status" heuristic
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }


    /*

    Update Functionality

     */

    // Method to move the user from the update menu to the home menu
    private void updateContainerMainMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
        homeContainer.setVisible(true);
        updateContainer.setVisible(false);
    }

    // Update Booking Functionality

    // Method to move the user from the update menu to the update booking window
    private void updateBookingsButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Call the method to populate the form fields here to ensure the details of the first booking in the list are shown
        updateBookingRecordsComboBoxActionPerformed(evt);
        updateBookingContainer.setVisible(true);
        updateBookingContainerLabel.requestFocusInWindow();
        updateContainer.setVisible(false);
    }
    // Method to return the user to the main menu
    private void updateBookingContainerMainMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
        homeContainer.setVisible(true);
        updateBookingContainer.setVisible(false);
    }
    // Method to return the user to the update menu
    private void updateBookingContainerUpdateMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
        updateContainer.setVisible(true);
        updateBookingContainer.setVisible(false);
    }
    // Method to view the details of a booking record in the database in the update booking form
    private void updateBookingRecordsComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
        // The value of the selected item of the combo box must first be checked
        // because this is initially null and would therefore cause a null pointer exception
        if(updateBookingRecordsComboBox.getSelectedItem() != null){
            try{
                // Create a Booking object using the booking ID from the combo box showing the IDs of all the booking records in the database
                Booking booking = BookingController.getBookingRow(Integer.parseInt(updateBookingRecordsComboBox.getSelectedItem().toString()));
                // Create a Guest object using the guest ID stored in the booking record
                Guest guest = GuestController.getGuestRow(booking.getGuestID());
                // Temporarily store the object used to hold the list of selected rooms
                DefaultListModel selectedRooms = new DefaultListModel();
                //  Get the rooms which are part of the room-booking records with the requested booking ID
                // and split them to get an array in which each element is one of the rooms
                String[] rooms = RoomBookingController.getRooms(booking.getBookingID()).split(",");
                // Use a for each loop to add each room to the list
                for(String room: rooms) {
                    selectedRooms.addElement(room);
                }
                // Populate the form fields with the appropriate data
                // Get the dates from the database, then convert them to a string
                // and then split that string by the delimiting character set in the database to extract the individual values
                String[] arrivalValues = booking.getArrivalDate().toString().split("-");
                String[] departureValues = booking.getDepartureDate().toString().split("-");
                // Populate the controls with the appropriate data
                // Assign each of the three values (year, month and day) to the appropriate combo box
                updateBookingArrivalDateDayComboBox.setSelectedItem(arrivalValues[2]);
                updateBookingArrivalDateMonthComboBox.setSelectedItem(arrivalValues[1]);
                updateBookingArrivalDateYearComboBox.setSelectedItem(arrivalValues[0]);
                updateBookingDepartureDateDayComboBox.setSelectedItem(departureValues[2]);
                updateBookingDepartureDateMonthComboBox.setSelectedItem(departureValues[1]);
                updateBookingDepartureDateYearComboBox.setSelectedItem(departureValues[0]);
                updateBookingGuestIDComboBox.setSelectedItem(guest.getGuestID() + "");
                updateBookingGuestNameLabel.setText(guest.getTitle() + " " + guest.getFirstName() + " " + guest.getLastName());
                updateBookingSelectedRoomList.setModel(selectedRooms);
            } catch(SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    // Method to add a room ID to a list of selected rooms in the update booking form
    private void updateBookingRoomAddButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Temporarily store the object used to hold the list of selected rooms every time the button is triggered
        DefaultListModel selectedRooms = (DefaultListModel) updateBookingSelectedRoomList.getModel();
        // Only add the selected value in the available rooms list to the list of selected rooms if it isn't already in the list
        if(!selectedRooms.contains(updateBookingRoomList.getSelectedValue())){
            selectedRooms.addElement(updateBookingRoomList.getSelectedValue());
        } else {
            // Follow the "visibility of system status" heuristic
            // If it is, inform the user
            JOptionPane.showMessageDialog(null, "The room you selected was already added to the list.");
        }
        // Reset the list of selected rooms to the temporary list to include any successfully added rooms
        updateBookingSelectedRoomList.setModel(selectedRooms);
    }
    // Method to remove a room ID from a list of selected rooms in the update booking form
    private void updateBookingRoomRemoveButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Temporarily store the object used to hold the list of selected rooms every time the button is triggered
        DefaultListModel selectedRooms = (DefaultListModel) updateBookingSelectedRoomList.getModel();
        // First check if there is anything to remove in the list
        // If there is, then remove whatever is at the index of the selected item
        if(updateBookingSelectedRoomList.getSelectedIndex() >= 0){
            selectedRooms.removeElementAt(updateBookingSelectedRoomList.getSelectedIndex());
        } else {
            // Follow the "visibility of system status" heuristic
            // If the user did not select anything, inform them
            JOptionPane.showMessageDialog(null, "You did not select a room so nothing was removed.");
        }
        // Reset the list of selected rooms to the temporary list to reflect any successfully removed rooms
        updateBookingSelectedRoomList.setModel(selectedRooms);
    }
    // Method to view the name fields of a guest record in the database in the update booking window
    private void updateBookingGuestIDComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
        // The value of the selected item of the combo box must first be checked
        // because this is initially null and would therefore cause a null pointer exception
        if(updateBookingGuestIDComboBox.getSelectedItem() != null){
            try {
                // Create a Guest object using the guest ID from the combo box showing the IDs of all the guest records in the database
                Guest guest = GuestController.getGuestRow(Integer.parseInt(updateBookingGuestIDComboBox.getSelectedItem().toString()));
                // Populate the label with the appropriate data
                updateBookingGuestNameLabel.setText(guest.getTitle() + " " + guest.getFirstName() + " " + guest.getLastName());
            } catch (SQLException e) {
                // Follow the "visibility of system status" heuristic
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    // Method to update a booking record in the database using data
    private void updateBookingButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Create a temporary list to store the rooms the user selected when they were filling out the form
        ListModel model = updateBookingSelectedRoomList.getModel();
        // First ensure the user selected at least one room
        if(model.getSize() > 0) {
            // Construct the strings for the dates
            String arrival = updateBookingArrivalDateYearComboBox.getSelectedItem().toString()
                    + "-" +
                    updateBookingArrivalDateMonthComboBox.getSelectedItem().toString()
                    + "-" +
                    updateBookingArrivalDateDayComboBox.getSelectedItem().toString();
            String departure = updateBookingDepartureDateYearComboBox.getSelectedItem().toString()
                    + "-" +
                    updateBookingDepartureDateMonthComboBox.getSelectedItem().toString()
                    + "-" +
                    updateBookingDepartureDateDayComboBox.getSelectedItem().toString();
            // Convert the strings for the dates to the appropriate Date format (java.sql.Date)
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.sql.Date arrivalDate = null;
            java.sql.Date departureDate = null;
            try {
                arrivalDate = new java.sql.Date((format.parse(arrival)).getTime());
                departureDate = new java.sql.Date((format.parse(departure)).getTime());
            } catch(ParseException  e) {
                // Follow the "visibility of system status" heuristic
                JOptionPane.showMessageDialog(null, e);
            }
            // Store the booking ID in a variable because the ID will be used multiple times in the rest of the method and this makes the code more readable
            int bookingID = Integer.parseInt(updateBookingRecordsComboBox.getSelectedItem().toString());
            // Create a Booking object to be passed to the booking controller
            Booking booking = new Booking(bookingID, Integer.parseInt(updateBookingGuestIDComboBox.getSelectedItem().toString()), arrivalDate, departureDate);
            try{
                // Attempt to update the booking record in the booking table and store whether it was successful
                boolean result = BookingController.updateBooking(booking);
                // If it was successful, continue with the database modification process for adding a booking record
                if(result){
                    // Update all the lists of bookings used in the application
                    fillBookingRecordsComboBoxes();
                    // Follow the "visibility of system status" heuristic
                    JOptionPane.showMessageDialog(null, "The booking was successfully updated.");
                    // Now attempt to update the room-booking records which are necessary because of the M:M relationship between bookings and rooms
                    // Note: The room-booking records will not be directly updated to reduce the complexity of the application.
                    //       Instead, they will first be deleted and then the list of selected rooms will replace them.
                    try {
                        // Delete all the room-booking records with a booking ID of the booking to be updated
                        RoomBookingController.deleteRoomBookings(bookingID);
                        // Go through each selected room
                        for(int i = 0; i < model.getSize(); i++){
                            // Create a RoomBooking object to be passed to the room-booking controller
                            RoomBooking roomBooking = new RoomBooking(Integer.valueOf(model.getElementAt(i).toString()), bookingID);
                            // Attempt to insert a new room-booking record into the room_booking table and store whether it was successful
                            result = RoomBookingController.insertRoomBooking(roomBooking);
                            if(result){
                                // Follow the "visibility of system status" heuristic
                                // Confirm the successful insertion by providing the new ID of the room-booking record
                                // Note: This is not necessary for any of the hotel staff to know.
                                //       This message is only intended for the purposes of unit testing and verification of the application's correct operation, i.e.,
                                //       it is only seen when run via a Java IDE such as NetBeans.
                                System.out.println("A room-booking for the booking with an ID of " + bookingID + " was successfully created.");
                            } else {
                                // Follow the "visibility of system status" heuristic
                                // If any of attempt to add a room-booking record failed, then inform the user
                                JOptionPane.showMessageDialog(null, "There was an error. The room with an ID of " + model.getElementAt(i).toString() + " was not successfully included in the booking update.");
                            }
                        }
                    } catch(SQLException e){
                        // Follow the "visibility of system status" heuristic
                        JOptionPane.showMessageDialog(null, e);
                    }
                } else {
                    // Follow the "visibility of system status" heuristic
                    JOptionPane.showMessageDialog(null, "There was an error. Nothing was updated.");
                }
            } catch (SQLException e) {
                // Follow the "visibility of system status" heuristic
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    // Update Guest Functionality

    // Method to move the user from the update menu to the update guest window
    private void updateGuestsButtonActionPerformed(java.awt.event.ActionEvent evt) {
        updateGuestContainer.setVisible(true);
        updateGuestContainerLabel.requestFocusInWindow();
        updateContainer.setVisible(false);
    }
    // Method to return the user to the main menu
    private void updateGuestContainerMainMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
        homeContainer.setVisible(true);
        updateGuestContainer.setVisible(false);
    }
    // Method to return the user to the update menu
    private void updateGuestContainerUpdateMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
        updateContainer.setVisible(true);
        updateGuestContainer.setVisible(false);
    }
    // Method to view the details of a guest record in the database in the update guest form
    private void updateGuestRecordsComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
        // The value of the selected item of the combo box must first be checked
        // because this is initially null and would therefore cause a null pointer exception
        if(updateGuestRecordsComboBox.getSelectedItem() != null) {
            try{
                // Create a Guest object using the guest ID from the combo box showing the IDs of all the guest records in the database
                Guest guest = GuestController.getGuestRow(Integer.parseInt(updateGuestRecordsComboBox.getSelectedItem().toString()));
                // Populate the fields with the appropriate data
                updateGuestTitleField.setText(guest.getTitle());
                updateGuestFirstNameField.setText(guest.getFirstName());
                updateGuestLastNameField.setText(guest.getLastName());
                updateGuestAddressField.setText(guest.getAddress());
                updateGuestEmailField.setText(guest.getEmail());
                updateGuestPhoneField.setText(guest.getPhone());
            } catch(SQLException e){
                // Follow the "visibility of system status" heuristic
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    // Method to update a guest record in the database
    private void updateGuestButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Create a Guest object to be passed to the guest controller using the form fields' values entered by the user
        Guest guest = new Guest(
                Integer.parseInt(updateGuestRecordsComboBox.getSelectedItem().toString()),
                updateGuestTitleField.getText(),
                updateGuestFirstNameField.getText(),
                updateGuestLastNameField.getText(),
                updateGuestAddressField.getText(),
                updateGuestEmailField.getText(),
                updateGuestPhoneField.getText()
        );
        try {
            // Attempt to update the guest record in the guest table and store whether it was successful
            boolean result = GuestController.updateGuest(guest);
            if(result) {
                // Update all the lists of guests used in the application
                fillGuestRecordsComboBoxes();
                // Follow the "visibility of system status" heuristic
                JOptionPane.showMessageDialog(null, "The guest with the ID of " + guest.getGuestID() + " was successfully updated.");
            }
        } catch (SQLException e) {
            // Follow the "visibility of system status" heuristic
            JOptionPane.showMessageDialog(null, e);
        }
    }

    // Update Hotel Functionality

    // Method to move the user from the update menu to the update hotel window
    private void updateHotelsButtonActionPerformed(java.awt.event.ActionEvent evt) {
        updateHotelContainer.setVisible(true);
        updateHotelContainerLabel.requestFocusInWindow();
        updateContainer.setVisible(false);
    }
    // Method to return the user to the main menu
    private void updateHotelContainerMainMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
        homeContainer.setVisible(true);
        updateHotelContainer.setVisible(false);
    }
    // Method to return the user to the update menu
    private void updateHotelContainerUpdateMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
        updateContainer.setVisible(true);
        updateHotelContainer.setVisible(false);
    }
    // Method to view the details of a hotel record in the database in the update hotel form
    private void updateHotelRecordsComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
        // The value of the selected item of the combo box must first be checked
        // because this is initially null and would therefore cause a null pointer exception
        if(updateHotelRecordsComboBox.getSelectedItem() != null) {
            try{
                // Create a Hotel object using the hotel ID from the combo box showing the IDs of all the hotel records in the database
                Hotel room = HotelController.getHotelRow(Integer.parseInt(updateHotelRecordsComboBox.getSelectedItem().toString()));
                // Populate the field with the appropriate data
                updateHotelAddressField.setText(String.valueOf(room.getAddress()));
            } catch(SQLException e){
                // Follow the "visibility of system status" heuristic
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    // Method to update a hotel record from the database
    private void updateHotelButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Create a Guest object to be passed to the guest controller using the form fields' values entered by the user
        Hotel hotel = new Hotel(
                Integer.parseInt(updateHotelRecordsComboBox.getSelectedItem().toString()),
                updateHotelAddressField.getText()
        );
        try {
            // Attempt to update the hotel record in the hotel table and store whether it was successful
            boolean result = HotelController.updateHotel(hotel);
            if(result) {
                // Update all the lists of hotels used in the application
                fillHotelRecordsComboBoxes();
                // Follow the "visibility of system status" heuristic
                JOptionPane.showMessageDialog(null, "The hotel with the ID of " + hotel.getHotelID() + " was successfully updated.");
            }
        } catch (SQLException e) {
            // Follow the "visibility of system status" heuristic
            JOptionPane.showMessageDialog(null, e);
        }
    }

    // Update Manager Functionality

    // Method to move the user from the update menu to the update manager window
    private void updateManagersButtonActionPerformed(java.awt.event.ActionEvent evt) {
        updateManagerContainer.setVisible(true);
        updateManagerContainerLabel.requestFocusInWindow();
        updateContainer.setVisible(false);
    }
    // Method to return the user to the main menu
    private void updateManagerContainerMainMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
        homeContainer.setVisible(true);
        updateManagerContainer.setVisible(false);
    }
    // Method to return the user to the update menu
    private void updateManagerContainerUpdateMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
        updateContainer.setVisible(true);
        updateManagerContainer.setVisible(false);
    }
    // Method to view the details of a manager record in the database in the update manager form
    private void updateManagerRecordsComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
        // The value of the selected item of the combo box must first be checked
        // because this is initially null and would therefore cause a null pointer exception
        if(updateManagerRecordsComboBox.getSelectedItem() != null) {
            try{
                // Create a Manager object using the manager ID from the combo box showing the IDs of all the manager records in the database
                Manager manager = ManagerController.getManagerRow(Integer.parseInt(updateManagerRecordsComboBox.getSelectedItem().toString()));
                // Populate the fields with the appropriate data
                updateManagerFirstNameField.setText(manager.getFirstName());
                updateManagerLastNameField.setText(manager.getLastName());
                updateManagerEmailField.setText(manager.getEmail());
                updateManagerPhoneField.setText(manager.getPhone());
                updateManagerHotelIDField.setText(String.valueOf(manager.getHotelID()));
            } catch(SQLException e){
                // Follow the "visibility of system status" heuristic
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    // Method to update a manager record from the database
    private void updateManagerButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Create a Guest object to be passed to the guest controller using the form fields' values entered by the user
        Manager manager = new Manager(
                Integer.parseInt(updateManagerRecordsComboBox.getSelectedItem().toString()),
                updateManagerFirstNameField.getText(),
                updateManagerLastNameField.getText(),
                updateManagerEmailField.getText(),
                updateManagerPhoneField.getText(),
                Integer.parseInt(updateManagerHotelIDField.getText())
        );
        try {
            // Attempt to update the manager record in the manager table and store whether it was successful
            boolean result = ManagerController.updateManager(manager);
            if(result) {
                // Update all the lists of managers used in the application
                fillManagerRecordsComboBoxes();
                // Follow the "visibility of system status" heuristic
                JOptionPane.showMessageDialog(null, "The manager with the ID of " + manager.getManagerID() + " was successfully updated.");
            }
        } catch (SQLException e) {
            // Follow the "visibility of system status" heuristic
            JOptionPane.showMessageDialog(null, e);
        }
    }

    // Update Room Functionality

    // Method to move the user from the update menu to the update room window
    private void updateRoomsButtonActionPerformed(java.awt.event.ActionEvent evt) {
        updateRoomContainer.setVisible(true);
        updateRoomContainerLabel.requestFocusInWindow();
        updateContainer.setVisible(false);
    }
    // Method to return the user to the main menu
    private void updateRoomContainerMainMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
        homeContainer.setVisible(true);
        updateRoomContainer.setVisible(false);
    }
    // Method to return the user to the update menu
    private void updateRoomContainerUpdateMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
        updateContainer.setVisible(true);
        updateRoomContainer.setVisible(false);
    }
    // Method to view the details of a room record in the database in the update room form
    private void updateRoomRecordsComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
        // The value of the selected item of the combo box must first be checked
        // because this is initially null and would therefore cause a null pointer exception
        if(updateRoomRecordsComboBox.getSelectedItem() != null) {
            try{
                // Create a Room object using the room ID from the combo box showing the IDs of all the room records in the database
                Room room = RoomController.getRoomRow(Integer.parseInt(updateRoomRecordsComboBox.getSelectedItem().toString()));
                // Populate the fields with the appropriate data
                // Add the Euro currency symbol to a label preceding the price field
                updateRoomEuroLabel.setText("\u20ac");
                updateRoomPriceField.setText(String.valueOf(room.getPrice()));
                updateRoomCapacityField.setText(String.valueOf(room.getCapacity()));
                updateRoomSeaViewField.setText(String.valueOf(room.getSeaView()));
                updateRoomHotelIDField.setText(String.valueOf(room.getHotelID()));
            } catch(SQLException e){
                // Follow the "visibility of system status" heuristic
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    // Method to update a room record from the database
    private void updateRoomButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Create a Guest object to be passed to the guest controller using the form fields' values entered by the user
        Room room = new Room(
                Integer.parseInt(updateRoomRecordsComboBox.getSelectedItem().toString()),
                Double.parseDouble(updateRoomPriceField.getText()),
                Integer.parseInt(updateRoomCapacityField.getText()),
                Boolean.parseBoolean(updateRoomSeaViewField.getText()),
                Integer.parseInt(updateRoomHotelIDField.getText())
        );
        try {
            // Attempt to update the room record in the room table and store whether it was successful
            boolean result = RoomController.updateRoom(room);
            if(result) {
                // Update all the lists of rooms used in the application
                fillRoomRecordsComboBoxes();
                // Follow the "visibility of system status" heuristic
                JOptionPane.showMessageDialog(null, "The room with the ID of " + room.getRoomID() + " was successfully updated.");
            }
        } catch (SQLException e) {
            // Follow the "visibility of system status" heuristic
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /*

    Delete Functionality

     */

    // Method to move the user from the delete menu to the home menu
    private void deleteContainerMainMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
        homeContainer.setVisible(true);
        deleteContainer.setVisible(false);
    }

    // Delete Booking Functionality

    // Method to move the user from the delete menu to the delete booking window
    private void deleteBookingsButtonActionPerformed(java.awt.event.ActionEvent evt) {
        deleteBookingContainer.setVisible(true);
        deleteBookingContainerLabel.requestFocusInWindow();
        deleteContainer.setVisible(false);
    }
    // Method to return the user to the main menu
    private void deleteBookingContainerMainMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
        homeContainer.setVisible(true);
        deleteBookingContainer.setVisible(false);
    }
    // Method to return the user to the delete menu
    private void deleteBookingContainerDeleteMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
        deleteContainer.setVisible(true);
        deleteBookingContainer.setVisible(false);
    }
    // Method to view the details of a room record in the database in the delete booking window
    private void deleteBookingRecordsComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
        // The value of the selected item of the combo box must first be checked
        // because this is initially null and would therefore cause a null pointer exception
        if(deleteBookingRecordsComboBox.getSelectedItem() != null) {
            try {
                // Create a Booking object using the booking ID from the combo box showing the IDs of all the booking records in the database
                Booking booking = BookingController.getBookingRow(Integer.parseInt(deleteBookingRecordsComboBox.getSelectedItem().toString()));
                // Get the list of rooms for the booking via the room-booking controller
                String rooms = RoomBookingController.getRooms(booking.getBookingID());
                // Create a Guest object using the guest ID taken from the Booking object above
                Guest guest = GuestController.getGuestRow(booking.getGuestID());
                // Populate the labels with the appropriate data
                deleteBookingGuestValueLabel.setText(guest.getGuestID() + ":    " + guest.getTitle() + " " + guest.getFirstName() + " " + guest.getLastName());
                deleteBookingRoomsValueLabel.setText(rooms);
                deleteBookingArrivalDateValueLabel.setText(String.valueOf(booking.getArrivalDate()));
                deleteBookingDepartureDateValueLabel.setText(String.valueOf(booking.getDepartureDate()));
            } catch (SQLException e) {
                // Follow the "visibility of system status" heuristic
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    // Method to delete a booking record from the database
    private void deleteBookingButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Store the ID of the booking the user requested deleted
        int bookingID = Integer.parseInt(deleteBookingRecordsComboBox.getSelectedItem().toString());
        try {
            // Attempt to delete all room-booking records with the booking ID of the selected booking from the room_booking table and store whether it was successful
            boolean delete = RoomBookingController.deleteRoomBookings(Integer.parseInt(deleteBookingRecordsComboBox.getSelectedItem().toString()));
            // If it was successful, continue with the database modification process for deleting a booking record
            if(delete) {
                // Attempt to delete the booking with the booking ID of the selected booking from the booking table and store whether it was successful
                delete = BookingController.deleteBooking(bookingID);
                // If it was successful, continue with the database modification process for deleting a booking record
                if(delete) {
                    // Update all the lists of bookings used in the application
                    fillBookingRecordsComboBoxes();
                    // Check if all booking records were deleted
                    // If so, then reset the label to inform the user
                    if (deleteGuestRecordsComboBox.getSelectedItem() == null) {
                        // Follow the "visibility of system status" heuristic
                        deleteBookingGuestValueLabel.setText("All bookings have been deleted from the database.");
                        deleteBookingRoomsValueLabel.setText("");
                        deleteBookingArrivalDateValueLabel.setText("");
                        deleteBookingDepartureDateValueLabel.setText("");
                    }
                    // Follow the "visibility of system status" heuristic
                    JOptionPane.showMessageDialog(null, "The booking was successfully deleted.");
                } else {
                    // Follow the "visibility of system status" heuristic
                    JOptionPane.showMessageDialog(null, "There was an error. The booking with an ID of " + bookingID + " was not deleted.");
                }
            } else {
                // Follow the "visibility of system status" heuristic
                JOptionPane.showMessageDialog(null, "There was an error. The room-bookings for the booking with an ID of " + bookingID + " were not deleted.");
            }
        } catch (SQLException e) {
            // Follow the "visibility of system status" heuristic
            JOptionPane.showMessageDialog(null, "There was an error. Nothing was deleted.");
        }
    }

    // Delete Guest Functionality

    // Method to move the user from the delete menu to the delete guest window
    private void deleteGuestsButtonActionPerformed(java.awt.event.ActionEvent evt) {
        deleteGuestContainer.setVisible(true);
        deleteGuestContainerLabel.requestFocusInWindow();
        deleteContainer.setVisible(false);
    }
    // Method to return the user to the main menu
    private void deleteGuestContainerMainMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
        homeContainer.setVisible(true);
        deleteGuestContainer.setVisible(false);
    }
    // Method to return the user to the delete menu
    private void deleteGuestContainerDeleteMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
        deleteContainer.setVisible(true);
        deleteGuestContainer.setVisible(false);
    }
    // Method to view the details of a guest record in the database in the delete guest window
    private void deleteGuestRecordsComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
        // The value of the selected item of the combo box must first be checked
        // because this is initially null and would therefore cause a null pointer exception
        if(deleteGuestRecordsComboBox.getSelectedItem() != null) {
            try {
                // Create a Guest object using the guest ID from the combo box showing the IDs of all the guest records in the database
                Guest guest = GuestController.getGuestRow(Integer.parseInt(deleteGuestRecordsComboBox.getSelectedItem().toString()));
                // Populate the labels with the appropriate data
                deleteGuestTitleLabel.setText(guest.getTitle());
                deleteGuestNameLabel.setText(guest.getFirstName() + " " + guest.getLastName());
                deleteGuestAddressLabel.setText(guest.getAddress());
                deleteGuestEmailLabel.setText(guest.getEmail());
                deleteGuestPhoneLabel.setText(guest.getPhone());
            } catch (SQLException e) {
                // Follow the "visibility of system status" heuristic
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    // Method to delete a booking record from the database
    private void deleteGuestButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Store the ID of the guest the user requested deleted
        int guestID = Integer.parseInt(deleteGuestRecordsComboBox.getSelectedItem().toString());
        try {
            // Attempt to delete the guest with the guest ID of the selected guest from the guest table and store whether it was successful
            boolean result = GuestController.deleteGuest(guestID);
            if(result) {
                // Update all the lists of guests used in the application
                fillGuestRecordsComboBoxes();
                // Check if all guest records were deleted
                // If so, then reset the label to inform the user
                if (deleteGuestRecordsComboBox.getSelectedItem() == null) {
                    // Follow the "visibility of system status" heuristic
                    deleteGuestTitleLabel.setText("All guests have been deleted from the database.");
                    deleteGuestNameLabel.setText("");
                    deleteGuestAddressLabel.setText("");
                    deleteGuestEmailLabel.setText("");
                    deleteGuestPhoneLabel.setText("");
                }
                // Follow the "visibility of system status" heuristic
                JOptionPane.showMessageDialog(null, "The guest was successfully deleted.");
            }
        } catch (SQLException e) {
            // Follow the "visibility of system status" heuristic
            JOptionPane.showMessageDialog(null, e);
        }
    }

    // Delete Hotel Functionality

    // Method to move the user from the delete menu to the delete hotel window
    private void deleteHotelsButtonActionPerformed(java.awt.event.ActionEvent evt) {
        deleteHotelContainer.setVisible(true);
        deleteHotelContainerLabel.requestFocusInWindow();
        deleteContainer.setVisible(false);
    }
    // Method to return the user to the main menu
    private void deleteHotelContainerMainMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
        homeContainer.setVisible(true);
        deleteHotelContainer.setVisible(false);
    }
    // Method to return the user to the delete menu
    private void deleteHotelContainerDeleteMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
        deleteContainer.setVisible(true);
        deleteHotelContainer.setVisible(false);
    }
    // Method to view the details of a hotel record in the database in the delete hotel window
    private void deleteHotelRecordsComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
        // The value of the selected item of the combo box must first be checked
        // because this is initially null and would therefore cause a null pointer exception
        if(deleteHotelRecordsComboBox.getSelectedItem() != null) {
            try{
                // Create a Hotel object using the hotel ID from the combo box showing the IDs of all the hotel records in the database
                Hotel hotel = HotelController.getHotelRow(Integer.parseInt(deleteHotelRecordsComboBox.getSelectedItem().toString()));
                // Populate the label with the appropriate data
                deleteHotelAddressLabel.setText(String.valueOf(hotel.getAddress()));
            } catch(SQLException e){
                // Follow the "visibility of system status" heuristic
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    // Method to delete a  record from the database
    private void deleteHotelButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Store the ID of the hotel the user requested deleted
        int hotelID = Integer.parseInt(deleteHotelRecordsComboBox.getSelectedItem().toString());
        try{
            // Attempt to delete the hotel with the hotel ID of the selected hotel from the hotel table and store whether it was successful
            boolean result = HotelController.deleteHotel(hotelID);
            if(result) {
                // Update all the lists of hotels used in the application
                fillHotelRecordsComboBoxes();
                // Check if all hotel records were deleted
                // If so, then reset the label to inform the user
                if (deleteHotelRecordsComboBox.getSelectedItem() == null) {
                    // Follow the "visibility of system status" heuristic
                    deleteHotelAddressLabel.setText("All hotels have been deleted from the database.");
                }
                // Follow the "visibility of system status" heuristic
                JOptionPane.showMessageDialog(null, "The hotel was successfully deleted.");
            }
        } catch(SQLException e) {
            // Follow the "visibility of system status" heuristic
            JOptionPane.showMessageDialog(null, e);
        }
    }

    // Delete Manager Functionality

    // Method to move the user from the delete menu to the delete manager window
    private void deleteManagersButtonActionPerformed(java.awt.event.ActionEvent evt) {
        deleteManagerContainer.setVisible(true);
        deleteManagerContainerLabel.requestFocusInWindow();
        deleteContainer.setVisible(false);
    }
    // Method to return the user to the main menu
    private void deleteManagerContainerMainMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
        homeContainer.setVisible(true);
        deleteManagerContainer.setVisible(false);
    }
    // Method to return the user to the delete menu
    private void deleteManagerContainerDeleteMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
        deleteContainer.setVisible(true);
        deleteManagerContainer.setVisible(false);
    }
    // Method to view the details of a manager record in the database in the delete manager window
    private void deleteManagerRecordsComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
        // The value of the selected item of the combo box must first be checked
        // because this is initially null and would therefore cause a null pointer exception
        if(deleteManagerRecordsComboBox.getSelectedItem() != null) {
            try {
                // Create a Manager object using the manager ID from the combo box showing the IDs of all the manager records in the database
                Manager manager = ManagerController.getManagerRow(Integer.parseInt(deleteManagerRecordsComboBox.getSelectedItem().toString()));
                // Populate the labels with the appropriate data
                deleteManagerNameLabel.setText(manager.getFirstName() + " " + manager.getLastName());
                deleteManagerEmailLabel.setText(manager.getEmail());
                deleteManagerPhoneLabel.setText(manager.getPhone());
                deleteManagerHotelIDLabel.setText(manager.getHotelID() + "");
            } catch (SQLException e) {
                // Follow the "visibility of system status" heuristic
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    // Method to delete a manager record from the database
    private void deleteManagerButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Store the ID of the hotel the user requested deleted
        int managerID = Integer.parseInt(deleteManagerRecordsComboBox.getSelectedItem().toString());
        try {
            // Attempt to delete the manager with the manager ID of the selected hotel from the manager table and store whether it was successful
            boolean result = ManagerController.deleteManager(managerID);
            if(result) {
                // Update all the lists of managers used in the application
                fillManagerRecordsComboBoxes();
                // Check if all manager records were deleted
                // If so, then reset the label to inform the user
                if (deleteManagerRecordsComboBox.getSelectedItem() == null) {
                    // Follow the "visibility of system status" heuristic
                    deleteManagerNameLabel.setText("All managers have been deleted from the database.");
                    deleteManagerEmailLabel.setText("");
                    deleteManagerPhoneLabel.setText("");
                    deleteManagerHotelIDLabel.setText("");
                }
                // Follow the "visibility of system status" heuristic
                JOptionPane.showMessageDialog(null, "The manager was successfully deleted.");
            }
        } catch(SQLException e) {
            // Follow the "visibility of system status" heuristic
            JOptionPane.showMessageDialog(null, "There was an error.");
        }
    }

    // Delete Room Functionality

    // Method to move the user from the delete menu to the delete room window
    private void deleteRoomsButtonActionPerformed(java.awt.event.ActionEvent evt) {
        deleteRoomContainer.setVisible(true);
        deleteRoomContainerLabel.requestFocusInWindow();
        deleteContainer.setVisible(false);
    }
    // Method to return the user to the main menu
    private void deleteRoomContainerMainMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
        homeContainer.setVisible(true);
        deleteRoomContainer.setVisible(false);
    }
    // Method to return the user to the delete menu
    private void deleteRoomContainerDeleteMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {
        deleteContainer.setVisible(true);
        deleteRoomContainer.setVisible(false);
    }
    // Method to view the details of a room record in the database in the delete room window
    private void deleteRoomRecordsComboBoxActionPerformed(java.awt.event.ActionEvent evt) {
        // The value of the selected item of the combo box must first be checked
        // because this is initially null and would therefore cause a null pointer exception
        if(deleteRoomRecordsComboBox.getSelectedItem() != null) {
            try {
                // Create a Room object using the room ID from the combo box showing the IDs of all the room records in the database
                Room room = RoomController.getRoomRow(Integer.parseInt(deleteRoomRecordsComboBox.getSelectedItem().toString()));
                // Populate the labels with the appropriate data
                deleteRoomPriceLabel.setText(room.getPrice() + "");
                deleteRoomCapacityLabel.setText(room.getCapacity() + "");
                if(room.getSeaView()) {
                    deleteRoomSeaViewLabel.setText("Yes");
                } else {
                    deleteRoomSeaViewLabel.setText("No");
                }
                deleteRoomHotelIDLabel.setText(room.getHotelID() + "");
            } catch (SQLException e) {
                // Follow the "visibility of system status" heuristic
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    // Method to delete a room record from the database
    private void deleteRoomButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Store the ID of the hotel the user requested deleted
        int roomID = Integer.parseInt(deleteRoomRecordsComboBox.getSelectedItem().toString());
        try {
            // Attempt to delete the manager with the manager ID of the selected hotel from the manager table and store whether it was successful
            boolean result = RoomController.deleteRoom(roomID);
            if(result) {
                // Update all the lists of managers used in the application
                fillRoomRecordsComboBoxes();
                if (deleteRoomRecordsComboBox.getSelectedItem() == null) {
                    // Follow the "visibility of system status" heuristic
                    deleteRoomPriceLabel.setText("All rooms have been deleted from the database.");
                    deleteRoomCapacityLabel.setText("");
                    deleteRoomSeaViewLabel.setText("");
                    deleteRoomHotelIDLabel.setText("");
                }
                // Follow the "visibility of system status" heuristic
                JOptionPane.showMessageDialog(null, "The room was successfully deleted.");
            }
        } catch (SQLException e) {
            // Follow the "visibility of system status" heuristic
            JOptionPane.showMessageDialog(null, e);
        }
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Hotela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Hotela().setVisible(true);
            }
        });
    }

    //<editor-fold defaultstate="collapsed" desc=" Variables declaration ">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DeleteRecordButton;
    private javax.swing.JButton addRecordButton;
    private javax.swing.JLabel appNameLabel;
    private javax.swing.JLabel bylineLabel;
    private javax.swing.JComboBox createBookingArrivalDateDayComboBox;
    private javax.swing.JComboBox createBookingArrivalDateMonthComboBox;
    private javax.swing.JComboBox createBookingArrivalDateYearComboBox;
    private javax.swing.JLabel createBookingArrivalTimeLabel;
    private javax.swing.JButton createBookingButton;
    private javax.swing.JPanel createBookingContainer;
    private javax.swing.JButton createBookingContainerCreateMenuButton;
    private javax.swing.JLabel createBookingContainerInstructionsLabel;
    private javax.swing.JLabel createBookingContainerLabel;
    private javax.swing.JButton createBookingContainerMainMenuButton;
    private javax.swing.JComboBox createBookingDepartureDateDayComboBox;
    private javax.swing.JComboBox createBookingDepartureDateMonthComboBox;
    private javax.swing.JComboBox createBookingDepartureDateYearComboBox;
    private javax.swing.JLabel createBookingDepartureTimeLabel;
    private javax.swing.JComboBox createBookingGuestIDComboBox;
    private javax.swing.JLabel createBookingGuestIDLabel;
    private javax.swing.JButton createBookingRoomAddButton;
    private javax.swing.JLabel createBookingRoomIDLabel;
    private javax.swing.JList createBookingRoomList;
    private javax.swing.JButton createBookingRoomRemoveButton;
    private javax.swing.JScrollPane createBookingRoomScrollPane;
    private javax.swing.JList createBookingSelectedRoomList;
    private javax.swing.JScrollPane createBookingSelectedRoomScrollPane;
    private javax.swing.JButton createBookingsButton;
    private javax.swing.JPanel createContainer;
    private javax.swing.JLabel createContainerLabel;
    private javax.swing.JButton createContainerMainMenuButton;
    private javax.swing.JTextField createGuestAddressField;
    private javax.swing.JLabel createGuestAddressLabel;
    private javax.swing.JButton createGuestButton;
    private javax.swing.JPanel createGuestContainer;
    private javax.swing.JButton createGuestContainerCreateMenuButton;
    private javax.swing.JLabel createGuestContainerInstructionsLabel;
    private javax.swing.JLabel createGuestContainerLabel;
    private javax.swing.JButton createGuestContainerMainMenuButton;
    private javax.swing.JTextField createGuestEmailField;
    private javax.swing.JLabel createGuestEmailLabel;
    private javax.swing.JTextField createGuestFirstNameField;
    private javax.swing.JLabel createGuestFirstNameLabel;
    private javax.swing.JTextField createGuestLastNameField;
    private javax.swing.JLabel createGuestLastNameLabel;
    private javax.swing.JLabel createGuestLastNameLabel1;
    private javax.swing.JTextField createGuestPhoneField;
    private javax.swing.JLabel createGuestPhoneLabel;
    private javax.swing.JTextField createGuestTitleField;
    private javax.swing.JLabel createGuestTitleLabel;
    private javax.swing.JButton createGuestsButton;
    private javax.swing.JTextField createHotelAddressField;
    private javax.swing.JLabel createHotelAddressLabel;
    private javax.swing.JButton createHotelButton;
    private javax.swing.JPanel createHotelContainer;
    private javax.swing.JButton createHotelContainerCreateMenuButton;
    private javax.swing.JLabel createHotelContainerInstructionsLabel;
    private javax.swing.JLabel createHotelContainerLabel;
    private javax.swing.JButton createHotelContainerMainMenuButton;
    private javax.swing.JButton createHotelsButton;
    private javax.swing.JButton createManagerButton;
    private javax.swing.JPanel createManagerContainer;
    private javax.swing.JButton createManagerContainerCreateMenuButton;
    private javax.swing.JLabel createManagerContainerInstructionsLabel;
    private javax.swing.JLabel createManagerContainerLabel;
    private javax.swing.JButton createManagerContainerMainMenuButton;
    private javax.swing.JTextField createManagerEmailField;
    private javax.swing.JLabel createManagerEmailLabel;
    private javax.swing.JTextField createManagerFirstNameField;
    private javax.swing.JLabel createManagerFirstNameLabel;
    private javax.swing.JComboBox createManagerHotelIDComboBox;
    private javax.swing.JLabel createManagerHotelIDLabel;
    private javax.swing.JTextField createManagerLastNameField;
    private javax.swing.JTextField createManagerPhoneField;
    private javax.swing.JLabel createManagerPhoneLabel;
    private javax.swing.JButton createManagersButton;
    private javax.swing.JButton createRoomButton;
    private javax.swing.JComboBox createRoomCapacityComboBox;
    private javax.swing.JLabel createRoomCapacityLabel;
    private javax.swing.JPanel createRoomContainer;
    private javax.swing.JButton createRoomContainerCreateMenuButton;
    private javax.swing.JLabel createRoomContainerInstructionsLabel;
    private javax.swing.JLabel createRoomContainerLabel;
    private javax.swing.JButton createRoomContainerMainMenuButton;
    private javax.swing.JComboBox createRoomHotelIDComboxBox;
    private javax.swing.JLabel createRoomHotelIDLabel;
    private javax.swing.JTextField createRoomPriceField;
    private javax.swing.JLabel createRoomPriceLabel;
    private javax.swing.JCheckBox createRoomSeaViewCheckBox;
    private javax.swing.JLabel createRoomSeaViewLabel;
    private javax.swing.JButton createRoomsButton;
    private javax.swing.JLabel deleteBookingArrivalDateLabel;
    private javax.swing.JLabel deleteBookingArrivalDateValueLabel;
    private javax.swing.JButton deleteBookingButton;
    private javax.swing.JPanel deleteBookingContainer;
    private javax.swing.JButton deleteBookingContainerDeleteMenuButton;
    private javax.swing.JLabel deleteBookingContainerInstructionsLabel;
    private javax.swing.JLabel deleteBookingContainerLabel;
    private javax.swing.JButton deleteBookingContainerMainMenuButton;
    private javax.swing.JLabel deleteBookingDepartureDateLabel;
    private javax.swing.JLabel deleteBookingDepartureDateValueLabel;
    private javax.swing.JLabel deleteBookingGuestLabel;
    private javax.swing.JLabel deleteBookingGuestValueLabel;
    private javax.swing.JComboBox deleteBookingRecordsComboBox;
    private javax.swing.JLabel deleteBookingRoomsLabel;
    private javax.swing.JLabel deleteBookingRoomsValueLabel;
    private javax.swing.JButton deleteBookingsButton;
    private javax.swing.JPanel deleteContainer;
    private javax.swing.JLabel deleteContainerLabel;
    private javax.swing.JButton deleteContainerMainMenuButton;
    private javax.swing.JLabel deleteGuestAddressLabel;
    private javax.swing.JButton deleteGuestButton;
    private javax.swing.JPanel deleteGuestContainer;
    private javax.swing.JButton deleteGuestContainerDeleteMenuButton;
    private javax.swing.JLabel deleteGuestContainerInstructionsLabel;
    private javax.swing.JLabel deleteGuestContainerLabel;
    private javax.swing.JButton deleteGuestContainerMainMenuButton;
    private javax.swing.JLabel deleteGuestEmailLabel;
    private javax.swing.JLabel deleteGuestNameLabel;
    private javax.swing.JLabel deleteGuestPhoneLabel;
    private javax.swing.JComboBox deleteGuestRecordsComboBox;
    private javax.swing.JLabel deleteGuestTitleLabel;
    private javax.swing.JButton deleteGuestsButton;
    private javax.swing.JLabel deleteHotelAddressLabel;
    private javax.swing.JButton deleteHotelButton;
    private javax.swing.JPanel deleteHotelContainer;
    private javax.swing.JButton deleteHotelContainerDeleteMenuButton;
    private javax.swing.JLabel deleteHotelContainerInstructionsLabel;
    private javax.swing.JLabel deleteHotelContainerLabel;
    private javax.swing.JButton deleteHotelContainerMainMenuButton;
    private javax.swing.JComboBox deleteHotelRecordsComboBox;
    private javax.swing.JButton deleteHotelsButton;
    private javax.swing.JButton deleteManagerButton;
    private javax.swing.JPanel deleteManagerContainer;
    private javax.swing.JButton deleteManagerContainerDeleteMenuButton;
    private javax.swing.JLabel deleteManagerContainerInstructionsLabel;
    private javax.swing.JLabel deleteManagerContainerLabel;
    private javax.swing.JButton deleteManagerContainerMainMenuButton;
    private javax.swing.JLabel deleteManagerEmailLabel;
    private javax.swing.JLabel deleteManagerHotelIDLabel;
    private javax.swing.JLabel deleteManagerNameLabel;
    private javax.swing.JLabel deleteManagerPhoneLabel;
    private javax.swing.JComboBox deleteManagerRecordsComboBox;
    private javax.swing.JButton deleteManagersButton;
    private javax.swing.JButton deleteRoomButton;
    private javax.swing.JLabel deleteRoomCapacityLabel;
    private javax.swing.JPanel deleteRoomContainer;
    private javax.swing.JButton deleteRoomContainerDeleteMenuButton;
    private javax.swing.JLabel deleteRoomContainerInstructionsLabel;
    private javax.swing.JLabel deleteRoomContainerLabel;
    private javax.swing.JButton deleteRoomContainerMainMenuButton;
    private javax.swing.JLabel deleteRoomHotelIDLabel;
    private javax.swing.JLabel deleteRoomPriceLabel;
    private javax.swing.JComboBox deleteRoomRecordsComboBox;
    private javax.swing.JLabel deleteRoomSeaViewLabel;
    private javax.swing.JButton deleteRoomsButton;
    private javax.swing.JPanel homeContainer;
    private javax.swing.JLabel homeContainerLabel;
    private javax.swing.JComboBox updateBookingArrivalDateDayComboBox;
    private javax.swing.JComboBox updateBookingArrivalDateMonthComboBox;
    private javax.swing.JComboBox updateBookingArrivalDateYearComboBox;
    private javax.swing.JLabel updateBookingArrivalTimeLabel;
    private javax.swing.JButton updateBookingButton;
    private javax.swing.JPanel updateBookingContainer;
    private javax.swing.JLabel updateBookingContainerInstructionsLabel;
    private javax.swing.JLabel updateBookingContainerLabel;
    private javax.swing.JButton updateBookingContainerMainMenuButton;
    private javax.swing.JButton updateBookingContainerUpdateMenuButton;
    private javax.swing.JComboBox updateBookingDepartureDateDayComboBox;
    private javax.swing.JComboBox updateBookingDepartureDateMonthComboBox;
    private javax.swing.JComboBox updateBookingDepartureDateYearComboBox;
    private javax.swing.JLabel updateBookingDepartureTimeLabel;
    private javax.swing.JComboBox updateBookingGuestIDComboBox;
    private javax.swing.JLabel updateBookingGuestLabel;
    private javax.swing.JLabel updateBookingGuestNameLabel;
    private javax.swing.JComboBox updateBookingRecordsComboBox;
    private javax.swing.JButton updateBookingRoomAddButton;
    private javax.swing.JLabel updateBookingRoomIDLabel;
    private javax.swing.JList updateBookingRoomList;
    private javax.swing.JLabel updateBookingRoomListLabel;
    private javax.swing.JButton updateBookingRoomRemoveButton;
    private javax.swing.JScrollPane updateBookingRoomScrollPane;
    private javax.swing.JList updateBookingSelectedRoomList;
    private javax.swing.JLabel updateBookingSelectedRoomListLabel;
    private javax.swing.JScrollPane updateBookingSelectedRoomScrollPane;
    private javax.swing.JButton updateBookingsButton;
    private javax.swing.JPanel updateContainer;
    private javax.swing.JLabel updateContainerLabel;
    private javax.swing.JButton updateContainerMainMenuButton;
    private javax.swing.JTextField updateGuestAddressField;
    private javax.swing.JButton updateGuestButton;
    private javax.swing.JPanel updateGuestContainer;
    private javax.swing.JLabel updateGuestContainerInstructionsLabel;
    private javax.swing.JLabel updateGuestContainerLabel;
    private javax.swing.JButton updateGuestContainerMainMenuButton;
    private javax.swing.JButton updateGuestContainerUpdateMenuButton;
    private javax.swing.JTextField updateGuestEmailField;
    private javax.swing.JTextField updateGuestFirstNameField;
    private javax.swing.JTextField updateGuestLastNameField;
    private javax.swing.JTextField updateGuestPhoneField;
    private javax.swing.JComboBox updateGuestRecordsComboBox;
    private javax.swing.JTextField updateGuestTitleField;
    private javax.swing.JButton updateGuestsButton;
    private javax.swing.JTextField updateHotelAddressField;
    private javax.swing.JButton updateHotelButton;
    private javax.swing.JPanel updateHotelContainer;
    private javax.swing.JLabel updateHotelContainerInstructionsLabel;
    private javax.swing.JLabel updateHotelContainerLabel;
    private javax.swing.JButton updateHotelContainerMainMenuButton;
    private javax.swing.JButton updateHotelContainerUpdateMenuButton;
    private javax.swing.JComboBox updateHotelRecordsComboBox;
    private javax.swing.JButton updateHotelsButton;
    private javax.swing.JButton updateManagerButton;
    private javax.swing.JPanel updateManagerContainer;
    private javax.swing.JLabel updateManagerContainerInstructionsLabel;
    private javax.swing.JLabel updateManagerContainerLabel;
    private javax.swing.JButton updateManagerContainerMainMenuButton;
    private javax.swing.JButton updateManagerContainerUpdateMenuButton;
    private javax.swing.JTextField updateManagerEmailField;
    private javax.swing.JLabel updateManagerEmailLabel;
    private javax.swing.JTextField updateManagerFirstNameField;
    private javax.swing.JLabel updateManagerFirstNameLabel;
    private javax.swing.JTextField updateManagerHotelIDField;
    private javax.swing.JLabel updateManagerHotelIDLabel;
    private javax.swing.JTextField updateManagerLastNameField;
    private javax.swing.JLabel updateManagerLastNameLabel;
    private javax.swing.JTextField updateManagerPhoneField;
    private javax.swing.JLabel updateManagerPhoneLabel;
    private javax.swing.JComboBox updateManagerRecordsComboBox;
    private javax.swing.JButton updateManagersButton;
    private javax.swing.JButton updateRecordButton;
    private javax.swing.JButton updateRoomButton;
    private javax.swing.JTextField updateRoomCapacityField;
    private javax.swing.JPanel updateRoomContainer;
    private javax.swing.JLabel updateRoomContainerInstructionsLabel;
    private javax.swing.JLabel updateRoomContainerLabel;
    private javax.swing.JButton updateRoomContainerMainMenuButton;
    private javax.swing.JButton updateRoomContainerUpdateMenuButton;
    private javax.swing.JLabel updateRoomEuroLabel;
    private javax.swing.JTextField updateRoomHotelIDField;
    private javax.swing.JTextField updateRoomPriceField;
    private javax.swing.JComboBox updateRoomRecordsComboBox;
    private javax.swing.JTextField updateRoomSeaViewField;
    private javax.swing.JButton updateRoomsButton;
    private javax.swing.JLabel viewBookingArrivalDateLabel;
    private javax.swing.JLabel viewBookingArrivalDateValueLabel;
    private javax.swing.JPanel viewBookingContainer;
    private javax.swing.JLabel viewBookingContainerInstructionsLabel;
    private javax.swing.JLabel viewBookingContainerLabel;
    private javax.swing.JButton viewBookingContainerMainMenuButton;
    private javax.swing.JButton viewBookingContainerViewMenuButton;
    private javax.swing.JLabel viewBookingDepartureDateLabel;
    private javax.swing.JLabel viewBookingDepartureDateValueLabel;
    private javax.swing.JLabel viewBookingGuestLabel;
    private javax.swing.JLabel viewBookingGuestValueLabel;
    private javax.swing.JComboBox viewBookingRecordsComboBox;
    private javax.swing.JLabel viewBookingRoomsLabel;
    private javax.swing.JLabel viewBookingRoomsValueLabel;
    private javax.swing.JButton viewBookingsButton;
    private javax.swing.JPanel viewContainer;
    private javax.swing.JLabel viewContainerLabel;
    private javax.swing.JButton viewContainerMainMenuButton;
    private javax.swing.JLabel viewGuestAddressLabel;
    private javax.swing.JPanel viewGuestContainer;
    private javax.swing.JLabel viewGuestContainerInstructionsLabel;
    private javax.swing.JLabel viewGuestContainerLabel;
    private javax.swing.JButton viewGuestContainerMainMenuButton;
    private javax.swing.JButton viewGuestContainerViewMenuButton;
    private javax.swing.JLabel viewGuestEmailLabel;
    private javax.swing.JLabel viewGuestNameLabel;
    private javax.swing.JLabel viewGuestPhoneLabel;
    private javax.swing.JComboBox viewGuestRecordsComboBox;
    private javax.swing.JLabel viewGuestTitleLabel;
    private javax.swing.JButton viewGuestsButton;
    private javax.swing.JLabel viewHotelAddressLabel;
    private javax.swing.JPanel viewHotelContainer;
    private javax.swing.JLabel viewHotelContainerInstructionsLabel;
    private javax.swing.JLabel viewHotelContainerLabel;
    private javax.swing.JButton viewHotelContainerMainMenuButton;
    private javax.swing.JButton viewHotelContainerViewMenuButton;
    private javax.swing.JComboBox viewHotelRecordsComboBox;
    private javax.swing.JButton viewHotelsButton;
    private javax.swing.JPanel viewManagerContainer;
    private javax.swing.JLabel viewManagerContainerInstructionsLabel;
    private javax.swing.JLabel viewManagerContainerLabel;
    private javax.swing.JButton viewManagerContainerMainMenuButton;
    private javax.swing.JButton viewManagerContainerViewMenuButton;
    private javax.swing.JLabel viewManagerEmailLabel;
    private javax.swing.JLabel viewManagerHotelIDLabel;
    private javax.swing.JLabel viewManagerNameLabel;
    private javax.swing.JLabel viewManagerPhoneLabel;
    private javax.swing.JComboBox viewManagerRecordsComboBox;
    private javax.swing.JButton viewManagersButton;
    private javax.swing.JButton viewRecordButton;
    private javax.swing.JLabel viewRoomCapacityLabel;
    private javax.swing.JPanel viewRoomContainer;
    private javax.swing.JLabel viewRoomContainerInstructionsLabel;
    private javax.swing.JLabel viewRoomContainerLabel;
    private javax.swing.JButton viewRoomContainerMainMenuButton;
    private javax.swing.JButton viewRoomContainerViewMenuButton;
    private javax.swing.JLabel viewRoomHotelIDLabel;
    private javax.swing.JLabel viewRoomPriceLabel;
    private javax.swing.JComboBox viewRoomRecordsComboBox;
    private javax.swing.JLabel viewRoomSeaViewLabel;
    private javax.swing.JButton viewRoomsButton;
    // End of variables declaration//GEN-END:variables
    //</editor-fold>
}
