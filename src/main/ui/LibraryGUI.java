package ui;

import model.Book;
import model.Library;
import persistence.JsonReader;
import persistence.JsonWriter;
import sound.AudioPlayer;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LibraryGUI extends JPanel implements ListSelectionListener {
    private static final String JSON_STORE = "./data/library.json";
    private Library library;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JList list;
    private DefaultListModel listModel;
    private AudioPlayer audioPlayer;

    private static final String addString = "Add";
    private static final String removeString = "Remove";
    private static final String saveString = "Save";
    private static final String loadString = "Load";
    private static final String titleString = "Title: ";
    private static final String authorString = "Author: ";
    private static final String bodyString = "Body: ";

    private JPanel textPane;
    private GroupLayout layout;

    private JLabel titleLabel;
    private JTextField titleField;

    private JLabel authorLabel;
    private JTextField authorField;

    private JLabel bodyLabel;
    private JTextArea bodyArea;
    private JScrollPane bodyScrollArea;

    private JButton addButton;
    private JButton removeButton;
    private JButton saveButton;
    private JButton loadButton;

    private String addSound = "./data/addsound.wav";
    private String removeSound = "./data/removesound.wav";

    public LibraryGUI() {
        super(new BorderLayout());
        init();
        displayList();
        displayButtons();
        displayTextPane();
    }

    private void init() {
        library = new Library();
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        audioPlayer = new AudioPlayer();
    }

    // https://docs.oracle.com/javase/8/docs/api/javax/swing/GroupLayout.html
    private void displayTextPane() {
        textPane = new JPanel();
        layout = new GroupLayout(textPane);
        textPane.setLayout(layout);

        initLabelField();

        // Turn on automatically adding gaps between components
        layout.setAutoCreateGaps(true);

        // Turn on automatically creating gaps between components that touch
        // the edge of the container and the container.
        layout.setAutoCreateContainerGaps(true);

        // Create a sequential group for the horizontal axis.

        GroupLayout.SequentialGroup horizontalGroup = layout.createSequentialGroup();

        // The sequential group in turn contains two parallel groups.
        // One parallel group contains the labels, the other the text fields.
        // Putting the labels in a parallel group along the horizontal axis
        // positions them at the same x location.
        //
        // Variable indentation is used to reinforce the level of grouping.
        horizontalGroup.addGroup(layout.createParallelGroup()
                .addComponent(titleLabel).addComponent(authorLabel).addComponent(bodyLabel));
        horizontalGroup.addGroup(layout.createParallelGroup()
                .addComponent(titleField).addComponent(authorField).addComponent(bodyScrollArea));
        layout.setHorizontalGroup(horizontalGroup);

        // Create a sequential group for the vertical axis.
        GroupLayout.SequentialGroup verticalGroup = layout.createSequentialGroup();

        // The sequential group contains two parallel groups that align
        // the contents along the baseline. The first parallel group contains
        // the first label and text field, and the second parallel group contains
        // the second label and text field. By using a sequential group
        // the labels and text fields are positioned vertically after one another.
        verticalGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(titleLabel).addComponent(titleField));
        verticalGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(authorLabel).addComponent(authorField));
        verticalGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(bodyLabel).addComponent(bodyScrollArea));

        layout.setVerticalGroup(verticalGroup);

        add(textPane, BorderLayout.EAST);
    }

    private void initLabelField() {
        titleLabel = new JLabel();
        titleLabel.setText(titleString);
        titleField = new JTextField(20);

        authorLabel = new JLabel();
        authorLabel.setText(authorString);
        authorField = new JTextField(20);

        bodyLabel = new JLabel();
        bodyLabel.setText(bodyString);
        bodyArea = new JTextArea();
        bodyArea.setColumns(40);
        bodyArea.setLineWrap(true);
        bodyArea.setRows(10);
        bodyArea.setWrapStyleWord(true);
        bodyArea.setEditable(true);
        bodyScrollArea = new JScrollPane(bodyArea);
    }

    private void displayButtons() {
        initButtons();

        //Create a panel that uses BoxLayout.
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                BoxLayout.LINE_AXIS));

        buttonPane.add(addButton);
        buttonPane.add(removeButton);
        buttonPane.add(loadButton);
        buttonPane.add(saveButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        add(buttonPane, BorderLayout.PAGE_END);
    }

    private void initButtons() {
        addButton = new JButton(addString);
        AddListener addListener = new AddListener(addButton);
        addButton.setActionCommand(addString);
        addButton.addActionListener(addListener);
        addButton.setEnabled(true);

        removeButton = new JButton(removeString);
        RemoveListener removeListener = new RemoveListener(removeButton);
        removeButton.setActionCommand(removeString);
        removeButton.addActionListener(removeListener);
        removeButton.setEnabled(false);

        saveButton = new JButton(saveString);
        SaveListener saveListener = new SaveListener(saveButton);
        saveButton.setActionCommand(saveString);
        saveButton.addActionListener(saveListener);
        saveButton.setEnabled(true);

        loadButton = new JButton(loadString);
        LoadListener loadListener = new LoadListener(loadButton);
        loadButton.setActionCommand(loadString);
        loadButton.addActionListener(loadListener);
        loadButton.setEnabled(true);
    }

    private void displayList() {
        //Create the list and put it in a scroll pane.
        listModel = new DefaultListModel();
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(10);
        JScrollPane listScrollPane = new JScrollPane(list);

        add(listScrollPane, BorderLayout.CENTER);
    }

    @Override
    //This method is required by ListSelectionListener.
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (list.getSelectedIndex() == -1) {
                //No selection, disable fire button.
                removeButton.setEnabled(false);

            } else {
                //Selection, enable the fire button.
                removeButton.setEnabled(true);
            }
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Library App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new LibraryGUI();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    private class AddListener implements ActionListener {
        private JButton button;

        public AddListener(JButton button) {
            this.button = button;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String title = titleField.getText();
            String author = authorField.getText();
            String body = bodyArea.getText();

            //User didn't type in a unique name...
            if (title.equals("") || alreadyInList(title)) {
                Toolkit.getDefaultToolkit().beep();
                titleField.requestFocusInWindow();
                titleField.selectAll();
                return;
            }

            library.addBook(new Book(title, author, body));
            listModel.addElement(titleField.getText());

            //Reset the text field.
            titleField.requestFocusInWindow();
            titleField.setText("");
            authorField.setText("");
            bodyArea.setText("");

            //Select the new item and make it visible.
            int index = list.getSelectedIndex(); //get selected index
            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);

            audioPlayer.playSound(addSound);
        }

        private boolean alreadyInList(String name) {
            return listModel.contains(name);
        }
    }

    private class RemoveListener implements ActionListener {
        private JButton button;

        public RemoveListener(JButton button) {
            this.button = button;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            int size = listModel.getSize();

            //User didn't type in a unique name...
            if (size == 0) {
                Toolkit.getDefaultToolkit().beep();
                return;

            } else { //Select an index.
                //This method can be called only if
                //there's a valid selection
                //so go ahead and remove whatever's selected.
                int index = list.getSelectedIndex();
                listModel.remove(index);
                library.removeBook(index);

                if (index == listModel.getSize()) {
                    //removed item in last position
                    index--;
                }

                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);

                audioPlayer.playSound(removeSound);
            }
        }
    }

    private class LoadListener implements ActionListener {
        private JButton button;

        public LoadListener(JButton button) {
            this.button = button;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            doLoadLibrary();
            updateListModel();
            button.setEnabled(false);
        }

        // MODIFIES: this
        // EFFECTS: loads library from file
        private void doLoadLibrary() {
            try {
                library = jsonReader.read();
//            System.out.println("Loaded from " + JSON_STORE);
            } catch (IOException e) {
//            System.out.println("Unable to read from file: " + JSON_STORE);
            }
        }

        private void updateListModel() {
            for (Book b : library.getCatalogue()) {
                listModel.addElement(b.getTitle());
            }
        }
    }

    private class SaveListener implements ActionListener {
        private JButton button;

        public SaveListener(JButton button) {
            this.button = button;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            doSaveLibrary();
        }

        // EFFECTS: saves library to file
        private void doSaveLibrary() {
            try {
                jsonWriter.open();
                jsonWriter.write(library);
                jsonWriter.close();
//            System.out.println("Saved to " + JSON_STORE);
            } catch (FileNotFoundException e) {
//            System.out.println("Unable to write to file: " + JSON_STORE);
            }
        }
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
